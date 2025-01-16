package net.champioxdev.backupmaker.ui;

import net.champioxdev.backupmaker.constants.Constants;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
	public Button(String text) {
		super(text);
		super.setBackground(Color.decode(Constants.getConfig().getButton().get("background")));
		super.setOpaque(true);
		super.setBorderPainted(false);
		super.setForeground(Color.decode(Constants.getConfig().getButton().get("color")));
		super.setFocusPainted(false);
	}
}
