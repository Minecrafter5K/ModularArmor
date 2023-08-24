package de.miniwar.modulararmor.gui.slot;

import de.miniwar.modulararmor.gui.screen.ArmorModifierMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class ArmorModifierSlot extends RestrictedInputSlot {
    ArmorModifierMenu menu;

    public ArmorModifierSlot(ArmorModifierMenu menu, IItemHandler itemHandler, int index, int xPosition, int yPosition, PlacableItemType valid) {
        super(itemHandler, index, xPosition, yPosition, valid);
        this.menu = menu;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        menu.modifyItem(stack);
        super.onTake(player, stack);
        this.setChanged();
    }

    @Override
    public void set(@NotNull ItemStack stack) {
        menu.getUpgrades(stack);
        super.set(stack);
        this.setChanged();
    }
}
