package net.thevaliantsquidward.rainbowreef.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public class StarfishBlock extends FaceAttachedHorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final MapCodec<StarfishBlock> CODEC = simpleCodec(StarfishBlock::new);
    private final Function<BlockState, VoxelShape> shapes;

    public StarfishBlock(Properties properties) {
        super(properties);
        this.shapes = this.makeShapes();
        this.registerDefaultState(this.defaultBlockState()
            .setValue(WATERLOGGED, false)
            .setValue(FACE, AttachFace.WALL)
            .setValue(FACING, Direction.NORTH)
        );
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        Map<Direction, VoxelShape> shapes = Shapes.rotateAll(Block.boxZ(16.0F, 0.0F, 1.0F));
        return this.getShapeForEachState(state -> switch (state.getValue(FACE)) {
            case FLOOR -> shapes.get(Direction.DOWN);
            case WALL -> shapes.get(state.getValue(FACING).getOpposite());
            case CEILING -> shapes.get(Direction.UP);
        }, WATERLOGGED);
    }

    @Override
    protected MapCodec<? extends StarfishBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED, FACE, FACING);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state =  super.getStateForPlacement(context);
        return state == null ? null : state.setValue(WATERLOGGED, fluid.is(FluidTags.WATER) && fluid.getAmount() == 8);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return this.shapes.apply(state);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(reader));
        }
        return super.updateShape(state, reader, tickAccess, pos, facing, facingPos, facingState, random);
    }
}
