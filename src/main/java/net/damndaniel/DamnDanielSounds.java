package net.damndaniel;

import net.damndaniel.items.DamnDanielItems;
import net.minecraft.client.sound.Sound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DamnDanielSounds {
    public static final SoundEvent SOUND_CLOUD_GLIDING = register("music_disk.cloud_gliding");
    public static final SoundEvent SOUND_SLIDE_AROUND = register("music_disk.slide_around");
    public static final SoundEvent SOUND_MORNING_GLOW = register("music_disk.morning_glow");
    public static final SoundEvent SOUND_FLIGHT = register("music_disk.flight");
    public static final SoundEvent SOUND_GO = register("music_disk.go");
    public static final SoundEvent SOUND_SHRED = register("music_disk.shred");
    public static final SoundEvent SOUND_DERNIERITE = register("music_disk.dernierite");
    public static final SoundEvent SOUND_CHAMBRE = register("music_disk.une_chambre");
    public static final SoundEvent SOUND_DARK_RETRO = register("music_disk.dark_retro");
    public static final SoundEvent SOUND_MALLSOFT = register("music_disk.mallsoft");
    public static final SoundEvent SOUND_DESPERA = register("music_disk.despera");
    public static final SoundEvent SOUND_INTIMACY = register("music_disk.intimacy");
    public static final SoundEvent SOUND_SETTANTA = register("music_disk.settanta");

    public static final SoundEvent SOUND_DAMNDANIEL_HURT = register("damndaniel.hurt");
    public static final SoundEvent SOUND_DAMNDANIEL_AMBIENT = register("damndaniel.ambient");

    public static SoundEvent register(String name) {
        Identifier id = new Identifier(DamnDanielEntry.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void init() {

    }
}
