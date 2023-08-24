package de.miniwar.modulararmor.gui.screen;

import de.miniwar.modulararmor.block.ModBlocks;
import de.miniwar.modulararmor.block.entity.ArmorModifierBlockEntity;
import de.miniwar.modulararmor.gui.slot.ArmorModifierSlot;
import de.miniwar.modulararmor.gui.slot.DeactivatableSlotItemHandler;
import de.miniwar.modulararmor.gui.slot.ArmorSlot;
import de.miniwar.modulararmor.gui.slot.SlotValidator;
import de.miniwar.modulararmor.item.custom.ModularArmorItem;
import de.miniwar.modulararmor.item.custom.UpgradeItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class ArmorModifierMenu extends AbstractContainerMenu implements SlotValidator {
    public final ArmorModifierBlockEntity blockEntity;
    private final Level level;

    public ArmorModifierMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public ArmorModifierMenu(int id, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.ARMOR_MODIFIER_MENU.get(), id);
        checkContainerSize(inv, 9);
        blockEntity = (ArmorModifierBlockEntity) entity;
        this.level = inv.player.level;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addPlayerArmor(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new ArmorModifierSlot(this, handler, 0, 79, 23));

            for (int i = 0; i < 8; ++i) {
                this.addSlot(new DeactivatableSlotItemHandler(this, 1, handler, i + 1, 10 + i * 20, 50));
            }
        });
    }

    @Override
    public boolean isValidItemForSlot(ItemStack stack) {
        return stack.getItem() instanceof UpgradeItem && this.slots.get(TE_INVENTORY_FIRST_SLOT_INDEX).hasItem();
    }

    public void modifyItem(ItemStack stack) {
        if (stack.getItem() instanceof ModularArmorItem) {
            stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(cap -> {
                for (int i = 0; i < cap.getSlots(); i++) {
                    // i plus fist slot index of the armor modifier menu plus one because the first slot is the armor slot
                    Slot slot = this.slots.get(i + TE_INVENTORY_FIRST_SLOT_INDEX + 1);
                    if (slot.hasItem()) {
                        ItemStack slotStack = slot.getItem();
                        ItemStack remainingStack = cap.insertItem(i, slotStack, false);

                        if (remainingStack.isEmpty()) {
                            slot.set(ItemStack.EMPTY);
                        } else {
                            slot.set(remainingStack);
                        }
                    }
                }
            });
        }
    }

    public void getUpgrades(ItemStack stack) {
        if (stack.getItem() instanceof ModularArmorItem) {

            stack.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(cap -> {
                // Loop through the slots in your ArmorModifierMenu
                for (int i = 0; i < cap.getSlots(); i++) {
                    // i plus fist slot index of the armor modifier menu plus one because the first slot is the armor slot
                    Slot slot = this.slots.get(i + TE_INVENTORY_FIRST_SLOT_INDEX + 1);

                    if (!slot.hasItem()) {
                        ItemStack slotStack = cap.getStackInSlot(i);

                        if (!slotStack.isEmpty()) {
                            slot.set(slotStack);
                            cap.extractItem(i, slotStack.getCount(), false);
                        }
                    }
                }
            });
        }
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)

    private static final int ARMOR_SLOT_COUNT = 4;
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT + ARMOR_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    // THIS YOU HAVE TO DEFINE!

    private static final int TE_INVENTORY_SLOT_COUNT = 9;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            getUpgrades(sourceStack);
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            modifyItem(sourceStack);
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.ARMOR_MODIFIER_BLOCK.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private void addPlayerArmor(Inventory playerInventory) {
        for (int i = 0; i < 4; i++) {
            this.addSlot(new ArmorSlot(playerInventory, VANILLA_SLOT_COUNT - ARMOR_SLOT_COUNT + i, -15, 138 + i * -18, i));
        }
    }
}
