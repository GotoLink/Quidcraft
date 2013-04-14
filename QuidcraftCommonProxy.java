package mods.quidcraft;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;

public class QuidcraftCommonProxy implements IGuiHandler{

	public int tabQuidditch;

	public void registerRenderThings() {	}
	public void preInit() 	{	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendPacket(int i, Entity playerEntity) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(2);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeShort(i);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Broom";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
                EntityPlayerMP player = (EntityPlayerMP) playerEntity;
        } else if (side == Side.CLIENT) {
                EntityClientPlayerMP player = (EntityClientPlayerMP) playerEntity;
                player.sendQueue.addToSendQueue(packet);
        		}
	}

}
