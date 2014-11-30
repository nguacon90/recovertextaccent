package vn.edu.vnu.ai.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
	private StandardizeData standardizeData;
	
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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputFile);
		Reader reader = new InputStreamReader(inputStream, Constants.charsets.UTF_8);
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuilder contents = new StringBuilder();
		String sCurrentLine = StringUtils.EMPTY;
		while ((sCurrentLine = bufferedReader.readLine()) != null) {
			contents.append(sCurrentLine).append(" ");
		}
		
		System.out.println("---corpus--: " + contents.length());
		trainingData(contents.toString());
		
		bufferedReader.close();
		
	}
	
	@Override
	public void trainingData(String data) {
		trainingUnigram(data);
		trainingBigram(data);
		trainingTrigram(data);
		System.out.println("---unigram--: " + unigramDic.size());
		System.out.println("---bigram--: " + bigramDic.size());
		System.out.println("---trigram--: " + trigramDic.size());
	}
	
	private void trainingUnigram(String line) {
		List<String> words = standardizeData.normalize(line);
		for (String word : words) {
			if (unigramDic.containsKey(word)) {
				unigramDic.put(word, unigramDic.get(word) + 1);
			} else {
				unigramDic.put(word, 1);
			}
		}
		unigramDic = sortByComparator(unigramDic);
	}

	private void trainingBigram(String line) {
		List<String> words = standardizeData.normalize(line);
		for (int i=1; i<words.size(); i++) {
			String key = words.get(i-1) + " " + words.get(i);
			if (bigramDic.containsKey(key)) {
				bigramDic.put(key, bigramDic.get(key) + 1);
			} else {
				bigramDic.put(key, 1);
			}
		}
		bigramDic = sortByComparator(bigramDic);
	}
	
	private void trainingTrigram(String line) {
		List<String> words = standardizeData.normalize(line);
		for (int i = 1; i < (words.size() - 1); i++) {
			String key = words.get(i-1) + " " + words.get(i) + " " + words.get(i+1);
			if (trigramDic.containsKey(key)) {
				trigramDic.put(key, trigramDic.get(key) + 1);
			} else {
				trigramDic.put(key, 1);
			}
		}
	}

	private Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {
		 
		List<Map.Entry<String, Integer>> list = 
			new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
 
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
                                           Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
 
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
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
