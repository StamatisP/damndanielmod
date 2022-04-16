// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17
// Paste this class into your mod and generate all required imports

package net.damndaniel.entities;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class DamnDanielNPCModel extends EntityModel<DamnDanielNPC> {
	private final ModelPart bb_main;

	public DamnDanielNPCModel(ModelPart bb_main) {
		this.bb_main = bb_main;
	}

	public static ModelData getModelData(){
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -32.0F, 0.0F, 16.0F, 32.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return modelData;
	}


	public static TexturedModelData getTexturedModelData() {
		return TexturedModelData.of(getModelData(), 32, 32);
	}

	@Override
	public void setAngles(DamnDanielNPC entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
	}

}