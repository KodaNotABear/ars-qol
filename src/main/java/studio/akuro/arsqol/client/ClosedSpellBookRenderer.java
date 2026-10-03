package studio.akuro.arsqol.client;

import com.hollingsworth.arsnouveau.client.renderer.item.SpellBookRenderer;
import com.hollingsworth.arsnouveau.common.items.SpellBook;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class ClosedSpellBookRenderer extends SpellBookRenderer {

    @Override
    public GeoModel<SpellBook> getGeoModel() {return closedModel;}

    @Override
    public ResourceLocation getTextureLocation(SpellBook book) {
        if (DyedSpellBooks.isDyed(this.currentItemStack)) return super.getTextureLocation(book);

        ResourceLocation texture = ownTexture(book);
        return texture != null ? texture : super.getTextureLocation(book);
    }

    private static ResourceLocation ownTexture(SpellBook book) {
        if (!(GeoRenderProvider.of(book).getGeoItemRenderer() instanceof GeoRenderer renderer)) return null;
        GeoModel model = renderer.getGeoModel();
        return model == null ? null : model.getTextureResource(book);
    }
}
