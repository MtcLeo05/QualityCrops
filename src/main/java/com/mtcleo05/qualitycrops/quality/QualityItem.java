package com.mtcleo05.qualitycrops.quality;

import net.minecraft.world.item.*;

public class QualityItem extends Item{

    public final int cropQuality;

    public QualityItem(Properties properties, int cropQuality) {
        super(properties);
        this.cropQuality = cropQuality;
    }


}
