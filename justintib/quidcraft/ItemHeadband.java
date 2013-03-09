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

public class ItemHeadband extends ItemArmor implements IArmorTextureProvider{
	public static final String[] headbandNames=new String[]{"","Beater","Chaser","Keeper","Seeker"};
	protected ItemHeadband(int i,int t){
		super(i, EnumArmorMaterial.GOLD, t, 0);//EnumArmorMaterial.DIAMOND
		this.setHasSubtypes(true);
		//damageReduceAmount=100;
		this.setMaxDamage(-1);//1000000
		this.setCreativeTab(CreativeTabs.creativeTabArray[Quidcraft.proxy.tabQuidditch]);
	}
	/*@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){
		//itemstack.stackSize--;
		if(!world.isRemote){
			if(renderIndex == Quidcraft.headbandSkin)
				itemstack = (new ItemStack(Quidcraft.HeadbandKeeper, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyRed, 1));
			else if(renderIndex == Quidcraft.headbandKeeperSkin)
				itemstack = (new ItemStack(Quidcraft.HeadbandSeeker, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyOrange, 1));
			else if(renderIndex == Quidcraft.headbandSeekerSkin)
				itemstack = (new ItemStack(Quidcraft.HeadbandChaser, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyYellow, 1));
			else if(renderIndex == Quidcraft.headbandChaserSkin)
				itemstack = (new ItemStack(Quidcraft.HeadbandBeater, 1));
				//entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_QuidditchSMP.JerseyGreen, 1));
			else
				itemstack = (new ItemStack(Quidcraft.Headband, 1));
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
        for (int var4 = 0; var4 < 5; ++var4)
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
        int var2 = MathHelper.clamp_int(par1, 0, 4);
        return this.iconIndex + var2;
    }

    public String getItemNameIS(ItemStack par1ItemStack)
    {
        int var2 = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 4);
        return super.getItemName()/*.concat(headbandNames[var2])*/;
    }
	@Override
	public String getTextureFile(){
		return "/justintib/quidcraft/items.png";
	}
	@Override
	public String getArmorTextureFile(ItemStack itemStack) {
		int var2 = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 4);
		return "/armor/headband"+headbandNames[var2]+"_1.png";
	}
}
