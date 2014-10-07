package vn.edu.vnu.ai.service;

import java.util.Map;

public interface NormalizeService {

	void computBigramPropability();
	void computTrigramPropability();
	
	Map<String, Double> getBigramWithFirstWordPropability();
	Map<String, Double> getBigramWithSecondWordPropability();
	Map<String, Double> getTrigramWithFirstWordPropability();
	Map<String, Double> getTrigramWithSecondWordPropability();
	Map<String, Double> getTrigramWithThirdWordPropability();

}