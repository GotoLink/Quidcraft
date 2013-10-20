package assets.quidcraft.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import assets.quidcraft.entities.EntityBludger;
import assets.quidcraft.models.ModelBludger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBludger extends Render {
	public static final ResourceLocation bludger = new ResourceLocation("quidcraft", "textures/models/BludgerSkin.png");

	public RenderBludger(ModelBludger model, float f) {
		this.shadowSize = f;
		this.modelBludger = model;
	}

	public void renderNew(EntityBludger entitybludger, double d, double d1, double d2, float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		float yaw = entitybludger.rotationYaw;
		float pitch = entitybludger.rotationPitch;
		GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(pitch, 0.0F, 0.0F, 1.0F);
		bindEntityTexture(entitybludger);
		GL11.glScalef(-1F, -1F, 1.0F);
		modelBludger.render(entitybludger, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderNew((EntityBludger) entity, d, d1, d2, f, f1);
	}

	protected ModelBase modelBludger;

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return bludger;
	}
}
