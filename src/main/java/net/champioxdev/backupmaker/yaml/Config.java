package net.champioxdev.backupmaker.yaml;
import java.util.HashMap;
import java.util.Map;

public class Config {
	private String background = null;
	private String color = null;
	private Map<String, String> button;

	public Config() {}

	public Config(String background, String color, Map<String, String> button) {
		this.background = background;
		this.color = color;
		this.button = button;
	}

	public String getBackground() {
		if (this.background == null) {
			this.background = "#202020";
		}
		return this.background;
	}
	public String getColor() {
		if (this.color == null) {
			this.color = "#ffffff";
		}
		return this.color;
	}
	public Map<String, String> getButton() {
		if(this.button == null) {
			Map<String, String> defaults = new HashMap<String, String>();
			defaults.put("background", "#252525");
			defaults.put("color", "#ffffff");
			this.button = defaults;
		}
		this.button.putIfAbsent("background", "#00a0ff");
		this.button.putIfAbsent("color", "#ffffff");
		return this.button;
	}
}
