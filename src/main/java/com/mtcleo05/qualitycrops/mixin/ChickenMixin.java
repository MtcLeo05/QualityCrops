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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

import static com.mtcleo05.qualitycrops.utils.Utils.getItemFromID;
import static com.mtcleo05.qualitycrops.utils.Utils.getItemName;

@Mixin(Chicken.class)
public abstract class ChickenMixin extends Animal {

    protected ChickenMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @ModifyArg(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Chicken;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"))
    private ItemLike layRarityEgg(final ItemLike egg) {
        int chance = this.random.nextInt(100) + 1;

        if (chance <= 5) {
            return getItemFromID("qualitycrops:egg_diamond");
        } else if (chance <= 10) {
            return getItemFromID("qualitycrops:egg_gold");
        } else if (chance <= 15) {
            return getItemFromID("qualitycrops:egg_iron");
        } else {
            return egg;
        }
    }
}
