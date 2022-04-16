package net.damndaniel.entities;

import net.damndaniel.DamnDanielEntry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DamnDanielEntities {

    public static final EntityType<DamnDanielNPC> DANIEL_NPC = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(DamnDanielEntry.MOD_ID, "damn_daniel_npc"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DamnDanielNPC::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(DANIEL_NPC, DamnDanielNPC.createMobAttributes());
    }
}
