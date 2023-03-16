package de.miniwar.modulararmor.block.entity;

import de.miniwar.modulararmor.ModularArmor;
import de.miniwar.modulararmor.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntites {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModularArmor.MOD_ID);

    public static final RegistryObject<BlockEntityType<ArmorModifierBlockEntity>> ARMOR_MODIFIER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("armor_modifier", () -> BlockEntityType.Builder.of(ArmorModifierBlockEntity::new,
                    ModBlocks.ARMOR_MODIFIER_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
