package com.blakebr0.mysticalagriculture.entity.arrow;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

public class RenderIntermediumArrow extends RenderArrow<EntityIntermediumArrow> {

    public RenderIntermediumArrow(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityIntermediumArrow entity) {
        return new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/entity/projectiles/intermedium_arrow.png");
    }
}
