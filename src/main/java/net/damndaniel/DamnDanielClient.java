package net.damndaniel;

import net.damndaniel.entities.DamnDanielEntities;
//import net.damndaniel.entities.DamnDanielNPCRenderer;
import net.damndaniel.entities.DamnDanielNPCModel;
import net.damndaniel.entities.DamnDanielNPCRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class DamnDanielClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_DAMNDANIEL_LAYER = new EntityModelLayer(new Identifier(DamnDanielEntry.MOD_ID, "damndaniel"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(DamnDanielEntities.DANIEL_NPC, (context) -> {
            return new DamnDanielNPCRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_DAMNDANIEL_LAYER, DamnDanielNPCModel::getTexturedModelData);
    }
}