package vn.edu.vnu.ai.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GenerateUnicodeServiceTest {

	private GenerateUnicodeService generateUnicodeService;
	
	@Before
	public void setUp() {
		generateUnicodeService = new GenerateUnicodeService();
	}
	
	@Test
	public void test() {
		String a = "Luoi";
		List<String> actual = generateUnicodeService.getAllUnicodeWords(a);
		assertTrue(actual.contains("Lười"));
		assertTrue(actual.contains("Lưỡi"));
	}
}

