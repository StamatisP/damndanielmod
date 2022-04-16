package net.damndaniel.entities;

import net.damndaniel.DamnDanielEntry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class DamnDanielEntities {

    public static final EntityType<DamnDanielNPC> DANIEL_NPC = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(DamnDanielEntry.MOD_ID, "damn_daniel_npc"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DamnDanielNPC::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(DANIEL_NPC, DamnDanielNPC.createDamnDanielAttribs());
        /*BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BADLANDS), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 100, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DESERT), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 100, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FOREST), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 100, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 100, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 100, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BEACH), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 100, 1, 1);*/
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.BEACH), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 1, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.DESERT), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 1, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 1, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.FOREST), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 1, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.JUNGLE), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 1, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.SWAMP), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 1, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.UNDERGROUND), SpawnGroup.CREATURE, DamnDanielEntities.DANIEL_NPC, 1, 1, 1);
    }
}
