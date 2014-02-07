package assets.quidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import assets.quidcraft.Quidcraft;
import assets.quidcraft.entities.EntityQuaffle;
import assets.quidcraft.entities.TileEntityGoal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoal extends BlockContainer//Breakable
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockGoal() {
		super(Material.field_151567_E);
	}

	@Override
	public TileEntity func_149915_a(World var1, int i) {
		return new TileEntityGoal();
	}

	@Override
	public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon func_149691_a(int i, int j) {
		return this.icons[j == 0 ? 0 : 1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int func_149701_w() {
		return 0;
	}

	@Override
	public Item func_149650_a(int i, Random random, int j) {
		return Item.func_150898_a(Quidcraft.proxy.BlockGoal);
	}

	public boolean isBlockAt(IBlockAccess iblockaccess, int i, int j, int k) {
		Block l = iblockaccess.func_147439_a(i, j, k);
		if (l != Blocks.air) {
			return true;
		}
		return false;
	}

	@Override
	public boolean func_149686_d() {
		return false;
	}

	@Override
	public void func_149670_a(World world, int i, int j, int k, Entity entity) {
		if (!world.isRemote) {
			TileEntityGoal tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
			//world.removeBlockTileEntity(i, j, k);
			if (entity instanceof EntityQuaffle) {
				tileentitygoal.func_145829_t();
				tileentitygoal.setHasQuaffle(true);
				//notify neighbors
				world.func_147459_d(i, j, k, this);
				entity.motionX = 0F;
				entity.motionY = 0F;
				entity.motionZ = 0F;
			}
		}
	}

	@Override
	public void func_149695_a(World world, int i, int j, int k, Block l) {
		//System.out.println("neighbor updated "+i+" "+j+" "+k);
		/*
		 * TileEntityGoal tileentitygoal =
		 * (TileEntityGoal)world.getBlockTileEntity(i-1, j, k);
		 * if(tileentitygoal != null){
		 * tileentitygoal.setGoalTime(tileentitygoal.goalTime); }
		 */
		if (!world.isRemote) {
			TileEntityGoal tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
			if (tileentitygoal != null) {
				if (tileentitygoal.hasQuaffle)
					return;
			}
			if (world.func_147439_a(i - 1, j, k) == Quidcraft.proxy.BlockGoal) {
				tileentitygoal = (TileEntityGoal) world.func_147438_o(i - 1, j, k);
				if (tileentitygoal != null) {
					//if neighbor has quaffle, set neighborhasquaffle
					if (tileentitygoal.hasQuaffle) {
						tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
						if (tileentitygoal != null) {
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i - 1, j, k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if (tileentitygoal.neighborHasQuaffle) {
						TileEntityGoal tileentitygoal2 = (TileEntityGoal) world.func_147438_o(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if (tileentitygoal2 != null) {
							if (tileentitygoal2.hasQuaffle) {
								tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
								if (tileentitygoal != null) {
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.field_145851_c, tileentitygoal2.field_145848_d, tileentitygoal2.field_145849_e);
								}
								return;
							}
						}
					}
				}
			}
			if (world.func_147439_a(i + 1, j, k) == Quidcraft.proxy.BlockGoal) {
				tileentitygoal = (TileEntityGoal) world.func_147438_o(i + 1, j, k);
				if (tileentitygoal != null) {
					//if neighbor has quaffle, set neighborhasquaffle
					if (tileentitygoal.hasQuaffle) {
						tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
						if (tileentitygoal != null) {
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i + 1, j, k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if (tileentitygoal.neighborHasQuaffle) {
						TileEntityGoal tileentitygoal2 = (TileEntityGoal) world.func_147438_o(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if (tileentitygoal2 != null) {
							if (tileentitygoal2.hasQuaffle) {
								tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
								if (tileentitygoal != null) {
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.field_145851_c, tileentitygoal2.field_145848_d, tileentitygoal2.field_145849_e);
								}
								return;
							}
						}
					}
				}
			}
			if (world.func_147439_a(i, j - 1, k) == Quidcraft.proxy.BlockGoal) {
				tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j - 1, k);
				if (tileentitygoal != null) {
					//if neighbor has quaffle, set neighborhasquaffle
					if (tileentitygoal.hasQuaffle) {
						tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
						if (tileentitygoal != null) {
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i, j - 1, k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if (tileentitygoal.neighborHasQuaffle) {
						TileEntityGoal tileentitygoal2 = (TileEntityGoal) world.func_147438_o(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if (tileentitygoal2 != null) {
							if (tileentitygoal2.hasQuaffle) {
								tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
								if (tileentitygoal != null) {
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.field_145851_c, tileentitygoal2.field_145848_d, tileentitygoal2.field_145849_e);
								}
								return;
							}
						}
					}
				}
			}
			if (world.func_147439_a(i, j + 1, k) == Quidcraft.proxy.BlockGoal) {
				tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j + 1, k);
				if (tileentitygoal != null) {
					//if neighbor has quaffle, set neighborhasquaffle
					if (tileentitygoal.hasQuaffle) {
						tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
						if (tileentitygoal != null) {
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i, j + 1, k);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if (tileentitygoal.neighborHasQuaffle) {
						TileEntityGoal tileentitygoal2 = (TileEntityGoal) world.func_147438_o(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if (tileentitygoal2 != null) {
							if (tileentitygoal2.hasQuaffle) {
								tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
								if (tileentitygoal != null) {
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.field_145851_c, tileentitygoal2.field_145848_d, tileentitygoal2.field_145849_e);
								}
								return;
							}
						}
					}
				}
			}
			if (world.func_147439_a(i, j, k - 1) == Quidcraft.proxy.BlockGoal) {
				tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k - 1);
				if (tileentitygoal != null) {
					//if neighbor has quaffle, set neighborhasquaffle
					if (tileentitygoal.hasQuaffle) {
						tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
						if (tileentitygoal != null) {
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i, j, k - 1);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if (tileentitygoal.neighborHasQuaffle) {
						TileEntityGoal tileentitygoal2 = (TileEntityGoal) world.func_147438_o(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if (tileentitygoal2 != null) {
							if (tileentitygoal2.hasQuaffle) {
								tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
								if (tileentitygoal != null) {
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.field_145851_c, tileentitygoal2.field_145848_d, tileentitygoal2.field_145849_e);
								}
								return;
							}
						}
					}
				}
			}
			if (world.func_147439_a(i, j, k + 1) == Quidcraft.proxy.BlockGoal) {
				tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k + 1);
				if (tileentitygoal != null) {
					//if neighbor has quaffle, set neighborhasquaffle
					if (tileentitygoal.hasQuaffle) {
						tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
						if (tileentitygoal != null) {
							tileentitygoal.setNeighborHasQuaffle(true);
							tileentitygoal.setNeighborPosition(i, j, k + 1);
						}
						return;
					}
					//if neighbors neighbor has quaffle, verify then set neighborhasquaffle
					else if (tileentitygoal.neighborHasQuaffle) {
						TileEntityGoal tileentitygoal2 = (TileEntityGoal) world.func_147438_o(tileentitygoal.neighborX, tileentitygoal.neighborY, tileentitygoal.neighborZ);
						if (tileentitygoal2 != null) {
							if (tileentitygoal2.hasQuaffle) {
								tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
								if (tileentitygoal != null) {
									tileentitygoal.setNeighborHasQuaffle(true);
									tileentitygoal.setNeighborPosition(tileentitygoal2.field_145851_c, tileentitygoal2.field_145848_d, tileentitygoal2.field_145849_e);
								}
								return;
							}
						}
					}
				}
			}
			//otherwise, set both false
			tileentitygoal = (TileEntityGoal) world.func_147438_o(i, j, k);
			if (tileentitygoal != null) {
				tileentitygoal.setNeighborHasQuaffle(false);
				tileentitygoal.setHasQuaffle(false);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_149651_a(IIconRegister par1IconRegister) {
		this.icons = new IIcon[2];
		this.icons[0] = par1IconRegister.registerIcon("quidcraft:GoalBlock");
		this.icons[1] = par1IconRegister.registerIcon("quidcraft:GoalBlockScore");
	}

	@Override
	public boolean func_149662_c() {
		return false;
	}

	@Override
	public void func_149719_a(IBlockAccess iblockaccess, int i, int j, int k) {
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
		if (flag) {
			f2 = 0.0F;
		}
		if (flag1) {
			f3 = 1.0F;
		}
		if (flag2) {
			f = 0.0F;
		}
		if (flag3) {
			f1 = 1.0F;
		}
		if (flag4) {
			f4 = 0.0F;
		}
		if (flag5) {
			f5 = 1.0F;
		}
        func_149676_a(f, f4, f2, f1, f5, f3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		if (iblockaccess.func_147439_a(i, j, k) != Blocks.air) {
			return false;
		}
		/*
		 * boolean flag = iblockaccess.getBlockId(i - 1, j, k) == blockID &&
		 * iblockaccess.getBlockId(i - 2, j, k) != blockID; boolean flag1 =
		 * iblockaccess.getBlockId(i + 1, j, k) == blockID &&
		 * iblockaccess.getBlockId(i + 2, j, k) != blockID; boolean flag2 =
		 * iblockaccess.getBlockId(i, j, k - 1) == blockID &&
		 * iblockaccess.getBlockId(i, j, k - 2) != blockID; boolean flag3 =
		 * iblockaccess.getBlockId(i, j, k + 1) == blockID &&
		 * iblockaccess.getBlockId(i, j, k + 2) != blockID; boolean flag4 = flag
		 * || flag1; boolean flag5 = flag2 || flag3; if(flag4 && l == 4) {
		 * return true; } if(flag4 && l == 5) { return true; } if(flag5 && l ==
		 * 2) { return true; } return flag5 && l == 3;
		 */
		return true;
	}

	public static void updateGoalBlockState(boolean flag1, boolean flag2, World world, int i, int j, int k) {
		int meta = world.getBlockMetadata(i, j, k);
		if (flag1 && meta == 1) {
			world.setBlockMetadataWithNotify(i, j, k, 0, 3);
			world.func_147471_g(i, j, k);
		} else if (flag2 && meta == 0) {
			world.setBlockMetadataWithNotify(i, j, k, 1, 3);
			world.func_147471_g(i, j, k);
		}
	}
}
