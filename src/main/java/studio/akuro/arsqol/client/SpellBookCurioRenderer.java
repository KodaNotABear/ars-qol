package studio.akuro.arsqol.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class SpellBookCurioRenderer implements ICurioRenderer {

    private static final ClosedSpellBookRenderer INSTANCE = new ClosedSpellBookRenderer();

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (!(renderLayerParent.getModel() instanceof HumanoidModel<?> model)) return;

        matrixStack.pushPose();
        model.body.translateAndRotate(matrixStack);

        matrixStack.translate(-0.5,0.9,-0.4);
        matrixStack.mulPose(Axis.ZP.rotationDegrees(5));
        matrixStack.mulPose(Axis.XP.rotationDegrees(90));
        matrixStack.scale(0.4f,0.4f,0.4f);

        INSTANCE.renderByItem(stack, ItemDisplayContext.FIXED, matrixStack, renderTypeBuffer, light, OverlayTexture.NO_OVERLAY);

        matrixStack.popPose();

    }
}
