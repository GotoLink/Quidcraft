package assets.quidcraft.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import assets.quidcraft.entities.EntitySnitch;
import assets.quidcraft.models.ModelSnitch;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class RenderSnitch extends Render {

	protected ModelSnitch modelSnitch;
	public RenderSnitch(ModelSnitch model, float f) {
		this.shadowSize =f;
		this.modelSnitch = model;
	}

	public void renderNew(EntitySnitch entitysnitch, double d, double d1,
			double d2, float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		
		float yaw = entitysnitch.rotationYaw;
	    float pitch = entitysnitch.rotationPitch;
	        
	    GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
	    GL11.glRotatef(pitch, 0.0F, 0.0F, 1.0F);
		
		//loadTexture("/mods/quidcraft/textures/models/SnitchSkin.png");
	    func_110777_b(entitysnitch);
		GL11.glScalef(-1F, -1F, 1.0F);
		modelSnitch.flap(entitysnitch.wingFlap);
		modelSnitch.render(entitysnitch, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1) {
		renderNew((EntitySnitch) entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation("quidcraft","/textures/models/SnitchSkin.png");
	}
}
