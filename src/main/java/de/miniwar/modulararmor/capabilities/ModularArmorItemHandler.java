package de.miniwar.modulararmor.capabilities;

import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ModularArmorItemHandler implements IItemHandler, INBTSerializable<CompoundTag> {
    private static final int NUM_SLOTS = 8; // Number of inventory slots
    private final ItemStack stack;
    private final ItemStackHandler handler;

    public ModularArmorItemHandler(ItemStack stack) {
        this.stack = stack;
        this.handler = new ItemStackHandler(NUM_SLOTS);
    }

    @Override
    public int getSlots() {
        return NUM_SLOTS;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return handler.getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return handler.insertItem(slot, stack, simulate);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return handler.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() == ModItems.TEST_ITEM.get();
    }


    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        compound.put("modulararmor.upgrades", handler.serializeNBT());
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        if (compound.contains("modulararmor.upgrades")) {
            handler.deserializeNBT(compound.getCompound("modulararmor.upgrades"));
        }
    }
}
