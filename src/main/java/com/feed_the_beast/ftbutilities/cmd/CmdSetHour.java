package com.feed_the_beast.ftbutilities.cmd;

import com.feed_the_beast.ftblib.lib.cmd.CmdBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * @author LatvianModder
 */
public class CmdSetHour extends CmdBase
{
	public CmdSetHour()
	{
		super("set_hour", Level.OP);
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		checkArgs(sender, args, 1);
		server.getWorld(0).getWorldInfo().setWorldTime(((server.getWorld(0).getWorldInfo().getWorldTime() / 24000L) * 24000L) + 24000L + parseInt(args[0]) * 1000);
	}
}