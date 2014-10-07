package vn.edu.vnu.ai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GenerateUnicodeService {
	private String[] aUnicode = new String[] { "a", "\u00e0", "\u00e1", "\u1ea3", "\u00e3", "\u1ea1", "\u00e2", "\u1ea7", "\u1ea5", "\u1ea9", "\u1eab", "\u1ead", "\u0103",
			"\u1eb1", "\u1eaf", "\u1eb3", "\u1eb5", "\u1eb7" };
	
	private String[] AUnicode = new String[] { "A", "\u00c0", "\u00c1", "\u1ea2", "\u00c3", "\u1ea0", "\u00c2", "\u1ea4", "\u1ea6", "\u1eaa", "\u1ea8", "\u1eac", "\u0102", "\u1eb0", "\u1eae", "\u1eb2", "\u1eb6", "\u1eb4" };
	
	private String[] dUnicode = new String[] { "d", "\u0111" };
	
	private String[] DUnicode = new String[] {"D", "\u0110"};

	private String[] eUnicode = new String[] { "e", "\u00e8", "\u00e9", "\u1ebb", "\u1ebd", "\u1eb9", "\u00ea", "\u1ec1", "\u1ebf", "\u1ec3", "\u1ec5", "\u1ec7" };
	
	private String[] EUnicode = new String[] {"E", "\u00c8", "\u00c9", "\u1eba", "\u1ebc", "\u1eb8", "\u00ca", "\u1ec0", "\u1ebe", "\u1ec2", "\u1ec6", "\u1ec4"};

	private String[] iUnicode = new String[] { "i", "\u00ec", "\u00ed", "\u1ec9", "\u0129", "\u1ecb" };
	
	private String[] IUnicode = new String[] {"I", "\u00cc", "\u00cd", "\u1ec8", "\u0128", "\u1eca"};

	private String[] oUnicode = new String[] { "o", "\u00f2", "\u00f3", "\u1ecf", "\u00f5", "\u1ecd", "\u00f4", "\u1ed5", "\u1ed7", "\u1ed9", "\u1ed3", "\u1ed1", "\u01a1",
			"\u1edd", "\u1edb", "\u1edf", "\u1ee1", "\u1ee3" };
	
	private String[] OUnicode = new String[] {"O", "\u00d2", "\u00d3", "\u1ece", "\u00d5", "\u1ecc", "\u00d4", "\u1ed4", "\u1ed6", "\u1ed8", "\u1ed2", "\u1ed0", "\u01a0",
			"\u1edc", "\u1eda", "\u1ede", "\u1ee0", "\u1ee2"};

	private String[] uUnicode = new String[] { "u", "\u00f9", "\u00fa", "\u1ee7", "\u0169", "\u1ee5", "\u01b0", "\u1eeb", "\u1ee9", "\u1eed", "\u1eef", "\u1ef1" };

	private String[] UUnicode = new String[] {"U", "\u00d9", "\u00da", "\u1ee6", "\u0168", "\u1ee4", "\u01af", "\u1eea", "\u1ee8", "\u1eec", "\u1eee", "\u1ef0"};

	private String[] yUnicode = new String[] {"y", "\u1ef3", "\u00fd", "\u1ef7", "\u1ef9", "\u1ef5" };
	
	private String[] YUnicode = new String[] {"Y", "\u1ef2", "\u00dd", "\u1ef6", "\u1ef8", "\u1ef4" };

	public List<String> getAllUnicodeWords(String input) {
		List<String> result = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		result.add(input);

		if (input.contains("a")) {
			for (String item : aUnicode) {
				result.add(input.replace("a", item));
			}
		}
		
		if (input.contains("A")) {
			temp.clear();
			for (String word : result) {
				for (String item : AUnicode) {
					temp.add(word.replace("A", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("e")) {
			temp.clear();
			for (String word : result) {
				for (String item : eUnicode) {
					temp.add(word.replace("e", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("E")) {
			temp.clear();
			for (String word : result) {
				for (String item : EUnicode) {
					temp.add(word.replace("E", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("i")) {
			temp.clear();
			for (String word : result) {
				for (String item : iUnicode) {
					temp.add(word.replace("i", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("I")) {
			temp.clear();
			for (String word : result) {
				for (String item : IUnicode) {
					temp.add(word.replace("I", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("o")) {
			temp.clear();
			for (String word : result) {
				for (String item : oUnicode) {
					temp.add(word.replace("o", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("O")) {
			temp.clear();
			for (String word : result) {
				for (String item : OUnicode) {
					temp.add(word.replace("O", item));
				}
			}
			result.addAll(temp);
		}

		if (input.contains("u")) {
			temp.clear();
			for (String word : result) {
				for (String item : uUnicode) {
					temp.add(word.replace("u", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("U")) {
			temp.clear();
			for (String word : result) {
				for (String item : UUnicode) {
					temp.add(word.replace("U", item));
				}
			}
			result.addAll(temp);
		}

		if (input.contains("y")) {
			temp.clear();
			for (String word : result) {
				for (String item : yUnicode) {
					temp.add(word.replace("y", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("Y")) {
			temp.clear();
			for (String word : result) {
				for (String item : YUnicode) {
					temp.add(word.replace("Y", item));
				}
			}
			result.addAll(temp);
		}

		if (input.contains("d")) {
			temp.clear();
			for (String word : result) {
				for (String item : dUnicode) {
					temp.add(word.replace("d", item));
				}
			}
			result.addAll(temp);
		}
		
		if (input.contains("D")) {
			temp.clear();
			for (String word : result) {
				for (String item : DUnicode) {
					temp.add(word.replace("D", item));
				}
			}
			result.addAll(temp);
		}

		return result;
	}
}
