package net.thevaliantsquidward.rainbowreef.client.model.entity;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.client.animation.ArrowCrabAnimations;
import net.thevaliantsquidward.rainbowreef.client.model.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.CrabRenderState;


@SuppressWarnings("FieldCanBeLocal, unused")
public class ArrowCrabModel extends ReefModel<CrabRenderState> {
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart r_pincer;
	private final ModelPart l_pincer;
	private final ModelPart mandibles;
	private final ModelPart l_legs;
	private final ModelPart l_leg_1;
	private final ModelPart l_leg_2;
	private final ModelPart l_leg_3;
	private final ModelPart l_leg_4;
	private final ModelPart r_legs;
	private final ModelPart r_leg_1;
	private final ModelPart r_leg_2;
	private final ModelPart r_leg_3;
	private final ModelPart r_leg_4;
	protected final KeyframeAnimation walkAnimation;
	protected final KeyframeAnimation idleAnimation;
	protected final KeyframeAnimation danceAnimation;

	public ArrowCrabModel(ModelPart root) {
		super(root);
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.head = this.body.getChild("head");
		this.r_pincer = this.body.getChild("r_pincer");
		this.l_pincer = this.body.getChild("l_pincer");
		this.mandibles = this.body.getChild("mandibles");
		this.l_legs = this.core.getChild("l_legs");
		this.l_leg_1 = this.l_legs.getChild("l_leg_1");
		this.l_leg_2 = this.l_legs.getChild("l_leg_2");
		this.l_leg_3 = this.l_legs.getChild("l_leg_3");
		this.l_leg_4 = this.l_legs.getChild("l_leg_4");
		this.r_legs = this.core.getChild("r_legs");
		this.r_leg_1 = this.r_legs.getChild("r_leg_1");
		this.r_leg_2 = this.r_legs.getChild("r_leg_2");
		this.r_leg_3 = this.r_legs.getChild("r_leg_3");
		this.r_leg_4 = this.r_legs.getChild("r_leg_4");
		this.walkAnimation = ArrowCrabAnimations.WALK.bake(root);
		this.idleAnimation = ArrowCrabAnimations.IDLE.bake(root);
		this.danceAnimation = ArrowCrabAnimations.DANCE.bake(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		applyTransformation(root, pos -> PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.9259F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0852F, -0.0474F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5852F, -0.9526F, -0.0873F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5852F, -1.4526F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(32, 19).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 23).addBox(-1.5F, -7.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.1745F, 0.0F, 0.0F));

		PartDefinition r_pincer = body.addOrReplaceChild("r_pincer", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.4148F, -1.9526F));

		PartDefinition r_pincer_r1 = r_pincer.addOrReplaceChild("r_pincer_r1", CubeListBuilder.create().texOffs(26, 7).mirror().addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.1745F, 0.3491F, 0.0F));

		PartDefinition l_pincer = body.addOrReplaceChild("l_pincer", CubeListBuilder.create(), PartPose.offset(2.5F, 0.4148F, -1.9526F));

		PartDefinition l_pincer_r1 = l_pincer.addOrReplaceChild("l_pincer_r1", CubeListBuilder.create().texOffs(26, 7).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.1745F, -0.3491F, 0.0F));

		PartDefinition mandibles = body.addOrReplaceChild("mandibles", CubeListBuilder.create().texOffs(4, 12).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.9148F, -2.1286F));

		PartDefinition l_legs = core.addOrReplaceChild("l_legs", CubeListBuilder.create(), PartPose.offset(2.0F, 0.5F, 0.0741F));

		PartDefinition l_leg_1 = l_legs.addOrReplaceChild("l_leg_1", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, -1.0741F));

		PartDefinition l_leg_1_r1 = l_leg_1.addOrReplaceChild("l_leg_1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.5236F, -0.6109F));

		PartDefinition l_leg_2 = l_legs.addOrReplaceChild("l_leg_2", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, -0.0741F));

		PartDefinition l_leg_2_r1 = l_leg_2.addOrReplaceChild("l_leg_2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0873F, -0.6109F));

		PartDefinition l_leg_3 = l_legs.addOrReplaceChild("l_leg_3", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 0.9259F));

		PartDefinition l_leg_3_r1 = l_leg_3.addOrReplaceChild("l_leg_3_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.0873F, -0.6109F));

		PartDefinition l_leg_4 = l_legs.addOrReplaceChild("l_leg_4", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 1.9259F));

		PartDefinition l_leg_4_r1 = l_leg_4.addOrReplaceChild("l_leg_4_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.5236F, -0.6109F));

		PartDefinition r_legs = core.addOrReplaceChild("r_legs", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.5F, 0.0741F));

		PartDefinition r_leg_1 = r_legs.addOrReplaceChild("r_leg_1", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, -1.0741F));

		PartDefinition r_leg_1_r1 = r_leg_1.addOrReplaceChild("r_leg_1_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, -1.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.0F, -0.5236F, 0.6109F));

		PartDefinition r_leg_2 = r_legs.addOrReplaceChild("r_leg_2", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, -0.0741F));

		PartDefinition r_leg_2_r1 = r_leg_2.addOrReplaceChild("r_leg_2_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.0873F, 0.6109F));

		PartDefinition r_leg_3 = r_legs.addOrReplaceChild("r_leg_3", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 0.9259F));

		PartDefinition r_leg_3_r1 = r_leg_3.addOrReplaceChild("r_leg_3_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0873F, 0.6109F));

		PartDefinition r_leg_4 = r_legs.addOrReplaceChild("r_leg_4", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 1.9259F));

		PartDefinition r_leg_4_r1 = r_leg_4.addOrReplaceChild("r_leg_4_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.5236F, 0.6109F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(CrabRenderState state) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.body.xRot = (state.xRot * (Mth.DEG_TO_RAD));

		this.walkAnimation.apply(state.walk, state.ageInTicks, state.walkAnimationSpeed * 4);
		this.idleAnimation.apply(state.idle, state.ageInTicks, state.walkAnimationSpeed * 4);
		this.danceAnimation.apply(state.dance, state.ageInTicks, 1);
	}
}