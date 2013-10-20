package assets.quidcraft;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import assets.quidcraft.entities.EntityBroom;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class QuidcraftKeyHandler extends KeyHandler {
	Minecraft client = Minecraft.getMinecraft();
	public static final String upDesc = "Broom Up";
	public static final String downDesc = "Broom Down";

	public QuidcraftKeyHandler(int upKey, int downKey) {
		super(new KeyBinding[] { new KeyBinding(upDesc, upKey), new KeyBinding(downDesc, downKey) }, new boolean[] { false, false });
	}

	@Override
	public String getLabel() {
		return "Broom Keys Handler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if (client != null && client.thePlayer != null && tickEnd) {
			Entity ent = client.thePlayer.ridingEntity;
			if (ent != null && ent instanceof EntityBroom) {
				if (kb.keyDescription.equals(upDesc)) {
					client.getNetHandler().addToSendQueue(QuidcraftPacketHandler.getPacket(2));
				} else if (kb.keyDescription.equals(downDesc)) {
					client.getNetHandler().addToSendQueue(QuidcraftPacketHandler.getPacket(0));
				}
			}
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (client != null && client.thePlayer != null && tickEnd) {
			Entity ent = client.thePlayer.ridingEntity;
			if (ent != null && ent instanceof EntityBroom) {
				client.getNetHandler().addToSendQueue(QuidcraftPacketHandler.getPacket(1));
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}
