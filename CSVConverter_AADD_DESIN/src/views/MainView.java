package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import io.ReadWrite;
import utils.Logger;

public class MainView {
	private JLabel lblWindowTitle;
	private JFrame frmCsvconverter;
	private JTextPane textPane;
	private JLabel lblPreview;
	private JLabel lblCurrentPreviewPath;
	private JButton btnLoadFile;
	private JScrollPane scrollPane;
	private JButton btnConvert;
	private JButton btnSort;
	private JButton btnBinaryToSortedCsv;

	private String currentFilePath;
	private String currentFileExt;
	private ArrayList<String> currentFile;

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCsvconverter = new JFrame("CSV Converter");
		frmCsvconverter.setTitle("CSV Converter");
		frmCsvconverter.setBounds(100, 100, 650, 304);
		frmCsvconverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCsvconverter.getContentPane().setLayout(null);

		setUIComponents();
		setUIListeners();

		frmCsvconverter.setVisible(true);
	}

	/**
	 * Sets UI components
	 */
	private void setUIComponents() {
		lblWindowTitle = new JLabel("CSV Converter");
		lblWindowTitle.setForeground(new Color(204, 0, 0));
		lblWindowTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblWindowTitle.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblWindowTitle.setBounds(0, 0, 216, 64);
		frmCsvconverter.getContentPane().add(lblWindowTitle);

		lblPreview = new JLabel("Opened File Preview");
		lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreview.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPreview.setBounds(214, 35, 410, 29);
		frmCsvconverter.getContentPane().add(lblPreview);

		btnLoadFile = new JButton("Load File");
		btnLoadFile.setBounds(20, 75, 184, 25);
		frmCsvconverter.getContentPane().add(btnLoadFile);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 75, 410, 140);
		frmCsvconverter.getContentPane().add(scrollPane);

		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		textPane.setBackground(Color.LIGHT_GRAY);

		btnConvert = new JButton("Conver to ");
		btnConvert.setBounds(20, 111, 184, 25);
		btnConvert.setVisible(false);
		frmCsvconverter.getContentPane().add(btnConvert);

		btnSort = new JButton("Sort");
		btnSort.setBounds(20, 147, 184, 25);
		btnSort.setVisible(false);
		frmCsvconverter.getContentPane().add(btnSort);

		btnBinaryToSortedCsv = new JButton("Convert to Sorted CSV");
		btnBinaryToSortedCsv.setBounds(20, 183, 184, 25);
		btnBinaryToSortedCsv.setVisible(false);
		frmCsvconverter.getContentPane().add(btnBinaryToSortedCsv);

		lblCurrentPreviewPath = new JLabel("Current file: ");
		lblCurrentPreviewPath.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentPreviewPath.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblCurrentPreviewPath.setBounds(20, 226, 604, 22);
		lblCurrentPreviewPath.setVisible(false);
		frmCsvconverter.getContentPane().add(lblCurrentPreviewPath);

		frmCsvconverter.setResizable(false);
		frmCsvconverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Sets UI Elements Listeners...
	 */
	private void setUIListeners() {
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});

		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentFileExt.equalsIgnoreCase("csv")) {
					convertFile(".dat");
				} else {
					convertFile(".csv");
				}
			}
		});

		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortFile();
			}
		});

		btnBinaryToSortedCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				binaryToSortedCsv();
			}
		});

		frmCsvconverter.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Logger.saveLogToFile("log.txt");
			}
		});
	}

	/**
	 * Method that let the user load a file into the app
	 */
	private void loadFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a CSV/DAT file");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setMultiSelectionEnabled(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV and DAT files", "csv", "dat");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			currentFilePath = jfc.getSelectedFile().getPath();
			currentFileExt = getFileExtension(currentFilePath);
			if (currentFileExt.equalsIgnoreCase("csv")) {
				currentFile = ReadWrite.readCsv(currentFilePath);
			} else {
				currentFile = ReadWrite.readBinary(currentFilePath);
			}
			setupButtons();
			previewFile(currentFilePath);
		}
	}

	/**
	 * Method that let the user to convert a file
	 * 
	 * @param targetExt the target extension to convert
	 */
	public void convertFile(String targetExt) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Save file path (no need to put extension to file)");
		jfc.setMultiSelectionEnabled(false);
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV and DAT files", "csv", "dat");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String savePath = jfc.getSelectedFile().getPath() + targetExt;
			if (targetExt.equalsIgnoreCase(".csv") && ReadWrite.writeBinary(savePath, currentFile) == true) {
				JOptionPane.showMessageDialog(frmCsvconverter, "Converted file saved in: " + savePath);
			} else if (targetExt.equalsIgnoreCase(".dat") && ReadWrite.writeCsv(savePath, currentFile) == true) {
				JOptionPane.showMessageDialog(frmCsvconverter, "Converted file saved in: " + savePath);
			} else {
				JOptionPane.showMessageDialog(frmCsvconverter, "Please select a valid path.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frmCsvconverter, "You cancelled file save action.");
		}
	}

	/**
	 * Method that let the user to sort file and save it
	 */
	public void sortFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Save file path (no need to put extension to file)");
		jfc.setMultiSelectionEnabled(false);
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV and DAT files", "csv", "dat");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String savePath = jfc.getSelectedFile().getPath() + "." + currentFileExt;
			if (currentFileExt.equalsIgnoreCase("csv") && ReadWrite.sortCsv(currentFilePath, savePath) == true) {
				JOptionPane.showMessageDialog(frmCsvconverter, "Sorted file saved in: " + savePath);
			} else if (currentFileExt.equalsIgnoreCase("dat")
					&& ReadWrite.sortBinary(currentFilePath, savePath) == true) {
				JOptionPane.showMessageDialog(frmCsvconverter, "Sorted file saved in: " + savePath);
			} else {
				JOptionPane.showMessageDialog(frmCsvconverter, "Please select a valid path.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frmCsvconverter, "You cancelled file save action.");
		}
	}

	/**
	 * Magic method that convert and sort a file and save it to the user preferred
	 * path.
	 */
	private void binaryToSortedCsv() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Save file path (no need to put extension to file)");
		jfc.setMultiSelectionEnabled(false);
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV and DAT files", "csv", "dat");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String savePath = jfc.getSelectedFile().getPath() + ".csv";
			if (ReadWrite.binaryFileToSortedCsv(currentFilePath, savePath) == true) {
				JOptionPane.showMessageDialog(frmCsvconverter, "Sorted & converted file saved in: " + savePath);
			} else {
				JOptionPane.showMessageDialog(frmCsvconverter, "Please select a valid path.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frmCsvconverter, "You cancelled file save action.");
		}
	}

	/**
	 * Simple method that returns the file extension of a path
	 * 
	 * @param fullPath path of file
	 * @return the file path as String
	 */
	private String getFileExtension(String fullPath) {
		return (fullPath.endsWith(".csv") ? "csv" : "dat");
	}

	/**
	 * Method that setup the buttons based in what type of file is loaded
	 */
	private void setupButtons() {
		if (currentFileExt.equalsIgnoreCase("csv")) {
			btnConvert.setText("Convert to Binary");
			btnConvert.setVisible(true);
			btnSort.setText("Sort CSV");
			btnSort.setVisible(true);
			btnBinaryToSortedCsv.setVisible(false);
		} else {
			btnConvert.setText("Convert to CSV");
			btnConvert.setVisible(true);
			btnSort.setText("Sort Binary");
			btnSort.setVisible(true);
			btnBinaryToSortedCsv.setVisible(true);
		}
	}

	/**
	 * Method that insert file info into textField
	 * 
	 * @param filePath file path to show in info text label
	 */
	private void previewFile(String filePath) {
		String formattedText = "";
		for (String line : currentFile) {
			formattedText += line + "\n";
		}
		textPane.setText(formattedText);

		if (filePath != null) {
			lblCurrentPreviewPath.setText("Current file: " + filePath);
			lblCurrentPreviewPath.setVisible(true);
		}
	}
}