package vn.edu.vnu.ai.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RemoveAccentService {
	private static final String[] utf8List = new String[] { "\u00E0", "\u00E1", "\u1EA3", "\u00E3", "\u1EA1", "\u00C0", "\u00C1",
			"\u1EA2", "\u00C3", "\u1EA0", "\u00E2", "\u1EA7", "\u1EA5", "\u1EA9", "\u1EAB", "\u1EAD", "\u00C2", "\u1EA6",
			"\u1EA4", "\u1EA8", "\u1EAA", "\u1EAC", "\u0103", "\u1EB1", "\u1EAF", "\u1EB3", "\u1EB5", "\u1EB7", "\u0102",
			"\u1EB0", "\u1EAE", "\u1EB2", "\u1EB4", "\u1EB6", "\u0111", "\u0110", "\u00E8", "\u00E9", "\u1EBB", "\u1EBD",
			"\u1EB9", "\u00C8", "\u00C9", "\u1EBA", "\u1EBC", "\u1EB8", "\u00EA", "\u1EC1", "\u1EBF", "\u1EC3", "\u1EC5",
			"\u1EC7", "\u00CA", "\u1EC0", "\u1EBE", "\u1EC2", "\u1EC4", "\u1EC6", "\u00EC", "\u00ED", "\u1EC9", "\u0129",
			"\u1ECB", "\u00CC", "\u00CD", "\u1EC8", "\u0128", "\u1ECA", "\u00F2", "\u00F3", "\u1ECF", "\u00F5", "\u1ECD",
			"\u00D2", "\u00D3", "\u1ECE", "\u00D5", "\u1ECC", "\u00F4", "\u1ED3", "\u1ED1", "\u1ED5", "\u1ED7", "\u1ED9",
			"\u1ED2", "\u1ED2", "\u1ED0", "\u1ED4", "\u1ED6", "\u1ED8", "\u01A1", "\u1EDD", "\u1EDB", "\u1EDF", "\u1EE1",
			"\u1EE3", "\u01A0", "\u1EDC", "\u1EDA", "\u1EDE", "\u1EE0", "\u1EE2", "\u00F9", "\u00FA", "\u1EE7", "\u0169",
			"\u1EE5", "\u00D9", "\u00DA", "\u1EE6", "\u0168", "\u1EE4", "\u01B0", "\u1EEB", "\u1EE9", "\u1EED", "\u1EEF",
			"\u1EF1", "\u01AF", "\u1EEA", "\u1EE8", "\u1EEC", "\u1EEE", "\u1EF0", "\u1EF3", "\u00FD", "\u1EF7", "\u1EF9",
			"\u1EF5", "\u1EF2", "\u00DD", "\u1EF6", "\u1EF8", "\u1EF4", "\u00D4" };

	private static final String[] asciiList = new String[] { "a", "a", "a", "a", "a", "A", "A", "A", "A", "A", "a", "a", "a",
			"a", "a", "a", "A", "A", "A", "A", "A", "A", "a", "a", "a", "a", "a", "a", "A", "A", "A", "A", "A", "A", "d", "D",
			"e", "e", "e", "e", "e", "E", "E", "E", "E", "E", "e", "e", "e", "e", "e", "e", "E", "E", "E", "E", "E", "E", "i",
			"i", "i", "i", "i", "I", "I", "I", "I", "I", "o", "o", "o", "o", "o", "O", "O", "O", "O", "O", "o", "o", "o", "o",
			"o", "o", "O", "O", "O", "O", "O", "O", "o", "o", "o", "o", "o", "o", "O", "O", "O", "O", "O", "O", "u", "u", "u",
			"u", "u", "U", "U", "U", "U", "U", "u", "u", "u", "u", "u", "u", "U", "U", "U", "U", "U", "U", "y", "y", "y", "y",
			"y", "Y", "Y", "Y", "Y", "Y", "O" };

	public String utfToAcsii(String utf) {
		return StringUtils.replaceEach(utf, utf8List, asciiList);
	}
}
