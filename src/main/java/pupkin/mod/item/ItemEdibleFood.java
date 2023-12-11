package pupkin.mod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import pupkin.mod.VishroomPowder;
import pupkin.mod.init.ItemInit;
import pupkin.mod.util.interfaces.IHasModel;

import javax.annotation.Nonnull;
import java.util.Random;

public class ItemEdibleFood extends ItemFood implements IHasModel
{

	public ItemEdibleFood(String name, CreativeTabs tab, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		VishroomPowder.proxy.registerModel(this, 0);
	}

	@Override
	protected void onFoodEaten(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityPlayer player)
	{
		applyRandomPotionEffect(player);
		super.onFoodEaten(stack, worldIn, player);
	}

	private void applyRandomPotionEffect(EntityLivingBase entity)
	{
		Random random = new Random();
		int randomEffect = random.nextInt(3);

		switch (randomEffect) {
		case 0:
			applyPotionEffect(entity, Potion.getPotionFromResourceLocation("speed"));
			break;
		case 1:
			applyPotionEffect(entity, Potion.getPotionFromResourceLocation("strength"));
			break;
		case 2:
			applyPotionEffect(entity, Potion.getPotionFromResourceLocation("jump_boost"));
			break;
		}
	}

	private void applyPotionEffect(EntityLivingBase entity, Potion potion)
	{
		if (potion != null) {
			entity.addPotionEffect(new PotionEffect(potion, 200, 1));
		}
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
