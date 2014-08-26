package org.spigot.sidebar;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Com extends Command {

	public Com(Sidebar sidebar) {
		super("sb", "sb"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@SuppressWarnings("deprecation")
	public void sendmsg(CommandSender sender, String msg) {
		if (sender instanceof ProxiedPlayer) {
			((ProxiedPlayer) sender).sendMessage("§c[§aSideBar§c] §2" + msg); //$NON-NLS-1$
		} else {
			ProxyServer.getInstance().getLogger().info("[SideBar] " + msg); //$NON-NLS-1$
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			if (!((ProxiedPlayer) sender).hasPermission("sb.use")) { //$NON-NLS-1$
				return;
			}
		}
		if (args[0].equals("set")) { //$NON-NLS-1$
			String obj = args[1];
			if (shared.getInstance().scoreboardexists(obj)) {
				shared.setactivescoreboard(obj);
				sendmsg(sender, Messages.getString("Com.success")); //$NON-NLS-1$
			} else {
				sendmsg(sender, Messages.getString("Com.notfound")); //$NON-NLS-1$
			}
		} else if (args[0].equals("unfreeze")) { //$NON-NLS-1$
			shared.getInstance().blocked = false;
			sendmsg(sender, Messages.getString("Com.success")); //$NON-NLS-1$

		} else if (args[0].equals("freeze")) { //$NON-NLS-1$
			shared.getInstance().blocked = true;
			sendmsg(sender, Messages.getString("Com.success")); //$NON-NLS-1$

		} else if (args[0].equals("next")) { //$NON-NLS-1$
			synchronized (shared.getInstance().o) {
				shared.getInstance().o.notify();
			}
			sendmsg(sender, Messages.getString("Com.success")); //$NON-NLS-1$

		} else if (args[0].equals("frozen")) { //$NON-NLS-1$
			if (shared.getInstance().blocked) {
				sendmsg(sender, Messages.getString("Com.blocked")); //$NON-NLS-1$
			} else {
				sendmsg(sender, Messages.getString("Com.notblocked")); //$NON-NLS-1$
			}

		} else if (args[0].equals("reload")) { //$NON-NLS-1$
			shared.getInstance().blocked = true;
			shared.removeall();
			config conf = new config("plugins/sidebar/config.txt"); //$NON-NLS-1$
			conf.load();
			shared.getInstance().blocked = false;
			sendmsg(sender, Messages.getString("Com.reload")); //$NON-NLS-1$
		}

	}

}
