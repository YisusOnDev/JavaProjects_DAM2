package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import io.ReadWrite;

public class MainView {
	private JLabel lblWindowTitle;
	private JFrame frmCsvconverter;
	private JTextPane textPane;
	private JLabel lblPreview;
	private JButton btnLoadFile;
	private JScrollPane scrollPane;
	
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
		frmCsvconverter.setBounds(100, 100, 650, 450);
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
		lblWindowTitle.setFont(new Font("Yu Gothic UI", Font.BOLD, 32));
		lblWindowTitle.setBounds(10, 11, 240, 60);
		frmCsvconverter.getContentPane().add(lblWindowTitle);

		lblPreview = new JLabel("CSV/Data Preview");
		lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreview.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPreview.setBounds(272, 35, 352, 29);
		frmCsvconverter.getContentPane().add(lblPreview);

		btnLoadFile = new JButton("Load File");
		btnLoadFile.setBounds(20, 106, 105, 25);
		frmCsvconverter.getContentPane().add(btnLoadFile);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(272, 75, 352, 325);
		frmCsvconverter.getContentPane().add(scrollPane);

		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		
		JButton btnConvert = new JButton("Conver to ");
		btnConvert.setBounds(135, 106, 105, 25);
		frmCsvconverter.getContentPane().add(btnConvert);

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
	}

	private void loadFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a CSV/DAT file");
		jfc.setAcceptAllFileFilterUsed(false);
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
			previewFile();
		}
	}

	private String getFileExtension(String fullPath) {
		return (fullPath.endsWith(".csv") ? "csv" : "dat");
	}
	
	private void setupButtons() {
		if (currentFileExt.equalsIgnoreCase("csv")) {
			// CSVToBinary
			// SortCSV
		} else {
			// BinaryToCsv
			//BinaryToSortedCsv
			// SortBianry
		}
	}
	
	private void previewFile() {
		String formattedText = "";
		for (String line : currentFile) {
			formattedText += line + "\n";
		}
		textPane.setText(formattedText);
	}
}