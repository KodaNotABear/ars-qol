package studio.akuro.arsqol;

import com.hollingsworth.arsnouveau.common.items.SpellBook;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import studio.akuro.arsqol.common.CuriosBookUtil;
import top.theillusivec4.curios.api.CuriosApi;

@Mod(ArsQol.MOD_ID)
public class ArsQol {
    public static final String MOD_ID = "ars_qol";

    public ArsQol(ModContainer container, IEventBus modBus) {
        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            for (Item book : CuriosBookUtil.spellBookItems()) {
                CauldronInteraction.WATER.map().put(book, CauldronInteraction.DYED_ITEM);
            }

            CuriosApi.registerCurioPredicate(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "spellbook"),
                    slotResult -> slotResult.stack().getItem() instanceof SpellBook);
        });
    }

}
