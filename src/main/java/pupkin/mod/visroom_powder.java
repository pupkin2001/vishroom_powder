package pupkin.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import pupkin.mod.init.RecipesInit;
import pupkin.mod.proxy.CommonProxy;
import pupkin.mod.util.Reference;

@Mod(useMetadata = true, modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class visroom_powder
{
	@Instance(Reference.MOD_ID)
	public static visroom_powder instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModConfiguration.WorldGenerationConfig();
		RecipesInit.init();
	}
}
