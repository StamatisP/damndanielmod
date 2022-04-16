package net.damndaniel.items;

import net.damndaniel.DamnDanielEntry;
import net.damndaniel.entities.DamnDanielEntities;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.lwjgl.system.CallbackI;

import java.util.HashSet;
import java.util.Set;

public class DamnDanielLootTables {
    public static final Set<Identifier> lootTables = new HashSet<>();
    public static final Set<String> lootPhrases = new HashSet<>();

    public static void init() {
        lootTables.add(new Identifier("minecraft:chests/abandoned_mineshaft"));
        lootTables.add(new Identifier("minecraft:chests/buried_treasure"));
        lootTables.add(new Identifier("minecraft:chests/desert_pyramid"));
        lootTables.add(new Identifier("minecraft:chests/end_city_treasure"));
        lootTables.add(new Identifier("minecraft:chests/igloo_chest"));
        lootTables.add(new Identifier("minecraft:chests/jungle_temple"));
        lootTables.add(new Identifier("minecraft:chests/nether_bridge"));
        lootTables.add(new Identifier("minecraft:chests/pillager_outpost"));
        lootTables.add(new Identifier("minecraft:chests/shipwreck_treasure"));
        lootTables.add(new Identifier("minecraft:chests/simple_dungeon"));
        lootTables.add(new Identifier("minecraft:chests/woodland_mansion"));

        lootPhrases.add("dungeon");
        lootPhrases.add("treasure");
        DamnDanielEntry.LOGGER.info("Loading loot tables");

        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (lootTables.contains(id) || phrasesContains(id)) {
                LootPool pool = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .withCondition(RandomChanceLootCondition.builder(0.5f).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_CLOUD_GLIDING).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_CHAMBRE).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_DARK_RETRO).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_DERNIERITE).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_DESPERA).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_FLIGHT).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_GO).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_INTIMACY).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_MALLSOFT).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_MORNING_GLOW).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_SHRED).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_SLIDE_AROUND).build())
                        .withEntry(ItemEntry.builder(DamnDanielItems.DISK_SETTANTA).build())
                        .build();

                supplier.withPool(pool);
            }
        }));
    }

    private static boolean phrasesContains(Identifier identifier) {
        for (String phrase : lootPhrases) {
            if (identifier.getPath().contains(phrase)) {
                return true;
            }
        }

        return false;
    }
}
