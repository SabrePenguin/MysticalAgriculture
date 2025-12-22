package com.blakebr0.mysticalagriculture.items.arrow;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.entity.arrow.EntityIntermediumArrow;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

public class ItemIntermediumArrow extends ItemArrow {

    public ItemIntermediumArrow() {
        String name = "intermedium_arrow";
        this.setTranslationKey("ma." + name);
        this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
        return new EntityIntermediumArrow(world, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, EntityPlayer player) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        if (Utils.isShiftKeyDown()) {
            tooltip.add(Tooltips.DAMAGE + Colors.GOLD + "+2.0");
            tooltip.add(Tooltips.GIVES_DEBUFFS);
            tooltip.add(" - " + Tooltips.BLINDNESS);
            tooltip.add(" - " + Tooltips.SLOWNESS);
            tooltip.add(" - " + Tooltips.POISON);
        } else {
            tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO);
        }
    }
}
