package justintib.quidcraft;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
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
@Mod(modid="Quidcraft",name="Quidcraft",version="0.1")
@NetworkMod(clientSideRequired=true,serverSideRequired=false)
public class Quidcraft{
	@Instance("Quidcraft")
	public static Quidcraft instance;
	@SidedProxy(clientSide="justintib.quidcraft.QuidcraftClientProxy",serverSide="justintib.quidcraft.QuidcraftCommonProxy")
	public static QuidcraftCommonProxy proxy;
	public static Item Quaffle,Bludger,Snitch,Broom,Bat,BludgerGlove,SnitchGlove;
	public static Item Jersey, Headband;
	public static Block BlockGoal,QuidditchChest,BoundaryFlag,ScoreAreaFlag;
	
	public static int Quaffle_ID, Bludger_ID, Snitch_ID, Bat_ID,Broom_ID,BludgerGlove_ID,
	SnitchGlove_ID,Jersey_ID,Headband_ID;
	public static int BlockGoal_ID,QuidditchChest_ID,BoundaryFlag_ID,ScoreAreaFlag_ID;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
		Configuration config=new Configuration(event.getSuggestedConfigurationFile());
		try 
		{	config.load();
			Quaffle_ID=config.get("Item", "Quaffle ID", 1160).getInt(1160);
			Bludger_ID=config.get("Item", "Bludger ID", 1161).getInt(1161);
			Snitch_ID=config.get("Item", "Snitch ID", 1162).getInt(1162);
			Bat_ID=config.get("Item", "Bat ID", 1163).getInt(1163);
			Broom_ID=config.get("Item", "Broom ID", 1164).getInt(1164);
			BludgerGlove_ID=config.get("Item", "Bludger Glove ID", 1165).getInt(1165);
			SnitchGlove_ID=config.get("Item", "Snitch Glove ID", 1166).getInt(1166);
			Jersey_ID=config.get("Item", "Jersey ID", 1167).getInt(1167);
			Headband_ID=config.get("Item", "Headband ID", 1168).getInt(1168);
			BlockGoal_ID=config.get("Block", "Block Goal ID", 200).getInt(200);
			QuidditchChest_ID=config.get("Block", "Quidditch Chest ID", 201).getInt(201);
			BoundaryFlag_ID=config.get("Block", "Boundary Flag ID", 202).getInt(202);
			ScoreAreaFlag_ID=config.get("Block", "Score Area Flag ID", 203).getInt(203);	
		}
		catch(Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Quidcraft failed to load config file");
		}
		finally
		{
			config.save();
		}
	}
	@Init
	public void loadQuidditch(FMLInitializationEvent event) {

		instance = this;	
		
		/* Entities*/
		EntityRegistry.registerModEntity(EntityBludger.class, "QuidditchModBludger", 1,this,40,1,true);
		EntityRegistry.registerModEntity(EntitySnitch.class, "QuidditchModSnitch", 2,this,40,1,true);
		EntityRegistry.registerModEntity(EntityQuaffle.class, "QuidditchModQuaffle",3,this,40,1,true);
		EntityRegistry.registerModEntity(EntityBroom.class, "QuidditchModBroom",4,this,40,1,true);		
		GameRegistry.registerTileEntity(TileEntityGoal.class, "GoalTile");
		
		/* Bat */
		Bat = (new ItemBat(Bat_ID)).setItemName("Bat").setMaxStackSize(1).setIconIndex(0);
		LanguageRegistry.addName(Bat, "Bat");
		GameRegistry.addRecipe(new ItemStack(Bat, 1), new Object[] {
				"  w", " w ", "s  ", Character.valueOf('s'), Item.stick,
				Character.valueOf('w'), Block.planks});
		/* Bludger */
		Bludger = (new ItemBludger(Bludger_ID)).setItemName("Bludger").setMaxStackSize(1).setIconIndex(1);
		LanguageRegistry.addName(Bludger, "Bludger");
		GameRegistry.addRecipe(new ItemStack(Bludger, 1), new Object[] {
				" i ", "ibi", " i ", Character.valueOf('i'), Item.ingotIron,
									 Character.valueOf('b'), Item.bone});
		/* Bludger Glove */
		BludgerGlove = (new ItemBludgerGlove(BludgerGlove_ID)).setItemName("BludgerGlove").setMaxStackSize(1).setIconIndex(2);
		LanguageRegistry.addName(BludgerGlove, "Bludger Gloves");
		GameRegistry.addRecipe(new ItemStack(BludgerGlove, 1), new Object[] {
				"i i", Character.valueOf('i'), Item.ingotIron});
		/* Broom */
		Broom = (new ItemBroom(Broom_ID)).setItemName("Broom").setMaxStackSize(1).setIconIndex(3);
		LanguageRegistry.addName(Broom, "Broom");
		GameRegistry.addRecipe(new ItemStack(Broom, 1), new Object[] {
				"  s", " s ", "w  ", Character.valueOf('s'), Item.stick,
				Character.valueOf('w'), Item.wheat});
		/* Quaffle */
		Quaffle = (new ItemQuaffle(Quaffle_ID)).setItemName("Quaffle").setMaxStackSize(1).setIconIndex(4);
		LanguageRegistry.addName(Quaffle, "Quaffle");
		GameRegistry.addRecipe(new ItemStack(Quaffle, 1), new Object[] {
				" l ", "l l", " l ", Character.valueOf('l'), Item.leather});
		/* Snitch */
		Snitch = (new ItemSnitch(Snitch_ID)).setItemName("Snitch").setMaxStackSize(1).setIconIndex(5);
		LanguageRegistry.addName(Snitch, "Snitch");
		GameRegistry.addRecipe(new ItemStack(Snitch, 1), new Object[] {
				"fgf", Character.valueOf('g'), Item.ingotGold,
				Character.valueOf('f'), Item.feather});		
		/* Snitch Glove */
		SnitchGlove = (new ItemSnitchGlove(SnitchGlove_ID)).setItemName("SnitchGlove").setMaxStackSize(1).setIconIndex(6);
		LanguageRegistry.addName(SnitchGlove, "Snitch Gloves");
		GameRegistry.addRecipe(new ItemStack(SnitchGlove, 1), new Object[] {
				"l l", Character.valueOf('l'), Item.leather});
		
		/* Jerseys */
		Jersey = (new ItemJersey(Jersey_ID, proxy.jerseySkin)).setItemName("Jersey").setIconIndex(9);
				
		LanguageRegistry.addName(Jersey, "Jersey");
		GameRegistry.addRecipe(new ItemStack(Jersey, 1), new Object[] {
				"c c","ccc","ccc", Character.valueOf('c'), Block.cloth});
		/*LanguageRegistry.addName(JerseyRed, "Red Jersey");
		LanguageRegistry.addName(JerseyOrange, "Orange Jersey");
		LanguageRegistry.addName(JerseyYellow, "Yellow Jersey");
		LanguageRegistry.addName(JerseyGreen, "Green Jersey");
		LanguageRegistry.addName(JerseyBlue, "Blue Jersey");
		LanguageRegistry.addName(JerseyPurple, "Purple Jersey");*/
		
		/* Headbands */
		Headband = (new ItemHeadband(Headband_ID,proxy.headbandSkin)).setItemName("Headband").setIconIndex(27);
				
		LanguageRegistry.addName(Headband, "Headband");
		GameRegistry.addRecipe(new ItemStack(Headband, 1), new Object[] {
				"ccc", Character.valueOf('c'), Block.cloth});
		
		/*LanguageRegistry.addName(HeadbandKeeper, "Keeper Headband");
		LanguageRegistry.addName(HeadbandSeeker, "Seeker Headband");
		LanguageRegistry.addName(HeadbandChaser, "Chaser Headband");
		LanguageRegistry.addName(HeadbandBeater, "Beater Headband");*/
		
		/* Goal Block */
		BlockGoal = (new BlockGoal(BlockGoal_ID, 0)).setHardness(2.5F).setStepSound(Block.soundClothFootstep)
				.setBlockName("BlockGoal");
		GameRegistry.registerBlock(BlockGoal, "Net Block");
		LanguageRegistry.addName(BlockGoal, "Net Block");
		GameRegistry.addRecipe(new ItemStack(BlockGoal, 5), new Object[] {
				"c c"," c ","c c", Character.valueOf('c'), Block.cloth});
		
		/* Quidditch Chest */
		QuidditchChest = (new BlockQuidditchChest(QuidditchChest_ID, 0)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep)
				.setBlockName("QuidditchChest");
		GameRegistry.registerBlock(QuidditchChest, "Quidditch Chest");
		LanguageRegistry.addName(QuidditchChest, "Quidditch Chest");
		GameRegistry.addRecipe(new ItemStack(QuidditchChest, 1), new Object[] {
				" q ", "bcb", " s ", Character.valueOf('q'), Quaffle,
				Character.valueOf('b'), Bludger,
				Character.valueOf('s'), Snitch,
				Character.valueOf('c'), Block.chest});
		
		/* Boundary Flag */
		BoundaryFlag= (new BlockFlag(BoundaryFlag_ID, 16)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep)
				.setBlockName("BoundaryFlag");
		GameRegistry.registerBlock(BoundaryFlag, "Boundary Flag");
		LanguageRegistry.addName(BoundaryFlag, "Boundary Flag");
		GameRegistry.addRecipe(new ItemStack(BoundaryFlag, 1), new Object[] {
			"wws", "wws", "  s", Character.valueOf('w'), Block.cloth,
			Character.valueOf('s'), Item.stick});
		
		/* Score Area Flag */
		ScoreAreaFlag= (new BlockFlag(ScoreAreaFlag_ID, 17)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep)
				.setBlockName("ScoreAreaFlag");
		GameRegistry.registerBlock(ScoreAreaFlag, "Score Area Flag");
		LanguageRegistry.addName(ScoreAreaFlag, "Score Area Flag");
		GameRegistry.addShapelessRecipe(new ItemStack(ScoreAreaFlag, 1), new Object[] {
			 BoundaryFlag, new ItemStack(Item.dyePowder, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(BoundaryFlag, 1), new Object[] {
			ScoreAreaFlag, new ItemStack(Item.dyePowder, 1, 15)});
		
		proxy.registerRenderThings();
	}
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{}
}
