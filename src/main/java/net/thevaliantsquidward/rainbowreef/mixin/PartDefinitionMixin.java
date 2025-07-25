package net.thevaliantsquidward.rainbowreef.mixin;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.thevaliantsquidward.rainbowreef.duck.PartPoseDuck;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.UnaryOperator;

/**
 * Hack to maintain old strange code that applied offsets to the root model.
 * @author Apollo
 */
@Mixin(PartDefinition.class)
public class PartDefinitionMixin implements PartPoseDuck {
    @Shadow @Mutable @Final private PartPose partPose;

    @Override
    public void transform(UnaryOperator<PartPose> operator) {
        partPose = operator.apply(partPose);
    }
}
