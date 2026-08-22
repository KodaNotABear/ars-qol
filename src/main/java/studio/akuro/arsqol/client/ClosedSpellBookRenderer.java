package studio.akuro.arsqol.client;

import com.hollingsworth.arsnouveau.client.renderer.item.SpellBookRenderer;
import com.hollingsworth.arsnouveau.common.items.SpellBook;
import software.bernie.geckolib.model.GeoModel;

public class ClosedSpellBookRenderer extends SpellBookRenderer {
    @Override
    public GeoModel<SpellBook> getGeoModel() {return closedModel;}
}
