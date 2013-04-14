package mods.quidcraft;

import java.util.logging.Level;

import mods.quidcraft.blocks.BlockFlag;
import mods.quidcraft.blocks.BlockGoal;
import mods.quidcraft.blocks.BlockQuidditchChest;
import mods.quidcraft.entities.EntityBludger;
import mods.quidcraft.entities.EntityBroom;
import mods.quidcraft.entities.EntityQuaffle;
import mods.quidcraft.entities.EntitySnitch;
import mods.quidcraft.entities.TileEntityGoal;
import mods.quidcraft.items.ItemBat;
import mods.quidcraft.items.ItemBludger;
import mods.quidcraft.items.ItemBludgerGlove;
import mods.quidcraft.items.ItemBroom;
import mods.quidcraft.items.ItemHeadband;
import mods.quidcraft.items.ItemJersey;
import mods.quidcraft.items.ItemQuaffle;
import mods.quidcraft.items.ItemSnitch;
import mods.quidcraft.items.ItemSnitchGlove;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
@Mod(modid="quidcraft",name="Quidcraft",version="0.1")
@NetworkMod(clientSideRequired=true,serverSideRequired=false,channels="Broom",packetHandler=QuidcraftPacketHandler.class)
public class Quidcraft{
	@Instance("Quidcraft")
	public static Quidcraft instance;
	@SidedProxy(clientSide="mods.quidcraft.QuidcraftClientProxy",serverSide="mods.quidcraft.QuidcraftCommonProxy")
	public static QuidcraftCommonProxy proxy;
	public static Item Quaffle,Bludger,Snitch,Broom,Bat,BludgerGlove,SnitchGlove;
	public static Item[] Jersey=new Item[7], Headband=new Item[5];
	public static Block BlockGoal,QuidditchChest,BoundaryFlag,ScoreAreaFlag;
	public static int KEY_UP = Keyboard.KEY_NUMPAD8,KEY_DOWN = Keyboard.KEY_NUMPAD2;
    
