package net.thevaliantsquidward.rainbowreef.client.model.entity.base;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.thevaliantsquidward.rainbowreef.duck.PartPoseDuck;

import java.util.function.UnaryOperator;

public abstract class ReefModel<E extends EntityRenderState> extends EntityModel<E> {
    public ReefModel(ModelPart root) {
        super(root);
    }

    public static void applyTransformation(PartDefinition definition, UnaryOperator<PartPose> operator) {
        ((PartPoseDuck)definition).transform(operator);
    }
    /*
    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    protected void animate(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks) {
        this.animate(animationState, animationDefinition, ageInTicks, 1.0F);
    }

    protected void animateWalk(AnimationDefinition animationDefinition, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor) {
        long i = (long) (limbSwing * 50.0F * maxAnimationSpeed);
        float j = Math.min(limbSwingAmount * animationScaleFactor, 1.0F);
        KeyframeAnimations.animate(this, animationDefinition, i, j, ANIMATION_VECTOR_CACHE);
    }

    protected void animateIdle(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks, float speed, float animationScaleFactor) {
        float scale = Math.max(0, Math.min(1 - Math.abs(speed), 1f));
        animationState.updateTime(ageInTicks, speed);
        animationState.ifStarted((state) -> KeyframeAnimations.animate(this, animationDefinition, state.getAccumulatedTime(), animationScaleFactor, ANIMATION_VECTOR_CACHE));
    }

    protected void animate(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks, float speed) {
        animationState.updateTime(ageInTicks, speed);
        animationState.ifStarted((state) -> KeyframeAnimations.animate(this, animationDefinition, state.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE));
    }

    protected void applyStatic(AnimationDefinition pAnimationDefinition) {
        KeyframeAnimations.animate(this, pAnimationDefinition, 0L, 1.0F, ANIMATION_VECTOR_CACHE);
    }
    */
}
