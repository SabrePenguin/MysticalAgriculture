package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.BehaviorSeeds;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

public class ItemSeed extends ItemSeeds {

    private Block crops;
    private int tier;

    public ItemSeed(String name, Block crops, int tier) {
        super(crops, Blocks.FARMLAND);
        this.setTranslationKey("ma." + name);
        this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
        this.crops = crops;
        this.tier = tier;

        if (ModConfig.confDispenserPlanting) {
            BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new BehaviorSeeds(this.crops));
        }
    }

    public int getTier() {
        return this.tier;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        switch (this.tier - 1) {
            case 0:
                tooltip.add(Tooltips.TIER + Colors.YELLOW + "1");
                break;
            case 1:
                tooltip.add(Tooltips.TIER + Colors.GREEN + "2");
                break;
            case 2:
                tooltip.add(Tooltips.TIER + Colors.GOLD + "3");
                break;
            case 3:
                tooltip.add(Tooltips.TIER + Colors.AQUA + "4");
                break;
            case 4:
                tooltip.add(Tooltips.TIER + Colors.RED + "5");
                break;
            case 5:
                tooltip.add(Tooltips.TIER + Colors.DARK_PURPLE + this.tier);
                break;
            default:
                if (this.tier - 7 < ModConfig.confTierColors.length) {
                    String[] color = convertToColor(ModConfig.confTierColors[this.tier - 7].split(","));
                    if (isValidColor(color)) {
                        tooltip.add(Tooltips.TIER + String.join("",color) + this.tier);
                    }
                } else {
                    tooltip.add(Tooltips.TIER + Colors.WHITE + this.tier);
                }
        }
    }

    private String[] convertToColor(String[] input) {
        for (int i = 0; i < input.length; i++) {
            String color = input[i];
            if (color == null) return null;
            if (color.length() == 1 && !color.startsWith("§")) {
                input[i] = "§" + color;
            }
        }
        return input;
    }

    private boolean isValidColor(String[] input) {
        if (input == null) return false;
        for (String color: input) {
            if (color == null) return false;
            if (color.length() != 2) return false;
            if (!color.startsWith("§")) return false;
        }
        return true;
    }
}
