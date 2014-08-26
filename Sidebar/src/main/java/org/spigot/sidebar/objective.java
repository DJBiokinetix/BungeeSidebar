package org.spigot.sidebar;

import java.util.ArrayList;
import java.util.List;

public class objective {
	public String name;
	public String title;
	public int displayslot;
	public List<scoreboarditem> items;

	public objective(String title) {
		this.title=title;
		this.items=new ArrayList<scoreboarditem>();
	}
	
	
	public synchronized void additem(String name, int value) {
		scoreboarditem item = new scoreboarditem(name,value);
		this.items.add(item);
	}
}
