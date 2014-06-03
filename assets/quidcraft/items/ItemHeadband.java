package assets.quidcraft.items;

import assets.quidcraft.QuidcraftCommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import assets.quidcraft.Quidcraft;

public class ItemHeadband extends ItemArmor {
    public final int index;
	public ItemHeadband(int i) {
		super(ArmorMaterial.DIAMOND, 0, 0);
        this.index = i;
		//damageReduceAmount=100;
		this.setMaxDamage(1000000);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isRemote) {
			itemstack = new ItemStack(Quidcraft.proxy.Headband[index]);
		}
		return itemstack;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "quidcraft:textures/models/headband" + QuidcraftCommonProxy.headbandNames[index] + ".png";
	}
}
