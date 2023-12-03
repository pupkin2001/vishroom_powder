package pupkin.mod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import pupkin.mod.proxy.CommonProxy;
import pupkin.mod.util.Reference;

@Mod(useMetadata = true, modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class VisroomPowder
{
	@Instance(Reference.MOD_ID)
	public static VisroomPowder instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
}
