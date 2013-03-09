package justintib.quidcraft;

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

    /**
     * Gets the translated Label.
     */
    public String getTranslatedTabLabel()
    {
        return StringTranslate.getInstance().translateKey(this.getTabLabel());
    }
	@SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return Quidcraft.Snitch_ID+256;
    }
}
