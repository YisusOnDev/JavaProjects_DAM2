package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

import dao.StudentDAO;
import models.Student;
import utils.StudentHelper;

public class MainView {

	private final int NAME_LENGHT = 40;
	private final int PHONE_LENGHT = 13;

	private JFrame frame;
	private JLabel lblTitle;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnFirst;
	private JLabel lblRegistrationNum;
	private JLabel lblName;
	private JButton btnNew;
	private JButton btnSave;
	private JLabel lblBirthdate;
	private JLabel lblPhoneNumber;
	private JTextField tFieldRegistrationNumber;
	private JTextField tFieldName;
	private JTextField tFieldBirthdate;
	private JTextField tFieldPhoneNumber;

	private boolean isNewFlag = false;
	private ArrayList<Student> students;
	private int currentIndex = 0;

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
		frame = new JFrame("Student Manager");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		students = new ArrayList<Student>();

		getDataFromDb();
		setUIComponents();
		setListeners();
		showCurrent();

		frame.setVisible(true);
	}

	/**
	 * Method that sets all UI Components
	 */
	private void setUIComponents() {
		lblTitle = new JLabel("Student Manager");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("SF Florencesans Exp", Font.BOLD, 18));
		lblTitle.setBounds(10, 11, 464, 35);
		frame.getContentPane().add(lblTitle);

		btnNew = new JButton("New");
		btnNew.setFont(new Font("Montserrat", Font.PLAIN, 12));
		btnNew.setBounds(385, 57, 89, 23);
		frame.getContentPane().add(btnNew);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Montserrat", Font.PLAIN, 12));
		btnSave.setBounds(385, 95, 89, 23);
		frame.getContentPane().add(btnSave);

		btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Montserrat", Font.PLAIN, 12));
		btnPrevious.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnPrevious);

		btnNext = new JButton("Next");
		btnNext.setFont(new Font("Montserrat", Font.PLAIN, 12));
		btnNext.setBounds(109, 227, 89, 23);
		frame.getContentPane().add(btnNext);

		btnLast = new JButton("Last");
		btnLast.setFont(new Font("Montserrat", Font.PLAIN, 12));
		btnLast.setBounds(385, 227, 89, 23);
		frame.getContentPane().add(btnLast);

		btnFirst = new JButton("First");
		btnFirst.setFont(new Font("Montserrat", Font.PLAIN, 12));
		btnFirst.setBounds(286, 227, 89, 23);
		frame.getContentPane().add(btnFirst);

		lblRegistrationNum = new JLabel("Registration Number:");
		lblRegistrationNum.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblRegistrationNum.setBounds(20, 57, 150, 25);
		frame.getContentPane().add(lblRegistrationNum);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblName.setBounds(20, 91, 150, 25);
		frame.getContentPane().add(lblName);

		lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblBirthdate.setBounds(20, 127, 150, 25);
		frame.getContentPane().add(lblBirthdate);

		lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblPhoneNumber.setBounds(20, 163, 150, 25);
		frame.getContentPane().add(lblPhoneNumber);

		tFieldRegistrationNumber = new JTextField();
		tFieldRegistrationNumber.setBounds(180, 57, 195, 25);
		frame.getContentPane().add(tFieldRegistrationNumber);
		tFieldRegistrationNumber.setColumns(10);

		tFieldName = new JTextField();
		tFieldName.setColumns(10);
		tFieldName.setBounds(180, 95, 195, 25);
		frame.getContentPane().add(tFieldName);

		tFieldBirthdate = new JTextField();
		tFieldBirthdate.setColumns(10);
		tFieldBirthdate.setBounds(180, 128, 195, 25);
		frame.getContentPane().add(tFieldBirthdate);

		tFieldPhoneNumber = new JTextField();
		tFieldPhoneNumber.setColumns(10);
		tFieldPhoneNumber.setBounds(180, 164, 195, 25);
		frame.getContentPane().add(tFieldPhoneNumber);
	}

	/**
	 * Method that set listeners and events
	 */
	private void setListeners() {
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousButtonPressed();
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextButtonPressed();
			}
		});

		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstButtonPressed();
			}
		});

		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastButtonPressed();
			}
		});

		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newButtonPressed();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveButtonPressed();
			}
		});
	}

	/**
	 * Get previous student data (if there) and show on screen
	 */
	private void previousButtonPressed() {
		isNewFlag = false;
		if (currentIndex - 1 >= 0) {
			currentIndex -= 1;
			showCurrent();
		} else {
			JOptionPane.showMessageDialog(frame, "There is no previous student, you're on the first one.",
					"No previous student", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Get next student data (if there) and show on screen
	 */
	private void nextButtonPressed() {
		isNewFlag = false;
		if (currentIndex + 1 <= students.size() - 1) {
			currentIndex += 1;
			showCurrent();
		} else {
			JOptionPane.showMessageDialog(frame, "There is no next student, you're on the last one.",
					"No next student", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/**
	 * Show the first student data on screen
	 */
	private void firstButtonPressed() {
		isNewFlag = false;
		currentIndex = 0;
		showCurrent();

	}

	/**
	 * Show the last student data on screen
	 */
	private void lastButtonPressed() {
		isNewFlag = false;
		currentIndex = students.size() - 1;
		showCurrent();

	}

	/**
	 * Reset all text fields in order to create a new student
	 */
	private void newButtonPressed() {
		isNewFlag = true;
		resetTextFields();

	}

	/**
	 * Saves the current user if edited or create a new one
	 */
	private void saveButtonPressed() {
		StudentDAO DB = new StudentDAO();
		Student student = checkStudent();
		if (student != null) {
			if (isNewFlag) {
				students.add(student);
				DB.insertStudent(student);
				JOptionPane.showMessageDialog(frame, "New student successfully inserted into database",
						"Operation success", JOptionPane.INFORMATION_MESSAGE);
				currentIndex += 1;
			} else {
				int identifier = students.get(currentIndex).getRegistrationNumber();
				students.set(currentIndex, student);
				DB.updateStudent(student, identifier);
				JOptionPane.showMessageDialog(frame, "New student successfully updated and saved into database",
						"Operation success", JOptionPane.INFORMATION_MESSAGE);
			}
			isNewFlag = false;
			showCurrent();
		}
	}

	/**
	 * Method that gets all student from db and saves it into students ArrayList
	 */
	private void getDataFromDb() {
		isNewFlag = false;
		students = new StudentDAO().getAllStudents();
		currentIndex = 0;
	}

	/**
	 * Method to show currentIndex student data on screen if there is data of course
	 */
	private void showCurrent() {
		if (students != null) {
			if (students.size() == 0) {
				JOptionPane.showMessageDialog(frame, "There is no students in database, feel free to create the first one!",
						"No students", JOptionPane.INFORMATION_MESSAGE);
			} else {
				Student firstStudent = students.get(currentIndex);
				tFieldRegistrationNumber.setText(firstStudent.getRegistrationNumber().toString());
				tFieldName.setText(firstStudent.getName());
				tFieldBirthdate.setText(firstStudent.getBirthdate().toString());
				tFieldPhoneNumber.setText(firstStudent.getPhoneNumber());
			}	
		}
	}

	/**
	 * Sets all text fields blank
	 */
	private void resetTextFields() {
		tFieldRegistrationNumber.setText("");
		tFieldName.setText("");
		tFieldBirthdate.setText("");
		tFieldPhoneNumber.setText("");
	}

	/**
	 * Method that checks if all textfields are valid and return a new student if
	 * those are ok
	 * 
	 * @return valid Student object
	 */
	private Student checkStudent() {
		boolean valid = true;
		int registrationNumber = 0;
		String name = "";
		String birthdate = "";
		String phonenumber = "";

		if (StudentHelper.isNumber(tFieldRegistrationNumber.getText())) {
			registrationNumber = Integer.parseInt(tFieldRegistrationNumber.getText());
		} else {
			JOptionPane.showMessageDialog(frame, "Registration number must be a number", "Format error",
					JOptionPane.ERROR_MESSAGE);
			valid = false;
		}

		if (isNewFlag || registrationNumber != students.get(currentIndex).getRegistrationNumber()) {
			if (!new StudentDAO().isValidRegistrationNumber(registrationNumber)) {
				JOptionPane.showMessageDialog(frame, "That Registration number already exists...",
						"Duplicate registration number", JOptionPane.ERROR_MESSAGE);
				valid = false;
			}
		}

		if (StudentHelper.hasCorrectLenght(tFieldName.getText(), NAME_LENGHT)) {
			name = tFieldName.getText();
		} else {
			JOptionPane.showMessageDialog(frame, "Name cannot contain more than " + NAME_LENGHT + " characters.",
					"Format error", JOptionPane.ERROR_MESSAGE);
			valid = false;
		}

		if (StudentHelper.isValidDate(tFieldBirthdate.getText())) {
			birthdate = tFieldBirthdate.getText();
		} else {
			JOptionPane.showMessageDialog(frame, "Birthdate does not have the correct format (yyyy-MM-dd)",
					"Format error", JOptionPane.ERROR_MESSAGE);
			valid = false;
		}

		if (StudentHelper.hasCorrectLenght(tFieldPhoneNumber.getText(), PHONE_LENGHT)
				&& StudentHelper.isNumber(tFieldPhoneNumber.getText())) {
			phonenumber = tFieldPhoneNumber.getText();
		} else {
			JOptionPane.showMessageDialog(frame,
					"Phone number must be numbers and cannot contain more than " + PHONE_LENGHT + " characters.",
					"Format error", JOptionPane.ERROR_MESSAGE);
			valid = false;
		}

		if (valid) {
			return (new Student(registrationNumber, name, birthdate, phonenumber));
		} else {
			return null;
		}
	}

}
