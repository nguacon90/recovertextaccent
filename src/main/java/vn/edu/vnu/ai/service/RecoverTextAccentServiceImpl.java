package vn.edu.vnu.ai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoverTextAccentServiceImpl implements RecovertextAccentService {

	@Autowired
	private TrainingService trainingService;

	@Autowired
	private GenerateUnicodeService generateUnicodeService;

	@Autowired
	private StandardizeData standardizeData;

	@Autowired
	private NormalizeService normalizeService;

	@PostConstruct
	public void normalize() {
		normalizeService.computBigramPropability();
		normalizeService.computTrigramPropability();
	}

	@Override
	public String recoverTextAccent(String input) {
		List<String> words = standardizeData.normalize(input);
		return recoverUsingTrigram(words);
	}

	private String recoverUsingTrigram(List<String> words) {
		StringBuilder result = new StringBuilder();

		List<String> unicodeWords1;
		List<String> unicodeWords2;
		List<String> unicodeWords3;
		int a = 1;
		int length = words.size();
		while (a < length) {
			unicodeWords1 = generateUnicodeService.getAllUnicodeWords(words.get(a - 1));
			unicodeWords2 = generateUnicodeService.getAllUnicodeWords(words.get(a));
			unicodeWords1 = unigramFilter(unicodeWords1);
			unicodeWords2 = unigramFilter(unicodeWords2);

			if (unicodeWords1.size() == 0) {
				result.append(words.get(a - 1)).append(" ");
				a++;
				continue;
			}

			if (unicodeWords2.size() == 0) {
				result.append(recoverUsingUnigram(unicodeWords1)).append(" ");

				result.append(words.get(a)).append(" ");
				a += 2;
				continue;
			}
			
			if(a < length - 1) {
				unicodeWords3 = generateUnicodeService.getAllUnicodeWords(words.get(a + 1));
				unicodeWords3 = unigramFilter(unicodeWords3);
				List<String> trigramWords = trigramFilter(unicodeWords1, unicodeWords2, unicodeWords3);
				if (unicodeWords3.size() == 0 || trigramWords.size() == 0) {
					result.append(recoverUsingUniAndBigram(unicodeWords1, unicodeWords2)).append(" ");
					a += 2;
					continue;
				}
				
				result.append(recoverTrigram(trigramWords)).append(" ");
				a += 3;
			}

		}
		return result.toString();
	}

	private String recoverTrigram(List<String> trigramWords) {
		String word = "";
		Map<String, Double> trigramProb = normalizeService.getTrigramPropability();
		for (Entry<String, Double> item : trigramProb.entrySet()) {
			if (trigramWords.contains(item.getKey())) {
				word = item.getKey();
				break;
			}
		}

		return word;
	}

	private String recoverUsingUniAndBigram(List<String> unicodeWords1, List<String> unicodeWords2) {
		List<String> biagramWords = new ArrayList<String>();
		biagramWords = bigramFilter(unicodeWords1, unicodeWords2);
		if (biagramWords.size() == 0) {
			return recoverUsingUnigram(unicodeWords1) + " " + recoverUsingUnigram(unicodeWords2);
		}

		return recoverUsingBigram(biagramWords);
	}

	private String recoverUsingUnigram(List<String> unicodeWords) {
		String word = "";
		for (Entry<String, Integer> item : trainingService.getUnigram().entrySet()) {
			word = item.getKey();
			if (unicodeWords.contains(word)) {
				break;
			}
		}
		return word;
	}

	private String recoverUsingBigram(List<String> unicodeWords) {
		String word = "";
		Map<String, Double> bigramProb = normalizeService.getBigramPropability();
		for (Entry<String, Double> item : bigramProb.entrySet()) {
			if (unicodeWords.contains(item.getKey())) {
				word = item.getKey();
				break;
			}
		}

		return word;
	}

	private List<String> unigramFilter(List<String> unicodeWords) {
		List<String> result = new ArrayList<String>();
		for (String word : unicodeWords) {
			if (trainingService.getUnigram().containsKey(word)) {
				result.add(word);
			}
		}

		return result;
	}

	private List<String> bigramFilter(List<String> unicodeWords1, List<String> unicodeWords2) {
		List<String> result = new ArrayList<String>();
		for (String firstWord : unicodeWords1) {
			for (String secondWord : unicodeWords2) {
				String key = firstWord + " " + secondWord;
				if (trainingService.getBigram().containsKey(key)) {
					result.add(key);
				}
			}
		}

		return result;
	}

	private List<String> trigramFilter(List<String> unicodeWords1, List<String> unicodeWords2, List<String> unicodeWords3) {
		List<String> result = new ArrayList<String>();
		for (String firstWord : unicodeWords1) {
			for (String secondWord : unicodeWords2) {
				for (String thirdWord : unicodeWords3) {
					String key = firstWord + " " + secondWord + " " + thirdWord;
					if (trainingService.getTrigram().containsKey(key)) {
						result.add(key);
					}
				}
			}
		}

		return result;
	}

}
