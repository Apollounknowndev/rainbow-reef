package net.thevaliantsquidward.rainbowreef.blocks.set;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;

public record CoralBlockSet(Block deadBlock, Block block, Block deadPlant, Block plant, Block deadFan, Block fan, Block deadWallFan, Block wallFan) {
    public static CoralBlockSet create(String name, MapColor color) {
        Block deadBlock = ReefBlocks.register("dead_" + name + "_coral_block", Block::new, Blocks.DEAD_BRAIN_CORAL_BLOCK);
        Block block = ReefBlocks.register(name + "_coral_block", properties -> new CoralBlock(deadBlock, properties.mapColor(color)), Blocks.BRAIN_CORAL_BLOCK);

        Block deadPlant = ReefBlocks.register("dead_" + name + "_coral", BaseCoralPlantBlock::new, Blocks.DEAD_BRAIN_CORAL);
        Block plant = ReefBlocks.register(name + "_coral", properties -> new CoralPlantBlock(deadPlant, properties.mapColor(color)), Blocks.BRAIN_CORAL);

        Block deadFan = ReefBlocks.registerStandalone("dead_" + name + "_coral_fan", BaseCoralFanBlock::new, Blocks.DEAD_BRAIN_CORAL_FAN);
        Block fan = ReefBlocks.registerStandalone(name + "_coral_fan", properties -> new CoralFanBlock(deadFan, properties.mapColor(color)), Blocks.BRAIN_CORAL_FAN);

        Block deadWallFan = ReefBlocks.registerStandalone("dead_" + name + "_coral_wall_fan", BaseCoralWallFanBlock::new, Blocks.DEAD_BRAIN_CORAL_WALL_FAN);
        Block wallFan = ReefBlocks.registerStandalone(name + "_coral_wall_fan", properties -> new CoralWallFanBlock(deadWallFan, properties.mapColor(color)), Blocks.BRAIN_CORAL_WALL_FAN);

        return new CoralBlockSet(deadBlock, block, deadPlant, plant, deadFan, fan, deadWallFan, wallFan);
    }

    public Block[] translucentBlocks() {
        return new Block[]{this.deadPlant, this.plant, this.deadFan, this.fan, this.deadWallFan, this.wallFan};
    }
}
