package assets.quidcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import assets.quidcraft.Quidcraft;

public class ItemJersey extends ItemArmor {
	public ItemJersey(int i) {
		super(i, EnumArmorMaterial.DIAMOND, 0, 1);
		this.setMaxDamage(1000000);
		this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isRemote) {
			itemstack = new ItemStack(Quidcraft.Jersey[itemstack.itemID - Quidcraft.Jersey_ID - 256]);
		}
		return itemstack;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return "quidcraft:textures/models/jersey" + Quidcraft.jerseyNames[stack.itemID - Quidcraft.Jersey_ID - 256] + ".png";
	}
}
