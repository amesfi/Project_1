/*
	Class: CMSC 204
	CRN: 40541
	Instructor: Professor Gary C. Thai
	Project: 1
	Due Date: 6/11/23
	Programmer: Abidara Mesfin
*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PasswordCheckerTestPublic {
	ArrayList<String> passwordsArray;
	String password = "Hello";
	String passwordConfirm = "hello";
	String allCaps = "HELLO";
	String withDigit = "Hello6";

	@BeforeEach
	void setUp() throws Exception {
		String[] p = { "334455BB", "Im2cool4U", withDigit };
		passwordsArray = new ArrayList<String>(Arrays.asList(p));
	}

	@AfterEach
	void tearDown() throws Exception {
		passwordsArray = null;
	}

	@Test
	void testComparePasswords() {
		Throwable exception = assertThrows(UnmatchedException.class, () -> {
			PasswordCheckerUtility.comparePasswords(password, passwordConfirm);
		});

		assertEquals("Passwords do not match", exception.getMessage());
	}

	@Test
	void testComparePasswordsWithReturn() {
		assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
		assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
	}

	@Test
	void testUppercaseAlpha() {
		assertTrue(PasswordCheckerUtility.hasUppercaseAlpha("Beautiful"));
	}

	@Test
	void testIsValidLength() {
		Throwable exception = assertThrows(LengthException.class, () -> {
			PasswordCheckerUtility.isValidLength(password);
		});

		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}

	@Test
	void testGetInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		assertEquals(3, results.size());
		assertEquals("334455BB The password must contain at least one lowercase alphabetic character", results.get(0));
		assertEquals("Im2cool4U The password must contain at least one special character", results.get(1));
	}
}
