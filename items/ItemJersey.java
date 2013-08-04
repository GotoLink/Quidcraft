package assets.quidcraft.items;

import assets.quidcraft.Quidcraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJersey extends ItemArmor {
	public ItemJersey(int i){
		super(i, EnumArmorMaterial.DIAMOND, 0, 1); 
		this.setMaxDamage(1000000);
		this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){
		if(!world.isRemote){	
			itemstack = new ItemStack(Quidcraft.instance.Jersey[itemstack.itemID-Quidcraft.Jersey_ID-256]);
		}
		return itemstack;
	}
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {	
		return "quidcraft:textures/models/jersey"+Quidcraft.instance.jerseyNames[stack.itemID-Quidcraft.Jersey_ID-256]+".png";
    }
}
