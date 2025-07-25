package net.thevaliantsquidward.rainbowreef.client.model.entity;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.thevaliantsquidward.rainbowreef.client.animation.RayAnimations;
import net.thevaliantsquidward.rainbowreef.client.model.entity.base.ReefFishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.TailBearerRenderState;
import net.thevaliantsquidward.rainbowreef.util.MathHelpers;

@SuppressWarnings("FieldCanBeLocal, unused")
public class RayModel extends ReefFishModel<TailBearerRenderState> {
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart onlyBody;
	private final ModelPart r_fin;
	private final ModelPart r_tip;
	private final ModelPart l_fin;
	private final ModelPart l_tip;
	private final ModelPart tail;
	private final ModelPart l_tail_fin;
	private final ModelPart r_tail_fin;
	private final ModelPart tail_tip;
	private final ModelPart tail_tip2;
	private final ModelPart tail_tip3;
	private final ModelPart tail_tip4;
	private final KeyframeAnimation idleAnimation;

	public RayModel(ModelPart root) {
		super(root);
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.onlyBody = this.body.getChild("onlyBody");
		this.r_fin = this.onlyBody.getChild("r_fin");
		this.r_tip = this.r_fin.getChild("r_tip");
		this.l_fin = this.onlyBody.getChild("l_fin");
		this.l_tip = this.l_fin.getChild("l_tip");
		this.tail = this.body.getChild("tail");
		this.l_tail_fin = this.tail.getChild("l_tail_fin");
		this.r_tail_fin = this.tail.getChild("r_tail_fin");
		this.tail_tip = this.tail.getChild("tail_tip");
		this.tail_tip2 = this.tail_tip.getChild("tail_tip2");
		this.tail_tip3 = this.tail_tip2.getChild("tail_tip3");
		this.tail_tip4 = this.tail_tip3.getChild("tail_tip4");
		this.idleAnimation = RayAnimations.IDLE.bake(root);
		this.setupBaseAnimations(root, RayAnimations.SWIM, RayAnimations.FLOP);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		applyTransformation(root, pos -> PartPose.offset(0.0F, 22.5F, 0.0F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, -7.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));
		PartDefinition onlyBody = body.addOrReplaceChild("onlyBody", CubeListBuilder.create().texOffs(34, 16).addBox(-3.5F, -1.0F, -10.0F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-5.5F, -2.0F, -7.0F, 11.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));
		PartDefinition r_fin = onlyBody.addOrReplaceChild("r_fin", CubeListBuilder.create().texOffs(0, 16).addBox(-11.0F, -0.5F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 0.5F, 0.0F));
		PartDefinition r_tip = r_fin.addOrReplaceChild("r_tip", CubeListBuilder.create().texOffs(29, 29).addBox(-7.0F, -0.5F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 0.0F, -2.5F));
		PartDefinition l_fin = onlyBody.addOrReplaceChild("l_fin", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0.0F, -0.5F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.5F, 0.5F, 0.0F));
		PartDefinition l_tip = l_fin.addOrReplaceChild("l_tip", CubeListBuilder.create().texOffs(29, 29).mirror().addBox(0.0F, -0.5F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(11.0F, 0.0F, -2.5F));
		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(7, 26).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));
		PartDefinition l_tail_fin = tail.addOrReplaceChild("l_tail_fin", CubeListBuilder.create().texOffs(30, 21).addBox(0.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition r_tail_fin = tail.addOrReplaceChild("r_tail_fin", CubeListBuilder.create().texOffs(30, 21).mirror().addBox(-5.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition tail_tip = tail.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(0, 31).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));
		PartDefinition tail_tip2 = tail_tip.addOrReplaceChild("tail_tip2", CubeListBuilder.create().texOffs(10, 21).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 7.0F));
		PartDefinition tail_tip3 = tail_tip2.addOrReplaceChild("tail_tip3", CubeListBuilder.create().texOffs(1, 41).addBox(0.0F, -1.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 8.0F));
		PartDefinition tail_tip4 = tail_tip3.addOrReplaceChild("tail_tip4", CubeListBuilder.create().texOffs(1, 46).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 8.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public ModelPart xRotatedPart() {
		return this.body;
	}

	@Override
	public void setupAnim(TailBearerRenderState state) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.idleAnimation.apply(state.idle, state.ageInTicks);

		if (state.isInWater) {
			this.tail.yRot = this.tail.yRot - ((float) MathHelpers.LerpDegrees(state.currentTailYaws[0], state.lastTailYaws[0], 0.1));
			this.tail_tip.yRot = this.tail_tip.yRot - ((float) MathHelpers.LerpDegrees(state.currentTailYaws[1], state.lastTailYaws[1], 0.1));
			this.tail_tip2.yRot = this.tail_tip2.yRot - ((float) MathHelpers.LerpDegrees(state.currentTailYaws[2], state.lastTailYaws[2], 0.1));
			this.tail_tip3.yRot = this.tail_tip3.yRot - ((float) MathHelpers.LerpDegrees(state.currentTailYaws[3], state.lastTailYaws[3], 0.1));
			this.tail_tip4.yRot = this.tail_tip4.yRot - ((float) MathHelpers.LerpDegrees(state.currentTailYaws[4], state.lastTailYaws[4], 0.1));
			state.currentTailYaws[0] = (float) MathHelpers.LerpDegrees(state.currentTailYaws[0], state.lastTailYaws[0], 0.1);
			state.currentTailYaws[1] = (float) MathHelpers.LerpDegrees(state.currentTailYaws[1], state.lastTailYaws[1], 0.1);
			state.currentTailYaws[2] = (float) MathHelpers.LerpDegrees(state.currentTailYaws[2], state.lastTailYaws[2], 0.1);
			state.currentTailYaws[3] = (float) MathHelpers.LerpDegrees(state.currentTailYaws[3], state.lastTailYaws[3], 0.1);
			state.currentTailYaws[4] = (float) MathHelpers.LerpDegrees(state.currentTailYaws[4], state.lastTailYaws[4], 0.1);

			this.tail.xRot = -((float) (this.tail.xRot + MathHelpers.LerpDegrees(state.currentTailPitches[0], state.lastTailPitches[0], 0.1)));
			this.tail_tip.xRot = -((float) (this.tail_tip.xRot + MathHelpers.LerpDegrees(state.currentTailPitches[1], state.lastTailPitches[1], 0.1)));
			this.tail_tip2.xRot = -((float) (this.tail_tip2.xRot + MathHelpers.LerpDegrees(state.currentTailPitches[2], state.lastTailPitches[2], 0.1)));
			this.tail_tip3.xRot = -((float) (this.tail_tip3.xRot + MathHelpers.LerpDegrees(state.currentTailPitches[3], state.lastTailPitches[3], 0.1)));
			this.tail_tip4.xRot = -((float) (this.tail_tip4.xRot + MathHelpers.LerpDegrees(state.currentTailPitches[4], state.lastTailPitches[4], 0.1)));
			state.currentTailPitches[0] = (float) MathHelpers.LerpDegrees(state.currentTailPitches[0], state.lastTailPitches[0], 0.1);
			state.currentTailPitches[1] = (float) MathHelpers.LerpDegrees(state.currentTailPitches[1], state.lastTailPitches[1], 0.1);
			state.currentTailPitches[2] = (float) MathHelpers.LerpDegrees(state.currentTailPitches[2], state.lastTailPitches[2], 0.1);
			state.currentTailPitches[3] = (float) MathHelpers.LerpDegrees(state.currentTailPitches[3], state.lastTailPitches[3], 0.1);
			state.currentTailPitches[4] = (float) MathHelpers.LerpDegrees(state.currentTailPitches[4], state.lastTailPitches[4], 0.1);
		}
	}
}