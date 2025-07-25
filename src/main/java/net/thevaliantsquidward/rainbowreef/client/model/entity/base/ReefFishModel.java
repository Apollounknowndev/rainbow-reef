package net.thevaliantsquidward.rainbowreef.client.model.entity.base;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;

public abstract class ReefFishModel<S extends ReefRenderState> extends ReefModel<S> {
    protected KeyframeAnimation swimAnimation;
    protected KeyframeAnimation flopAnimation;

    public ReefFishModel(ModelPart root) {
        super(root);
    }

    public void setupBaseAnimations(ModelPart root, AnimationDefinition swim, AnimationDefinition flop) {
        this.swimAnimation = swim.bake(root);
        this.flopAnimation = flop.bake(root);
    }

    public abstract ModelPart xRotatedPart();

    @Override
    public void setupAnim(S state) {
        super.setupAnim(state);
        if (this.xRotatedPart() != null) {
            this.xRotatedPart().xRot = state.xRot * Mth.DEG_TO_RAD;
        }
        this.swimAnimation.apply(state.swim, state.ageInTicks);
        this.flopAnimation.apply(state.flop, state.ageInTicks);
    }
}
