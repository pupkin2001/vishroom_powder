package pupkin.mod.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import pupkin.mod.potion.ClairvoyanceEffect;
import pupkin.mod.potion.ItemVishroomPowder;
import pupkin.mod.potion.YellowTintEffect;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
	public static final List<Item> ITEMS = new ArrayList<>();

	private static final List<PotionEffect> vishroomPowderEffects = new ArrayList<>();
	public static final Item VISHROOM_POWDER = new ItemVishroomPowder("vishroom_powder", CreativeTabs.FOOD, 1, 1, false, vishroomPowderEffects);
	private static final List<PotionEffect> sweetSugarEffects = new ArrayList<>();
	public static final Item SWEET_SUGAR = new ItemVishroomPowder("sweet_sugar", CreativeTabs.FOOD, 0, 2, false, sweetSugarEffects);
	private static final List<PotionEffect> sweetSugarCubeEffects = new ArrayList<>();
	public static final Item SWEET_SUGAR_CUBE = new ItemVishroomPowder("sweet_sugar_cube", CreativeTabs.FOOD, 0, 4, false, sweetSugarCubeEffects);

	static {
		vishroomPowderEffects.add(new PotionEffect(YellowTintEffect.YELLOW_TINT, 1200, 0));
	}

	static {
		sweetSugarEffects.add(new PotionEffect(ClairvoyanceEffect.CLAIRVOYANCE, 600, 0));
	}

	static {
		sweetSugarCubeEffects.add(new PotionEffect(ClairvoyanceEffect.CLAIRVOYANCE, 1800, 1));
	}
}
