package assets.quidcraft.entities;

import assets.quidcraft.Quidcraft;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityQuaffle extends Entity {
    int ticksInAir;
    public static double bounceFactor = 0.4D;
    public static double force = 0.5D;
    boolean stopped;
	public EntityQuaffle(World world) {
		super(world);
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
		yOffset = height / 3.0F;
		stopped = false;
	}

	public EntityQuaffle(World world, EntityLivingBase entity) {
		this(world);
		double x = entity.posX;
		double y = entity.posY + entity.getEyeHeight();
		double z = entity.posZ;
		float yaw = entity.rotationYaw;
		float pitch = entity.rotationPitch;
		setRotation(yaw, 0);
		double xHeading = -MathHelper.sin((yaw * 3.141593F) / 180F);
		double zHeading = MathHelper.cos((yaw * 3.141593F) / 180F);
		motionX = /* entity.motionX + */2 * force * xHeading * MathHelper.cos((pitch / 180F) * 3.141593F);
		motionY = /* entity.motionY + */-1 * force * MathHelper.sin((pitch / 180F) * 3.141593F);
		motionZ = /* entity.motionZ + */2 * force * zHeading * MathHelper.cos((pitch / 180F) * 3.141593F);
		setPosition(x + xHeading * 0.2, y, z + zHeading * 0.2);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}

	public EntityQuaffle(World world, double i, double j, double k) {
		this(world);
		//setRotation(yaw, 0);
		//double xHeading = -MathHelper.sin((yaw * 3.141593F) / 180F);
		//double zHeading = MathHelper.cos((yaw * 3.141593F) / 180F);
		//motionX = /*entity.motionX + */2*force*xHeading*MathHelper.cos((pitch / 180F) * 3.141593F);
		//motionY = /*entity.motionY + */-1*force*MathHelper.sin((pitch / 180F) * 3.141593F);
		//motionZ = /*entity.motionZ + */2*force*zHeading*MathHelper.cos((pitch / 180F) * 3.141593F);
		//setPosition(i,j,k);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public void onUpdate() {
		if (ticksInAir < 10)
			ticksInAir++;
		if (!stopped) {
			double prevVelX = motionX;
			double prevVelY = motionY;
			double prevVelZ = motionZ;
			prevPosX = posX;
			prevPosY = posY;
			prevPosZ = posZ;
			boolean inNet = (worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)).getMaterial() == Material.portal);
			boolean willBeInNet = (worldObj.getBlock(MathHelper.floor_double(posX + motionX), MathHelper.floor_double(posY + motionY), MathHelper.floor_double(posZ + motionZ)).getMaterial() == Material.portal);
			/*
			 * if(inNet || willBeInNet) isInWeb = true;
			 */
			moveEntity(motionX, motionY, motionZ);
			if (!worldObj.isRemote) {
				boolean collided = false;
				//boolean inNet = (worldObj.getBlockMaterial(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) == Material.portal);
				if (motionX != 0) {
					/*
					 * if(inNet){ motionX = 0; } else if(willBeInNet){ posX +=
					 * motionX; motionX = 0; }
					 */
					if (motionX != prevVelX && !inNet) {
						motionX = -prevVelX;
						collided = true;
					} else if (onGround && !inNet) {
						if (motionX < 0) {
							motionX += 0.01;
							if (motionX > 0)
								motionX = 0;
						} else if (motionX > 0) {
							motionX -= 0.01;
							if (motionX < 0)
								motionX = 0;
						}
					}
				}
				if (motionZ != 0) {
					/*
					 * if(inNet){ motionZ = 0; } else if(willBeInNet){ posZ +=
					 * motionZ; motionZ = 0; }
					 */
					if (motionZ != prevVelZ && !inNet) {
						motionZ = -prevVelZ;
						collided = true;
					} else if (onGround && !inNet) {
						if (motionZ < 0) {
							motionZ += 0.01;
							if (motionZ > 0)
								motionZ = 0;
						} else if (motionZ > 0) {
							motionZ -= 0.01;
							if (motionZ < 0)
								motionZ = 0;
						}
					}
				}
				if (!onGround) {
					/*
					 * if(inNet){ motionY = 0; } else if(willBeInNet){ posY +=
					 * motionY; motionY = 0; }
					 */
					if (motionY != prevVelY && !inNet) {
						motionY = -prevVelY;
						collided = true;
					} else if (!inNet) {
						motionY -= 0.02;
					}
				}
				if (onGround && !inNet && !willBeInNet) {
					motionX *= 0.25;
					motionZ *= 0.25;
				}
				if (collided && !inNet && !willBeInNet) {
					motionX *= bounceFactor;
					motionY *= 0.75 * bounceFactor;
					motionZ *= bounceFactor;
				}
				/*
				 * if(inNet){ motionX = 0; motionY = 0; motionZ = 0; }
				 * if(willBeInNet){ posX = posX + motionX; posY = posY +
				 * motionY; posZ = posZ + motionZ; motionX = 0; motionY = 0;
				 * motionZ = 0; }
				 */
				//motionX *= 0.99;
				//motionY *= 0.99;
				//motionZ *= 0.99;
				if (onGround && (motionX * motionX + motionY * motionY + motionZ * motionZ) < 0.02) {
					stopped = true;
					motionX = 0;
					motionY = 0;
					motionZ = 0;
				}
			}
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if (!worldObj.isRemote) {
			if (ticksInAir > 3 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Quidcraft.proxy.Quaffle, 1))) {
				worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				entityplayer.onItemPickup(this, 1);
				setDead();
			}
		}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
	}

}