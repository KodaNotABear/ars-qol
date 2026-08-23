package studio.akuro.arsqol.client;


import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import studio.akuro.arsqol.ArsQol;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@EventBusSubscriber(modid = ArsQol.MOD_ID, value = Dist.CLIENT)

public class ArsQolClient {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosRendererRegistry.register(ItemsRegistry.NOVICE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
            CuriosRendererRegistry.register(ItemsRegistry.APPRENTICE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
            CuriosRendererRegistry.register(ItemsRegistry.ARCHMAGE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
            CuriosRendererRegistry.register(ItemsRegistry.CREATIVE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
        });
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CROSSHAIR, ResourceLocation.fromNamespaceAndPath(ArsQol.MOD_ID, "curios_spell_hud"), CuriosHUD.OVERLAY);
    }
}
