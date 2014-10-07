package vn.edu.vnu.ai.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.vnu.ai.utils.Constants;

@Service
public class TrainingServiceImpl implements TrainingService {
	private String inputFile;
	private Map<String, Integer> unigramDic;
	private Map<String, Integer> bigramDic;
	private Map<String, Integer> trigramDic;
	
	@Autowired
	public TrainingServiceImpl(ServerConfigure serverConfigure) {
		this.inputFile = serverConfigure.getTrainingFileInput();
		unigramDic = new HashMap<String, Integer>();
		bigramDic = new HashMap<String, Integer>();
		trigramDic = new HashMap<String, Integer>();
	}

	@Override
	@PostConstruct
	public void init() throws IOException {
		Reader reader = new InputStreamReader(new FileInputStream(inputFile), Constants.charsets.UTF_8);
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuilder contents = new StringBuilder();
		String sCurrentLine = StringUtils.EMPTY;
		while ((sCurrentLine = bufferedReader.readLine()) != null) {
			contents.append(sCurrentLine).append(" ");
		}
		bufferedReader.close();
		trainingUnigram(contents.toString());
		trainingBigram(contents.toString());
		trainingTrigram(contents.toString());
	}
	
	private void trainingUnigram(String line) {
		String[] words = line.split(" ");
		for (String word : words) {
			if (unigramDic.containsKey(word)) {
				unigramDic.put(word, unigramDic.get(word) + 1);
			} else {
				unigramDic.put(word, 1);
			}
		}
	}

	private void trainingBigram(String line) {
		String[] words = line.split(" ");
		for (int i=1; i<words.length; i++) {
			String key = words[i-1] + " " + words[i];
			if (bigramDic.containsKey(key)) {
				bigramDic.put(key, bigramDic.get(key) + 1);
			} else {
				bigramDic.put(key, 1);
			}
		}
	}
	
	private void trainingTrigram(String line) {
		String[] words = line.split(" ");
		for (int i = 1; i < (words.length - 1); i++) {
			String key = words[i-1] + " " + words[i] + " " + words[i+1];
			if (trigramDic.containsKey(key)) {
				trigramDic.put(key, trigramDic.get(key) + 1);
			} else {
				trigramDic.put(key, 1);
			}
		}
	}

	@Override
	public Map<String, Integer> getUnigram() {
		return this.unigramDic;
	}
	
	@Override
	public Map<String, Integer> getBigram() {
		return this.bigramDic;
	}

	@Override
	public Map<String, Integer> getTrigram() {
		return this.trigramDic;
	}
}
