package net.damndaniel.entities;

import net.damndaniel.DamnDanielClient;
import net.damndaniel.DamnDanielEntry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class DamnDanielNPCRenderer extends MobEntityRenderer<DamnDanielNPC, DamnDanielNPCModel> {

    public DamnDanielNPCRenderer(EntityRendererFactory.Context context) {
        super(context, new DamnDanielNPCModel(context.getPart(DamnDanielClient.MODEL_DAMNDANIEL_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(DamnDanielNPC entity) {
        return new Identifier(DamnDanielEntry.MOD_ID, "textures/entity/damndaniel/damndaniel.png");
    }
}
