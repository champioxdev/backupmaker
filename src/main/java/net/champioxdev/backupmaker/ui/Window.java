package net.champioxdev.backupmaker.ui;

import net.champioxdev.backupmaker.compression.Compress;
import net.champioxdev.backupmaker.constants.Constants;
import net.champioxdev.backupmaker.constants.SystemInfo;

import javax.swing.*;
import java.awt.*;

public class Window {
	private JFrame window = new JFrame("BackUp Maker v" + Constants.getVersion());
	private JMenuBar menuBar = new JMenuBar();
	private JMenu windowMenu = new JMenu("Edit");
	private JMenuItem configReloadMenu = new JMenuItem("Reload Configuration");
	public static JLabel label = new JLabel();

	public Window() {
		System.out.println("[BackUp Maker] " + Constants.getTimestamp() + " Loading window");
		window.setSize(854, 534);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Taskbar.getTaskbar().setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

		window.getContentPane().setBackground(Color.decode(Constants.getConfig().getBackground()));
		window.getContentPane().setForeground(Color.decode(Constants.getConfig().getColor()));
		window.setSize(window.getWidth(), window.getHeight());

		Button button = new Button("Create BackUp");
		button.setBounds((window.getWidth() / 2 ) - 75, window.getHeight() - 100, 150, 50);
		button.addActionListener(e -> {

			Compress.compress();
		});
		window.add(button);

		Button button1 = new Button("Open Folder");
		button1.setBounds((window.getWidth() / 2 ) - 75, window.getHeight() - 175, 150, 50);
		button1.addActionListener(e -> new FileChooser());
		window.add(button1);

		Button button2 = new Button("Decompress");
		button2.setBounds((window.getWidth() / 2 ) - 75, window.getHeight() - 250, 150, 50);
		button2.addActionListener(e -> Compress.decompress());
		window.add(button2);

		label.setBounds(10, 10, 834, 514);
		label.setBackground(Color.decode(Constants.getConfig().getBackground()));
		label.setForeground(Color.decode(Constants.getConfig().getColor()));
		label.setText("<html>");
		window.add(label);

		configReloadMenu.addActionListener(e -> {
			System.out.println("[BackUp Maker] " + Constants.getTimestamp() + " Reloading Config");
			window.setVisible(false);
			Constants.init();
			Constants.getConfig();
			new Window();
		});
		configReloadMenu.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		JMenuItem testdialog = new JMenuItem("Test Dialog Window");
		testdialog.addActionListener(e -> {
			new DialogWindow("This is just a test", DialogType.INFO);
			new DialogWindow("This is just a test", DialogType.WARNING);
			new DialogWindow("This is just a test", DialogType.ERROR);
		});
		testdialog.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));


		if (SystemInfo.isMacOS()) {
			Desktop.getDesktop().setPreferencesHandler(p -> new SettingsWindow());
			Desktop.getDesktop().setAboutHandler(e -> new DialogWindow("<b>BackUp Maker v" + Constants.getVersion() + "</b><br>by " + Constants.getAuthor()));
		} else {
			JMenu file = new JMenu("File");
			JMenuItem about = new JMenuItem("About BackUp Maker v" + Constants.getVersion());
			about.addActionListener(e -> new DialogWindow("<b>BackUp Maker v" + Constants.getVersion() + "</b><br>by " + Constants.getAuthor()));
			JMenuItem settings = new JMenuItem("Settings");
			settings.addActionListener(e -> new SettingsWindow());
			file.add(about);
			file.add(settings);
			menuBar.add(file);
		}

		window.add(testdialog);

		windowMenu.add(configReloadMenu);
		windowMenu.add(testdialog);
		menuBar.add(windowMenu);

		window.setJMenuBar(menuBar);
		window.setVisible(true);
	}
}
