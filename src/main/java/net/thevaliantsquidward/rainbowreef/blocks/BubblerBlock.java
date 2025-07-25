package net.thevaliantsquidward.rainbowreef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BubblerBlock extends Block {
    private static final int BUBBLE_COLUMN_CHECK_DELAY = 20;

    public BubblerBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        FakeBubbleBlock.updateColumn(level, pos.above(), state);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
        if (facing == Direction.UP && facingState.is(Blocks.WATER)) {
            tickAccess.scheduleTick(pos, this, 20);
        }
        return super.updateShape(state, reader, tickAccess, pos, facing, facingPos, facingState, random);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean moving) {
        level.scheduleTick(pos, this, 20);
    }
}