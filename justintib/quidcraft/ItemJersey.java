package justintib.quidcraft;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.IArmorTextureProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJersey extends ItemArmor implements IArmorTextureProvider{
	public static final String[] jerseyNames = new String[] {"", "Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
    
	protected ItemJersey(int i,int t){
		super(i, EnumArmorMaterial.GOLD, t, 1);// EnumArmorMaterial.DIAMOND
		this.setHasSubtypes(true);
		this.setMaxDamage(-1);//1000000
		this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
	}
	/*@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){
		//itemstack.stackSize--;
		if(!world.isRemote){
			if(renderIndex == Quidcraft.jerseySkin)
				itemstack = (new ItemStack(Quidcraft.JerseyRed, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyRed, 1));
			else if(renderIndex == Quidcraft.jerseyRedSkin)
				itemstack = (new ItemStack(Quidcraft.JerseyOrange, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyOrange, 1));
			else if(renderIndex == Quidcraft.jerseyOrangeSkin)
				itemstack = (new ItemStack(Quidcraft.JerseyYellow, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyYellow, 1));
			else if(renderIndex == Quidcraft.jerseyYellowSkin)
				itemstack = (new ItemStack(Quidcraft.JerseyGreen, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyGreen, 1));
			else if(renderIndex == Quidcraft.jerseyGreenSkin)
				itemstack = (new ItemStack(Quidcraft.JerseyBlue, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyBlue, 1));
			else if(renderIndex == Quidcraft.jerseyBlueSkin)
				itemstack = (new ItemStack(Quidcraft.JerseyPurple, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyPurple, 1));
			else
				itemstack = (new ItemStack(Quidcraft.Jersey, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.Jersey, 1));
		}
		return itemstack;
	}*/
	@SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < jerseyNames.length; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
	@SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int par1)
    {
        int var2 = MathHelper.clamp_int(par1, 0, jerseyNames.length-1);
        return this.iconIndex + var2;
    }

    public String getItemNameIS(ItemStack par1ItemStack)
    {
        int var2 = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, jerseyNames.length-1);
        return super.getItemName()+"."+jerseyNames[var2];
    }
	@Override
	public String getTextureFile(){
		return "/justintib/quidcraft/items.png";
	}
	//public int bludgerDamage;

	@Override
	public String getArmorTextureFile(ItemStack itemStack) {
		int var2 = MathHelper.clamp_int(itemStack.getItemDamage(), 0, jerseyNames.length-1);
		return "/armor/jersey"+jerseyNames[var2]+"_1.png";
	}
}
