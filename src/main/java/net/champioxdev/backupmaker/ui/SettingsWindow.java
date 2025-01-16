package net.champioxdev.backupmaker.ui;

import net.champioxdev.backupmaker.constants.Constants;
import javax.swing.*;
import java.awt.*;

public class SettingsWindow {

	public SettingsWindow() {
		int spacing = 30;
		System.out.println("[BackUp Maker] " + Constants.getTimestamp() + " Loading settings");
		JFrame window = new JFrame("BackUp Maker v" + Constants.getVersion());
		window.setSize(334, 534);
		window.setResizable(false);
		window.setLocationRelativeTo(null);


		window.getContentPane().setBackground(Color.decode(Constants.getConfig().getBackground()));
		window.getContentPane().setForeground(Color.decode(Constants.getConfig().getColor()));
		window.setSize(window.getWidth(), window.getHeight());

		Label backgroundLabel = new Label("Background: " + Constants.getConfig().getBackground());
		backgroundLabel.setForeground(Color.decode(Constants.getConfig().getColor()));
		Label colorLabel = new Label("Color: " + Constants.getConfig().getColor());
		colorLabel.setForeground(Color.decode(Constants.getConfig().getColor()));

		backgroundLabel.setBounds(5, 0, window.getWidth() - 5, spacing);
		colorLabel.setBounds(5, spacing, window.getWidth() - 5, spacing);

		window.add(backgroundLabel);
		window.add(colorLabel);

		window.setLayout(null);
		window.setVisible(true);
	}
}
