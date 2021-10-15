package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.cj.util.StringUtils;

public class StudentHelper {

	/**
	 * Method that check if some string is numeric and also if starts with (+)
	 * country digit
	 * 
	 * @param s the string to check
	 * @return true if is number false if not
	 */
	public static boolean isNumber(String s) {
		if (s.charAt(0) == '+') {
			return true;
		} else {
			return StringUtils.isStrictlyNumeric(s);
		}
	}

	/**
	 * Check if current string has a correct lenght
	 * 
	 * @param s      the string
	 * @param lenght maximum lenght
	 * @return true if ok false if not ok
	 */
	public static boolean hasCorrectLenght(String s, int lenght) {
		if (s.length() <= lenght) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if a string date is in valid date format
	 * 
	 * @param inDate string date
	 * @return true if valid date false if not valid date
	 */
	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

}
