package com.feed_the_beast.ftbu.cmd;

import com.feed_the_beast.ftbl.lib.client.ClientUtils;
import com.feed_the_beast.ftbl.lib.cmd.CmdBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * @author LatvianModder
 */
public class CmdShrug extends CmdBase
{
	public CmdShrug()
	{
		super("shrug", Level.ALL);
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		ClientUtils.execClientCommand("\u00AF\\_(\u30C4)_/\u00AF");
	}
}