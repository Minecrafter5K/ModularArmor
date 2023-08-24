package de.miniwar.modulararmor.gui.slot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class DeactivatableSlot extends SlotItemHandler {
    private SlotValidator validator;
    private final int maxStackSize;

    public DeactivatableSlot(AbstractContainerMenu menu, int maxStackSize, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        if (menu instanceof SlotValidator) {
            validator = (SlotValidator) menu;
        }
        this.maxStackSize = maxStackSize;
    }

    @Override
    public int getMaxStackSize() {
        return maxStackSize;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return super.mayPlace(stack) && validator.isValidItemForSlot(stack);
    }

    @Override
    public Slot setBackground(ResourceLocation atlas, ResourceLocation sprite) {
        return super.setBackground(atlas, sprite);
    }
}
