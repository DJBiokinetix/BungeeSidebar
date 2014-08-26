package org.spigot.sidebar;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.packet.ScoreboardDisplay;
import net.md_5.bungee.protocol.packet.ScoreboardObjective;
import net.md_5.bungee.protocol.packet.ScoreboardScore;

public class shared {
	private static shared instance = null;
	protected HashMap<String, objective> objectives;
	protected String activeobjective;
	protected int activeobjectiveint;
	protected HashMap<ProxiedPlayer, String> rec;
	protected HashMap<Integer, String> mapper;
	protected boolean random = false;
	protected int delay = 5000;
	protected List<String> exserver;
	protected boolean blocked = false;
	protected Runnable run;
	protected Object o;

	
	//Prepare the variable and make it double
	private shared() {

	}

	//To protect the code from deprecation
	protected synchronized static void addscoreboard(String name, objective objective, boolean excluded) {
		if (!scoreboardexists(name)) {
			objective.name = name;
			if (!excluded) {
				int len = shared.getInstance().objectives.size();
				shared.getInstance().mapper.put(len, name);
			}
			shared.getInstance().objectives.put(name, objective);
		}
	}

	//To unite all constants within our function
	protected synchronized static void removescoreboard(String name) {
		if (scoreboardexists(name)) {
			shared.getInstance().objectives.remove(name);
		}
	}

	//To denounce the evil of blur and cast
	protected synchronized static void setactivescoreboard(String name) {
		if (!shared.getInstance().activeobjective.equals(name) && scoreboardexists(name)) {
			senditems(name);
			shared.getInstance().activeobjective = name;
		}
	}

	
	//To extend our reach to the class above 
	public synchronized static void removeall() {
		HashMap<ProxiedPlayer, String> maps = shared.getInstance().rec;
		Set<ProxiedPlayer> keyset = maps.keySet();
		for (ProxiedPlayer player : keyset) {
			remove(player,false);
		}
		shared.getInstance().rec=new HashMap<ProxiedPlayer, String>();
	}

	protected synchronized static void remove(ProxiedPlayer player, boolean nicz) {
		String oldobj = shared.getInstance().rec.get(player);
		player.unsafe().sendPacket(new ScoreboardObjective(oldobj, oldobj, oldobj, (byte) 1));
	}
	
	protected synchronized static void remove(ProxiedPlayer player) {
		remove(player,false);
		shared.getInstance().rec.remove(player);
	}

	protected synchronized static void senditems(String name) {
		if (scoreboardexists(name)) {
			Collection<ProxiedPlayer> players = ProxyServer.getInstance().getPlayers();
			for (ProxiedPlayer player : players) {
				String ps = player.getServer().getInfo().getName();
				if (!(shared.getInstance().exserver.contains(ps))) {
					updateplayer(player, name);
				}
			}
		}
	}

	protected synchronized static void updateplayer(ProxiedPlayer player, String objname, boolean forced) {
		if (!(shared.getInstance().exserver.contains(player.getServer().getInfo().getName()))) {
			if ((!(shared.getInstance().activeobjective.equals(objname))) || forced) {
				String oldobj = shared.getInstance().rec.get(player);
				player.unsafe().sendPacket(new ScoreboardObjective(oldobj, oldobj, oldobj, (byte) 1));
				objective obj = shared.getInstance().objectives.get(objname);
				player.unsafe().sendPacket(new ScoreboardObjective(objname, obj.title, objname, (byte) 0));
				for (scoreboarditem item : obj.items) {
					player.unsafe().sendPacket(new ScoreboardScore(item.name, (byte) 0, objname, item.value));
				}
				player.unsafe().sendPacket(new ScoreboardDisplay((byte) 1, objname));
				shared.getInstance().rec.put(player, objname);
			}
		}
	}

	protected static void updateplayer(ProxiedPlayer player) {
		updateplayer(player, shared.getInstance().activeobjective, false);
	}

	protected static void updateplayer(ProxiedPlayer player, boolean forced) {
		updateplayer(player, shared.getInstance().activeobjective, forced);
	}

	protected static void updateplayer(ProxiedPlayer player, String obj) {
		updateplayer(player, obj, false);
	}

	protected static boolean scoreboardexists(String name) {
		if (shared.getInstance().objectives.containsKey(name)) {
			return true;
		} else {
			return false;
		}
	}

	public static shared getInstance() {
		if (instance == null) {
			instance = new shared();
		}
		return instance;
	}
}
