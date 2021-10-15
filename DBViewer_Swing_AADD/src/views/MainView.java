package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

public class MainView {

	private JFrame frame;
	private JButton btnNewButton;
	private JLabel lblTitle;
	private JLabel lblPleaseSelect;
	private JComboBox tableCombo;
	
	/**
	 * Create the application.
	 * 
	 */
	public MainView() {
		SwingUtilities.invokeLater(() -> {
			LafManager.install(new DarculaTheme());
			initialize();
		});
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		setUIComponents();
		setListeners();

		frame.setVisible(true);
	}

	/**
	 * Method that sets all UI Components
	 * 
	 */
	private void setUIComponents() {
		btnNewButton = new JButton("Select");
		btnNewButton.setBounds(94, 157, 100, 25);
		btnNewButton.setFont(new Font("SF Florencesans SC Exp", Font.BOLD, 12));
		frame.getContentPane().add(btnNewButton);
		
		lblTitle = new JLabel("DB Manager");
		lblTitle.setBounds(0, 0, 284, 66);
		lblTitle.setFont(new Font("SF Florencesans Exp", Font.PLAIN, 32));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblTitle);
		
		lblPleaseSelect = new JLabel("Select a table to start:");
		lblPleaseSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelect.setBounds(0, 76, 284, 32);
		lblPleaseSelect.setFont(new Font("SF Florencesans Exp", Font.ITALIC, 12));
		frame.getContentPane().add(lblPleaseSelect);
		
		tableCombo = new JComboBox();
		tableCombo.setBounds(58, 119, 170, 25);
		frame.getContentPane().add(tableCombo);
		
	}

	/**
	 * Method that set listeners and events
	 * 
	 */
	private void setListeners() {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectButtonPressed();
			}
		});
	}
	
	private void selectButtonPressed() {
		ArrayList<String> items = new ArrayList<String>();
		
	}
}
