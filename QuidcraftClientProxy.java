package mods.quidcraft;

import mods.quidcraft.entities.EntityBludger;
import mods.quidcraft.entities.EntityBroom;
import mods.quidcraft.entities.EntityQuaffle;
import mods.quidcraft.entities.EntitySnitch;
import mods.quidcraft.models.ModelBludger;
import mods.quidcraft.models.ModelBroom;
import mods.quidcraft.models.ModelQuaffle;
import mods.quidcraft.models.ModelSnitch;
import mods.quidcraft.renderers.RenderBludger;
import mods.quidcraft.renderers.RenderBroom;
import mods.quidcraft.renderers.RenderQuaffle;
import mods.quidcraft.renderers.RenderSnitch;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class QuidcraftClientProxy extends QuidcraftCommonProxy{
	@Override
	public void preInit() 
	{
		tabQuidditch=new QuidcraftCreativeTab("Quidditch").getTabIndex();
		KeyBindingRegistry.registerKeyBinding(new QuidcraftKeyHandler(Quidcraft.instance.KEY_UP,Quidcraft.instance.KEY_DOWN));
	}
	@Override
	public void registerRenderThings() 
	{		
		RenderingRegistry.registerEntityRenderingHandler(EntityQuaffle.class, new RenderQuaffle(new ModelQuaffle(),0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBludger.class, new RenderBludger(new ModelBludger(),0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySnitch.class, new RenderSnitch(new ModelSnitch(),0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, new RenderBroom(new ModelBroom(),0.2F));
	}
}
