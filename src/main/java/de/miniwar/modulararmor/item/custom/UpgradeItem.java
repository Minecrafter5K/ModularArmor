package de.miniwar.modulararmor.item.custom;

import de.miniwar.modulararmor.item.IArmorUpgradeItems;
import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class UpgradeItem extends Item implements IArmorUpgradeItems {

    Class<? extends ModularArmorItem>[] applicableTo;

    public UpgradeItem(Properties props, Class<? extends ModularArmorItem>... t) {
        super(props);
        applicableTo = t;
    }
    public UpgradeItem(Class<? extends ModularArmorItem>... t) {
        super(new Item.Properties());
        applicableTo = t;
    }

    public UpgradeItem() {
        super(new Item.Properties());
        applicableTo = new Class[]{ModItems.ModularArmorHelmetItem.class, ModItems.ModularArmorChestplateItem.class, ModItems.ModularArmorLegginsItem.class, ModItems.ModularArmorBootsItem.class};
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean isApplicableTo(ModularArmorItem item) {
        for (Class<? extends ModularArmorItem> t : applicableTo) {
            if (t.isInstance(item)) {
                return true;
            }
        }
        return false;
    }
}
