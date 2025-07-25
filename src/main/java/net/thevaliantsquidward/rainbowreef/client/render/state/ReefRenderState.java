package net.thevaliantsquidward.rainbowreef.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.ReefVariant;

public class ReefRenderState extends LivingEntityRenderState {
    public ReefVariant variant;
    public final AnimationState swim = new AnimationState();
    public final AnimationState flop = new AnimationState();
}
