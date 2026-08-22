package studio.akuro.arsqol.common;

import com.hollingsworth.arsnouveau.common.items.SpellBook;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public class CuriosBookUtil {

    public static ItemStack findBook(LivingEntity entity) {
        ItemStack book = CuriosApi.getCuriosInventory(entity)
                .flatMap(inv -> inv.findFirstCurio(s -> s.getItem() instanceof SpellBook))
                .map(SlotResult::stack)
                .orElse(ItemStack.EMPTY);
        return book;
    }
}
