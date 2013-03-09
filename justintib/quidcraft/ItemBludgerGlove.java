package justintib.quidcraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBludgerGlove extends Item{
	protected ItemBludgerGlove(int i){
		super(i);
		this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
		}
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10){
		//itemstack.stackSize--;
		return true;
	}
	@Override
	public String getTextureFile(){
		return "/justintib/quidcraft/items.png";
	}
}
