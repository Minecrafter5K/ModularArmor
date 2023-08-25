package de.miniwar.modulararmor.datagen;

import de.miniwar.modulararmor.ModularArmor;
import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ModularArmor.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.MODULAR_HELMET);
        simpleItem(ModItems.MODULAR_CHESTPLATE);
        simpleItem(ModItems.MODULAR_LEGGINGS);
        simpleItem(ModItems.MODULAR_BOOTS);

        simpleItem(ModItems.ELYTRA_UPGRADE);
        simpleItem(ModItems.PIGLIN_UPGRADE);
        simpleItem(ModItems.SNOW_WALKER_UPGRADE);
//        simpleItem(ModItems.SOUL_SPEED_UPGRADE);
        simpleItem(ModItems.SPEED_UPGRADE);

        simpleItem(ModItems.TEST_ITEM);
        simpleItem(ModItems.BASIC_BATTERY);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(ModularArmor.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                        new ResourceLocation(ModularArmor.MOD_ID, "item/" + item.getId().getPath()));
    }
}
