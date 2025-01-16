package net.champioxdev.backupmaker.constants;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.champioxdev.backupmaker.ui.DialogType;
import net.champioxdev.backupmaker.ui.DialogWindow;
import net.champioxdev.backupmaker.yaml.Config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Constants {
	private static final File configFile = new File(System.getProperty("user.dir") + File.separator + "config.yml");
	public static void init() {
		try {
			if (configFile.createNewFile() || configFile.length() == 0) {
				System.out.println("[BackUp Maker] " + Constants.getTimestamp() + " Creating configuration file");
				PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + File.separator +"config.yml", "UTF-8");
				writer.println("# Default configuration for Version v" + Constants.getVersion());
				writer.println("background: #1e1e2e");
				writer.println("color: #ffffff");
				writer.println("button:");
				writer.println("\tbackground: #252525");
				writer.println("\tcolor: #ffffff");
			} else {
				System.out.println("[BackUp Maker] " + Constants.getTimestamp() + " Found configuration file at: " + System.getProperty("user.dir") + File.separator + "config.yml");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	public static String getAuthor() { return "ChampioxDev"; };
	public static String getVersion() {
		return "1.1-alpha";
	}

	public static String getTimestamp() {
		Date date = new Date();
		String seconds = String.valueOf(date.getSeconds());
		String minutes = String.valueOf(date.getMinutes());
		String hours = String.valueOf(date.getHours());
		if (seconds.length() == 1) { seconds = "0" + seconds; }
		if (minutes.length() == 1) { minutes = "0" + minutes; }
		if (hours.length() == 1) { hours = "0" + hours; }

		return String.format("%s:%s:%s", hours, minutes, seconds);
	}

	public static Config getConfig() {
		try {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			// reads the value of the File and maps them to the Config class
			return mapper.readValue(configFile, Config.class);
		} catch (IOException e) {
			new DialogWindow("IO Exception when reading Config", DialogType.ERROR);
			throw new RuntimeException(e);
		}
	}
}