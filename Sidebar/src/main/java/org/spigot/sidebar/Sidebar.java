package org.spigot.sidebar;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Sidebar extends Plugin {

	@Override
	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, new Events());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Com(this));

		config conf = new config("plugins/sidebar/config.txt");
		conf.load();
		clog("Sidebar Enabled");

		shared.getInstance().o=new Object();
		
		ProxyServer.getInstance().getScheduler().runAsync(this, new Runnable() {
			@Override
			public void run() {
				Object o = shared.getInstance().o;
				synchronized (o) {
					try {
						int max = shared.getInstance().mapper.size();
						if (max > 2) {
							while (true) {
								if (!(shared.getInstance().blocked)) {
									int cur = shared.getInstance().activeobjectiveint;
									int ne = cur;
									if (shared.getInstance().random) {
										while (("" + cur).equals("" + ne)) {
											ne = (int) (Math.random() * 10000) % max;
										}
									} else {
										ne = (cur + 1) % max;
									}
									shared.getInstance().activeobjectiveint = ne;
									String newer = shared.getInstance().mapper.get(ne);
									shared.setactivescoreboard(newer);
								}
								o.wait(shared.getInstance().delay);
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});


	}

	
	
	
	private void clog(String msg) {
		ProxyServer.getInstance().getLogger().info(msg);
	}
}
