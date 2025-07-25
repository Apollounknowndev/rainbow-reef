package net.thevaliantsquidward.rainbowreef.duck;

import net.minecraft.client.model.geom.PartPose;

import java.util.function.UnaryOperator;

public interface PartPoseDuck {
    void transform(UnaryOperator<PartPose> operator);
}
