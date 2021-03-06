package vn.edu.vnu.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.vnu.ai.domain.ResponseModel;
import vn.edu.vnu.ai.service.NormalizeService;
import vn.edu.vnu.ai.service.RecovertextAccentService;
import vn.edu.vnu.ai.service.RemoveAccentService;
import vn.edu.vnu.ai.service.TrainingService;

@RestController
@RequestMapping("/service")
public class RestServiceController {

	@Autowired
	private RemoveAccentService removeAccentService;
	
	@Autowired
	private RecovertextAccentService recovertextAccentService;
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private NormalizeService normalizeService;
	
	@RequestMapping(value="removeAccent", method=RequestMethod.POST, headers="Accept=application/json")
	public ResponseModel<String> removeAccent(@RequestParam("input") String input) {
		ResponseModel<String> result = new ResponseModel<String>();
		result.setModel(removeAccentService.utfToAcsii(input));
		return result;
	}
	
	@RequestMapping(value="recoverAccent", method=RequestMethod.POST, headers="Accept=application/json")
	public ResponseModel<String> recoverAccent(@RequestParam("input") String input) {
		ResponseModel<String> result = new ResponseModel<String>();
		result.setModel(recovertextAccentService.recoverTextAccent(input));
		return result;
	}
	
	@RequestMapping(value="training", method=RequestMethod.POST, headers="Accept=application/json")
	public Boolean training(@RequestParam("data") String data) {
		trainingService.trainingData(data);
		normalizeService.computBigramPropability();
		normalizeService.computTrigramPropability();
		return true;
	}
}
