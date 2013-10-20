package assets.quidcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import assets.quidcraft.Quidcraft;

public class ItemHeadband extends ItemArmor {
	public ItemHeadband(int i) {
		super(i, EnumArmorMaterial.DIAMOND, 0, 0);
		//damageReduceAmount=100;
		this.setMaxDamage(1000000);
		this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isRemote) {
			itemstack = new ItemStack(Quidcraft.instance.Headband[itemstack.itemID - Quidcraft.Headband_ID - 256]);
		}
		return itemstack;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return "quidcraft:textures/models/headband" + Quidcraft.instance.headbandNames[stack.itemID - Quidcraft.Headband_ID - 256] + ".png";
	}
}
