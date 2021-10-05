package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;


public class MainView {
	private JLabel lblWindowTitle;
	private JFrame frmCsvconverter;
	private JTextPane textPane;
	private JLabel lblPreview;
	private JButton btnLoadCsv;

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
		lblPreview.setBounds(315, 42, 309, 29);
		frmCsvconverter.getContentPane().add(lblPreview);
		
		btnLoadCsv = new JButton("Load CSV");
		btnLoadCsv.setBounds(10, 100, 89, 23);
		frmCsvconverter.getContentPane().add(btnLoadCsv);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 82, 309, 300);
		frmCsvconverter.getContentPane().add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setBackground(Color.LIGHT_GRAY);

		frmCsvconverter.setResizable(false);
		frmCsvconverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Sets UI Elements Listeners...
	 */
	private void setUIListeners() {
	}
}