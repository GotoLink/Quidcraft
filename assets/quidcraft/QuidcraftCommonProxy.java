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

	public void preInit() {
        tabQuidditch = new QuidcraftCreativeTab("Quidditch");
        /* Entities */
        EntityRegistry.registerModEntity(EntityBludger.class, "QuidditchModBludger", 1, Quidcraft.INSTANCE, 40, 1, true);
        EntityRegistry.registerModEntity(EntitySnitch.class, "QuidditchModSnitch", 2, Quidcraft.INSTANCE, 40, 1, true);
        EntityRegistry.registerModEntity(EntityQuaffle.class, "QuidditchModQuaffle", 3, Quidcraft.INSTANCE, 40, 1, true);
        EntityRegistry.registerModEntity(EntityBroom.class, "QuidditchModBroom", 4, Quidcraft.INSTANCE, 40, 1, true);
		/* Bat */
        Bat = new ItemBat().setUnlocalizedName("quidcraft:Bat").setMaxStackSize(1).setTextureName("quidcraft:Bat").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Bat, "Bat");
        GameRegistry.addRecipe(new ItemStack(Bat), "  w", " w ", "s  ", Character.valueOf('s'), Items.stick, Character.valueOf('w'), Blocks.planks);
		/* Bludger */
        Bludger = new ItemBludger().setUnlocalizedName("quidcraft:Bludger").setMaxStackSize(1).setTextureName("quidcraft:Bludger").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Bludger, "Bludger");
        GameRegistry.addRecipe(new ItemStack(Bludger), " i ", "ibi", " i ", Character.valueOf('i'), Items.iron_ingot, Character.valueOf('b'), Items.bone);
		/* Bludger Glove */
        BludgerGlove = new ItemBludgerGlove().setUnlocalizedName("quidcraft:BludgerGlove").setMaxStackSize(1).setTextureName("quidcraft:BludgerGlove");
        GameRegistry.registerItem(BludgerGlove, "Bludger Gloves");
        GameRegistry.addRecipe(new ItemStack(BludgerGlove), "i i", Character.valueOf('i'), Items.iron_ingot);
		/* Broom */
        Broom = new ItemBroom().setUnlocalizedName("quidcraft:Broom").setMaxStackSize(1).setTextureName("quidcraft:Broom").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Broom, "Broom");
        GameRegistry.addRecipe(new ItemStack(Broom), "  s", " s ", "w  ", Character.valueOf('s'), Items.stick, Character.valueOf('w'), Items.wheat);
		/* Quaffle */
        Quaffle = new ItemQuaffle().setUnlocalizedName("quidcraft:Quaffle").setMaxStackSize(1).setTextureName("quidcraft:Quaffle").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Quaffle, "Quaffle");
        GameRegistry.addRecipe(new ItemStack(Quaffle), " l ", "l l", " l ", Character.valueOf('l'), Items.leather);
		/* Snitch */
        Snitch = new ItemSnitch().setUnlocalizedName("quidcraft:Snitch").setMaxStackSize(1).setTextureName("quidcraft:Snitch").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(Snitch, "Snitch");
        GameRegistry.addRecipe(new ItemStack(Snitch, 1), "fgf", Character.valueOf('g'), Items.gold_ingot, Character.valueOf('f'), Items.feather);
		/* Snitch Glove */
        SnitchGlove = new ItemSnitchGlove().setUnlocalizedName("quidcraft:SnitchGlove").setMaxStackSize(1).setTextureName("quidcraft:SnitchGlove").setCreativeTab(tabQuidditch);
        GameRegistry.registerItem(SnitchGlove, "Snitch Gloves");
        GameRegistry.addRecipe(new ItemStack(SnitchGlove), "l l", Character.valueOf('l'), Items.leather);
		/* Jerseys */
        for (int i = 0; i < jerseyNames.length; i++) {
            Jersey[i] = new ItemJersey(i).setUnlocalizedName("quidcraft:Jersey" + jerseyNames[i]).setTextureName("quidcraft:Jersey" + jerseyNames[i]).setCreativeTab(tabQuidditch);
            GameRegistry.registerItem(Jersey[i], jerseyNames[i] + " Jersey");
            for (int j = 0; j < jerseyNames.length && j != i; j++) {
                GameRegistry.addShapelessRecipe(new ItemStack(Jersey[i]), new ItemStack(Jersey[j]), new ItemStack(Items.dye, 1, i));
            }
        }
        GameRegistry.addRecipe(new ItemStack(Jersey[0]), "c c", "ccc", "ccc", Character.valueOf('c'), Blocks.wool);
		/* Headbands */
        for (int i = 0; i < headbandNames.length; i++) {
            Headband[i] = new ItemHeadband(i).setUnlocalizedName("quidcraft:Headband" + headbandNames[i]).setTextureName("quidcraft:Headband" + headbandNames[i]).setCreativeTab(tabQuidditch);
            GameRegistry.registerItem(Headband[i], headbandNames[i] + " Headband");
            for (int j = 0; j < headbandNames.length && j != i; j++) {
                GameRegistry.addShapelessRecipe(new ItemStack(Headband[i]), new ItemStack(Headband[j]), new ItemStack(Items.dye, 1, i));
            }
        }
        GameRegistry.addRecipe(new ItemStack(Headband[0]), "ccc", Character.valueOf('c'), Blocks.wool);
		/* Goal Block */
        BlockGoal = new BlockGoal().setHardness(2.5F).setStepSound(Block.soundTypeCloth).setBlockName("quidcraft:BlockGoal").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(BlockGoal, "Net Block");
        GameRegistry.addRecipe(new ItemStack(BlockGoal, 5), "c c", " c ", "c c", Character.valueOf('c'), Blocks.wool);
        GameRegistry.registerTileEntity(TileEntityGoal.class, "GoalTile");
		/* Quidditch Chest */
        QuidditchChest = new BlockQuidditchChest().setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("quidcraft:QuidcraftChest").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(QuidditchChest, "Quidditch Chest");
        GameRegistry.addRecipe(new ItemStack(QuidditchChest), " q ", "bcb", " s ", Character.valueOf('q'), Quaffle, Character.valueOf('b'), Bludger, Character.valueOf('s'), Snitch,
                Character.valueOf('c'), Blocks.chest);
		/* Boundary Flag */
        BoundaryFlag = new BlockFlag().setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("quidcraft:Flag").setBlockTextureName("quidcraft:Flag").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(BoundaryFlag, "Boundary Flag");
        GameRegistry.addRecipe(new ItemStack(BoundaryFlag), "wws", "wws", "  s", Character.valueOf('w'), Blocks.wool, Character.valueOf('s'), Items.stick);
		/* Score Area Flag */
        ScoreAreaFlag = new BlockFlag().setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("quidcraft:ScoreAreaFlag").setBlockTextureName("quidcraft:ScoreAreaFlag").setCreativeTab(tabQuidditch);
        GameRegistry.registerBlock(ScoreAreaFlag, "Score Area Flag");
        GameRegistry.addShapelessRecipe(new ItemStack(ScoreAreaFlag), BoundaryFlag, new ItemStack(Items.dye, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(BoundaryFlag), ScoreAreaFlag, new ItemStack(Items.dye, 1, 15));
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(QuidcraftPacketHandler.CHANNEL);
        channel.register(new QuidcraftPacketHandler());
	}
}
