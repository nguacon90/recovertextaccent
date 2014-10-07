package vn.edu.vnu.ai.service;

import java.util.HashMap;
import java.util.Map;

public class NormalizeServiceImpl implements NormalizeService {
	private TrainingService trainingService;
	private Map<String, Double> bigramWithFirstWordPropability;
	private Map<String, Double> bigramWithSecondWordPropability;
	private Map<String, Double> trigramWithFirstWordPropability;
	private Map<String, Double> trigramWithSecondWordPropability;
	private Map<String, Double> trigramWithThirdWordPropability;
	
	public NormalizeServiceImpl(TrainingService trainingService) {
		this.trainingService = trainingService;
		bigramWithFirstWordPropability = new HashMap<String, Double>();
		bigramWithSecondWordPropability = new HashMap<String, Double>();
		trigramWithFirstWordPropability = new HashMap<String, Double>();
		trigramWithSecondWordPropability = new HashMap<String, Double>();
		trigramWithThirdWordPropability = new HashMap<String, Double>();
	}
	
	@Override
	public void computBigramPropability() {
		Map<String, Integer> unigram = trainingService.getUnigram();
		Map<String, Integer> bigram = trainingService.getBigram();
		
		for (Map.Entry<String, Integer> entry : bigram.entrySet()) {
			String key = entry.getKey();
			String[] keys = key.split(" ");
			double prob1 = (double)entry.getValue() / (double)unigram.get(keys[0]);
			double prob2 = (double)entry.getValue() / (double)unigram.get(keys[1]);
			bigramWithFirstWordPropability.put(key, prob1);
			bigramWithSecondWordPropability.put(key, prob2);
					
		}
	}
	
	@Override
	public void computTrigramPropability() {
		Map<String, Integer> unigram = trainingService.getUnigram();
		Map<String, Integer> trigram = trainingService.getTrigram();
		
		for (Map.Entry<String, Integer> entry : trigram.entrySet()) {
			String key = entry.getKey();
			String[] keys = key.split(" ");
			double prob1 = (double)entry.getValue() / (double)unigram.get(keys[0]);
			double prob2 = (double)entry.getValue() / (double)unigram.get(keys[1]);
			double prob3 = (double)entry.getValue() / (double)unigram.get(keys[2]);
			trigramWithFirstWordPropability.put(key, prob1);
			trigramWithSecondWordPropability.put(key, prob2);
			trigramWithThirdWordPropability.put(key, prob3);
		}
	}

	@Override
	public Map<String, Double> getBigramWithFirstWordPropability() {
		return this.bigramWithFirstWordPropability;
	}

	@Override
	public Map<String, Double> getBigramWithSecondWordPropability() {
		return this.bigramWithSecondWordPropability;
	}

	@Override
	public Map<String, Double> getTrigramWithFirstWordPropability() {
		return this.trigramWithFirstWordPropability;
	}

	@Override
	public Map<String, Double> getTrigramWithSecondWordPropability() {
		return this.trigramWithSecondWordPropability;
	}

	@Override
	public Map<String, Double> getTrigramWithThirdWordPropability() {
		return this.trigramWithThirdWordPropability;
	}
}
