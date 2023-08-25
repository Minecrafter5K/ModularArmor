package de.miniwar.modulararmor.datagen;

import de.miniwar.modulararmor.ModularArmor;
import de.miniwar.modulararmor.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModularArmor.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockItem(ModBlocks.ARMOR_MODIFIER_BLOCK.get(), cubeAll(ModBlocks.ARMOR_MODIFIER_BLOCK.get()));
        simpleBlock(ModBlocks.ARMOR_MODIFIER_BLOCK.get());
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
