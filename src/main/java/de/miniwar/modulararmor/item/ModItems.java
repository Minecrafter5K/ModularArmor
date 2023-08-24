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

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new UpgradeItem(new Item.Properties()));

    public static final RegistryObject<Item> MODULAR_HELMET = ITEMS.register("modular_helmet", () ->
            new ModularArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MODULAR_CHESTPLATE = ITEMS.register("modular_chestplate", () ->
            new ModularArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> MODULAR_LEGGINGS = ITEMS.register("modular_leggings", () ->
            new ModularArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> MODULAR_BOOTS = ITEMS.register("modular_boots", () ->
            new ModularArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> BASIC_BATTERY = ITEMS.register("basic_battery",
            () -> new BasicBatteryItem(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
