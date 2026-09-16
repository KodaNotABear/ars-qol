package studio.akuro.arsqol.mixin;

import com.hollingsworth.arsnouveau.api.item.IRadialProvider;
import com.hollingsworth.arsnouveau.api.util.StackUtil;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import studio.akuro.arsqol.common.CuriosBookUtil;

@Mixin(StackUtil.class)
public class StackUtilMixin {

    @ModifyReturnValue(method = "getHeldRadial", remap = false, at = @At("RETURN"))
    private static ItemStack arsqol$curiosRadial(ItemStack stack, Player player) {
        if (stack.getItem() instanceof IRadialProvider) return stack;
        return CuriosBookUtil.findBook(player);
    }
}
