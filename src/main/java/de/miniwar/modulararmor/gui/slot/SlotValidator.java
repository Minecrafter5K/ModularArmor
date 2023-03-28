package de.miniwar.modulararmor.gui.slot;

import net.minecraft.world.item.ItemStack;

public interface SlotValidator {

    boolean isValidItemForSlot(ItemStack stack);

}
