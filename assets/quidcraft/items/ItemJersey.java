package assets.quidcraft.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import assets.quidcraft.Quidcraft;

public class ItemJersey extends ItemArmor {
    public final int index;
	public ItemJersey(int i) {
		super(ArmorMaterial.DIAMOND, 0, 1);
        this.index = i;
		this.setMaxDamage(1000000);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isRemote) {
			itemstack = new ItemStack(Quidcraft.proxy.Jersey[index]);
		}
		return itemstack;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "quidcraft:textures/models/jersey" + Quidcraft.proxy.jerseyNames[index] + ".png";
	}
}
