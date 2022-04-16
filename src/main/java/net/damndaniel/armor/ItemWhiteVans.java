package net.damndaniel.armor;

import net.damndaniel.items.DamnDanielItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWhiteVans extends ArmorItem {

    StatusEffectInstance statusEffectInstanceSpeed = new StatusEffectInstance(StatusEffects.SPEED, 50, 1);
    StatusEffectInstance statusEffectInstanceLuck = new StatusEffectInstance(StatusEffects.LUCK, 50, 1);
    public ItemWhiteVans(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {

                if (!player.getInventory().getArmorStack(0).isEmpty()) {
                    giveEffect(player);
                }
            }
        }
    }

    private void giveEffect(PlayerEntity player) {
        ArmorItem boots = (ArmorItem) player.getInventory().getArmorStack(0).getItem();
        if (boots.getMaterial() == DamnDanielItems.CUSTOM_MATERIAL) {
            player.addStatusEffect(new StatusEffectInstance(statusEffectInstanceSpeed.getEffectType(), statusEffectInstanceSpeed.getDuration(), statusEffectInstanceSpeed.getAmplifier()));
            player.addStatusEffect(new StatusEffectInstance(statusEffectInstanceLuck.getEffectType(), statusEffectInstanceLuck.getDuration(), statusEffectInstanceLuck.getAmplifier()));
        }
    }
}