	public static int Quaffle_ID, Bludger_ID, Snitch_ID, Bat_ID,Broom_ID,BludgerGlove_ID,
	SnitchGlove_ID,Jersey_ID,Headband_ID;
	public static int BlockGoal_ID,QuidditchChest_ID,BoundaryFlag_ID,ScoreAreaFlag_ID;
	public static final String[] jerseyNames = new String[] {"", "Red", "Green", "Orange", "Blue", "Purple", "Yellow"};
	public static final String[] headbandNames=new String[]{"","Beater","Chaser","Keeper","Seeker"};
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
		Configuration config=new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		Quaffle_ID=config.get("Item", "Quaffle ID", 11160).getInt(11160);
		Bludger_ID=config.get("Item", "Bludger ID", 11161).getInt(11161);
		Snitch_ID=config.get("Item", "Snitch ID", 11162).getInt(11162);
		Bat_ID=config.get("Item", "Bat ID", 11163).getInt(11163);
		Broom_ID=config.get("Item", "Broom ID", 11164).getInt(11164);
		BludgerGlove_ID=config.get("Item", "Bludger Glove ID", 11165).getInt(11165);
		SnitchGlove_ID=config.get("Item", "Snitch Glove ID", 11166).getInt(11166);
		Jersey_ID=config.get("Item", "Jersey ID", 11167).getInt(11167);
		Headband_ID=config.get("Item", "Headband ID", 11174).getInt(11174);
		BlockGoal_ID=config.get("Block", "Block Goal ID", 2000).getInt(2000);
		QuidditchChest_ID=config.get("Block", "Quidditch Chest ID", 2001).getInt(2001);
		BoundaryFlag_ID=config.get("Block", "Boundary Flag ID", 2002).getInt(2002);
		ScoreAreaFlag_ID=config.get("Block", "Score Area Flag ID", 2003).getInt(2003);	
		if (config.hasChanged())
			config.save();
	}
	@Init
	public void loadQuidditch(FMLInitializationEvent event) {

		instance = this;	
		proxy.registerRenderThings();
		/* Entities*/
		EntityRegistry.registerModEntity(EntityBludger.class, "QuidditchModBludger", 1,this,40,1,true);
		EntityRegistry.registerModEntity(EntitySnitch.class, "QuidditchModSnitch", 2,this,40,1,true);
		EntityRegistry.registerModEntity(EntityQuaffle.class, "QuidditchModQuaffle",3,this,40,1,true);
		EntityRegistry.registerModEntity(EntityBroom.class, "QuidditchModBroom",4,this,40,1,true);		
		
		/* Bat */
		Bat = (new ItemBat(Bat_ID)).setUnlocalizedName("quidcraft:Bat").setMaxStackSize(1);
		LanguageRegistry.addName(Bat, "Bat");
		GameRegistry.addRecipe(new ItemStack(Bat), new Object[] {
				"  w", " w ", "s  ", Character.valueOf('s'), Item.stick,
				Character.valueOf('w'), Block.planks});
		/* Bludger */
		Bludger = (new ItemBludger(Bludger_ID)).setUnlocalizedName("quidcraft:Bludger").setMaxStackSize(1);
		LanguageRegistry.addName(Bludger, "Bludger");
		GameRegistry.addRecipe(new ItemStack(Bludger), new Object[] {
				" i ", "ibi", " i ", Character.valueOf('i'), Item.ingotIron,
									 Character.valueOf('b'), Item.bone});
		/* Bludger Glove */
		BludgerGlove = (new ItemBludgerGlove(BludgerGlove_ID)).setUnlocalizedName("quidcraft:BludgerGlove").setMaxStackSize(1);
		LanguageRegistry.addName(BludgerGlove, "Bludger Gloves");
		GameRegistry.addRecipe(new ItemStack(BludgerGlove), new Object[] {
				"i i", Character.valueOf('i'), Item.ingotIron});
		/* Broom */
		Broom = (new ItemBroom(Broom_ID)).setUnlocalizedName("quidcraft:Broom").setMaxStackSize(1);
		LanguageRegistry.addName(Broom, "Broom");
		GameRegistry.addRecipe(new ItemStack(Broom), new Object[] {
				"  s", " s ", "w  ", Character.valueOf('s'), Item.stick,
				Character.valueOf('w'), Item.wheat});
		/* Quaffle */
		Quaffle = (new ItemQuaffle(Quaffle_ID)).setUnlocalizedName("quidcraft:Quaffle").setMaxStackSize(1);
		LanguageRegistry.addName(Quaffle, "Quaffle");
		GameRegistry.addRecipe(new ItemStack(Quaffle), new Object[] {
				" l ", "l l", " l ", Character.valueOf('l'), Item.leather});
		/* Snitch */
		Snitch = (new ItemSnitch(Snitch_ID)).setUnlocalizedName("quidcraft:Snitch").setMaxStackSize(1);
		LanguageRegistry.addName(Snitch, "Snitch");
		GameRegistry.addRecipe(new ItemStack(Snitch, 1), new Object[] {
				"fgf", Character.valueOf('g'), Item.ingotGold,
				Character.valueOf('f'), Item.feather});		
		/* Snitch Glove */
		SnitchGlove = (new ItemSnitchGlove(SnitchGlove_ID)).setUnlocalizedName("quidcraft:SnitchGlove").setMaxStackSize(1);
		LanguageRegistry.addName(SnitchGlove, "Snitch Gloves");
		GameRegistry.addRecipe(new ItemStack(SnitchGlove), new Object[] {
				"l l", Character.valueOf('l'), Item.leather});
		
		/* Jerseys */
		for (int i = 0; i < jerseyNames.length; i++) {
		Jersey[i] = (new ItemJersey(Jersey_ID+i)).setUnlocalizedName("quidcraft:Jersey"+jerseyNames[i]);
		LanguageRegistry.addName(Jersey[i], jerseyNames[i]+" Jersey");
		for (int j=0; j < jerseyNames.length && j!=i; j++)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(Jersey[i]), new Object[] {
					new ItemStack(Jersey[j]),new ItemStack(Item.dyePowder, 1, i)});
			}
		}
		GameRegistry.addRecipe(new ItemStack(Jersey[0]), new Object[] {
			"c c","ccc","ccc", Character.valueOf('c'), Block.cloth});
	
		/* Headbands */
		for (int i = 0; i < headbandNames.length; i++) {
		Headband[i] = (new ItemHeadband(Headband_ID+i)).setUnlocalizedName("quidcraft:Headband"+headbandNames[i]);			
		LanguageRegistry.addName(Headband[i], headbandNames[i]+" Headband");
			for (int j=0; j < headbandNames.length && j!=i; j++)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(Headband[i]), new Object[] {
					new ItemStack(Headband[j]),new ItemStack(Item.dyePowder, 1, i)});
			}
		}
		GameRegistry.addRecipe(new ItemStack(Headband[0]), new Object[] {
			"ccc", Character.valueOf('c'), Block.cloth});
		
		/* Goal Block */
		BlockGoal = (new BlockGoal(BlockGoal_ID)).setHardness(2.5F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("quidcraft:BlockGoal");
		GameRegistry.registerBlock(BlockGoal, "Net Block");
		LanguageRegistry.addName(BlockGoal, "Net Block");
		GameRegistry.addRecipe(new ItemStack(BlockGoal, 5), new Object[] {
				"c c"," c ","c c", Character.valueOf('c'), Block.cloth});
		GameRegistry.registerTileEntity(TileEntityGoal.class, "GoalTile");
		
		/* Quidditch Chest */
		QuidditchChest = (new BlockQuidditchChest(QuidditchChest_ID)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("quidcraft:QuidcraftChest");
		GameRegistry.registerBlock(QuidditchChest, "Quidditch Chest");
		LanguageRegistry.addName(QuidditchChest, "Quidditch Chest");
		GameRegistry.addRecipe(new ItemStack(QuidditchChest), new Object[] {
				" q ", "bcb", " s ", Character.valueOf('q'), Quaffle,
				Character.valueOf('b'), Bludger,
				Character.valueOf('s'), Snitch,
				Character.valueOf('c'), Block.chest});
		
		/* Boundary Flag */
		BoundaryFlag= (new BlockFlag(BoundaryFlag_ID)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep)
				.setUnlocalizedName("quidcraft:Flag");
		GameRegistry.registerBlock(BoundaryFlag, "Boundary Flag");
		LanguageRegistry.addName(BoundaryFlag, "Boundary Flag");
		GameRegistry.addRecipe(new ItemStack(BoundaryFlag), new Object[] {
			"wws", "wws", "  s", Character.valueOf('w'), Block.cloth,
			Character.valueOf('s'), Item.stick});
		
		/* Score Area Flag */
		ScoreAreaFlag= (new BlockFlag(ScoreAreaFlag_ID)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep)
				.setUnlocalizedName("quidcraft:ScoreAreaFlag");
		GameRegistry.registerBlock(ScoreAreaFlag, "Score Area Flag");
		LanguageRegistry.addName(ScoreAreaFlag, "Score Area Flag");
		GameRegistry.addShapelessRecipe(new ItemStack(ScoreAreaFlag), new Object[] {
			 BoundaryFlag, new ItemStack(Item.dyePowder, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(BoundaryFlag), new Object[] {
			ScoreAreaFlag, new ItemStack(Item.dyePowder, 1, 15)});		
	}
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{}
}
