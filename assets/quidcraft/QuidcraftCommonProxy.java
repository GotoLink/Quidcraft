package assets.quidcraft;

import assets.quidcraft.blocks.BlockFlag;
import assets.quidcraft.blocks.BlockGoal;
import assets.quidcraft.blocks.BlockQuidditchChest;
import assets.quidcraft.entities.*;
import assets.quidcraft.items.*;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class QuidcraftCommonProxy {
	public CreativeTabs tabQuidditch;
    public FMLEventChannel channel;
    public Block BlockGoal, QuidditchChest, BoundaryFlag, ScoreAreaFlag;
    public Item Quaffle, Bludger, Snitch, Broom, Bat, BludgerGlove, SnitchGlove;
    public Item[] Jersey = new Item[7], Headband = new Item[5];
    public static final String[] jerseyNames = new String[] { "", "Red", "Green", "Orange", "Blue", "Purple", "Yellow" };
    public static final String[] headbandNames = new String[] { "", "Beater", "Chaser", "Keeper", "Seeker" };

	public void registerRenderThings() {
	}

	public void preInit(File configFile) {
        Configuration config = new Configuration(configFile);
        double broomSpeed = config.get("Speed Values", "Broom", EntityBroom.maxSpeed).getDouble();
        if(broomSpeed>0 && broomSpeed<10){
            EntityBroom.maxSpeed = broomSpeed;
        }
        double bludgerSpeed = config.get("Speed Values", "Bludger", EntityBludger.speedFactor).getDouble();
        if(bludgerSpeed>0 && bludgerSpeed<10){
            EntityBludger.speedFactor = bludgerSpeed;
        }
        double snitchSpeed = config.get("Speed Values", "Snitch", EntitySnitch.speedFactor).getDouble();
        if(snitchSpeed>0 && snitchSpeed<10){
            EntitySnitch.speedFactor = snitchSpeed;
        }
        double quaffleSpeed = config.get("Speed Values", "Quaffle", EntityQuaffle.force).getDouble();
        if(quaffleSpeed>0 && quaffleSpeed<10){
            EntityQuaffle.force = quaffleSpeed;
        }
        config.save();
        tabQuidditch = new QuidcraftCreativeTab("Quidditch");
        /* Entities */
        EntityRegistry.registerModEntity(EntityBludger.class, "QuidditchModBludger", 1, Quidcraft.INSTANCE, 40, 1, true);
        EntityRegistry.registerModEntity(EntitySnitch.class, "QuidditchModSnitch", 2, Quidcraft.INSTANCE, 40, 1, true);
        EntityRegistry.registerModEntity(EntityQuaffle.class, "QuidditchModQuaffle", 3, Quidcraft.INSTANCE, 40, 1, true);
        EntityRegistry.registerModEntity(EntityBroom.class, "QuidditchModBroom", 4, Quidcraft.INSTANCE, 40, 1, true);
		/* Bat */
        Bat = new ItemBat().setUnlocalizedName("quidcraft:Bat").setMaxStackSize(1).setTextureName("quidcraft:Bat").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Bat, "Bat");
        GameRegistry.addRecipe(new ItemStack(Bat), "  w", " w ", "s  ",'s', Items.stick,'w', Blocks.planks);
		/* Bludger */
        Bludger = new ItemBludger().setUnlocalizedName("quidcraft:Bludger").setMaxStackSize(1).setTextureName("quidcraft:Bludger").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Bludger, "Bludger");
        GameRegistry.addRecipe(new ItemStack(Bludger), " i ", "ibi", " i ",'i', Items.iron_ingot,'b', Items.bone);
		/* Bludger Glove */
        BludgerGlove = new ItemBludgerGlove().setUnlocalizedName("quidcraft:BludgerGlove").setMaxStackSize(1).setTextureName("quidcraft:BludgerGlove");
        GameRegistry.registerItem(BludgerGlove, "BludgerGloves");
        GameRegistry.addRecipe(new ItemStack(BludgerGlove), "i i",'i', Items.iron_ingot);
		/* Broom */
        Broom = new ItemBroom().setUnlocalizedName("quidcraft:Broom").setMaxStackSize(1).setTextureName("quidcraft:Broom").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Broom, "Broom");
        GameRegistry.addRecipe(new ItemStack(Broom), "  s", " s ", "w  ",'s', Items.stick,'w', Items.wheat);
		/* Quaffle */
        Quaffle = new ItemQuaffle().setUnlocalizedName("quidcraft:Quaffle").setMaxStackSize(1).setTextureName("quidcraft:Quaffle").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Quaffle, "Quaffle");
        GameRegistry.addRecipe(new ItemStack(Quaffle), " l ", "l l", " l ",'l', Items.leather);
		/* Snitch */
        Snitch = new ItemSnitch().setUnlocalizedName("quidcraft:Snitch").setMaxStackSize(1).setTextureName("quidcraft:Snitch").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Snitch, "Snitch");
        GameRegistry.addRecipe(new ItemStack(Snitch, 1), "fgf",'g', Items.gold_ingot,'f', Items.feather);
		/* Snitch Glove */
        SnitchGlove = new ItemSnitchGlove().setUnlocalizedName("quidcraft:SnitchGlove").setMaxStackSize(1).setTextureName("quidcraft:SnitchGlove").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(SnitchGlove, "SnitchGloves");
        GameRegistry.addRecipe(new ItemStack(SnitchGlove), "l l",'l', Items.leather);
		/* Jerseys */
        for (int i = 0; i < jerseyNames.length; i++) {
            Jersey[i] = new ItemJersey(i).setUnlocalizedName("quidcraft:Jersey" + jerseyNames[i]).setTextureName("quidcraft:Jersey" + jerseyNames[i]).setCreativeTab(tabQuidditch);
            GameRegistry.registerItem(Jersey[i], jerseyNames[i] + "Jersey");
            for (int j = 0; j < jerseyNames.length && j != i; j++) {
                GameRegistry.addShapelessRecipe(new ItemStack(Jersey[i]), new ItemStack(Jersey[j]), new ItemStack(Items.dye, 1, i));
            }
        }
        GameRegistry.addRecipe(new ItemStack(Jersey[0]), "c c", "ccc", "ccc",'c', Blocks.wool);
		/* Headbands */
        for (int i = 0; i < headbandNames.length; i++) {
            Headband[i] = new ItemHeadband(i).setUnlocalizedName("quidcraft:Headband" + headbandNames[i]).setTextureName("quidcraft:Headband" + headbandNames[i]).setCreativeTab(tabQuidditch);
            GameRegistry.registerItem(Headband[i], headbandNames[i] + "Headband");
            for (int j = 0; j < headbandNames.length && j != i; j++) {
                GameRegistry.addShapelessRecipe(new ItemStack(Headband[i]), new ItemStack(Headband[j]), new ItemStack(Items.dye, 1, i));
            }
        }
        GameRegistry.addRecipe(new ItemStack(Headband[0]), "ccc",'c', Blocks.wool);
		/* Goal Block */
        BlockGoal = new BlockGoal().setHardness(2.5F).setStepSound(Block.soundTypeCloth).setBlockName("quidcraft:BlockGoal").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(BlockGoal, "NetBlock");
        GameRegistry.addRecipe(new ItemStack(BlockGoal, 5), "c c", " c ", "c c",'c', Blocks.wool);
        GameRegistry.registerTileEntity(TileEntityGoal.class, "GoalTile");
		/* Quidditch Chest */
        QuidditchChest = new BlockQuidditchChest().setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("quidcraft:QuidcraftChest").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(QuidditchChest, "QuidditchChest");
        GameRegistry.addRecipe(new ItemStack(QuidditchChest), " q ", "bcb", " s ",'q', Quaffle,'b', Bludger,'s', Snitch,
               'c', Blocks.chest);
		/* Boundary Flag */
        BoundaryFlag = new BlockFlag().setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("quidcraft:Flag").setBlockTextureName("quidcraft:Flag").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(BoundaryFlag, "BoundaryFlag");
        GameRegistry.addRecipe(new ItemStack(BoundaryFlag), "wws", "wws", "  s",'w', Blocks.wool,'s', Items.stick);
		/* Score Area Flag */
        ScoreAreaFlag = new BlockFlag().setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("quidcraft:ScoreAreaFlag").setBlockTextureName("quidcraft:ScoreAreaFlag").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(ScoreAreaFlag, "ScoreAreaFlag");
        GameRegistry.addShapelessRecipe(new ItemStack(ScoreAreaFlag), BoundaryFlag, new ItemStack(Items.dye, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(BoundaryFlag), ScoreAreaFlag, new ItemStack(Items.dye, 1, 15));
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(QuidcraftPacketHandler.CHANNEL);
        channel.register(new QuidcraftPacketHandler());
	}
}
