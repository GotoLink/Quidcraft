package assets.quidcraft.renderers;

import assets.quidcraft.entities.EntitySnitch;
import assets.quidcraft.models.ModelSnitch;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public final class RenderSnitch extends Render {
	protected final ModelSnitch modelSnitch;
	private final static ResourceLocation snitch = new ResourceLocation("quidcraft", "textures/models/SnitchSkin.png");

	public RenderSnitch() {
		this.shadowSize = 0.2F;
		this.modelSnitch = new ModelSnitch();
	}

	public void renderNew(EntitySnitch entitysnitch, double d, double d1, double d2, float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		float yaw = entitysnitch.rotationYaw;
		float pitch = entitysnitch.rotationPitch;
		GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(pitch, 0.0F, 0.0F, 1.0F);
		bindEntityTexture(entitysnitch);
		GL11.glScalef(-1F, -1F, 1.0F);
		modelSnitch.flap(entitysnitch.wingFlap);
		modelSnitch.render(entitysnitch, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderNew((EntitySnitch) entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return snitch;
	}
}
