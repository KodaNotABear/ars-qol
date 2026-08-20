package studio.akuro.arsqol.client;

import com.hollingsworth.arsnouveau.common.items.SpellBook;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.Color;

import static studio.akuro.arsqol.client.DyedSpellBooks.GOLD_TEXTURE;


public class SpellBookGoldLayer extends GeoRenderLayer<SpellBook> {

    public SpellBookGoldLayer(GeoRenderer<SpellBook> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, SpellBook animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer consumer, float partialTick, int packedLight, int packedOverlay) {
        RenderType overlay = RenderType.entityTranslucent(GOLD_TEXTURE);
        getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, overlay, bufferSource.getBuffer(overlay), partialTick, packedLight, packedOverlay, Color.WHITE.argbInt());
    }
}
