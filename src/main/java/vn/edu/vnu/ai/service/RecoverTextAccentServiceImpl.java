package vn.edu.vnu.ai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoverTextAccentServiceImpl implements RecovertextAccentService {
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private GenerateUnicodeService generateUnicodeService;
	
	@Override
	public String recoverTextAccent(String input) {
		StringBuilder result = new StringBuilder();
		String[] words = input.split(" ");
		int size = words.length;
		boolean isRecoverdWord = false;
		List<String> unigramWords;
		List<String> bigramWords;
		Map<String, Integer> unigramProb = trainingService.getUnigram();
		Map<String, Integer> bigramProb = trainingService.getBigram();
		for (int i=0; i<size; i++) {
			if(isRecoverdWord) {
				isRecoverdWord = false;
				continue;
			}
			List<String> unicodeWords = generateUnicodeService.getAllUnicodeWords(words[i]);
			unigramWords = unigramFilter(unicodeWords);
			if(unigramWords.isEmpty()) {
				result.append(words[i]).append(" ");
			} else {
				if(i < size - 1) {
					List<String> unicodeWords2 = generateUnicodeService.getAllUnicodeWords(words[i+1]);
					bigramWords = bigramFilter(unicodeWords, unicodeWords2);
					if(bigramWords.isEmpty()) {
						String temp = unigramWords.get(0);
						for (String word : unigramWords) {
							if(unigramProb.get(word) > unigramProb.get(temp)) {
								temp = word;
							}
						}
						result.append(temp).append(" ");
					} else {
						String temp = bigramWords.get(0);
						for (String item : bigramWords) {
							if(bigramProb.get(item) > bigramProb.get(temp)) {
								temp = item;
							}
						}
						
						result.append(temp).append(" ");
						isRecoverdWord = true;
					}
				} else {
					String temp = unigramWords.get(0);
					for (String item : unigramWords) {
						if(unigramProb.get(item) > unigramProb.get(temp)) {
							temp = item;
						}
					}
					result.append(temp).append(" ");
				}
			}
			
		}
		
		
		return result.toString();
	}
	
	private List<String> unigramFilter(List<String> unicodeWords) {
		List<String> result = new ArrayList<String>();
		for (String word : unicodeWords) {
			if(trainingService.getUnigram().containsKey(word)) {
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
				if(trainingService.getBigram().containsKey(key)) {
					result.add(key);
				}
			}
		}
		return result;
	}
}
