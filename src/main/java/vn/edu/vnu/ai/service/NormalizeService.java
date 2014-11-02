package vn.edu.vnu.ai.service;

import java.util.Map;

public interface NormalizeService {

	void computBigramPropability();

	void computTrigramPropability();

	Map<String, Double> getBigramPropability();

	Map<String, Double> getTrigramPropability();

}