package assets.quidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFlag extends Block {
	public BlockFlag() {
		super(Material.wood);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {
        dropBlockAsItem(world, i, j, k, l, 1);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
