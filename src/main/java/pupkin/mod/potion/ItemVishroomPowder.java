package pupkin.mod.potion;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import pupkin.mod.VishroomPowder;
import pupkin.mod.init.ItemInit;
import pupkin.mod.util.interfaces.IHasModel;

import javax.annotation.Nonnull;

public class ItemVishroomPowder extends ItemFood implements IHasModel
{

	public ItemVishroomPowder(String name, CreativeTabs tab, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);

		ItemInit.ITEMS.add(this);
	}

	@Override
	protected void onFoodEaten(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityPlayer player)
	{
		applyCustomEffect(player);
		super.onFoodEaten(stack, worldIn, player);
	}

	@Override
	public void registerModels()
	{
		VishroomPowder.proxy.registerModel(this, 0);
	}

	private void applyCustomEffect(EntityLivingBase entity)
	{
		if (!entity.world.isRemote) {
			entity.addPotionEffect(new PotionEffect(YellowTintEffect.YELLOW_TINT, 200, 0));
		}
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
