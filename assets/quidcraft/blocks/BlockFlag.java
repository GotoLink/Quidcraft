package assets.quidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFlag extends Block {
	public BlockFlag() {
		super(Material.field_151575_d);
	}

	@Override
	public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public int func_149645_b() {
		return 1;
	}

	@Override
	public boolean func_149686_d() {
		return false;
	}

	@Override
	public void func_149664_b(World world, int i, int j, int k, int l) {
        func_149697_b(world, i, j, k, l, 1);
	}

	@Override
	public boolean func_149662_c() {
		return false;
	}
}
