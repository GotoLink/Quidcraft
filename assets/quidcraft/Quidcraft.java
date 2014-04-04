package assets.quidcraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "quidcraft", name = "Quidcraft", useMetadata = true)
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
        if(event.getSourceFile().getName().endsWith(".jar") && event.getSide().isClient()){
            try {
                Class.forName("mods.mud.ModUpdateDetector").getDeclaredMethod("registerMod", ModContainer.class, String.class, String.class).invoke(null,
                        FMLCommonHandler.instance().findContainerFor(this),
                        "https://raw.github.com/GotoLink/Quidcraft/master/update.xml",
                        "https://raw.github.com/GotoLink/Quidcraft/master/changelog.md"
                );
            } catch (Throwable e) {
            }
        }
	}
}
