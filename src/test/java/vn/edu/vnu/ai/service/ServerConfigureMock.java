package vn.edu.vnu.ai.service;

public class ServerConfigureMock extends ServerConfigure {
	private static final String INPUT_FILE = "training.txt";
	private static final String ROOT_PATH = "E:/AI/";
	
	@Override
	public String getTrainingFileInput() {
		return ROOT_PATH + INPUT_FILE;
	}
}
