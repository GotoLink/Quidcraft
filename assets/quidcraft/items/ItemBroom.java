package assets.quidcraft.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.quidcraft.entities.EntityBroom;

public class ItemBroom extends Item {
	public ItemBroom() {
		super();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		float var5 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch);
		float var6 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw);
		double var7 = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX);
		double var9 = entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) + 1.62D - entityplayer.yOffset;
		double var11 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ);
		Vec3 var13 = Vec3.createVectorHelper(var7, var9, var11);
		float var14 = MathHelper.cos(-var6 * 0.017453292F - (float) Math.PI);
		float var15 = MathHelper.sin(-var6 * 0.017453292F - (float) Math.PI);
		float var16 = -MathHelper.cos(-var5 * 0.017453292F);
		float var17 = MathHelper.sin(-var5 * 0.017453292F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 5.0D;
		Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
		MovingObjectPosition var24 = world.rayTraceBlocks(var13, var23, true);
		if (var24 == null) {
			return itemstack;
		} else {
			Vec3 var25 = entityplayer.getLook(1.0F);
			boolean var26 = false;
			List<?> var28 = world.getEntitiesWithinAABBExcludingEntity(entityplayer,
					entityplayer.boundingBox.addCoord(var25.xCoord * var21, var25.yCoord * var21, var25.zCoord * var21).expand(1.0D, 1.0D, 1.0D));
			int var29;
			for (var29 = 0; var29 < var28.size(); ++var29) {
				Entity var30 = (Entity) var28.get(var29);
				if (var30.canBeCollidedWith()) {
					float var31 = var30.getCollisionBorderSize();
					AxisAlignedBB var32 = var30.boundingBox.expand(var31, var31, var31);
					if (var32.isVecInside(var13)) {
						var26 = true;
					}
				}
			}
			if (var26) {
				return itemstack;
			} else {
				if (var24.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					var29 = var24.blockX;
					int var33 = var24.blockY;
					int var34 = var24.blockZ;
					if (world.getBlock(var29, var33, var34) == Blocks.snow) {
						--var33;
					}
					EntityBroom var35 = new EntityBroom(world, var29 + 0.5F, var33 + 1.0F, var34 + 0.5F);
					var35.rotationYaw = ((MathHelper.floor_double(entityplayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3) - 1) * 90;
					if (!world.getCollidingBoundingBoxes(var35, var35.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty()) {
						return itemstack;
					}
					if (!world.isRemote) {
						world.spawnEntityInWorld(var35);
					}
					if (!entityplayer.capabilities.isCreativeMode) {
						--itemstack.stackSize;
					}
				}
				return itemstack;
			}
		}
	}
}
