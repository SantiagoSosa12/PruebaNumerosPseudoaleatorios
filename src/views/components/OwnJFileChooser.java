package views.components;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OwnJFileChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;

	public OwnJFileChooser() {
		setBackground(Color.WHITE);
		setFont(new Font("Arial", 0, 12));
		setAcceptAllFileFilterUsed(false);
		setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/"));

		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Archivos txt", "txt");
		addChoosableFileFilter(fnef);
	}
}