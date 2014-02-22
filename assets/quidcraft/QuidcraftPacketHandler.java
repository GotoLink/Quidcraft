package assets.quidcraft;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import assets.quidcraft.entities.EntityBroom;

public class QuidcraftPacketHandler {
	public static final String CHANNEL = "Quidcraft:Broom";

	@SubscribeEvent
	public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) {
		if (event.packet.channel().equals(CHANNEL)) {
			this.handle(event.packet, ((NetHandlerPlayServer)event.handler).playerEntity);
		}
	}

	private void handle(FMLProxyPacket packet, EntityPlayer player) {
		ByteBuf buf = packet.payload();
		short data = buf.readShort();
		Entity ent = player.ridingEntity;
		if (ent != null && ent instanceof EntityBroom) {
			((EntityBroom) ent).isGoingUp = false;
			((EntityBroom) ent).isGoingDown = false;
			if (data == 2)
				((EntityBroom) ent).isGoingUp = true;
			else if (data == 0)
				((EntityBroom) ent).isGoingDown = true;
		}
	}

	public static FMLProxyPacket getPacket(int i, Side side) {
		ByteBuf payload = Unpooled.buffer();
        payload.writeShort(i);
		FMLProxyPacket packet = new FMLProxyPacket(payload, CHANNEL);
		packet.setTarget(side);
		return packet;
	}
}
