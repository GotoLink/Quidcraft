package justintib.quidcraft;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoal extends BlockContainer//Breakable
{
	protected BlockGoal(int i, int j)
    {
        super(i, Material.portal);
        this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
        }
	 @SideOnly(Side.CLIENT)
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        int i1 = iblockaccess.getBlockMetadata(i, j, k);
        if(i1 == 0)
        {
            return 18;//goalBlockTexture;
        }
        else
        {
        	return 19;//goalBlockScore;
        }
    }
@Override
    public int idDropped(int i, Random random, int j)
    {
        return Quidcraft.BlockGoal.blockID;
    }
    
@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
@Override
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
    	boolean flag = isBlockAt(iblockaccess, i, j, k - 1);
        boolean flag1 = isBlockAt(iblockaccess, i, j, k + 1);
        boolean flag2 = isBlockAt(iblockaccess, i - 1, j, k);
        boolean flag3 = isBlockAt(iblockaccess, i + 1, j, k);
        boolean flag4 = isBlockAt(iblockaccess, i, j - 1, k);
        boolean flag5 = isBlockAt(iblockaccess, i, j + 1, k);
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;
        float f4 = 0.375F;
        float f5 = 0.625F;
        if(flag)
        {
            f2 = 0.0F;
        }
        if(flag1)
        {
            f3 = 1.0F;
        }
        if(flag2)
        {
            f = 0.0F;
        }
        if(flag3)
        {
            f1 = 1.0F;
        }
        if(flag4)
        {
        	f4 = 0.0F;
        }
        if(flag5)
        {
        	f5 = 1.0F;
        }
        setBlockBounds(f, f4, f2, f1, f5, f3);
    }
    
    public boolean isBlockAt(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getBlockId(i, j, k);
        if(l != 0)
        {
            return true;
        }
        return false;
    }
@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

@Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
    	//System.out.println("neighbor updated "+i+" "+j+" "+k);
    	/*TileEntityGoal tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i-1, j, k);
    	if(tileentitygoal != null){
    		tileentitygoal.setGoalTime(tileentitygoal.goalTime);
    	}*/
        if(!world.isRemote){
	    	TileEntityGoal tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
	    	if(tileentitygoal != null){
	    		if(tileentitygoal.hasQuaffle)
	    			return;
	    	}
	    	
	    	if(world.getBlockId(i-1,j,k) == Quidcraft.BlockGoal.blockID){
	    		tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i-1, j, k);
	    		if(tileentitygoal != null){
	    			//if neighbor has quaffle, set neighborhasquaffle
					if(tileentitygoal.hasQuaffle){
						tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
						if(tileentitygoal != null){
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i-1,j,k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if(tileentitygoal.neighborHasQuaffle){
						TileEntityGoal tileentitygoal2 = (TileEntityGoal)world.getBlockTileEntity(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if(tileentitygoal2 != null){
							if(tileentitygoal2.hasQuaffle){
								tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
								if(tileentitygoal != null){
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.xCoord, tileentitygoal2.yCoord, tileentitygoal2.zCoord);
								}
								return;
							}
						}
					}
				}
	    	}
	    	if(world.getBlockId(i+1,j,k) == Quidcraft.BlockGoal.blockID){
	    		tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i+1, j, k);
	    		if(tileentitygoal != null){
	    			//if neighbor has quaffle, set neighborhasquaffle
					if(tileentitygoal.hasQuaffle){
						tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
						if(tileentitygoal != null){
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i+1,j,k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if(tileentitygoal.neighborHasQuaffle){
						TileEntityGoal tileentitygoal2 = (TileEntityGoal)world.getBlockTileEntity(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if(tileentitygoal2 != null){
							if(tileentitygoal2.hasQuaffle){
								tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
								if(tileentitygoal != null){
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.xCoord, tileentitygoal2.yCoord, tileentitygoal2.zCoord);
								}
								return;
							}
						}
					}
				}
	    	}
	    	if(world.getBlockId(i,j-1,k) == Quidcraft.BlockGoal.blockID){
	    		tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j-1, k);
	    		if(tileentitygoal != null){
	    			//if neighbor has quaffle, set neighborhasquaffle
					if(tileentitygoal.hasQuaffle){
						tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
						if(tileentitygoal != null){
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i,j-1,k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if(tileentitygoal.neighborHasQuaffle){
						TileEntityGoal tileentitygoal2 = (TileEntityGoal)world.getBlockTileEntity(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if(tileentitygoal2 != null){
							if(tileentitygoal2.hasQuaffle){
								tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
								if(tileentitygoal != null){
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.xCoord, tileentitygoal2.yCoord, tileentitygoal2.zCoord);
								}
								return;
							}
						}
					}
				}
	    	}
	    	if(world.getBlockId(i,j+1,k) == Quidcraft.BlockGoal.blockID){
	    		tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j+1, k);
	    		if(tileentitygoal != null){
	    			//if neighbor has quaffle, set neighborhasquaffle
					if(tileentitygoal.hasQuaffle){
						tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
						if(tileentitygoal != null){
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i,j+1,k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if(tileentitygoal.neighborHasQuaffle){
						TileEntityGoal tileentitygoal2 = (TileEntityGoal)world.getBlockTileEntity(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if(tileentitygoal2 != null){
							if(tileentitygoal2.hasQuaffle){
								tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
								if(tileentitygoal != null){
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.xCoord, tileentitygoal2.yCoord, tileentitygoal2.zCoord);
								}
								return;
							}
						}
					}
				}
	    	}
	    	if(world.getBlockId(i,j,k-1) == Quidcraft.BlockGoal.blockID){
	    		tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k-1);
	    		if(tileentitygoal != null){
	    			//if neighbor has quaffle, set neighborhasquaffle
					if(tileentitygoal.hasQuaffle){
						tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
						if(tileentitygoal != null){
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i,j,k-1);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if(tileentitygoal.neighborHasQuaffle){
						TileEntityGoal tileentitygoal2 = (TileEntityGoal)world.getBlockTileEntity(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if(tileentitygoal2 != null){
							if(tileentitygoal2.hasQuaffle){
								tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
								if(tileentitygoal != null){
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.xCoord, tileentitygoal2.yCoord, tileentitygoal2.zCoord);
								}
								return;
							}
						}
					}
				}
	    	}
	    	if(world.getBlockId(i,j,k+1) == Quidcraft.BlockGoal.blockID){
	    		tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k+1);
	    		if(tileentitygoal != null){
	    			//if neighbor has quaffle, set neighborhasquaffle
					if(tileentitygoal.hasQuaffle){
						tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
						if(tileentitygoal != null){
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i,j,k+1);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if(tileentitygoal.neighborHasQuaffle){
						TileEntityGoal tileentitygoal2 = (TileEntityGoal)world.getBlockTileEntity(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if(tileentitygoal2 != null){
							if(tileentitygoal2.hasQuaffle){
								tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
								if(tileentitygoal != null){
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.xCoord, tileentitygoal2.yCoord, tileentitygoal2.zCoord);
								}
								return;
							}
						}
					}
				}
	    	}
	    	//otherwise, set both false
			tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i,j,k);
			if(tileentitygoal != null){
				tileentitygoal.setNeighborHasQuaffle(false);
				tileentitygoal.setHasQuaffle(false);
			}
        }
    }
@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(iblockaccess.getBlockId(i, j, k) != 0)
        {
            return false;
        }
        /*boolean flag = iblockaccess.getBlockId(i - 1, j, k) == blockID && iblockaccess.getBlockId(i - 2, j, k) != blockID;
        boolean flag1 = iblockaccess.getBlockId(i + 1, j, k) == blockID && iblockaccess.getBlockId(i + 2, j, k) != blockID;
        boolean flag2 = iblockaccess.getBlockId(i, j, k - 1) == blockID && iblockaccess.getBlockId(i, j, k - 2) != blockID;
        boolean flag3 = iblockaccess.getBlockId(i, j, k + 1) == blockID && iblockaccess.getBlockId(i, j, k + 2) != blockID;
        boolean flag4 = flag || flag1;
        boolean flag5 = flag2 || flag3;
        if(flag4 && l == 4)
        {
            return true;
        }
        if(flag4 && l == 5)
        {
            return true;
        }
        if(flag5 && l == 2)
        {
            return true;
        }
        return flag5 && l == 3;*/
        return true;
    }
@Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
@SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }
    
    public static void updateGoalBlockState(boolean flag1,boolean flag2,World world, int i, int j, int k){

    	int meta = world.getBlockMetadata(i,j,k);
    	
    	if(flag1 && meta == 1){
    		world.setBlockMetadataWithNotify(i, j, k, 0);
    		world.markBlockForUpdate(i,j,k);
    	}
    	else if(!flag1 && meta == 0){
    		world.setBlockMetadataWithNotify(i, j, k, 1);
    		world.markBlockForUpdate(i,j,k);
    	}
    	
    }
@Override
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	if(!world.isRemote){
	    	TileEntityGoal tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
	    	//world.removeBlockTileEntity(i, j, k);
	    	
	    	if(entity instanceof EntityQuaffle){
	    		tileentitygoal.validate();
	    		tileentitygoal.setHasQuaffle(true);
	    		
	    		//notify neighbors
	    		world.notifyBlocksOfNeighborChange(i - 1, j, k, blockID);
	            world.notifyBlocksOfNeighborChange(i + 1, j , k, blockID);
	    		world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
	            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
	            world.notifyBlocksOfNeighborChange(i, j, k - 1, blockID);
	            world.notifyBlocksOfNeighborChange(i, j, k + 1, blockID);
	
	    		entity.motionX=0F;
	        	entity.motionY=0F;
	        	entity.motionZ=0F;
	    	}
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityGoal();
	}
	@Override
	public String getTextureFile(){
		return "/justintib/quidcraft/items.png";
	}
}
