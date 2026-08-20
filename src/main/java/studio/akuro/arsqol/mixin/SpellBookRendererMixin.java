package studio.akuro.arsqol.mixin;

import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.client.renderer.item.SpellBookRenderer;
import com.hollingsworth.arsnouveau.common.items.SpellBook;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.Color;
import studio.akuro.arsqol.client.SpellBookGoldLayer;

import static studio.akuro.arsqol.client.DyedSpellBooks.DYED_TEXTURE;

@Mixin(SpellBookRenderer.class)
public abstract class SpellBookRendererMixin extends GeoItemRenderer<SpellBook> {

    protected SpellBookRendererMixin(GeoModel<SpellBook> model) {
        super(model);
    }

    //Taking over the spellbook renderer
    @Inject(method = "getTextureLocation(Lcom/hollingsworth/arsnouveau/common/items/SpellBook;)Lnet/minecraft/resources/ResourceLocation;",
        at = @At("HEAD"), remap = false, cancellable = true)
    private void arsqol$dyedTexture(SpellBook book, CallbackInfoReturnable<ResourceLocation> cir) {

        if (isDyed()) cir.setReturnValue(DYED_TEXTURE);

    }

    @Inject(method = "<init>()V", at = @At("TAIL"), remap = false)
    private void arsqol$addGoldLayer(CallbackInfo ci) {
        addRenderLayer(new SpellBookGoldLayer(this));
    }

    @Override
    public Color getRenderColor(SpellBook animatable, float partialTick, int packedLight) {
        if (isDyed()) {
            return Color.ofOpaque(currentItemStack.get(DataComponents.DYED_COLOR).rgb());
        }
        return super.getRenderColor(animatable, partialTick, packedLight);
    }

    @Unique
    private boolean isDyed() {
        return currentItemStack != null && currentItemStack.has(DataComponents.DYED_COLOR); //Prevents NPE
    }
}
