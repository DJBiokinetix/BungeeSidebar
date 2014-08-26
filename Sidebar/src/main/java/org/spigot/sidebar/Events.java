package org.spigot.sidebar;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Events implements Listener {


	public Events() {
	}

	@EventHandler
	public void onServerSwitch(ServerSwitchEvent event) {
		ProxiedPlayer player = event.getPlayer();
		String ps = player.getServer().getInfo().getName();
		if (shared.getInstance().exserver.contains(ps)) {
			if (shared.getInstance().rec.containsKey(player)) {
				shared.remove(player);
			}
		} else {
			shared.updateplayer(player, true);
		}
	}
}
