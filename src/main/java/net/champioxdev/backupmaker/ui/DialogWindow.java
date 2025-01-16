package net.champioxdev.backupmaker.ui;

import net.champioxdev.backupmaker.constants.Constants;
import javax.swing.*;
import java.awt.*;

public class DialogWindow extends JFrame {
	private final JFrame confirmDialog = new JFrame("BackUp Maker v" + Constants.getVersion());
	public DialogWindow(String text) {
		new DialogWindow(text, DialogType.INFO);
	}

	/**
	 * DialogWindow can be used to create a popup with an icon, text and an <code>OK</code> button
	 * @param text
	 * The Information given to the user
	 * @param type
	 * Sets the visible icon according to the DialogType
	 *
	 * @see DialogType
	 */
	public DialogWindow(String text, DialogType type) {
		confirmDialog.setSize(320, 200);
		confirmDialog.setResizable(false);
		confirmDialog.setLocationRelativeTo(null);
		confirmDialog.setAlwaysOnTop(true);

		confirmDialog.getContentPane().setBackground(Color.decode(Constants.getConfig().getBackground()));
		confirmDialog.getContentPane().setForeground(Color.decode(Constants.getConfig().getColor()));

		JLabel label = new JLabel("<html>" + text + "</html>");
		Button button = new Button("Ok");

		label.setForeground(Color.decode(Constants.getConfig().getColor()));
		label.setBounds(90, 10, 240, 100);

		button.addActionListener(e -> confirmDialog.setVisible(false));
		button.setBounds(245, 125, 65, 35);

		confirmDialog.add(label);
		confirmDialog.add(button);
		confirmDialog.setLayout(null);

		JLabel icon = new JLabel();
		icon.setBounds(15, 35, 50, 50);
		Image image;
		switch (type) {
			case WARNING: image = new ImageIcon("warning.png").getImage().getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH); break;
			case ERROR: image = new ImageIcon("error.png").getImage().getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH); break;
			case INFO:
			default: image = new ImageIcon("info.png").getImage().getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH);
		}
		icon.setIcon(new ImageIcon(image));

		confirmDialog.add(icon);

		confirmDialog.setVisible(true);
	}
}
