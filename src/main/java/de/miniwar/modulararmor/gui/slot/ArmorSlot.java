package de.miniwar.modulararmor.gui.slot;

import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ArmorSlot extends Slot {
    private final int index;

    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{EMPTY_ARMOR_SLOT_BOOTS, EMPTY_ARMOR_SLOT_LEGGINGS, EMPTY_ARMOR_SLOT_CHESTPLATE, EMPTY_ARMOR_SLOT_HELMET};

    public ArmorSlot(Container container, int id, int x, int y, int index) {
        super(container, id, x, y);
        this.index = index;
    }

    public static ResourceLocation[] getTextureEmptySlots() {
        return TEXTURE_EMPTY_SLOTS;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return (ModItems.MODULAR_BOOTS.get() == stack.getItem() && this.index == 0)
                || (ModItems.MODULAR_LEGGINGS.get() == stack.getItem() && this.index == 1)
                || (ModItems.MODULAR_CHESTPLATE.get() == stack.getItem() && this.index == 2)
                || (ModItems.MODULAR_HELMET.get() == stack.getItem() && this.index == 3);
    }
}
