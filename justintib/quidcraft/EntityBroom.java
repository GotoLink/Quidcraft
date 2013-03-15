package justintib.quidcraft;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBroom extends Entity
{

    private int broomPosRotationIncrements;
    private double broomX,broomY,broomZ;
    private double broomYaw,broomPitch;
    boolean isGoingUp,isGoingDown;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;
    
    public EntityBroom(World world)
    {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(1.3F, 0.6F);
        this.yOffset = this.height / 2.0F;
    }
    
    public EntityBroom(World world, double d, double d1, double d2)
    {
        this(world);
        this.setPosition(d, d1 + (double)this.yOffset, d2);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = d;
        this.prevPosY = d1;
        this.prevPosZ = d2;
    }
    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
    	this.dataWatcher.addObject(17, new Integer(0));
    	this.dataWatcher.addObject(18, new Integer(1));
    	this.dataWatcher.addObject(19, new Integer(0));
    }
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }
    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }
    @Override
    public boolean canBePushed()
    {
        return true;
    }
    
    @Override
    public double getMountedYOffset()
    {
        return 0;
    }
    @Override
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
    	if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (!this.worldObj.isRemote && !this.isDead)
        {
        	this.setForwardDirection(-this.getForwardDirection());
        	this.setTimeSinceHit(10);
        	this.setDamageTaken(this.getDamageTaken() + i * 10);
        	this.setBeenAttacked();
        	if (damagesource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damagesource.getEntity()).capabilities.isCreativeMode)
            {
                this.setDamageTaken(100);
            }
        	if(this.getDamageTaken() > 40)
        	{
        		if(this.riddenByEntity != null)
        		{
            	this.riddenByEntity.mountEntity(this);
        		}
            this.dropItemWithOffset(Quidcraft.Broom.itemID, 1, 0.0F);
            this.setDead();
        	}
        return true;
        }
        else return true;
    }
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
    	this.setForwardDirection(-this.getForwardDirection());
    	this.setTimeSinceHit(10);
    	this.setDamageTaken(this.getDamageTaken() * 11);
    }
    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double d, double d1, double d2, float f,float f1, int i)
    {
        this.broomPosRotationIncrements = i + 10;
    	this.broomX = d;
        this.broomY = d1;
        this.broomZ = d2;
        this.broomYaw = f;
        this.broomPitch = f1;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }
    @SideOnly(Side.CLIENT)
    public void setVelocity(double d, double d1, double d2)
    {
    	this.velocityX = this.motionX = d;
    	this.velocityY = this.motionY = d1;
    	this.velocityZ = this.motionZ = d2;
    }
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if(this.getTimeSinceHit() > 0)
        {
        	this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }
        if(this.getDamageTaken() > 0)
        {
        	this.setDamageTaken(this.getDamageTaken() - 1);
        }
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        //int i = 5;
       
        double d1 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        
        if(this.worldObj.isRemote)
        {
            if(this.broomPosRotationIncrements > 0)
            {
                double d4 = this.posX + (this.broomX - this.posX) / (double)this.broomPosRotationIncrements;
                double d10 = this.posY + (this.broomY - this.posY) / (double)this.broomPosRotationIncrements;
                double d13 = this.posZ + (this.broomZ - this.posZ) / (double)this.broomPosRotationIncrements;
                double d17 = MathHelper.wrapAngleTo180_double(this.broomYaw - (double)this.rotationYaw);
                this.rotationYaw += (float)(d17 / (double)this.broomPosRotationIncrements);
                this.rotationPitch += (float)( (this.broomPitch - (double)this.rotationPitch) / (double)this.broomPosRotationIncrements);
                --this.broomPosRotationIncrements;
                this.setPosition(d4, d10, d13);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            } else
            {
                double d5 = this.posX + this.motionX;
                double d11 = this.posY + this.motionY;
                double d14 = this.posZ + this.motionZ;
                this.setPosition(d5, d11, d14);
                if(this.onGround)
                {
                	this.motionX *= 0.5D;
                	this.motionY *= 0.5D;
                	this.motionZ *= 0.5D;
                }
                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        }else
        {
        if( this.riddenByEntity != null)
        {
        	double riderX = riddenByEntity.motionX;
			double riderZ = riddenByEntity.motionZ;
			
			double acc = 0.05;
			
			double maxX = riderX*29.0D;
			double maxZ = riderZ*29.0D;
			
            //motionX += maxX * 0.060000000000000001D;
            //motionZ += maxZ * 0.060000000000000001D;
			if(maxX == 0)
				if (this.motionX > 0)
					this.motionX -= acc;
				else if (this.motionX < 0)
					this.motionX += acc;
			if(maxZ == 0)
            	if(this.motionZ > 0)
            		this.motionZ -= acc;   	
            	else if( this.motionZ < 0)
            		this.motionZ += acc;
            //keep within max speeds
            if(this.motionX > maxX && maxX<0)
            		this.motionX -= acc;
            else if (this.motionX < maxX && maxX>0)
            		this.motionX += acc;
            else if(Math.abs(this.motionX) > Math.abs(maxX))
            		this.motionX = maxX;
                  
            if(this.motionZ > maxZ && maxZ<0)
        		this.motionZ -= acc;
        else if (this.motionZ < maxZ && maxZ>0)
        		this.motionZ += acc;
        else if(Math.abs(this.motionZ) > Math.abs(maxZ))
        		this.motionZ = maxZ;
                 
          //move up if hitting wall
    		if(this.isCollidedHorizontally)
    			this.motionY += acc;
    		//move up/down		
    		if( Math.abs(this.motionY) <= 0.5)
    		{	
    			if (isGoingUp)
    				this.motionY += acc;
    			else if(!onGround && isGoingDown)
    				this.motionY -= acc;
    		}
			else
			{
				if(this.motionY < 0)
					this.motionY += acc;
				else if (this.motionY > 0)
					this.motionY -= acc;
			}
			//decrease y speed
			/*if(motionY > 0 && !isGoingUp && !isGoingDown && !isCollidedHorizontally){
				motionY -= acc;
				if(motionY < 0)
					motionY = 0;
			}
			if(motionY < 0 && !isGoingUp && !isGoingDown && !isCollidedHorizontally){
				motionY += acc;
				if(motionY > 0)
					motionY = 0;
			}*/
        }
        else if(!this.onGround)
        {
        	this.motionY -= 0.1;
        	if(this.motionY < -0.5D)
        		this.motionY = -0.5D;
        }
        
        else
        {
        	//slow down
        	this.motionX *= 0.5D;
        	this.motionZ *= 0.5D;
        }
        
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        if (this.isCollidedHorizontally && d1 > 1.0D)
        {
            if (!this.worldObj.isRemote)
            {
                this.setDead();
                this.dropItemWithOffset(Item.wheat.itemID, 1, 0.0F);
                this.dropItemWithOffset(Item.stick.itemID, 2, 0.0F);    
            }
        }
        this.rotationPitch = 0.0F;
        double rot = (double)this.rotationYaw;
        double difX = this.prevPosX - this.posX;
        double difZ = this.prevPosZ - this.posZ;

        if (difX * difX + difZ * difZ > 0.001D)
        {
            rot = (double)((float)(Math.atan2(difZ, difX) * 180.0D / Math.PI));
        }

        double var14 = MathHelper.wrapAngleTo180_double(rot - (double)this.rotationYaw);

        if (var14 > 20.0D)
        {
            var14 = 20.0D;
        }

        if (var14 < -20.0D)
        {
            var14 = -20.0D;
        }

        this.rotationYaw = (float)((double)this.rotationYaw + var14);
        this.setRotation(this.rotationYaw, this.rotationPitch);
        if(!worldObj.isRemote){
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if(list != null && !list.isEmpty() )
        {
            for(int j1 = 0; j1 < list.size(); ++j1)
            {
                Entity entity = (Entity)list.get(j1);
                if(entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBroom)
                {
                    entity.applyEntityCollision(this);
                }
            }
        }       
        if(this.riddenByEntity != null && this.riddenByEntity.isDead)
        {
        	this.riddenByEntity = null;
        }
        }      
        }
    }
  
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }
    @Override
    public boolean interact(EntityPlayer entityplayer)
    {
        if(this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != entityplayer)
        {
            return true;
        }
        else
        {
        if(!this.worldObj.isRemote)
        {
            entityplayer.mountEntity(this);
        }
        return true;
        }
    }
    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            double var1 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            double var3 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            this.riddenByEntity.setPosition(this.posX + var1, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
        }
    }
    public void setDamageTaken(int i)
    {
        dataWatcher.updateObject(19, Integer.valueOf(i));
    }

    public int getDamageTaken()
    {
        return dataWatcher.getWatchableObjectInt(19);
    }

    public void setTimeSinceHit(int i)
    {
        dataWatcher.updateObject(17, Integer.valueOf(i));
    }

    public int getTimeSinceHit()
    {
        return dataWatcher.getWatchableObjectInt(17);
    }

    public void setForwardDirection(int i)
    {
        dataWatcher.updateObject(18, Integer.valueOf(i));
    }

    public int getForwardDirection()
    {
        return dataWatcher.getWatchableObjectInt(18);
    }
}
