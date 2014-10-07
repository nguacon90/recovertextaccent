package vn.edu.vnu.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServerConfigure {

	@Value("${training.file}")
	private String trainingFileInput;

	public String getTrainingFileInput() {
		return trainingFileInput;
	}
	
}
