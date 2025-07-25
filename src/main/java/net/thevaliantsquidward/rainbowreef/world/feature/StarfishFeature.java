package net.thevaliantsquidward.rainbowreef.world.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.blocks.StarfishBlock;
import net.thevaliantsquidward.rainbowreef.world.feature.config.StarfishConfig;

import java.util.Optional;

public class StarfishFeature extends Feature<StarfishConfig> {
    public StarfishFeature() {
        super(StarfishConfig.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<StarfishConfig> context) {
        WorldGenLevel level = context.level();
        StarfishConfig config = context.config();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        Optional<Holder<Block>> block = config.blocks().getRandomElement(random);
        if (block.isEmpty()) {
            RainbowReef.LOGGER.warn("Could not place blocks feature: no blocks present.");
            return false;
        }
        if (!(block.get().value() instanceof StarfishBlock starfish)) {
            RainbowReef.LOGGER.warn("Could not place blocks feature: provided block is not StarfishBlock.");
            return false;
        }

        for (Direction direction: Direction.allShuffled(random)) {
            if (config.canPlaceOn().contains(level.getBlockState(origin.relative(direction)).getBlockHolder())) {
                BlockState toPlace = starfish.defaultBlockState();
                if (level.getFluidState(origin).is(Fluids.WATER)) {
                    toPlace.setValue(BlockStateProperties.WATERLOGGED, true);
                }
                if (direction.getAxis().isVertical()) {
                    toPlace.setValue(
                        FaceAttachedHorizontalDirectionalBlock.FACE,
                        direction == Direction.DOWN ? AttachFace.FLOOR : AttachFace.CEILING
                    );
                    toPlace.setValue(
                        HorizontalDirectionalBlock.FACING,
                        Direction.allShuffled(random).stream().filter(dir -> dir.getAxis() != Direction.Axis.Y).findFirst().get()
                    );
                } else {
                    toPlace.setValue(
                        FaceAttachedHorizontalDirectionalBlock.FACE,
                        AttachFace.WALL
                    );
                    toPlace.setValue(
                        HorizontalDirectionalBlock.FACING,
                        direction.getOpposite()
                    );
                }
                level.setBlock(origin, toPlace, 3);
                return true;
            }
        }
        return false;
    }
}
