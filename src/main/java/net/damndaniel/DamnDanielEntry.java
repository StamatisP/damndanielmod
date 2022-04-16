package net.damndaniel;

import net.damndaniel.entities.DamnDanielEntities;
import net.damndaniel.entities.DamnDanielSpawns;
import net.damndaniel.items.DamnDanielItems;
import net.damndaniel.items.DamnDanielLootTables;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DamnDanielEntry implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("damndaniel");
	public static final String MOD_ID = "damndaniel";
	public static final String MOD_NAME = "Damn Daniel Mod";


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		DamnDanielItems.init();
		DamnDanielSounds.init();
		DamnDanielEntities.init();
		//DamnDanielSpawns.init();
		DamnDanielLootTables.init();
	}
}
