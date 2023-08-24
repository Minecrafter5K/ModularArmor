package de.miniwar.modulararmor.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class UpgradeItem extends Item {
    public UpgradeItem(Properties props) {
        super(props);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
