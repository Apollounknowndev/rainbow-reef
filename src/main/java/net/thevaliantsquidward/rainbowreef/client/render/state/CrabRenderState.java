package net.thevaliantsquidward.rainbowreef.client.render.state;

import net.minecraft.world.entity.AnimationState;

public class CrabRenderState extends ReefRenderState {
    public final AnimationState idle = new AnimationState();
    public final AnimationState walk = new AnimationState();
    public final AnimationState dance = new AnimationState();
}
