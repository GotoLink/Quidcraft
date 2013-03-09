package justintib.quidcraft;

import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class QuidcraftClientProxy extends QuidcraftCommonProxy{
	@Override
	public void preInit() 
	{
		tabQuidditch=new QuidcraftCreativeTab("Quidditch").getTabIndex();
		MinecraftForgeClient.preloadTexture("/justintib/quidcraft/items.png");
		//Localize input names
		 ModLoader.addLocalization("key.broomUp", "Broom Up");
        ModLoader.addLocalization("key.broomDown", "Broom Down"); 
        //ModLoader.AddLocalization("key.broomMouseKey", "Broom Follow Mouse"); 
        //Registering keybindings
        KeyBindingRegistry.registerKeyBinding(new QuidcraftKeyHandler());
	}
	@Override
	public void registerRenderThings() 
	{
		
		RenderingRegistry.registerEntityRenderingHandler(EntityQuaffle.class, new RenderQuaffle(new ModelQuaffle(),0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBludger.class, new RenderBludger(new ModelBludger(),0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySnitch.class, new RenderSnitch(new ModelSnitch(),0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, new RenderBroom(new ModelBroom(),0.2F));
		jerseySkin = RenderingRegistry.addNewArmourRendererPrefix("jersey");
		
		headbandSkin = RenderingRegistry.addNewArmourRendererPrefix("headband");
		
	}
}
