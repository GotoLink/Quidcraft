package assets.quidcraft;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import assets.quidcraft.entities.EntityBroom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class QuidcraftPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
	{
		if (packet.channel.equals("Broom")) 
		{
            this.handle(packet,player);
		}
	}

	private void handle(Packet250CustomPayload packet, Player player) {
		DataInputStream inStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		short data;
		try {
           data = inStream.readShort();
		} catch (IOException e) {
            e.printStackTrace();
            return;
		}
		Entity ent = ((EntityPlayer)player).ridingEntity;
		if (ent!=null && ent instanceof EntityBroom){
				((EntityBroom)ent).isGoingUp=false;
				((EntityBroom)ent).isGoingDown=false;
			if (data==2)
				((EntityBroom)ent).isGoingUp=true;
			else if (data==0)
				((EntityBroom)ent).isGoingDown=true;		
		}
	}
}
