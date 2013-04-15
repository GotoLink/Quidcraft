package mods.quidcraft;

import java.util.EnumSet;

import mods.quidcraft.entities.EntityBroom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class QuidcraftKeyHandler extends KeyHandler{
	public QuidcraftKeyHandler(int upKey, int downKey) {
		super(new KeyBinding[]{new KeyBinding("Broom Up", upKey),new KeyBinding("Broom Down", downKey)},new boolean[]{true,true});	
	}

	@Override
	public String getLabel() {
		return "Broom Keys Handler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		Minecraft client = Minecraft.getMinecraft();
		if (client!=null && client.thePlayer != null)
		{
			Entity ent=client.thePlayer.ridingEntity;
			if (ent!=null && ent instanceof EntityBroom){
				if(kb.keyDescription=="Broom Up"){
					Quidcraft.proxy.sendPacket(2,ent.riddenByEntity);
				}
				else if(kb.keyDescription=="Broom Down"){
					Quidcraft.proxy.sendPacket(0,ent.riddenByEntity);
				}
			}
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		Minecraft client = Minecraft.getMinecraft();
		if (client!=null && client.thePlayer != null)
		{
			Entity ent=client.thePlayer.ridingEntity;
			if (ent!=null && ent instanceof EntityBroom){
				Quidcraft.proxy.sendPacket(1,ent.riddenByEntity);
			}		
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
