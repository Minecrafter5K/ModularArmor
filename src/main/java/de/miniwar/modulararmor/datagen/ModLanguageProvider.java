package de.miniwar.modulararmor.datagen;

import de.miniwar.modulararmor.ModularArmor;
import de.miniwar.modulararmor.block.ModBlocks;
import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, ModularArmor.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        addBlock(ModBlocks.ARMOR_MODIFIER_BLOCK, "Armor Modifier");

        addItem(ModItems.MODULAR_HELMET, "Modular Helmet");
        addItem(ModItems.MODULAR_CHESTPLATE, "Modular Chestplate");
        addItem(ModItems.MODULAR_LEGGINGS, "Modular Leggings");
        addItem(ModItems.MODULAR_BOOTS, "Modular Boots");

        addItem(ModItems.ELYTRA_UPGRADE, "Elytra Upgrade");
        addItem(ModItems.PIGLIN_UPGRADE, "Piglin Upgrade");
        addItem(ModItems.SNOW_WALKER_UPGRADE, "Snow Walker Upgrade");
        addItem(ModItems.SPEED_UPGRADE, "Speed Upgrade");

        addItem(ModItems.TEST_ITEM, "Test Item");
        addItem(ModItems.BASIC_BATTERY, "Basic Battery");
    }
}
