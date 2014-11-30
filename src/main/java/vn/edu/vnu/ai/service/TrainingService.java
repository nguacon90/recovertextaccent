package vn.edu.vnu.ai.service;

import java.io.IOException;
import java.util.Map;

public interface TrainingService {

	Map<String, Integer> getUnigram();

	Map<String, Integer> getBigram();

	Map<String, Integer> getTrigram();

	void init() throws IOException;
	
	void trainingData(String data);
}