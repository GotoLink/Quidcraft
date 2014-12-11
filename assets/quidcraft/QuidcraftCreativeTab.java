package assets.quidcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class QuidcraftCreativeTab extends CreativeTabs {
	public QuidcraftCreativeTab(String label) {
		super(label);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Quidcraft.proxy.Snitch;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTranslatedTabLabel() {
		return this.getTabLabel();
	}
}
