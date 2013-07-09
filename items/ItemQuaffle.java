package assets.quidcraft.items;

import assets.quidcraft.Quidcraft;
import assets.quidcraft.entities.EntityQuaffle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemQuaffle extends Item{
	public ItemQuaffle(int i){
		super(i);
		this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
		}
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10){
		//itemstack.stackSize--;
		return true;
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityQuaffle(world, entityplayer));
        }
        return itemstack;
    }
}
