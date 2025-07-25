package net.thevaliantsquidward.rainbowreef.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nullable;

public class AnemoneBlock extends DirectionalBlock implements LiquidBlockContainer {
    public static final MapCodec<AnemoneBlock> CODEC = simpleCodec(AnemoneBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape EAST_AABB = Block.box(0.0, 0.0, 0.0, 5.0, 16.0, 16.0);
    protected static final VoxelShape WEST_AABB = Block.box(11.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 5.0);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0, 0.0, 11.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape UP_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0, 11.0, 0.0, 16.0, 16.0, 16.0);

    public AnemoneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, true));
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
        def.add(WATERLOGGED);
        def.add(FACING);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if(state.getValue(FACING) == Direction.NORTH) {
            BlockPos northPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
            return level.getBlockState(northPos).isFaceSturdy(level, pos, Direction.SOUTH);

        } else if (state.getValue(FACING) == Direction.SOUTH) {
            BlockPos southPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
            return level.getBlockState(southPos).isFaceSturdy(level, pos, Direction.NORTH);

        } else if (state.getValue(FACING) == Direction.EAST) {
            BlockPos eastPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
            return level.getBlockState(eastPos).isFaceSturdy(level, pos, Direction.WEST);

        } else if (state.getValue(FACING) == Direction.WEST) {
            BlockPos westPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
            return level.getBlockState(westPos).isFaceSturdy(level, pos, Direction.EAST);

        } else if (state.getValue(FACING) == Direction.UP) {
            BlockPos upPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
            return level.getBlockState(upPos).isFaceSturdy(level, pos, Direction.DOWN);

        } else {
            BlockPos downPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            return level.getBlockState(downPos).isFaceSturdy(level, pos, Direction.UP);
        }

    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext world) {

        Direction dir = world.getClickedFace();
        BlockState oppDir = world.getLevel().getBlockState(world.getClickedPos().relative(dir.getOpposite()));


        FluidState state = world.getLevel().getFluidState(world.getClickedPos());
        return (oppDir.is(this) && oppDir.getValue(FACING) == dir ? this.defaultBlockState().setValue(FACING, dir.getOpposite()) : this.defaultBlockState().setValue(FACING, dir))
                .setValue(WATERLOGGED, state.is(FluidTags.WATER) && state.getAmount() == 8);
    }


    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
        //set waterlogged if there was water there
    }


    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case DOWN -> DOWN_AABB;
            case UP -> UP_AABB;
            case NORTH -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
        };
    }
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    public BlockState mirror(BlockState state, Mirror flip) {
        return state.setValue(FACING, flip.mirror(state.getValue(FACING)));
    }

    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return switch (type) {
            case LAND -> true;
            case WATER -> state.getFluidState().is(FluidTags.WATER);
            default -> false;
        };
    }

    @Deprecated
    public InteractionResult useItemOn(ItemStack handStack, BlockState state, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand hand, BlockHitResult pHit) {
        if(hand != InteractionHand.MAIN_HAND) return InteractionResult.FAIL;

        if(handStack.is(RRTags.NEM_DIET) && !handStack.is(Items.TROPICAL_FISH) && !handStack.is(ReefItems.RAW_CLOWNFISH) && !pLevel.isClientSide() && state.getValue(WATERLOGGED)) {
            ItemStack stack = new ItemStack(state.getBlock());
            ItemEntity entitiy = new ItemEntity(pLevel, pPos.getX() + 0.5, pPos.getY(), pPos.getZ() + 0.5, stack);
            pLevel.addFreshEntity(entitiy);
        }

        return super.useItemOn(handStack, state, pLevel, pPos, pPlayer, hand, pHit);
    }

    @Override
    public boolean canPlaceLiquid(LivingEntity arg, BlockGetter getter, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        return false;
    }
}
