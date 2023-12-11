package pupkin.mod.item;

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
import java.util.List;

public class ItemEdibleWithEffects extends ItemFood implements IHasModel
{
	private final List<PotionEffect> effects;

	public ItemEdibleWithEffects(String name, CreativeTabs tab, int amount, float saturation, boolean isWolfFood, List<PotionEffect> effects)
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(Integer.MAX_VALUE);

		ItemInit.ITEMS.add(this);
		this.effects = effects;
	}

	@Override
	protected void onFoodEaten(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityPlayer player)
	{
		applyCustomEffects(player);
		super.onFoodEaten(stack, worldIn, player);
	}

	private void applyCustomEffects(EntityLivingBase entity)
	{
		if (!entity.world.isRemote) {
			for (PotionEffect effect : effects) {
				entity.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier()));
			}
		}
	}

	@Override
	public void registerModels()
	{
		VishroomPowder.proxy.registerModel(this, 0);
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		playerIn.setActiveHand(handIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
