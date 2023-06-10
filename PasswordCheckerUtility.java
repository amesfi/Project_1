/*
	Class: CMSC 204
	CRN: 40541
	Instructor: Professor Gary C. Thai
	Project: 1
	Due Date: 6/11/23
	Programmer: Abidara Mesfin
*/

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	public static boolean isValidPassword(String password) throws Exception {
	    // Check length
	    if (password.length() < 6) {
	        throw new LengthException("The password must be at least 6 characters long");
	    }

	    // Check other requirements
	    validatePassword(password);

	    // Check for absence of an uppercase alphabetic character
	    boolean hasUpperAlpha = false;
	    for (char c : password.toCharArray()) {
	        if (Character.isUpperCase(c)) {
	            hasUpperAlpha = true;
	            break;
	        }
	    }
	    if (!hasUpperAlpha) {
	        throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
	    }

	    // Check for invalid sequence
	    for (int i = 0; i < password.length() - 1; i++) {
	        char currentChar = password.charAt(i);
	        char nextChar = password.charAt(i + 1);

	        if (isInvalidSequence(currentChar, nextChar)) {
	            throw new InvalidSequenceException("The password contains an invalid sequence of characters");
	        }
	    }

	    return true;
	}

    private static boolean isInvalidSequence(char c1, char c2) {
        return (Character.isLetter(c1) && Character.isLetter(c2) && Math.abs(c1 - c2) == 1)
                || (Character.isDigit(c1) && Character.isDigit(c2) && Math.abs(c1 - c2) == 1);
    }

    public static boolean isWeakPassword(String password) throws Exception {
        if (password.length() >= 6 && password.length() <= 9) {
            throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
        }

        return isValidPassword(password);
    }

    public static ArrayList<String> getInvalidPasswords(List<String> passwordList) {
        ArrayList<String> invalidPasswords = new ArrayList<>();

        for (String password : passwordList) {
            try {
                isValidPassword(password);
            } catch (Exception e) {
                invalidPasswords.add(password + " " + e.getMessage());
            }
        }

        return invalidPasswords;
    }

    private static void validatePassword(String password) throws Exception {
        if (!password.matches(".*\\d+.*")) {
            throw new NoDigitException("The password must contain at least one digit");
        }

        if (!password.matches(".*[a-z]+.*")) {
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
        }

        if (!password.matches(".*[A-Z]+.*")) {
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        }

        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        }

        if (containsInvalidSequence(password)) {
            throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
        }
    }

    private static boolean containsInvalidSequence(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

	public static BooleanSupplier hasUpperAlpha(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
	        if (!password.equals(passwordConfirm)) {
	            throw new UnmatchedException("Passwords do not match");
	        }
	    }
	 
	 public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
	        return password.equals(passwordConfirm);
	    }
	 
	 public static boolean hasUppercaseAlpha(String password) {
	        for (int i = 0; i < password.length(); i++) {
	            if (Character.isUpperCase(password.charAt(i))) {
	                return true;
	            }
	        }
	        return false;
	    }
	 
	 public static void isValidLength(String password) throws LengthException {
	        if (password.length() < 6) {
	            throw new LengthException("The password must be at least 6 characters long");
	        }
	    }
}
