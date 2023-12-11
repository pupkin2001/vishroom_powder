package pupkin.mod.util.handlers;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import pupkin.mod.init.ItemInit;
import pupkin.mod.potion.ClairvoyanceEffect;
import pupkin.mod.potion.YellowTintEffect;
import pupkin.mod.util.Reference;
import pupkin.mod.util.interfaces.IHasModel;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryHandler
{
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		ItemInit.ITEMS.stream().filter(item -> item instanceof IHasModel).map(item -> (IHasModel) item).forEach(IHasModel::registerModels);
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event)
	{
		registerPotion(event.getRegistry(), YellowTintEffect.YELLOW_TINT);
		registerPotion(event.getRegistry(), ClairvoyanceEffect.CLAIRVOYANCE);
	}

	private static void registerPotion(IForgeRegistry<Potion> registry, Potion potion)
	{
		registry.register(potion);
	}
}
