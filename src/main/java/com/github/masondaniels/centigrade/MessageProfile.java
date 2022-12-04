package com.github.masondaniels.centigrade;

import java.util.HashMap;
import java.util.Map;

public class MessageProfile {

	private String input;
	private HashMap<String, String> map = new HashMap<String, String>();

	public MessageProfile(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public void add(String group, String string) {
		map.put(group, string);
	}

	public String getMessage() {
		String altered = input;
		for (Map.Entry<String,String> entry : map.entrySet()) {
			altered = altered.replaceAll(entry.getKey(), entry.getValue());
		}
		return altered;
	}
	
	public boolean isAltered() {
		return !map.isEmpty();
	}

}
