package vn.edu.vnu.ai.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class PatternTest {
	private static final String EMAIL_PATTERN = "\\w+[\\w\\d\\.\\_]*@\\w+(?:\\.\\w+)+";
	private static final String DATE_PATTERN ="\\d{1,2}[/-]\\d{1,2}(?:[/-]\\d+)?";
	private static final String PERCENT_PATTERN ="\\d+(?:[\\.,]\\d+)?[\\%\\$]";
	private static final String NUMBER_PATTERN ="\\d+(?:.\\d+)?";
	private static final String WORD_PATTERN = "(?:[^\\W\\d])+";
	
	@Test
	public void testInvalidEmailNoDotCom() {
		String input = "E";
		assertFalse(input.matches(EMAIL_PATTERN));
	}
	
	@Test
	public void testValidEmail() {
		String input = "asdasmsd@abc.com";
		assertTrue(input.matches(EMAIL_PATTERN));
	}
	
	@Test
	public void testInvalidDatePattern() {
		String input = "206/10/2014";
		assertFalse(input.matches(DATE_PATTERN));
	}
	
	@Test
	public void testValidDatePattern() {
		String input = "20/10/2014";
		assertTrue(input.matches(DATE_PATTERN));
	}
	
	@Test
	public void testInvalidPercentPattern() {
		String input = "20%a";
		assertFalse(input.matches(PERCENT_PATTERN));
	}
	
	@Test
	public void testValidPercentPattern() {
		String input = "20%";
		assertTrue(input.matches(PERCENT_PATTERN));
	}
	
	@Test
	public void testValidPercentPatternCaseDouble() {
		String input = "20,6%";
		assertTrue(input.matches(PERCENT_PATTERN));
	}
	
	@Test
	public void testInvalidNumberPattern() {
		String input = "20%";
		assertFalse(input.matches(NUMBER_PATTERN));
	}
	
	@Test
	public void testInvalidNumberPattern_NotSupportCommaFormat() {
		String input = "20,190.89";
		assertFalse(input.matches(NUMBER_PATTERN));
	}
	
	@Test
	public void testValidNumberPattern() {
		String input = "209839";
		assertTrue(input.matches(NUMBER_PATTERN));
	}
	
	@Test
	public void testValidNumberPattern_withoneComma() {
		String input = "209,839";
		assertTrue(input.matches(NUMBER_PATTERN));
	}
	
	@Test
	public void testInvalidWordPattern() {
		String input = "20.9839";
		assertFalse(input.matches(WORD_PATTERN));
	}
	
	@Test
	public void testValidWordPattern() {
		String input = "sad";
		Pattern word = Pattern.compile(WORD_PATTERN, Pattern.UNICODE_CHARACTER_CLASS);
		assertTrue(word.matcher(input).matches());
	}
	
	@Test
	public void testValidWordPattern2() {
		String input = "nghiếng";
		Pattern word = Pattern.compile(WORD_PATTERN, Pattern.UNICODE_CHARACTER_CLASS);
		assertTrue(word.matcher(input).matches());
	}
	
}