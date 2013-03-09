package justintib.quidcraft;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class QuidcraftPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// TODO Auto-generated method stub
		
	}
	 /*public void HandlePacket(Packet230ModLoader packet)
    {
        switch(packet.packetType)
        {
            case 0:
            {
                int EntityID = packet.dataInt[0];
                
                List entitiesList = ModLoader.getMinecraftInstance().theWorld.getLoadedEntityList();
                for(int i = 0; i < entitiesList.size(); i++){
                    if(entitiesList.get(i) instanceof EntityBroom){
                    	EntityBroom broom = (EntityBroom) entitiesList.get(i);
                        if(broom.entityId == EntityID && broom.riddenByEntity == null){
                        	//broom.setPositionAndRotation2(packet.dataFloat[0],
                        	//		packet.dataFloat[1], packet.dataFloat[2],
                        	//		packet.dataFloat[6], broom.rotationPitch, 0);
                        	//broom.setVelocity(packet.dataFloat[3],
                        	//		packet.dataFloat[4], packet.dataFloat[5]);
                        	//broom.posX = packet.dataFloat[0];
                        	//broom.posY = packet.dataFloat[1];
                        	//broom.posZ = packet.dataFloat[2];
                        	//broom.motionX = packet.dataFloat[3];
                        	//broom.motionY = packet.dataFloat[4];
                        	//broom.motionZ = packet.dataFloat[5];
                        	broom.rotationYaw = packet.dataFloat[6];
                        }
                    }
                }
            }
        }
    }*/
}
