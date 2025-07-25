package net.thevaliantsquidward.rainbowreef.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.thevaliantsquidward.rainbowreef.client.animation.SeahorseAnimations;
import net.thevaliantsquidward.rainbowreef.client.model.entity.base.ReefFishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;

@SuppressWarnings("FieldCanBeLocal, unused")
public class SeahorseModel extends ReefFishModel<ReefRenderState> {
	private final ModelPart core;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart back_fin;
	private final ModelPart tail;

	public SeahorseModel(ModelPart root) {
		super(root);
		this.core = this.root.getChild("core");
		this.head = this.core.getChild("head");
		this.body = this.core.getChild("body");
		this.back_fin = this.body.getChild("back_fin");
		this.tail = this.body.getChild("tail");
		this.setupBaseAnimations(root, SeahorseAnimations.SWIM, SeahorseAnimations.FLOP);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		applyTransformation(root, pos -> PartPose.offset(0.0F, 22.0F, 0.0F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));
		PartDefinition head = core.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.5F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(10, 0).addBox(-1.5F, -2.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 13).addBox(-0.5F, -1.5F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(6, 8).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));
		PartDefinition back_fin = body.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(14, 4).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));
		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -4.0F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(8, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public ModelPart xRotatedPart() {
		return this.root;
	}
}