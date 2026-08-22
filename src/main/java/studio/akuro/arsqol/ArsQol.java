package studio.akuro.arsqol;

import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import studio.akuro.arsqol.client.SpellBookCurioRenderer;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(ArsQol.MOD_ID)
public class ArsQol {
    public static final String MOD_ID = "ars_qol";

    public ArsQol(ModContainer container, IEventBus modBus) {
        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CauldronInteraction.WATER.map().put(ItemsRegistry.NOVICE_SPELLBOOK.get(), CauldronInteraction.DYED_ITEM);
            CauldronInteraction.WATER.map().put(ItemsRegistry.APPRENTICE_SPELLBOOK.get(), CauldronInteraction.DYED_ITEM);
            CauldronInteraction.WATER.map().put(ItemsRegistry.ARCHMAGE_SPELLBOOK.get(), CauldronInteraction.DYED_ITEM);
            CauldronInteraction.WATER.map().put(ItemsRegistry.CREATIVE_SPELLBOOK.get(), CauldronInteraction.DYED_ITEM);
            CuriosRendererRegistry.register(ItemsRegistry.NOVICE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
            CuriosRendererRegistry.register(ItemsRegistry.APPRENTICE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
            CuriosRendererRegistry.register(ItemsRegistry.ARCHMAGE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
            CuriosRendererRegistry.register(ItemsRegistry.CREATIVE_SPELLBOOK.get(), SpellBookCurioRenderer::new);
        });
    }

}
