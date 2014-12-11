package assets.quidcraft.renderers;

import assets.quidcraft.entities.EntityQuaffle;
import assets.quidcraft.models.ModelQuaffle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public final class RenderQuaffle extends Render {
    protected final ModelBase modelQuaffle;
    private final static ResourceLocation quaffle = new ResourceLocation("quidcraft", "textures/models/QuaffleSkin.png");
	public RenderQuaffle() {
		this.shadowSize = 0.2F;
		this.modelQuaffle = new ModelQuaffle();
	}

	public void renderNew(EntityQuaffle entityquaffle, double d, double d1, double d2, float f, float f1) {
		float yaw = (float) (entityquaffle.rotationYaw * (-180) / 3.1415927410125732D);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glRotatef((-1) * yaw, 0.0F, 1.0F, 0.0F);
		bindEntityTexture(entityquaffle);
		GL11.glScalef(-1F, -1F, 1.0F);
		modelQuaffle.render(entityquaffle, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderNew((EntityQuaffle) entity, d, d1, d2, f, f1);
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return quaffle;
	}
}
