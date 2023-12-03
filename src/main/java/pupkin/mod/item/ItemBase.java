package pupkin.mod.item;

import net.minecraft.item.Item;
import pupkin.mod.visroom_powder;
import pupkin.mod.init.ItemInit;

public class ItemBase extends Item
{
	public ItemBase(String name, CreativeTabs tab)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);

		ItemInit.ITEMS.add(this);
	}
}
