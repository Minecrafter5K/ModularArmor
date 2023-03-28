package de.miniwar.modulararmor.gui.slot;

import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class RestrictedInputSlot extends SlotItemHandler {
    private final PlacableItemType which;

    public RestrictedInputSlot(IItemHandler itemHandler, int p_40224_, int p_40225_, int p_40226_, PlacableItemType valid) {
        super(itemHandler, p_40224_, p_40225_, p_40226_);
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
        ARMOR();

        PlacableItemType() {
        }
    }
}