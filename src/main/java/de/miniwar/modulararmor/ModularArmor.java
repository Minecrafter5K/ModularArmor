package de.miniwar.modulararmor;

import com.mojang.logging.LogUtils;
import de.miniwar.modulararmor.block.ModBlocks;
import de.miniwar.modulararmor.block.entity.ModBlockEntites;
import de.miniwar.modulararmor.gui.screen.ArmorModifierScreen;
import de.miniwar.modulararmor.gui.screen.ModMenuTypes;
import de.miniwar.modulararmor.item.ModItems;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModularArmor.MOD_ID)
public class ModularArmor {
    public static final String MOD_ID = "modulararmor";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModularArmor() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntites.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.MODULAR_HELMET);
            event.accept(ModItems.MODULAR_CHESTPLATE);
            event.accept(ModItems.MODULAR_LEGGINGS);
            event.accept(ModItems.MODULAR_BOOTS);

            event.accept(ModItems.ELYTRA_UPGRADE);
            event.accept(ModItems.PIGLIN_UPGRADE);
            event.accept(ModItems.SPEED_UPGRADE);
            event.accept(ModItems.SNOW_WALKER_UPGRADE);

            event.accept(ModItems.TEST_ITEM);
            event.accept(ModItems.BASIC_BATTERY);
        }
        if (event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.ARMOR_MODIFIER_BLOCK);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ARMOR_MODIFIER_MENU.get(), ArmorModifierScreen::new);
        }
    }
}
