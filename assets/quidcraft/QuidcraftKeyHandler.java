package assets.quidcraft;

import assets.quidcraft.entities.EntityBroom;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;

public class QuidcraftKeyHandler{
	public static final String upDesc = "fly.up";
	public static final String downDesc = "fly.down";
    public static final String cat = "key.categories.movement";
    private KeyBinding up, down;

	public QuidcraftKeyHandler(int upKey, int downKey) {
        for(KeyBinding key:Minecraft.getMinecraft().gameSettings.keyBindings){
            if(up==null && key.getKeyDescription().contains(upDesc)){
                up = key;
            }
            if(down==null && key.getKeyDescription().contains(downDesc)){
                down = key;
            }
        }
        if(up==null){
		    up = new KeyBinding("key."+upDesc, upKey, cat);
            ClientRegistry.registerKeyBinding(up);
        }
        if(down==null){
            down = new KeyBinding("key."+downDesc, downKey, cat);
            ClientRegistry.registerKeyBinding(down);
        }
	}

	@SubscribeEvent
	public void keyDown(InputEvent.KeyInputEvent event) {
		if (Minecraft.getMinecraft().thePlayer != null) {
			Entity ent = Minecraft.getMinecraft().thePlayer.ridingEntity;
			if (ent != null && ent instanceof EntityBroom) {
				if (Keyboard.getEventKey() == up.getKeyCode()) {
					Quidcraft.proxy.channel.sendToServer(QuidcraftPacketHandler.getPacket(2, Side.SERVER));
				} else if (Keyboard.getEventKey() == down.getKeyCode()) {
                    Quidcraft.proxy.channel.sendToServer(QuidcraftPacketHandler.getPacket(0, Side.SERVER));
				}else if(!up.getIsKeyPressed()&& !down.getIsKeyPressed()){
                    Quidcraft.proxy.channel.sendToServer(QuidcraftPacketHandler.getPacket(1, Side.SERVER));
                }
			}
		}
	}
}
