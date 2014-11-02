package vn.edu.vnu.ai.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormalizeServiceImpl implements NormalizeService {
	private TrainingService trainingService;
	private Map<String, Double> bigramPropability;
	private Map<String, Double> trigramPropability;

	@Autowired
	public NormalizeServiceImpl(TrainingService trainingService) {
		this.trainingService = trainingService;
		bigramPropability = new HashMap<String, Double>();
		setTrigramPropability(new HashMap<String, Double>());
	}

	@Override
	public void computBigramPropability() {
		Map<String, Integer> unigram = trainingService.getUnigram();
		Map<String, Integer> bigram = trainingService.getBigram();
		int V = unigram.size();
		try {
			for (Map.Entry<String, Integer> entry : bigram.entrySet()) {
				String key = entry.getKey();
				String[] keys = key.split(" ");
					double probFirstWord =  (unigram.get(keys[0]) + 1) / V;
					double probSecondWordWithFirstWord = (double) (entry.getValue() + 1) / (double) (unigram.get(keys[0]) + V); 
					double probOfBigram = Math.log(probFirstWord) + Math.log(probSecondWordWithFirstWord); //laplace smoothing
					bigramPropability.put(key, probOfBigram);
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		bigramPropability = sortByComparator(bigramPropability);
	}

	private Map<String, Double> sortByComparator(Map<String, Double> unsortMap) {
		 
		List<Map.Entry<String, Double>> list = 
			new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());
 
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
                                           Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
 
		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	@Override
	public void computTrigramPropability() {
		Map<String, Integer> unigram = trainingService.getUnigram();
		Map<String, Integer> bigram = trainingService.getBigram();
		Map<String, Integer> trigram = trainingService.getTrigram();
		int V = unigram.size();
		try {
			for (Map.Entry<String, Integer> entry : trigram.entrySet()) {
				String key = entry.getKey();
				String[] keys = key.split(" ");
				String key12 = keys[0] + " " + keys[1];
				double prob = (double) (entry.getValue() + 1)/ (double) (bigram.get(key12) + V);
				double probTri = bigramPropability.get(key12) + Math.log(prob);
				trigramPropability.put(key, probTri);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	@Override
	public Map<String, Double> getBigramPropability() {
		return bigramPropability;
	}

	public void setBigramPropability(Map<String, Double> bigramPropability) {
		this.bigramPropability = bigramPropability;
	}

	@Override
	public Map<String, Double> getTrigramPropability() {
		return trigramPropability;
	}

	public void setTrigramPropability(Map<String, Double> trigramPropability) {
		this.trigramPropability = trigramPropability;
	}
}
