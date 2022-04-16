package net.damndaniel.entities;

import net.damndaniel.DamnDanielSounds;
import net.damndaniel.items.DamnDanielItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DamnDanielNPC extends PathAwareEntity implements Angerable {

    private int angerTime;
    @Nullable
    private UUID angryAt;
    private int angerPassingCooldown;
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private static final UniformIntProvider ANGER_PASSING_COOLDOWN_RANGE = TimeHelper.betweenSeconds(4, 6);

    public DamnDanielNPC(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);

        this.moveControl = new MoveControl(this);
        this.goalSelector.add(0, new SwimGoal(this));
        //this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0D));
        this.goalSelector.add(2, new AttackGoal(this));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(DamnDanielItems.WHITE_VANS), false));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(2, new DamnDanielNPC.TargetGoal<PlayerEntity>(this, PlayerEntity.class));
        this.targetSelector.add(2, new UniversalAngerGoal<DamnDanielNPC>(this, true));
    }

    public static DefaultAttributeContainer.Builder createDamnDanielAttribs() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    /*@Override
    public boolean canSpawn(WorldView world) {
        BlockPos blockUnderEntity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posEntity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return world.doesNotIntersectEntities(this) &&
                !world.containsFluid(this.getBoundingBox())
                && this.world.getBlockState(posEntity).getBlock().canMobSpawnInside()
                && this.world.getBlockState(posEntity).allowsSpawning(world, blockUnderEntity, DamnDanielEntities.DANIEL_NPC);
    }*/

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        //MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("Daaamn, Daniel!"));
        return entityData;
    }

    @Override
    protected void mobTick() {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        this.tickAngerLogic((ServerWorld)this.world, true);
        if (this.getTarget() != null) {
            this.tickAngerPassing();
        }
        if (this.hasAngerTime()) {
            this.playerHitTimer = this.age;
        }
        super.mobTick();
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

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.angerTime = angerTime;
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    private void tickAngerPassing() {
        if (this.angerPassingCooldown > 0) {
            --this.angerPassingCooldown;
            return;
        }
        if (this.getVisibilityCache().canSee(this.getTarget())) {
            this.angerNearbyDaniels();
        }
        this.angerPassingCooldown = ANGER_PASSING_COOLDOWN_RANGE.get(this.random);
    }

    private void angerNearbyDaniels() {
        double d = this.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        Box box = Box.from(this.getPos()).expand(d, 10.0, d);
        this.world.getEntitiesByClass(DamnDanielNPC.class, box, EntityPredicates.EXCEPT_SPECTATOR).stream().filter(danielNPC -> danielNPC != this).filter(danielNPC -> danielNPC.getTarget() == null).filter(danielNPC -> !danielNPC.isTeammate(this.getTarget())).forEach(danielNPC -> danielNPC.setTarget(this.getTarget()));
    }

    static class TargetGoal<T extends LivingEntity>
            extends ActiveTargetGoal<T> {
        public TargetGoal(DamnDanielNPC daniel, Class<T> targetEntityClass) {
            super((MobEntity)daniel, targetEntityClass, true);
        }

        @Override
        public boolean canStart() {
            return false;
        }
    }
}
