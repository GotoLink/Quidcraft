package justintib.quidcraft;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.inventory.Container;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class QuidcraftTickHandler implements ITickHandler
{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean onTickInGUI(float f, Minecraft minecraft, GuiScreen guiscreen) {
		if ((guiscreen instanceof GuiContainerCreative)
				&& !(lastGuiOpen instanceof GuiContainerCreative)
				&& !minecraft.theWorld.isRemote) {
			Container container = ((GuiContainer) guiscreen).inventorySlots;
			/*List list = ((ContainerCreative)container).itemList;
			int i = 0;
			list.add(new ItemStack(QuidditchChest, 1, i));
			list.add(new ItemStack(BlockGoal, 1, i));
			list.add(new ItemStack(BoundaryFlag, 1, i));
			list.add(new ItemStack(ScoreAreaFlag, 1, i));*/
			// continuing the list.add format for all of your blocks.
		}
		lastGuiOpen = guiscreen;
		return true;
	}

    private static GuiScreen lastGuiOpen;

}
