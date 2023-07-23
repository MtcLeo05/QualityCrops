package com.mtcleo05.qualitycrops.mixin;

import com.mtcleo05.qualitycrops.items.ModCrops;
import com.mtcleo05.qualitycrops.items.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Objects;

@Mixin(Chicken.class)
public abstract class ChickenMixin extends Animal {

    @Shadow
    public int eggTime = this.random.nextInt(6000) + 6000;
    @Shadow
    public float flap;
    @Shadow
    public float flapSpeed;
    @Shadow
    public float oFlapSpeed;
    @Shadow
    public float oFlap;
    @Shadow
    public float flapping = 1.0F;
    @Shadow
    public boolean isChickenJockey;

    protected ChickenMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
        if (!this.level.isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey() && --this.eggTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            layEgg();
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.eggTime = this.random.nextInt(6000) + 6000;
        }

    }

    @Shadow
    public boolean isChickenJockey() {
        return this.isChickenJockey;
    }

    @Unique
    public void layEgg(){
        int chance = this.random.nextInt(100) + 1;

        if (chance <= 5) {
            this.spawnAtLocation(getItemFromID("egg_diamond"));
        } else if (chance <= 10) {
            this.spawnAtLocation(getItemFromID("egg_gold"));
        } else if (chance <= 15) {
            this.spawnAtLocation(getItemFromID("egg_iron"));
        } else {
            this.spawnAtLocation(Items.EGG);
        }
    }

    @Unique
    public Item getItemFromID(String id){
        Iterable<Item> qualityCrops = ModItems.VANILLA_QUALITY.getEntries().stream().map(RegistryObject::get)::iterator;

        for (Item item : qualityCrops) {
            if (getItemName(item).equals(id)) {
                return item;
            }
        }

        return null;
    }

    @Unique
    private String getItemName(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }

}
