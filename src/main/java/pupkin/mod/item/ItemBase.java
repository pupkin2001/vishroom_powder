package pupkin.mod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import pupkin.mod.VisroomPowder;
import pupkin.mod.init.ItemInit;
import pupkin.mod.util.interfaces.IHasModel;

public class ItemBase extends Item implements IHasModel
{
	public ItemBase(String name, CreativeTabs tab)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		VisroomPowder.proxy.registerModel(this, 0);
	}
}
