package mods.quidcraft.blocks;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import mods.quidcraft.Quidcraft;
import mods.quidcraft.entities.EntityQuaffle;
import mods.quidcraft.entities.TileEntityGoal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNet extends Block
{
	@SideOnly(Side.CLIENT)
    private Icon[] icons;
    private boolean netsProvidePower;
    private boolean containsQuaffle;
    private Set blocksNeedingUpdate;

    public BlockNet(int i)
    {
        super(i, Material.portal);
        netsProvidePower = true;
        containsQuaffle = false;
        blocksNeedingUpdate = new HashSet();
        this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);      
        //setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }
    
    public TileEntity getBlockEntity()
    {
        return new TileEntityGoal();
    }
    @SideOnly(Side.CLIENT)
    public Icon getIcon( int i, int j)
    {   
         return this.icons[j==0?0:1];
    }
	 @SideOnly(Side.CLIENT)
	    public void registerIcons(IconRegister par1IconRegister)
	    {
		 this.icons = new Icon[2];
	     this.icons[0] = par1IconRegister.registerIcon("quidcraft:GoalBlock");
	     this.icons[1] = par1IconRegister.registerIcon("quidcraft:GoalBlockScore");
	    }
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        /*if(iblockaccess.getBlockId(i - 1, j, k) == blockID || iblockaccess.getBlockId(i + 1, j, k) == blockID)
        {
            float f = 0.5F;
            float f2 = 0.125F;
            setBlockBounds(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
        } else
        {
            float f1 = 0.125F;
            float f3 = 0.5F;
            setBlockBounds(0.5F - f1, 0.0F, 0.5F - f3, 0.5F + f1, 1.0F, 0.5F + f3);
        }*/
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
    public boolean isOpaqueCube()
    {
        return false;
    }
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    /*public int getRenderType()
    {
        return 5;
    }*/

    /*public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return 0x800000;
    }*/
    @Override
    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
    	return true;
        //return world.isBlockNormalCube(i, j - 1, k);
    }
    @Override
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	TileEntityGoal tileentitygoal = (TileEntityGoal)world.getBlockTileEntity(i, j, k);
    	
    	if(entity instanceof EntityQuaffle){
    		if(tileentitygoal != null){
    			tileentitygoal.validate();
    			tileentitygoal.setHasQuaffle(true);
    		}
    		
    		entity.setInWeb();
    		
    		world.notifyBlocksOfNeighborChange(i - 1, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i + 1, j , k, blockID);
    		world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j, k - 1, blockID);
            world.notifyBlocksOfNeighborChange(i, j, k + 1, blockID);
            
    		world.markBlockForUpdate(i,j,k);
    	}
    }
    
    public static void updateGoalBlockState(boolean flag1,boolean flag2,World world, int i, int j, int k){
    	int meta = world.getBlockMetadata(i,j,k);
    	
    	if(flag1 && meta == 1){
    		world.setBlockMetadataWithNotify(i, j, k, 0, 3);
    		world.markBlockForUpdate(i,j,k);
    	}
    	else if(!flag1 && meta == 0){
    		world.setBlockMetadataWithNotify(i, j, k, 1, 3);
    		world.markBlockForUpdate(i,j,k);
    	}
    }
    
    /*private void updateAndPropagateCurrentStrength(World world, int i, int j, int k)
    {
        calculateCurrentChanges(world, i, j, k, i, j, k);
        ArrayList arraylist = new ArrayList(blocksNeedingUpdate);
        blocksNeedingUpdate.clear();
        for(int l = 0; l < arraylist.size(); l++)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l);
            world.notifyBlocksOfNeighborChange(chunkposition.x, chunkposition.y, chunkposition.z, blockID);
        }

    }

    private void calculateCurrentChanges(World world, int i, int j, int k, int l, int i1, int j1)
    {
        int k1 = world.getBlockMetadata(i, j, k);
        int l1 = 0;
        netsProvidePower = false;
        boolean flag = world.isBlockIndirectlyGettingPowered(i, j, k);
        netsProvidePower = true;
        if(flag)
        {
            l1 = 15;
        } else
        {
            for(int i2 = 0; i2 < 4; i2++)
            {
                int k2 = i;
                int i3 = k;
                if(i2 == 0)
                {
                    k2--;
                }
                if(i2 == 1)
                {
                    k2++;
                }
                if(i2 == 2)
                {
                    i3--;
                }
                if(i2 == 3)
                {
                    i3++;
                }
                if(k2 != l || j != i1 || i3 != j1)
                {
                    l1 = getMaxCurrentStrength(world, k2, j, i3, l1);
                }
                if(world.isBlockNormalCube(k2, j, i3) && !world.isBlockNormalCube(i, j + 1, k))
                {
                    if(k2 != l || j + 1 != i1 || i3 != j1)
                    {
                        l1 = getMaxCurrentStrength(world, k2, j + 1, i3, l1);
                    }
                    continue;
                }
                if(!world.isBlockNormalCube(k2, j, i3) && (k2 != l || j - 1 != i1 || i3 != j1))
                {
                    l1 = getMaxCurrentStrength(world, k2, j - 1, i3, l1);
                }
            }

            if(l1 > 0)
            {
                l1--;
            } else
            {
                l1 = 0;
            }
        }
        if(k1 != l1)
        {
            world.editingBlocks = true;
            world.setBlockMetadataWithNotify(i, j, k, l1);
            world.markBlocksDirty(i, j, k, i, j, k);
            world.editingBlocks = false;
            for(int j2 = 0; j2 < 4; j2++)
            {
                int l2 = i;
                int j3 = k;
                int k3 = j - 1;
                if(j2 == 0)
                {
                    l2--;
                }
                if(j2 == 1)
                {
                    l2++;
                }
                if(j2 == 2)
                {
                    j3--;
                }
                if(j2 == 3)
                {
                    j3++;
                }
                if(world.isBlockNormalCube(l2, j, j3))
                {
                    k3 += 2;
                }
                int l3 = 0;
                l3 = getMaxCurrentStrength(world, l2, j, j3, -1);
                l1 = world.getBlockMetadata(i, j, k);
                if(l1 > 0)
                {
                    l1--;
                }
                if(l3 >= 0 && l3 != l1)
                {
                    calculateCurrentChanges(world, l2, j, j3, i, j, k);
                }
                l3 = getMaxCurrentStrength(world, l2, k3, j3, -1);
                l1 = world.getBlockMetadata(i, j, k);
                if(l1 > 0)
                {
                    l1--;
                }
                if(l3 >= 0 && l3 != l1)
                {
                    calculateCurrentChanges(world, l2, k3, j3, i, j, k);
                }
            }

            if(k1 == 0 || l1 == 0)
            {
                blocksNeedingUpdate.add(new ChunkPosition(i, j, k));
                blocksNeedingUpdate.add(new ChunkPosition(i - 1, j, k));
                blocksNeedingUpdate.add(new ChunkPosition(i + 1, j, k));
                blocksNeedingUpdate.add(new ChunkPosition(i, j - 1, k));
                blocksNeedingUpdate.add(new ChunkPosition(i, j + 1, k));
                blocksNeedingUpdate.add(new ChunkPosition(i, j, k - 1));
                blocksNeedingUpdate.add(new ChunkPosition(i, j, k + 1));
            }
        }
    }

    private void notifyWireNeighborsOfNeighborChange(World world, int i, int j, int k)
    {
        if(world.getBlockId(i, j, k) != blockID)
        {
            return;
        } else
        {
            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i - 1, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i + 1, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j, k - 1, blockID);
            world.notifyBlocksOfNeighborChange(i, j, k + 1, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
            return;
        }
    }*/

    /*public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        if(world.multiplayerWorld)
        {
            return;
        }
        updateAndPropagateCurrentStrength(world, i, j, k);
        world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
        world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
        notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
        notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
        notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
        notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
        if(world.isBlockNormalCube(i - 1, j, k))
        {
            notifyWireNeighborsOfNeighborChange(world, i - 1, j + 1, k);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i - 1, j - 1, k);
        }
        if(world.isBlockNormalCube(i + 1, j, k))
        {
            notifyWireNeighborsOfNeighborChange(world, i + 1, j + 1, k);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i + 1, j - 1, k);
        }
        if(world.isBlockNormalCube(i, j, k - 1))
        {
            notifyWireNeighborsOfNeighborChange(world, i, j + 1, k - 1);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i, j - 1, k - 1);
        }
        if(world.isBlockNormalCube(i, j, k + 1))
        {
            notifyWireNeighborsOfNeighborChange(world, i, j + 1, k + 1);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i, j - 1, k + 1);
        }
    }*/

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        //super.onBlockRemoval(world, i, j, k);
        if(world.isRemote)
        {
            return;
        }
        /*world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
        world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
        updateAndPropagateCurrentStrength(world, i, j, k);
        notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
        notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
        notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
        notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
        if(world.isBlockNormalCube(i - 1, j, k))
        {
            notifyWireNeighborsOfNeighborChange(world, i - 1, j + 1, k);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i - 1, j - 1, k);
        }
        if(world.isBlockNormalCube(i + 1, j, k))
        {
            notifyWireNeighborsOfNeighborChange(world, i + 1, j + 1, k);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i + 1, j - 1, k);
        }
        if(world.isBlockNormalCube(i, j, k - 1))
        {
            notifyWireNeighborsOfNeighborChange(world, i, j + 1, k - 1);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i, j - 1, k - 1);
        }
        if(world.isBlockNormalCube(i, j, k + 1))
        {
            notifyWireNeighborsOfNeighborChange(world, i, j + 1, k + 1);
        } else
        {
            notifyWireNeighborsOfNeighborChange(world, i, j - 1, k + 1);
        }*/
    }

    /*private int getMaxCurrentStrength(World world, int i, int j, int k, int l)
    {
        if(world.getBlockId(i, j, k) != blockID)
        {
            return l;
        }
        int i1 = world.getBlockMetadata(i, j, k);
        if(i1 > l)
        {
            return i1;
        } else
        {
            return l;
        }
    }*/
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        if(world.isRemote)
        {
            return;
        }
        int i1 = world.getBlockMetadata(i, j, k);
        /*boolean flag = canPlaceBlockAt(world, i, j, k);
        if(!flag)
        {
            dropBlockAsItem(world, i, j, k, i1, 0);
            world.setBlockWithNotify(i, j, k, 0);
        } else
        {
            updateAndPropagateCurrentStrength(world, i, j, k);
        }*/
        super.onNeighborBlockChange(world, i, j, k, l);
    }
    @Override
    public int idDropped(int i, Random random, int j)
    {
        return blockID;
    }

    public boolean isIndirectlyPoweringTo(World world, int i, int j, int k, int l)
    {
        if(!netsProvidePower)
        {
            return false;
        } else
        {
            return isIndirectlyPoweringTo(world, i, j, k, l);
        }
    }

    /*public boolean isPoweringTo(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(!netsProvidePower)
        {
            return false;
        }
        if(iblockaccess.getBlockMetadata(i, j, k) == 0)
        {
            return false;
        }
        if(l == 1)
        {
            return true;
        }
        boolean flag = func_41053_d(iblockaccess, i - 1, j, k, 1) || !iblockaccess.isBlockNormalCube(i - 1, j, k) && func_41053_d(iblockaccess, i - 1, j - 1, k, -1);
        boolean flag1 = func_41053_d(iblockaccess, i + 1, j, k, 3) || !iblockaccess.isBlockNormalCube(i + 1, j, k) && func_41053_d(iblockaccess, i + 1, j - 1, k, -1);
        boolean flag2 = func_41053_d(iblockaccess, i, j, k - 1, 2) || !iblockaccess.isBlockNormalCube(i, j, k - 1) && func_41053_d(iblockaccess, i, j - 1, k - 1, -1);
        boolean flag3 = func_41053_d(iblockaccess, i, j, k + 1, 0) || !iblockaccess.isBlockNormalCube(i, j, k + 1) && func_41053_d(iblockaccess, i, j - 1, k + 1, -1);
        if(!iblockaccess.isBlockNormalCube(i, j + 1, k))
        {
            if(iblockaccess.isBlockNormalCube(i - 1, j, k) && func_41053_d(iblockaccess, i - 1, j + 1, k, -1))
            {
                flag = true;
            }
            if(iblockaccess.isBlockNormalCube(i + 1, j, k) && func_41053_d(iblockaccess, i + 1, j + 1, k, -1))
            {
                flag1 = true;
            }
            if(iblockaccess.isBlockNormalCube(i, j, k - 1) && func_41053_d(iblockaccess, i, j + 1, k - 1, -1))
            {
                flag2 = true;
            }
            if(iblockaccess.isBlockNormalCube(i, j, k + 1) && func_41053_d(iblockaccess, i, j + 1, k + 1, -1))
            {
                flag3 = true;
            }
        }
        if(!flag2 && !flag1 && !flag && !flag3 && l >= 2 && l <= 5)
        {
            return true;
        }
        if(l == 2 && flag2 && !flag && !flag1)
        {
            return true;
        }
        if(l == 3 && flag3 && !flag && !flag1)
        {
            return true;
        }
        if(l == 4 && flag && !flag2 && !flag3)
        {
            return true;
        }
        return l == 5 && flag1 && !flag2 && !flag3;
    }*/
    @Override
    public boolean canProvidePower()
    {
        return netsProvidePower;
    }

    /*public static boolean isPowerProviderOrWire(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        int i1 = iblockaccess.getBlockId(i, j, k);
        if(i1 == Block.redstoneWire.blockID)
        {
            return true;
        }
        if(i1 == 0)
        {
            return false;
        }
        if(Block.blocksList[i1].canProvidePower() && l != -1)
        {
            return true;
        }
        if(i1 == Block.redstoneRepeaterIdle.blockID || i1 == Block.redstoneRepeaterActive.blockID)
        {
            int j1 = iblockaccess.getBlockMetadata(i, j, k);
            return l == (j1 & 3) || l == Direction.footInvisibleFaceRemap[j1 & 3];
        } else
        {
            return false;
        }
    }*/

    /*public static boolean func_41053_d(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(isPowerProviderOrWire(iblockaccess, i, j, k, l))
        {
            return true;
        }
        int i1 = iblockaccess.getBlockId(i, j, k);
        if(i1 == Block.redstoneRepeaterActive.blockID)
        {
            int j1 = iblockaccess.getBlockMetadata(i, j, k);
            return l == (j1 & 3);
        } else
        {
            return false;
        }
    }*/
}
