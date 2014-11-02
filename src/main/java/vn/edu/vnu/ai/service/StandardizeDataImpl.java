package vn.edu.vnu.ai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class StandardizeDataImpl implements StandardizeData {

	private static final String EMAIL_PATTERN = "\\w+[\\w\\d\\.\\_]*@\\w+(?:\\.\\w+)+";
	private static final String DATE_PATTERN = "\\d{1,2}[/-]\\d{1,2}(?:[/-]\\d+)?";
	private static final String PERCENT_PATTERN = "\\d+(?:[\\.,]\\d+)?[\\%\\$]";
	private static final String NUMBER_PATTERN = "\\d+(?:.\\d+)?";
	private static final String CURRENCY_PATTERN = "\\$\\d+(?:\\.\\d+)*";
	private static final String ETC_PATTERN = "\\.\\.\\.";
	private static final String APOSTROPHE_PATTERN = "\\'\\w+";
	private static final String OTHER_PUNCTUATION_PATTERN = "[.,;\"?!():_'/+%-]";
	private static final String WORD_PATTERN = "(?:[^\\W\\d_])+";

	@Override
	public List<String> normalize(String input) {
		List<String> allMatches = new ArrayList<String>();
		String regex = generateRegex();
		Matcher m = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS).matcher(input);
		while (m.find()) {
			allMatches.add(m.group());
		}
		return allMatches;
	}

	private String generateRegex() {
		StringBuilder patterns = new StringBuilder(WORD_PATTERN);
		patterns.append("|").append(DATE_PATTERN)
		.append("|").append(PERCENT_PATTERN)
		.append("|").append(NUMBER_PATTERN)
		.append("|").append(ETC_PATTERN)
		.append("|").append(CURRENCY_PATTERN)
		.append("|").append(APOSTROPHE_PATTERN)
		.append("|").append(OTHER_PUNCTUATION_PATTERN)
		.append("|").append(EMAIL_PATTERN);

		return patterns.toString();
	}

}
