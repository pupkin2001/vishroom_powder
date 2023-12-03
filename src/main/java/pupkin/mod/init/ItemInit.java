package pupkin.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import pupkin.mod.item.ItemBase;

public class ItemInit
{
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static final Item VISROOM_POWDER = new ItemBase("visroom_powder", CreativeTabs.MISC);
}
