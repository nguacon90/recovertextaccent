package vn.edu.vnu.ai.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import vn.edu.vnu.ai.service.TrainingService;
import vn.edu.vnu.ai.service.TrainingServiceImpl;

public class TrainingServiceTest{
	private ServerConfigure serverConfigure;
	
	@Test
	public void testInit() throws IOException {
		serverConfigure = new ServerConfigureMock();
		TrainingService trainingService = new TrainingServiceImpl(serverConfigure);
		trainingService.init();
		assertTrue(trainingService.getBigram().size() > 0);
		assertTrue(trainingService.getUnigram().size() > 0);
		assertTrue(trainingService.getTrigram().size() > 0);
		System.out.println(trainingService.getUnigram().get("thông"));
		System.out.println(trainingService.getUnigram());
	}

}
