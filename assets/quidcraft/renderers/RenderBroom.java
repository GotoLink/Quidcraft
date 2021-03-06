package assets.quidcraft.renderers;

import assets.quidcraft.entities.EntityBroom;
import assets.quidcraft.models.ModelBroom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public final class RenderBroom extends Render {
    private final static ResourceLocation broom = new ResourceLocation("quidcraft", "textures/models/BroomSkin.png");
    protected final ModelBase modelBroom;
	public RenderBroom() {
		this.shadowSize = 0.2F;
		this.modelBroom = new ModelBroom();
	}

	public void renderBroom(EntityBroom entitybroom, double d, double d1, double d2, float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1 + 1.0F, (float) d2);
		GL11.glRotatef(270F - f, 0.0F, 1.0F, 0.0F);
		float f2 = entitybroom.getTimeSinceHit() - f1;
		float f3 = entitybroom.getDamageTaken() - f1;
		if (f3 < 0.0F) {
			f3 = 0.0F;
		}
		if (f2 > 0.0F) {
			GL11.glRotatef(((MathHelper.sin(f2) * f2 * f3) / 10F) * entitybroom.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}
		//this.loadTexture("/terrain.png");
		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		bindEntityTexture(entitybroom);
		//this.loadTexture("/mods/quidcraft/textures/models/BroomSkin.png");
		GL11.glScalef(-1F, -1F, 1.0F);
		modelBroom.render(entitybroom, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		this.renderBroom((EntityBroom) entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return broom;
	}
}
