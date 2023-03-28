package de.miniwar.modulararmor.item;

import de.miniwar.modulararmor.ModularArmor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModularArmor.MOD_ID);

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MODULAR_HELMET = ITEMS.register("modular_helmet",
            () -> new ArmorItem(ModArmorMaterials.MODULAR, ArmorItem.Type.HELMET,
                    new Item.Properties()));
    public static final RegistryObject<Item> MODULAR_CHESTPLATE = ITEMS.register("modular_chestplate",
            () -> new ArmorItem(ModArmorMaterials.MODULAR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryObject<Item> MODULAR_LEGGINGS = ITEMS.register("modular_leggings",
            () -> new ArmorItem(ModArmorMaterials.MODULAR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryObject<Item> MODULAR_BOOTS = ITEMS.register("modular_boots",
            () -> new ArmorItem(ModArmorMaterials.MODULAR, ArmorItem.Type.BOOTS,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
