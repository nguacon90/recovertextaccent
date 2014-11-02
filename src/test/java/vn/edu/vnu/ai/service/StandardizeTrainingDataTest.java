package vn.edu.vnu.ai.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class StandardizeTrainingDataTest {

	private StandardizeData standardizeTrainingData;
	
	@Before 
	public void setUp() {
		standardizeTrainingData = new StandardizeDataImpl();
	}
	
	@Test
	public void testWithCommaAndDot() {
		String input = "20-10-2014! Em rảnh không, đi chơi nhé.";
		String expected = "20-10-2014 ! Em rảnh không , đi chơi nhé .";
		
		List<String> actual = standardizeTrainingData.normalize(input);
		assertEquals(10, actual.size());
		assertEquals(expected, StringUtils.join(actual, " "));
	}
	
	@Test
	public void testWithLongSentence() {
		String input = "Hôm qua em đi chùa hương, qua cầu còn nhỏ hơi sương. Cùng thầy mẹ em ngắm đầu soi gương!";
		String expected = "Hôm qua em đi chùa hương , qua cầu còn nhỏ hơi sương . Cùng thầy mẹ em ngắm đầu soi gương !";
		
		List<String> actual = standardizeTrainingData.normalize(input);
		assertEquals(23, actual.size());
		assertEquals(expected, StringUtils.join(actual, " "));
	}
	
	@Test
	public void testParagraph() {
		String input = "Nhập viện trong tình trạng chân phải đứt lìa, bé trai được các bác sĩ chuẩn bị phương án nối lại chi. Tuy nhiên, ê kíp phẫu thuật đã phải ngậm ngùi khi phần mô của chi đã bị dập nát không thể phục hồi... Hiện tình trạng sức khỏe của cháu bé vẫn chưa mấy cải thiện.";
		List<String> actual = standardizeTrainingData.normalize(input);
		assertEquals(63, actual.size());
	}
	
	@Test
	public void testMatching() {
		String input = "\"2\" - 3";
		List<String> actual = standardizeTrainingData.normalize(input);
		assertEquals(5, actual.size());
	}

	@Test
	public void testTuGhepBoiDauGachNgang() {
		String input = "kinh te-xa hoi";
		List<String> actual = standardizeTrainingData.normalize(input);
		assertEquals(5, actual.size());
	}
}
