package com.mtcleo05.qualitycrops.quality;

import net.minecraft.world.item.BowlFoodItem;

public class QualityBowlFoodItem extends BowlFoodItem {

    public final int cropQuality;

    public QualityBowlFoodItem(Properties properties, int cropQuality) {
        super(properties);
        this.cropQuality = cropQuality;
    }
}
