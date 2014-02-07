package assets.quidcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "quidcraft", name = "Quidcraft", version = "0.2")
public class Quidcraft {
    @Mod.Instance(value="quidcraft")
    public static Quidcraft INSTANCE;
	@SidedProxy(clientSide = "assets.quidcraft.QuidcraftClientProxy", serverSide = "assets.quidcraft.QuidcraftCommonProxy")
	public static QuidcraftCommonProxy proxy;

	@EventHandler
	public void loadQuidditch(FMLInitializationEvent event) {
		proxy.registerRenderThings();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}
}
