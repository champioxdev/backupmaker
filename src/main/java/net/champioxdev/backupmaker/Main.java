package net.champioxdev.backupmaker;

import net.champioxdev.backupmaker.constants.SystemInfo;
import net.champioxdev.backupmaker.ui.Window;
import net.champioxdev.backupmaker.constants.Constants;

public class Main {
	public static void main(String[] args) {
		System.out.println("[BackUp Maker] " + Constants.getTimestamp() + " Starting v" + Constants.getVersion());
		if (SystemInfo.isMacOS()) {
			System.setProperty("apple.awt.application.name", "BackUp Maker" + Constants.getVersion());
			System.setProperty( "apple.awt.application.appearance", "NSAppearanceNameDarkAqua" );
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		Constants.init();
		new Window();
	}
}
