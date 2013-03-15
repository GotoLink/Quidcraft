package justintib.quidcraft;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class QuidcraftKeyHandler extends KeyHandler{
	 public static KeyBinding broomUpKey = new KeyBinding("key.broomUp", 46);  //tied to c 
	 public static KeyBinding broomDownKey = new KeyBinding("key.broomDown", 29);  //tied to ctrl
	 //public static KeyBinding broomMouseKey = new KeyBinding("key.broomMouse", 47);  //tied to v
	    
	public QuidcraftKeyHandler() {
		super(new KeyBinding[]{broomUpKey,broomDownKey},new boolean[]{true,true});
		
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
		if(broomUpKey.pressed){
    		Quidcraft.proxy.sendPacket(2,ent.riddenByEntity);
    	}
    	else if(broomDownKey.pressed){
			Quidcraft.proxy.sendPacket(0,ent.riddenByEntity);
		}
    	else 
			Quidcraft.proxy.sendPacket(1,ent.riddenByEntity);
		//if(broomMouseKey.pressed){
		//	pressKey(broomMouseKey.keyCode);
		//}
		
		/*if(Minecraft.getMinecraft().currentScreen == null)
        {
        lastGuiOpen = null;
        }
        return true;*/
	}}}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		// TODO Auto-generated method stub	
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
