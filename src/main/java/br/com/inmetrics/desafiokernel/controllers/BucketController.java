package br.com.inmetrics.desafiokernel.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import br.com.inmetrics.desafiokernel.services.BucketService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BucketController {
	
	@Autowired
	private BucketService service;
	
	@GetMapping
	public List<S3ObjectSummary> list() throws IOException {
		return service.list();
	}
	
	@RequestMapping(path = {"/findByKey"})
	public S3Object findOne(@RequestParam("key") String key) {
		return service.findById(key);
	}
	
	@PutMapping(path = {"/{id}"})
	public @ResponseBody String rename(@PathVariable("id") String id) {
		return "a";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(
			@RequestParam(value="file") MultipartFile file) throws IOException {
		return service.save(file);
	}
}
