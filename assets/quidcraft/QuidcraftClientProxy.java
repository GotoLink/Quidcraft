package assets.quidcraft;

import assets.quidcraft.entities.EntityBludger;
import assets.quidcraft.entities.EntityBroom;
import assets.quidcraft.entities.EntityQuaffle;
import assets.quidcraft.entities.EntitySnitch;
import assets.quidcraft.models.ModelBludger;
import assets.quidcraft.models.ModelBroom;
import assets.quidcraft.models.ModelQuaffle;
import assets.quidcraft.models.ModelSnitch;
import assets.quidcraft.renderers.RenderBludger;
import assets.quidcraft.renderers.RenderBroom;
import assets.quidcraft.renderers.RenderQuaffle;
import assets.quidcraft.renderers.RenderSnitch;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class QuidcraftClientProxy extends QuidcraftCommonProxy {
	@Override
	public void preInit() {
		tabQuidditch = new QuidcraftCreativeTab("Quidditch").getTabIndex();
		KeyBindingRegistry.registerKeyBinding(new QuidcraftKeyHandler(Quidcraft.KEY_UP, Quidcraft.KEY_DOWN));
	}

	@Override
	public void registerRenderThings() {
		RenderingRegistry.registerEntityRenderingHandler(EntityQuaffle.class, new RenderQuaffle(new ModelQuaffle(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBludger.class, new RenderBludger(new ModelBludger(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySnitch.class, new RenderSnitch(new ModelSnitch(), 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, new RenderBroom(new ModelBroom(), 0.2F));
	}
}
