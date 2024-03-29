package de.miniwar.modulararmor.gui.slot;

import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class RestrictedInputSlot extends Slot {
    private final PlacableItemType which;

    public RestrictedInputSlot(Container container, int id, int x, int y, PlacableItemType valid) {
        super(container, id, x, y);
        this.which = valid;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        switch (this.which) {
            case ARMOR:
                return ModItems.MODULAR_HELMET.get() == stack.getItem()
                        || ModItems.MODULAR_CHESTPLATE.get() == stack.getItem()
                        || ModItems.MODULAR_LEGGINGS.get() == stack.getItem()
                        || ModItems.MODULAR_BOOTS.get() == stack.getItem();
            default:
                break;
        }

        return false;
    }

    public enum PlacableItemType {
        ARMOR(),

        PlacableItemType() {
        }
    }
}
