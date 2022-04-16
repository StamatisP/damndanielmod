package net.damndaniel.entities;

import net.damndaniel.DamnDanielEntry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.include.com.google.common.base.Preconditions;

import java.util.function.Predicate;

public class DamnDanielSpawns {
    public static int SpawnRate = 100;

    public static void addSpawn(Predicate<BiomeSelectionContext> biomeSelector, SpawnGroup spawnGroup, SpawnSettings.SpawnEntry se) {
        Preconditions.checkArgument(se.type.getSpawnGroup() != SpawnGroup.MISC, "MISC spawns pigs?");

        Identifier id = Registry.ENTITY_TYPE.getId(se.type);
        Preconditions.checkState(id != Registry.ENTITY_TYPE.getDefaultId(), "Unregistered entity type: ".concat(se.type.toString()));

        BiomeModifications.create(id).add(ModificationPhase.ADDITIONS, biomeSelector, context -> {
            context.getSpawnSettings().addSpawn(spawnGroup, se);
        });
    }

    private static void normalSpawn() {
        Predicate<BiomeSelectionContext> biomeSelector = (context) -> {
            return true;
        };

        addSpawn(biomeSelector, DamnDanielEntities.DANIEL_NPC.getSpawnGroup(),
                new SpawnSettings.SpawnEntry(DamnDanielEntities.DANIEL_NPC, SpawnRate, 1, 1));
    }

    public static void init() {
        normalSpawn();
    }
}
