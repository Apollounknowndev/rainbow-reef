package net.thevaliantsquidward.rainbowreef.entity.pathing;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.*;

import javax.annotation.Nullable;
import java.util.Map;

public class AdvancedSwimNodeEvaluator extends NodeEvaluator {
    private boolean allowBreaching;
    private boolean preferCrevices;
    private final Long2ObjectMap<PathType> pathTypesByPosCache = new Long2ObjectOpenHashMap<>();

    public AdvancedSwimNodeEvaluator(boolean pAllowBreaching, boolean pPreferCrevices) {
        this.allowBreaching = pAllowBreaching;
        this.preferCrevices = pPreferCrevices;
    }

    public void prepare(PathNavigationRegion pLevel, Mob pMob) {
        super.prepare(pLevel, pMob);
        this.pathTypesByPosCache.clear();
    }

    /**
     * This method is called when all nodes have been processed and PathEntity is created.
     */
    public void done() {
        super.done();
        this.pathTypesByPosCache.clear();
    }

    public Node getStart() {
        return this.getNode(Mth.floor(this.mob.getBoundingBox().minX), Mth.floor(this.mob.getBoundingBox().minY + 0.5D), Mth.floor(this.mob.getBoundingBox().minZ));
    }

    public Target getTarget(double pX, double pY, double pZ) {
        return this.getTargetNodeAt(pX, pY, pZ);
    }

    public int getNeighbors(Node[] output, Node baseNode) {
        int i = 0;
        Map<Direction, Node> map = Maps.newEnumMap(Direction.class);

        for(Direction direction : Direction.values()) {
            Node node = this.findAcceptedNode(baseNode.x + direction.getStepX(), baseNode.y + direction.getStepY(), baseNode.z + direction.getStepZ());
            map.put(direction, node);
            if (this.isNodeValid(node)) {
                output[i++] = node;
            }
        }

        for(Direction direction1 : Direction.Plane.HORIZONTAL) {
            Direction direction2 = direction1.getClockWise();
            Node node1 = this.findAcceptedNode(baseNode.x + direction1.getStepX() + direction2.getStepX(), baseNode.y, baseNode.z + direction1.getStepZ() + direction2.getStepZ());
            if (this.isDiagonalNodeValid(node1, map.get(direction1), map.get(direction2))) {
                output[i++] = node1;
            }
        }

        return i;
    }

    protected boolean isNodeValid(@Nullable Node pNode) {
        return pNode != null && !pNode.closed;
    }

    protected boolean isDiagonalNodeValid(@Nullable Node pRoot, @Nullable Node pHorizontal, @Nullable Node pClockwise) {
        return this.isNodeValid(pRoot) && pHorizontal != null && pHorizontal.costMalus >= 0.0F && pClockwise != null && pClockwise.costMalus >= 0.0F;
        //Malus has 3 states. Positive, zero, and negative.
        //Negative malus are all ignored. Unpathfindable. Doesn't matter how negative, just negative.
        //Zero has no malus. This is the perfect path.
        //Positive is less likely to be pathfound through depending on how high the value is.
    }

    @Nullable
    protected Node findAcceptedNode(int pX, int pY, int pZ) {
        Node node = null;
        PathType type = this.getCachedBlockType(pX, pY, pZ);


        if (this.allowBreaching && type == PathType.BREACH || type == PathType.WATER) {
            float f = this.mob.getPathfindingMalus(type);


            if (f >= 0.0F) {
                node = this.getNode(pX, pY, pZ);
                node.type = type;
                node.costMalus = Math.max(node.costMalus, f);

                if (preferCrevices) {
                    //System.out.println("pref");
                    node.costMalus += countOpenSides(pX, pY, pZ);
                }

                if (this.currentContext.level().getFluidState(new BlockPos(pX, pY, pZ)).isEmpty()) {
                    node.costMalus += 8.0F;
                }

                //System.out.println(node.costMalus);
                //System.out.println(pX + " " + pY + " " + pZ);
            }
        }

        return node;
    }

    protected PathType getCachedBlockType(int pX, int pY, int pZ) {
        return this.pathTypesByPosCache.computeIfAbsent(BlockPos.asLong(pX, pY, pZ), (p_192957_) -> {
            return this.getPathType(this.currentContext, pX, pY, pZ);
        });
    }

    /**
     * Returns the node type at the specified postion taking the block below into account
     */
    public PathType getPathType(PathfindingContext context, int x, int y, int z) {
        return this.getPathTypeOfMob(context, x, y, z, this.mob);
    }

    public PathType getPathTypeOfMob(PathfindingContext $$0, int $$1, int $$2, int $$3, Mob $$4) {
        BlockPos.MutableBlockPos $$5 = new BlockPos.MutableBlockPos();

        for(int $$6 = $$1; $$6 < $$1 + this.entityWidth; ++$$6) {
            for(int $$7 = $$2; $$7 < $$2 + this.entityHeight; ++$$7) {
                for(int $$8 = $$3; $$8 < $$3 + this.entityDepth; ++$$8) {
                    BlockState $$9 = $$0.getBlockState($$5.set($$6, $$7, $$8));
                    FluidState $$10 = $$9.getFluidState();
                    if ($$10.isEmpty() && $$9.isPathfindable(PathComputationType.WATER) && $$9.isAir()) {
                        return PathType.BREACH;
                    }

                    if (!$$10.is(FluidTags.WATER)) {
                        return PathType.BLOCKED;
                    }
                }
            }
        }

        BlockState $$11 = $$0.getBlockState($$5);
        if ($$11.isPathfindable(PathComputationType.WATER)) {
            return PathType.WATER;
        } else {
            return PathType.BLOCKED;
        }
    }

    public float countOpenSides(int x, int y, int z) {
        BlockPos nodepos = new BlockPos(x, y, z);
        float end = 2;

        if (!this.currentContext.level().getBlockState(nodepos.above()).isSolid()) {
            end *= end;
        }
        if (!this.currentContext.level().getBlockState(nodepos.below()).isSolid()) {
            end *= end;
        }
        if (!this.currentContext.level().getBlockState(nodepos.east()).isSolid()) {
            end *= end;
        }
        if (!this.currentContext.level().getBlockState(nodepos.west()).isSolid()) {
            end *= end;
        }
        if (!this.currentContext.level().getBlockState(nodepos.north()).isSolid()) {
            end *= end;
        }
        if (!this.currentContext.level().getBlockState(nodepos.south()).isSolid()) {
            end *= end;
        }

        return end - 2;

    }

    public void setAllowBreaching(boolean pAllow) {
        this.allowBreaching = pAllow;
    }

    public void setPreferCrevices(boolean pAllow) {
        this.preferCrevices = pAllow;
    }


}
