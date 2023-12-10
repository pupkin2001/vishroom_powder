package pupkin.mod.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import pupkin.mod.potion.ItemVishroomPowder;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
	public static final List<Item> ITEMS = new ArrayList<>();

	public static final Item VISROOM_POWDER = new ItemVishroomPowder("visroom_powder", CreativeTabs.FOOD, 1, 1, false);
}
