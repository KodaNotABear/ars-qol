package studio.akuro.arsqol.client;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import studio.akuro.arsqol.ArsQol;
import studio.akuro.arsqol.common.CuriosBookUtil;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@EventBusSubscriber(modid = ArsQol.MOD_ID, value = Dist.CLIENT)

public class ArsQolClient {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (Item book : CuriosBookUtil.spellBookItems()) {
                CuriosRendererRegistry.register(book, SpellBookCurioRenderer::new);
            }
        });
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CROSSHAIR, ResourceLocation.fromNamespaceAndPath(ArsQol.MOD_ID, "curios_spell_hud"), CuriosHUD.OVERLAY);
    }
}
