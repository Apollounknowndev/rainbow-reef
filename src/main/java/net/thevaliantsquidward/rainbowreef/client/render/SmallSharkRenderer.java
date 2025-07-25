package net.thevaliantsquidward.rainbowreef.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.client.model.entity.SmallSharkModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.TailBearerRenderState;
import net.thevaliantsquidward.rainbowreef.entity.SmallShark;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics.IKSolver;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class SmallSharkRenderer extends ReefRenderer<SmallShark, TailBearerRenderState> {
    public SmallSharkRenderer(EntityRendererProvider.Context context) {
        super("small_shark", SmallShark.Variant.values(), context, new SmallSharkModel(context.bakeLayer(ReefModelLayers.SMALL_SHARK_LAYER)), 0.4F);
    }

    @Override
    public TailBearerRenderState createRenderState() {
        return new TailBearerRenderState();
    }

    @Override
    public void extractRenderState(SmallShark shark, TailBearerRenderState state, float delta) {
        super.extractRenderState(shark, state, delta);
        state.idle.copyFrom(shark.idleAnimationState);
        state.swim.copyFrom(shark.swimAnimationState);

        IKSolver.Snapshot snapshot = shark.tailKinematics.getSnapshot();

        state.lastTilt = shark.prevTilt;
        state.lastTailYaws = snapshot.lastTailYaws();
        state.lastTailPitches = snapshot.lastTailPitches();

        state.currentTilt = shark.tilt;
        state.currentTailYaws = snapshot.currentTailYaws();
        state.currentTailPitches = snapshot.currentTailPitches();

        state.returnYaws = () -> shark.tailKinematics.setCurrentTailYaws(state.currentTailYaws);
        state.returnPitches = () -> shark.tailKinematics.setCurrentTailPitches(state.currentTailPitches);
    }

    @Override
    protected void setupRotations(TailBearerRenderState state, PoseStack stack, float f, float g) {
        super.setupRotations(state, stack, f, g);
        stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(g, -state.lastTilt, -state.currentTilt)));
    }
}