package justintib.quidcraft;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class QuidcraftKeyHandler extends KeyHandler{
	 public static KeyBinding broomUpKey = new KeyBinding("key.broomUp", 46);  //tied to c (see KeyboardKEY.png file)
	 public static KeyBinding broomDownKey = new KeyBinding("key.broomDown", 29);  //tied to ctrl (see KeyboardKEY.png file)
//public static KeyBinding broomMouseKey = new KeyBinding("key.broomMouse", 47);  //tied to v (see KeyboardKEY.png file)
	 public static int keyDoes = 0;
	    
	public QuidcraftKeyHandler() {
		super(new KeyBinding[]{broomUpKey,broomDownKey},new boolean[]{true,true});
		
	}

	@Override
	public String getLabel() {
		return "Broom Keys Handler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
		// TODO Auto-generated method stub
		if (Minecraft.getMinecraft().theWorld != null)
		{
    	Entity ent=Minecraft.getMinecraft().thePlayer.ridingEntity;
    	if (ent!=null && ent instanceof EntityBroom){
		keyDoes = 0;
    	if(broomUpKey.pressed){
    		keyDoes = 1;
    	}
		if(broomDownKey.pressed){
			keyDoes = 2;
		}
		if(broomUpKey.isPressed() && broomDownKey.isPressed())
			keyDoes = 0;
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
		// TODO Auto-generated method stub
		return null;
	}

}
