package org.spigot.sidebar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class config {
	private String file;

	public config(String loc) {
		this.file = loc;
		shared.getInstance().objectives = new HashMap<String, objective>();
		shared.getInstance().mapper = new HashMap<Integer, String>();
		shared.getInstance().rec = new HashMap<ProxiedPlayer, String>();
		shared.getInstance().exserver = new ArrayList<String>();

		shared.getInstance().activeobjective = "INIT@@@@@@@@@@@@@@@@@"; //$NON-NLS-1$
	}

	public boolean load() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(new File(file)));
		} catch (FileNotFoundException e1) {
			return false;
		}

		try {
			boolean inmsg = false;
			int delay = 5000;
			boolean random = false;
			boolean inservers = false;
			List<String> names = new ArrayList<String>();
			List<String> texts = new ArrayList<String>();
			List<String> excludeservers = new ArrayList<String>();

			objective objective = new objective("INIT"); //$NON-NLS-1$
			String name = genname(names);
			String title = Messages.getString("config.untitled"); //$NON-NLS-1$
			boolean exclude = false;
			while (in.ready()) {
				String s = in.readLine();
				if (s.equals("")) { //$NON-NLS-1$
					continue;
				}
				if (s.startsWith("[msg]")) { //$NON-NLS-1$
					name = genname(names);
					title = Messages.getString("config.untitled"); //$NON-NLS-1$
					exclude = false;
					inmsg = true;
					if (s.length() > 5) {
						String str = s.substring(5);
						String[] params = str.split(","); //$NON-NLS-1$
						for (String r : params) {
							if (r.startsWith("name=")) { //$NON-NLS-1$
								if (r.length() > 5) {
									if (!(names.contains(r.substring(5)))) {
										String namer = r.substring(5);
										if (namer.length() > 15) {
											name = namer.substring(0, 15);
										} else {
											name = namer;
										}
									}
								}
							} else if (r.startsWith("exclude=")) { //$NON-NLS-1$
								if (r.length() > 8) {
									exclude = Boolean.parseBoolean(r.substring(8));
								}
							} else if (r.startsWith("title=")) { //$NON-NLS-1$
								if (r.length() > 6) {
									String titler = r.substring(6);
									if (titler.length() > 31) {
										title = titler.substring(0, 31);
									} else {
										title = titler;
									}
									title=title.replace("&", "§");
								}
							}
						}
					}
					objective = new objective(title);
					texts = new ArrayList<String>();
					continue;
				} else if (s.startsWith("[/msg]")) { //$NON-NLS-1$
					int i = 0;
					int len=texts.size();
					for (String text : texts) {
						objective.additem(text, len-i);
						i++;
					}
					shared.addscoreboard(name, objective, exclude);
					inmsg = false;
					continue;
				} else if (inmsg) {
					s=s.replace("&", "§");
					if (s.length() > 15) {
						texts.add(s.substring(0, 15));
					} else {
						texts.add(s);
					}
					continue;
				} else if (s.startsWith("delay=")) { //$NON-NLS-1$
					if (s.length() > 6) {
						delay = Integer.parseInt(s.substring(6)) * 1000;
					}
					continue;
				} else if (s.startsWith("random=")) { //$NON-NLS-1$
					if (s.length() > 7) {
						random = Boolean.parseBoolean(s.substring(7));
					}
					continue;
				} else if (s.equals("[excludeservers]")) { //$NON-NLS-1$
					inservers = true;
					continue;
				} else if (s.equals("[/excludeservers]")) { //$NON-NLS-1$
					inservers = false;
					continue;
				} else if (inservers) {
					excludeservers.add(s);
				}

			}
			in.close();
			shared.getInstance().random = random;
			shared.getInstance().delay = delay;
			shared.getInstance().exserver = excludeservers;
			shared.getInstance().activeobjectiveint = 1;
			
			String newer = shared.getInstance().mapper.get(1);
			shared.getInstance().activeobjective=newer;
			shared.setactivescoreboard(newer);

		} catch (IOException e) {
			return false;
		}
		return true;
	}

	private String genname(List<String> existing) {
		String vinal;
		do {
			char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray(); //$NON-NLS-1$
			StringBuilder sb = new StringBuilder();
			sb.append("@_"); //$NON-NLS-1$
			Random random = new Random();
			for (int i = 0; i < 8; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb.append(c);
			}
			vinal = sb.toString();
		} while (existing.contains(vinal));
		return vinal;
	}

}
