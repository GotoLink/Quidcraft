package assets.quidcraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.StringTranslate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class QuidcraftCreativeTab extends CreativeTabs{

	public QuidcraftCreativeTab(String label) 
	{
		super(label);
	}
	
	@SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return Quidcraft.instance.Snitch_ID+256;
    }
}
