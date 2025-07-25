package net.thevaliantsquidward.rainbowreef.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.animation.PipefishAnimations;
import net.thevaliantsquidward.rainbowreef.client.model.entity.base.ReefFishModel;
import net.thevaliantsquidward.rainbowreef.client.model.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;

import java.util.Map;

@SuppressWarnings("FieldCanBeLocal, unused")
public class PipefishModel extends ReefFishModel<ReefRenderState> {
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart tail_base;
	private final ModelPart tail_end;

	public PipefishModel(ModelPart root) {
		super(root);
		this.core = root.getChild("core");
		this.body = this.core.getChild("body");
		this.l_fin = this.body.getChild("l_fin");
		this.r_fin = this.body.getChild("r_fin");
		this.tail_base = this.core.getChild("tail_base");
		this.tail_end = this.tail_base.getChild("tail_end");
		this.setupBaseAnimations(root, PipefishAnimations.SWIM, PipefishAnimations.FLOP);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		applyTransformation(root, pos -> PartPose.offset(0.0F, 22.5F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(9, 3).addBox(-0.5F, -1.0F, -8.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 3.0F));
		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create().texOffs(7, 2).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, -1.0F));
		PartDefinition r_fin = body.addOrReplaceChild("r_fin", CubeListBuilder.create().texOffs(7, 2).mirror().addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 1.0F, -1.0F));
		PartDefinition tail_base = core.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 5.0F));
		PartDefinition tail_end = tail_base.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 4.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public ModelPart xRotatedPart() {
		return this.root;
	}
}