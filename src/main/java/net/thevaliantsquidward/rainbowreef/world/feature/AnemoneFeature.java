package net.thevaliantsquidward.rainbowreef.world.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.blocks.AnemoneBlock;
import net.thevaliantsquidward.rainbowreef.blocks.StarfishBlock;
import net.thevaliantsquidward.rainbowreef.world.feature.config.AnemoneConfig;
import net.thevaliantsquidward.rainbowreef.world.feature.config.StarfishConfig;

import java.util.Optional;

public class AnemoneFeature extends Feature<AnemoneConfig> {
    public AnemoneFeature() {
        super(AnemoneConfig.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<AnemoneConfig> context) {
        WorldGenLevel level = context.level();
        AnemoneConfig config = context.config();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        Optional<Holder<Block>> block = config.blocks().getRandomElement(random);
        if (block.isEmpty()) {
            RainbowReef.LOGGER.warn("Could not place blocks feature: no blocks present.");
            return false;
        }
        if (!(block.get().value() instanceof AnemoneBlock anemone)) {
            RainbowReef.LOGGER.warn("Could not place blocks feature: provided block is not AnemoneBlock.");
            return false;
        }

        for (Direction direction: Direction.allShuffled(random)) {
            if (level.getBlockState(origin.relative(direction)).isFaceSturdy(level, origin, direction.getOpposite())) {
                BlockState toPlace = anemone.defaultBlockState();
                if (level.getFluidState(origin).is(Fluids.WATER)) {
                    toPlace.setValue(BlockStateProperties.WATERLOGGED, true);
                }
                toPlace.setValue(
                    DirectionalBlock.FACING,
                    direction.getOpposite()
                );
                level.setBlock(origin, toPlace, 3);
                return true;
            }
        }
        return false;
    }
}
