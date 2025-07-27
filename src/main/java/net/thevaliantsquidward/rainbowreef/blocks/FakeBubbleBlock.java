package net.thevaliantsquidward.rainbowreef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FakeBubbleBlock extends Block implements BucketPickup {
    public FakeBubbleBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, InsideBlockEffectApplier applier) {
        BlockState blockstate = pLevel.getBlockState(pPos.above());
        if (blockstate.isAir()) {
            if (!pLevel.isClientSide) {
                ServerLevel serverlevel = (ServerLevel)pLevel;

                for(int i = 0; i < 2; ++i) {
                    serverlevel.sendParticles(ParticleTypes.SPLASH, pPos.getX() + pLevel.random.nextDouble(), (pPos.getY() + 1), pPos.getZ() + pLevel.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    serverlevel.sendParticles(ParticleTypes.BUBBLE, pPos.getX() + pLevel.random.nextDouble(), (pPos.getY() + 1), pPos.getZ() + pLevel.random.nextDouble(), 1, 0.0D, 0.01D, 0.0D, 0.2D);
                }
            }
        }
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        updateColumn(pLevel, pPos, pState, pLevel.getBlockState(pPos.below()));
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.WATER.getSource(false);
    }

    public static void updateColumn(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        updateColumn(pLevel, pPos, pLevel.getBlockState(pPos), pState);
    }

    public static void updateColumn(LevelAccessor pLevel, BlockPos pPos, BlockState pFluid, BlockState pState) {
        if (canExistIn(pFluid)) {
            BlockState blockstate = getColumnState(pState);
            pLevel.setBlock(pPos, blockstate, 2);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable().move(Direction.UP);

            while(canExistIn(pLevel.getBlockState(blockpos$mutableblockpos))) {
                if (!pLevel.setBlock(blockpos$mutableblockpos, blockstate, 2)) {
                    return;
                }

                blockpos$mutableblockpos.move(Direction.UP);
            }

        }
    }

    private static boolean canExistIn(BlockState pBlockState) {
        return pBlockState.is(ReefBlocks.FAKE_BUBBLES) || pBlockState.is(Blocks.WATER) && pBlockState.getFluidState().getAmount() >= 8 && pBlockState.getFluidState().isSource();
    }

    private static BlockState getColumnState(BlockState pBlockState) {
        if (pBlockState.is(ReefBlocks.FAKE_BUBBLES)) {
            return pBlockState;
        } else {
            return pBlockState.is(ReefBlocks.BUBBLER) ? ReefBlocks.FAKE_BUBBLES.defaultBlockState() : Blocks.WATER.defaultBlockState();
        }
    }


    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double d0 = pPos.getX();
        double d1 = pPos.getY();
        double d2 = pPos.getZ();

        pLevel.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
        pLevel.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + (double)pRandom.nextFloat(), d1 + (double)pRandom.nextFloat(), d2 + (double)pRandom.nextFloat(), 0.0D, 0.04D, 0.0D);
        if (pRandom.nextInt(200) == 0) {
                pLevel.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundSource.BLOCKS, 0.2F + pRandom.nextFloat() * 0.2F, 0.9F + pRandom.nextFloat() * 0.15F, false);
            }
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
        tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(reader));
        if (!state.canSurvive(reader, pos) || facing == Direction.DOWN || facing == Direction.UP && !facingState.is(ReefBlocks.FAKE_BUBBLES) && canExistIn(facingState)) {
            tickAccess.scheduleTick(pos, this, 5);
        }

        return super.updateShape(state, reader, tickAccess, pos, facing, facingPos, facingState, random);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.below());
        return blockstate.is(ReefBlocks.FAKE_BUBBLES) || blockstate.is(ReefBlocks.BUBBLER);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }


    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add();
    }

    @Override
    public ItemStack pickupBlock(@Nullable LivingEntity entity, LevelAccessor levelAccessor, BlockPos pos, BlockState state) {
        levelAccessor.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        return new ItemStack(Items.WATER_BUCKET);
    }

    public Optional<SoundEvent> getPickupSound() {
        return Fluids.WATER.getPickupSound();
    }
}