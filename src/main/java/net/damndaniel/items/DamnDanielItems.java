package net.damndaniel.items;

import net.damndaniel.DamnDanielEntry;
import net.damndaniel.DamnDanielSounds;
import net.damndaniel.armor.DDArmorMaterial;
import net.damndaniel.entities.DamnDanielEntities;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class DamnDanielItems {
    public static final ItemGroup DAMNDANIEL_GROUP = FabricItemGroupBuilder.build(
            new Identifier(DamnDanielEntry.MOD_ID, "items"),
            () -> new ItemStack(Items.MUSIC_DISC_11)
    );
    public static final ArmorMaterial CUSTOM_MATERIAL = new DDArmorMaterial();
    public static final Item WHITE_VANS = registerArmor("white_vans");
    public static final Item DISK_CLOUD_GLIDING = registerDisk("music_disk_cloud_gliding", DamnDanielSounds.SOUND_CLOUD_GLIDING);
    public static final Item DISK_SLIDE_AROUND = registerDisk("music_disk_slide_around",DamnDanielSounds.SOUND_SLIDE_AROUND);
    public static final Item DISK_MORNING_GLOW = registerDisk("music_disk_morning_glow", DamnDanielSounds.SOUND_MORNING_GLOW);
    public static final Item DISK_FLIGHT = registerDisk("music_disk_flight", DamnDanielSounds.SOUND_FLIGHT);
    public static final Item DISK_GO = registerDisk("music_disk_go", DamnDanielSounds.SOUND_GO);
    public static final Item DISK_SHRED = registerDisk("music_disk_shred", DamnDanielSounds.SOUND_SHRED);
    public static final Item DISK_DERNIERITE = registerDisk("music_disk_dernierite", DamnDanielSounds.SOUND_DERNIERITE);
    public static final Item DISK_CHAMBRE = registerDisk("music_disk_une_chambre", DamnDanielSounds.SOUND_CHAMBRE);
    public static final Item DISK_DARK_RETRO = registerDisk("music_disk_dark_retro", DamnDanielSounds.SOUND_DARK_RETRO);
    public static final Item DISK_MALLSOFT = registerDisk("music_disk_mallsoft", DamnDanielSounds.SOUND_MALLSOFT);
    public static final Item DISK_DESPERA = registerDisk("music_disk_despera", DamnDanielSounds.SOUND_DESPERA);
    public static final Item DISK_INTIMACY = registerDisk("music_disk_intimacy", DamnDanielSounds.SOUND_INTIMACY);
    public static final Item DISK_SETTANTA = registerDisk("music_disk_settanta", DamnDanielSounds.SOUND_SETTANTA);

    public static final Item DANIEL_EGG = new SpawnEggItem(DamnDanielEntities.DANIEL_NPC, 12829384, 4675947, new Item.Settings().group(DAMNDANIEL_GROUP));

    public static Item registerDisk(String id, SoundEvent sound) {
        Item.Settings settings = new Item.Settings().rarity(Rarity.RARE).maxCount(1).group(DAMNDANIEL_GROUP);
        return Registry.register(Registry.ITEM, new Identifier(DamnDanielEntry.MOD_ID, id), new AbstractDiscItem(14, sound, settings));
    }

    public static Item registerItem(String id, int maxCount) {
        Item.Settings settings = new Item.Settings().rarity(Rarity.RARE).maxCount(maxCount).group(DAMNDANIEL_GROUP);
        return Registry.register(Registry.ITEM, new Identifier(DamnDanielEntry.MOD_ID, id), new Item(settings));
    }

    public static ArmorItem registerArmor(String id) {
        Item.Settings settings = new Item.Settings().group(DAMNDANIEL_GROUP).rarity(Rarity.EPIC).maxCount(1);
        return Registry.register(Registry.ITEM, new Identifier(DamnDanielEntry.MOD_ID, id), new ArmorItem(
                CUSTOM_MATERIAL, EquipmentSlot.FEET, settings
        ));
    }

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(DamnDanielEntry.MOD_ID, "daniel_egg"), DANIEL_EGG);
    }
}
