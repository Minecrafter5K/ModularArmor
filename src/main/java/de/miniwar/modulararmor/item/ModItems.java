package de.miniwar.modulararmor.item;

import de.miniwar.modulararmor.ModularArmor;
import de.miniwar.modulararmor.item.custom.BasicBatteryItem;
import de.miniwar.modulararmor.item.custom.ModularArmorItem;
import de.miniwar.modulararmor.item.custom.UpgradeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModularArmor.MOD_ID);

    // MODULAR ARMOR
    public static final RegistryObject<Item> MODULAR_HELMET = ITEMS.register("modular_helmet",
            ModularArmorHelmetItem::new);

    public static final RegistryObject<Item> MODULAR_CHESTPLATE = ITEMS.register("modular_chestplate",
            ModularArmorChestplateItem::new);

    public static final RegistryObject<Item> MODULAR_LEGGINGS = ITEMS.register("modular_leggings",
            ModularArmorLegginsItem::new);

    public static final RegistryObject<Item> MODULAR_BOOTS = ITEMS.register("modular_boots",
            ModularArmorBootsItem::new);

    // UPGRADES
    public static final RegistryObject<Item> ELYTRA_UPGRADE = ITEMS.register("elytra_upgrade",
            () -> new UpgradeItem(ModularArmorChestplateItem.class));

    public static final RegistryObject<Item> PIGLIN_UPGRADE = ITEMS.register("piglin_upgrade",
            () -> new UpgradeItem());

    public static final RegistryObject<Item> SPEED_UPGRADE = ITEMS.register("speed_upgrade",
            () -> new UpgradeItem(ModularArmorLegginsItem.class));

    public static final RegistryObject<Item> SNOW_WALKER_UPGRADE = ITEMS.register("snow_walker_upgrade",
            () -> new UpgradeItem(ModularArmorBootsItem.class));

    // OTHER STUFF
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BASIC_BATTERY = ITEMS.register("basic_battery",
            () -> new BasicBatteryItem(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



    // MODULAR ARMOR
    public static class ModularArmorHelmetItem extends ModularArmorItem {
        public ModularArmorHelmetItem() {
            super(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties());
        }
    }
    public static class ModularArmorChestplateItem extends ModularArmorItem {
        public ModularArmorChestplateItem() {
            super(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties());
        }
    }
    public static class ModularArmorLegginsItem extends ModularArmorItem {
        public ModularArmorLegginsItem() {
            super(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties());
        }
    }
    public static class ModularArmorBootsItem extends ModularArmorItem {
        public ModularArmorBootsItem() {
            super(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties());
        }
    }
}