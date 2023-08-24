package de.miniwar.modulararmor.item.custom;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BasicBatteryItem extends UpgradeItem {
    public BasicBatteryItem(Properties props) {
        super(props);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        // This is the standard green color of full durability bars
        return Mth.hsvToRgb(1 / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        LazyOptional<IEnergyStorage> cap = stack.getCapability(ForgeCapabilities.ENERGY);
        if (cap.isPresent()) {
            IEnergyStorage handler = cap.orElseThrow(IllegalStateException::new);
            return (int) (13.0F * ((float) handler.getEnergyStored() / (float) handler.getMaxEnergyStored()));
        }
        return 13;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new BasicBatteryCaps(stack);
    }

    static class BasicBatteryCaps implements ICapabilitySerializable<Tag> {
        private final ItemStack stack;
        private final EnergyStorage handler;

        public BasicBatteryCaps(ItemStack stack) {
            this.stack = stack;
            this.handler = new EnergyStorage(1000, 50);
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return ForgeCapabilities.ENERGY.orEmpty(cap, LazyOptional.of(() -> handler));
        }

        @Override
        public Tag serializeNBT() {
            return handler.serializeNBT();
        }

        @Override
        public void deserializeNBT(Tag nbt) {
            handler.deserializeNBT(nbt);
        }
    }
}
