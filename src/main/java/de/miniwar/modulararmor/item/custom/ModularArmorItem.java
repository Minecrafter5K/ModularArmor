package de.miniwar.modulararmor.item.custom;

import de.miniwar.modulararmor.capabilities.ModularArmorItemHandler;
import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModularArmorItem extends ArmorItem {
    public ModularArmorItem(ArmorMaterial material, Type component, Properties props) {
        super(material, component, props);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        // nothing
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        if (!level.isClientSide) {
            stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(cap -> {
                for (int i = 0; i < cap.getSlots(); i++) {
                    ItemStack upgrade = cap.getStackInSlot(i);
                    if (upgrade.getItem() == ModItems.TEST_ITEM.get()) {
                        player.addEffect(new MobEffectInstance(MobEffects.LUCK, 2));
                    }
                }
            });
        }
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        LazyOptional<IItemHandler> cap = stack.getCapability(ForgeCapabilities.ITEM_HANDLER);
        if (cap.isPresent()) {
            IItemHandler handler = cap.orElseThrow(IllegalStateException::new);
            for (int i = 0; i < handler.getSlots(); i++) {
                ItemStack upgrade = handler.getStackInSlot(i);
                if (upgrade.getItem() == ModItems.ELYTRA_UPGRADE.get()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level.isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                entity.gameEvent(net.minecraft.world.level.gameevent.GameEvent.ELYTRA_GLIDE);
            }
        }
        return true;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ModularArmorCaps(stack);
    }

    static class ModularArmorCaps implements ICapabilitySerializable<CompoundTag> {
        private final ItemStack stack;
        private final ModularArmorItemHandler handler;

        public ModularArmorCaps(ItemStack stack) {
            this.stack = stack;
            this.handler = new ModularArmorItemHandler(stack);
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, LazyOptional.of(() -> handler));
        }

        @Override
        public CompoundTag serializeNBT() {
            return handler.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            handler.deserializeNBT(nbt);
        }
    }
}
