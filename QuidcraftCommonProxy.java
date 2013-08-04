package assets.quidcraft;

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

public class QuidcraftCommonProxy{

	public int tabQuidditch;

	public void registerRenderThings() {	}
	public void preInit() 	{	}
}
