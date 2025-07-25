package net.thevaliantsquidward.rainbowreef.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.thevaliantsquidward.rainbowreef.client.animation.MoorishIdolAnimations;
import net.thevaliantsquidward.rainbowreef.client.model.entity.base.ReefFishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;

public class MoorishIdolModel extends ReefFishModel<ReefRenderState> {
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart fin;
	private final ModelPart fin_tip;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart l_bottom_fin;
	private final ModelPart r_bottom_fin;
	private final ModelPart bottom_fin;
	private final ModelPart tail;

	public MoorishIdolModel(ModelPart root) {
		super(root);
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.fin = this.body.getChild("fin");
		this.fin_tip = this.fin.getChild("fin_tip");
		this.l_fin = this.body.getChild("l_fin");
		this.r_fin = this.body.getChild("r_fin");
		this.l_bottom_fin = this.body.getChild("l_bottom_fin");
		this.r_bottom_fin = this.body.getChild("r_bottom_fin");
		this.bottom_fin = this.body.getChild("bottom_fin");
		this.tail = this.body.getChild("tail");
		this.setupBaseAnimations(root, MoorishIdolAnimations.SWIM, MoorishIdolAnimations.FLOP);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		applyTransformation(root, pos -> PartPose.offset(0.0F, 20.0F, -0.75F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -2.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(10, 14).addBox(-1.0F, -4.0F, -3.0F, 2.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.75F));

		PartDefinition fin = body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 6).addBox(-0.1285F, -3.9528F, -1.0071F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition fin_tip = fin.addOrReplaceChild("fin_tip", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1285F, -3.9528F, 6.9929F));

		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, 0.0F));

		PartDefinition l_fin_r1 = l_fin.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(-2, 4).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, -0.7854F));

		PartDefinition r_fin = body.addOrReplaceChild("r_fin", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, 0.0F));

		PartDefinition r_fin_r1 = r_fin.addOrReplaceChild("r_fin_r1", CubeListBuilder.create().texOffs(-2, 4).mirror().addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.7854F));

		PartDefinition l_bottom_fin = body.addOrReplaceChild("l_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 1.0F));

		PartDefinition l_bottom_fin_r1 = l_bottom_fin.addOrReplaceChild("l_bottom_fin_r1", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, -0.6109F));

		PartDefinition r_bottom_fin = body.addOrReplaceChild("r_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 1.0F));

		PartDefinition r_bottom_fin_r1 = r_bottom_fin.addOrReplaceChild("r_bottom_fin_r1", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.6109F));

		PartDefinition bottom_fin = body.addOrReplaceChild("bottom_fin", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, -1.0F, -2.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 2.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public ModelPart xRotatedPart() {
		return this.core;
	}
}