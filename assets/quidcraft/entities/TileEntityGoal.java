package assets.quidcraft.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import assets.quidcraft.blocks.BlockGoal;

public class TileEntityGoal extends TileEntity {
	public TileEntityGoal() {
		goalTime = 0;
		neighborHasQuaffle = false;
		hasQuaffle = false;
		justLostQuaffle = false;
	}

	@Override
	public void func_145839_a(NBTTagCompound nbttagcompound) {
		super.func_145839_a(nbttagcompound);
		goalTime = nbttagcompound.getShort("GoalTime");
		neighborHasQuaffle = nbttagcompound.getBoolean("neighborHasQuaffle");
		hasQuaffle = nbttagcompound.getBoolean("hasQuaffle");
		justLostQuaffle = nbttagcompound.getBoolean("justLostQuaffle");
		maxGoalTime = nbttagcompound.getShort("MaxGoalTime");
		neighborX = nbttagcompound.getShort("NeighborX");
		neighborY = nbttagcompound.getShort("NeighborY");
		neighborZ = nbttagcompound.getShort("NeighborZ");
	}

	@Override
	public void func_145841_b(NBTTagCompound nbttagcompound) {
		super.func_145841_b(nbttagcompound);
		nbttagcompound.setShort("GoalTime", (short) goalTime);
		nbttagcompound.setBoolean("neighborHasQuaffle", neighborHasQuaffle);
		nbttagcompound.setBoolean("hasQuaffle", hasQuaffle);
		nbttagcompound.setBoolean("justLostQuaffle", justLostQuaffle);
		nbttagcompound.setShort("MaxGoalTime", (short) maxGoalTime);
		nbttagcompound.setShort("NeighborX", (short) neighborX);
		nbttagcompound.setShort("NeighborY", (short) neighborY);
		nbttagcompound.setShort("NeighborZ", (short) neighborZ);
	}

	@Override
	public void func_145845_h() {
		if (!field_145850_b.isRemote) {
			BlockGoal.updateGoalBlockState(!hasQuaffle && !neighborHasQuaffle, (hasQuaffle || neighborHasQuaffle), field_145850_b, field_145851_c, field_145848_d, field_145849_e);
			if (!hasQuaffle && !neighborHasQuaffle && goalTime > 0)
				goalTime--;
			if (verifyQuaffle) {
				hasQuaffle = false;
			}
			//if hasquaffle, set verify to true, if verify still true on next update set hasquaffle false
			if (hasQuaffle) {
				verifyQuaffle = true;
				goalTime = maxGoalTime;
			}
			//if(neighborHasQuaffle)
			//	neighborHasQuaffle=false;
		}
	}

	public void setNeighborHasQuaffle(boolean b) {
		neighborHasQuaffle = b;
		if (b) {
			//goalTime = maxGoalTime;
			//countDown = false;
		}
		//else if(!hasQuaffle)
		//	countDown = true;
	}

	public void setHasQuaffle(boolean b) {
		//scored = b;
		hasQuaffle = (b);
		if (b) {
			verifyQuaffle = false;
			goalTime = maxGoalTime;
			//	countDown = false;
		}
		//else if(!neighborHasQuaffle)
		//	countDown = true;
	}

	public void setGoalTime(int i) {
		goalTime = i;
	}

	public void setNeighborPosition(int i, int j, int k) {
		neighborX = i;
		neighborY = j;
		neighborZ = k;
	}

	public boolean hasQuaffle;
	public boolean justLostQuaffle;
	public boolean verifyQuaffle;
	public boolean neighborHasQuaffle;
	public int neighborX;
	public int neighborY;
	public int neighborZ;
	public int goalTime;
	public int maxGoalTime = 50;
}
