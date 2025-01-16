package net.champioxdev.backupmaker.ui;

import net.champioxdev.backupmaker.constants.Constants;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileChooser extends JFrame {
	private static List<String> files = new LinkedList<>();
	private final JFrame window = new JFrame("Select Files or Folders");
	public FileChooser() {
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.getContentPane().setBackground(Color.decode(Constants.getConfig().getBackground()));
		window.getContentPane().setForeground(Color.decode(Constants.getConfig().getColor()));
		window.setSize(768, 480);
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setBounds(0 ,0, window.getWidth(), window.getHeight());
		fileChooser.addActionListener(e -> {
			window.setVisible(false);
			if (fileChooser.getSelectedFile() == null) {
				return;
			}
			System.out.println(fileChooser.getSelectedFile());
			files.add(fileChooser.getSelectedFile().toString());
			Window.label.setText(Window.label.getText() + fileChooser.getSelectedFile() + "<br>");
		});
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setBackground(Color.decode(Constants.getConfig().getBackground()));
		fileChooser.setForeground(Color.decode(Constants.getConfig().getColor()));
		window.add(fileChooser);
		window.setVisible(true);
	}
	public static List<String> getFiles() {
		return files;
	}
}
