package assets.quidcraft;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class QuidcraftCreativeTab extends CreativeTabs {
	public QuidcraftCreativeTab(String label) {
		super(label);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTranslatedTabLabel() {
		return this.getTabLabel();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return Quidcraft.instance.Snitch_ID + 256;
	}
}
