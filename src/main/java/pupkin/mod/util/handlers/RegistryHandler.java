package pupkin.mod.util.handlers;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pupkin.mod.init.ItemInit;
import pupkin.mod.potion.YellowTintEffect;
import pupkin.mod.util.interfaces.IHasModel;

@EventBusSubscriber
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
		for (Item item : ItemInit.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(
				YellowTintEffect.YELLOW_TINT
		);
	}
}
