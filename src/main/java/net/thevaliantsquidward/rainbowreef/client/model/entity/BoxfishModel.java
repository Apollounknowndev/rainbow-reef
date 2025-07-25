package net.thevaliantsquidward.rainbowreef.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.thevaliantsquidward.rainbowreef.client.animation.BoxfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.model.entity.base.ReefFishModel;
import net.thevaliantsquidward.rainbowreef.client.render.state.ReefRenderState;

public class BoxfishModel extends ReefFishModel<ReefRenderState> {
	private final ModelPart core;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart tail;
	private final ModelPart body;
	private final ModelPart TopFin;
	private final ModelPart BottomFin;

	public BoxfishModel(ModelPart root) {
		super(root);
		this.core = this.root.getChild("core");
		this.l_fin = this.core.getChild("l_fin");
		this.r_fin = this.core.getChild("r_fin");
		this.tail = this.core.getChild("tail");
		this.body = this.core.getChild("body");
		this.TopFin = this.body.getChild("TopFin");
		this.BottomFin = this.body.getChild("BottomFin");
		this.setupBaseAnimations(root, BoxfishAnimations.SWIM, BoxfishAnimations.FLOP);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		applyTransformation(root, pos -> PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition l_fin = core.addOrReplaceChild("l_fin", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 2.0F, 0.0F));

		PartDefinition r_fin = core.addOrReplaceChild("r_fin", CubeListBuilder.create().texOffs(16, 21).mirror().addBox(-2.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 2.0F, 0.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 5.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(23, 0).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 3).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-2.0F, -3.0F, -7.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(23, 9).addBox(2.0F, 1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(23, 9).addBox(-2.0F, 1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition TopFin = body.addOrReplaceChild("TopFin", CubeListBuilder.create().texOffs(13, 22).addBox(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 2.0F));

		PartDefinition BottomFin = body.addOrReplaceChild("BottomFin", CubeListBuilder.create().texOffs(21, 13).addBox(0.0261F, -1.0231F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public ModelPart xRotatedPart() {
		return this.core;
	}
}