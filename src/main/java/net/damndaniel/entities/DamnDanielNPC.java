package net.damndaniel.entities;

import net.damndaniel.DamnDanielSounds;
import net.damndaniel.items.DamnDanielItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class DamnDanielNPC extends PathAwareEntity {


    public DamnDanielNPC(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);

        this.moveControl = new MoveControl(this);
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0D));
        this.goalSelector.add(2, new TemptGoal(this, 1.25D, Ingredient.ofItems(new ItemConvertible[]{DamnDanielItems.WHITE_VANS}), false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    @Override
    public boolean canSpawn(WorldView world) {
        BlockPos blockUnderEntity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posEntity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return world.intersectsEntities(this) &&
                !world.containsFluid(this.getBoundingBox())
                && this.world.getBlockState(posEntity).getBlock().canMobSpawnInside()
                && this.world.getBlockState(posEntity).allowsSpawning(world, blockUnderEntity, DamnDanielEntities.DANIEL_NPC);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return DamnDanielSounds.SOUND_DAMNDANIEL_AMBIENT;
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 160;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return DamnDanielSounds.SOUND_DAMNDANIEL_HURT; }

    @Override
    protected SoundEvent getDeathSound() { return DamnDanielSounds.SOUND_DAMNDANIEL_HURT; }
}
