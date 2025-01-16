package net.champioxdev.backupmaker.constants;

public class SystemInfo {
	public static boolean isMacOS() {
		return System.getProperty("os.name").toLowerCase().contains("mac");
	}
}
