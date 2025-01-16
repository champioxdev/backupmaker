package net.champioxdev.backupmaker.compression;

import net.champioxdev.backupmaker.constants.Constants;
import net.champioxdev.backupmaker.ui.DialogType;
import net.champioxdev.backupmaker.ui.DialogWindow;
import net.champioxdev.backupmaker.ui.FileChooser;
import org.kamranzafar.jtar.TarEntry;
import org.kamranzafar.jtar.TarOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZInputStream;
import org.tukaani.xz.XZOutputStream;

import java.awt.*;
import java.io.*;

public class Compress {
	private static String filename = System.getProperty("user.dir") + File.separator + "BackUp at " + Constants.getTimestamp().replace(":","_") + ".tar";
	public static void compress() {
			new Thread(() -> {
				try {
					FileOutputStream target = new FileOutputStream(filename);
					TarOutputStream output = new TarOutputStream(new BufferedOutputStream(target));
					File[] file = new File[FileChooser.getFiles().size()];
					for (int i = 0; i <= file.length - 1; i++) {
						file[i] = new File(FileChooser.getFiles().get(i));
					}
					for (int j = 0; j <= file.length - 1; j++) {
						File f = file[j];
						output.putNextEntry(new TarEntry(f, f.getName()));
						BufferedInputStream origin = new BufferedInputStream(new FileInputStream(f));
						output.write(origin.readAllBytes());
						output.flush();
						origin.close();
					}
					output.close();

					FileOutputStream xzTarget = new FileOutputStream(filename.replace(".tar", ".ersin"));
					FileInputStream xzOrigin = new FileInputStream(filename);
					XZOutputStream xzOutput = new XZOutputStream(xzTarget, new LZMA2Options(9));

					byte[] buffer = new byte[16384];
					int dataRead;

					while ((dataRead = xzOrigin.read(buffer)) != -1) {
						xzOutput.write(buffer, 0, dataRead);
					}
					xzOutput.finish();

					new DialogWindow("BackUp created at " + Constants.getTimestamp());

				} catch (FileNotFoundException e) {
					new DialogWindow("FileNotFoundException", DialogType.ERROR);
					throw new RuntimeException(e);
				} catch (IOException e) {
					new DialogWindow("IOException", DialogType.ERROR);
					throw new RuntimeException(e);
				} catch (OutOfMemoryError e) {
					new DialogWindow("Not Enough RAM", DialogType.ERROR);
				}
			}).start();
	}
	public static void decompress() {
		new Thread(() -> {
			try {
				FileInputStream origin = new FileInputStream(filename.replace(".tar", ".ersin"));
				XZInputStream xzOrigin = new XZInputStream(origin);
				FileOutputStream output = new FileOutputStream(System.getProperty("user.home") + File.separator + "BackUp.tar");

				byte[] buffer = new byte[16384];
				int dataRead;

				while ((dataRead = xzOrigin.read(buffer)) != -1) {
					output.write(buffer, 0, dataRead);
				}
				output.flush();
				new DialogWindow("Decompressed");
				output.close();
				xzOrigin.close();
				origin.close();
				Desktop.getDesktop().open(new File(System.getProperty("user.home")));
			} catch (FileNotFoundException e) {
				new DialogWindow("File Not Found", DialogType.ERROR);
				throw new RuntimeException(e);
			} catch (IOException e) {
				new DialogWindow("Decompressed");
				try {
					Desktop.getDesktop().open(new File(System.getProperty("user.home")));
				} catch (IOException ex) {
					new DialogWindow("Error showing file in user.home", DialogType.ERROR);
					throw new RuntimeException(ex);
				}
				throw new RuntimeException(e);
			}
		}).start();
	}
}
