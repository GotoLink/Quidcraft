package justintib.quidcraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFlag extends Block{
		protected BlockFlag(int i, int j)
	    {
	        super(i, j, Material.wood);
	        this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
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
	    public int getRenderType()
	    {
	        return 1;
	    }
		@Override
	    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	    {
	        return null;
	    }
		@Override
		public void onBlockDestroyedByPlayer(World world, int i, int j, int k,int l)
	    {
			dropBlockAsItem(world, i, j, k, l, 1);
	    }
		@Override
		public String getTextureFile(){
			return "/justintib/quidcraft/items.png";
		}
}
