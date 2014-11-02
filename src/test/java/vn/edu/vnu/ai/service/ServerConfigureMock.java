package vn.edu.vnu.ai.service;

public class ServerConfigureMock extends ServerConfigure {
	private static final String INPUT_FILE = "training.txt";
	
	@Override
	public String getTrainingFileInput() {
		return INPUT_FILE;
	}
}
