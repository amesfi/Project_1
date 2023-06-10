/*
	Class: CMSC 204
	CRN: 40541
	Instructor: Professor Gary C. Thai
	Project: 1
	Due Date: 6/11/23
	Programmer: Abidara Mesfin
*/

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 * @throws Exception 
	 */
	@Test
	public void testIsValidPasswordTooShort() throws Exception {
	    // Test case 1: Password is less than 6 characters long
	    try {
	        assertFalse(PasswordCheckerUtility.isValidPassword("abc"));
	    } catch (LengthException e) {
	        // Expected exception, pass the test
	    }

	    assertThrows(NoSpecialCharacterException.class, () -> {
	        PasswordCheckerUtility.isValidPassword("Password1");
	    });
	}

	@Test
	public void testIsValidPasswordNoUpperAlpha() {
	    // Test case 1: Password has no uppercase alphabetic character
	    assertThrows(NoUpperAlphaException.class, () -> {
	        PasswordCheckerUtility.isValidPassword("password1!");
	    });

	    // Test case 2: Password has at least one uppercase alphabetic character
	    try {
	        assertTrue(PasswordCheckerUtility.isValidPassword("Password1!"));
	    } catch (Exception e) {
	        fail("Unexpected exception thrown: " + e.getClass().getSimpleName());
	    }
	}

	@Test
	public void testIsValidPasswordNoLowerAlpha() {
	    // Test case 1: Password has no lowercase alphabetic character
	    assertThrows(NoLowerAlphaException.class, () -> {
	        PasswordCheckerUtility.isValidPassword("PASSWORD1!");
	    });

	    // Test case 2: Password has at least one lowercase alphabetic character
	    try {
	        assertTrue(PasswordCheckerUtility.isValidPassword("Password1!"));
	    } catch (Exception e) {
	        fail("Unexpected exception thrown: " + e.getClass().getSimpleName());
	    }
	}

	@Test
	public void testIsWeakPassword() {
	    assertThrows(NoDigitException.class, () -> {
	        assertFalse(PasswordCheckerUtility.isValidPassword("weakpassword"));
	    });
	}


	@Test
	public void testIsValidPasswordInvalidSequence() {
	    // Test case 1: Password contains an invalid sequence of characters
	    try {
	        PasswordCheckerUtility.isValidPassword("Password12!ab");
	        fail("InvalidSequenceException was expected, but nothing was thrown");
	    } catch (InvalidSequenceException e) {
	        // Exception was correctly thrown, test case passes
	    } catch (Exception e) {
	        fail("Unexpected exception thrown: " + e.getClass().getSimpleName());
	    }

	    // Test case 2: Password does not contain any invalid sequence of characters
	    try {
	        assertTrue(PasswordCheckerUtility.isValidPassword("P@ssw0rd!"));
	    } catch (Exception e) {
	        fail("Unexpected exception thrown: " + e.getClass().getSimpleName());
	    }
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 * @throws Exception 
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
	    // Test case 1: Password has no digits
	    assertThrows(NoDigitException.class, () -> {
	        PasswordCheckerUtility.isValidPassword("password");
	    });

	    // Test case 2: Password has at least one digit
	    try {
	        assertTrue(PasswordCheckerUtility.isValidPassword("Password1"));
	    } catch (NoSpecialCharacterException e) {
	        // NoSpecialCharacterException is expected, so the test should not fail in this case
	    } catch (Exception e) {
	        fail("Unexpected exception thrown: " + e.getClass().getSimpleName());
	    }
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() {
	    try {
	        assertTrue(PasswordCheckerUtility.isValidPassword("Password1!"));
	    } catch (Exception e) {
	        fail("Unexpected exception thrown: " + e.getClass().getSimpleName());
	    }
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
	    ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(Arrays.asList(
	            "password", "Password1", "12345678", "abcABC123", "no_special_characters", "Password123!",
	            "pass word", "password1", "PASSWORD1"
	    ));

	    assertEquals(9, invalidPasswords.size());
	}
}