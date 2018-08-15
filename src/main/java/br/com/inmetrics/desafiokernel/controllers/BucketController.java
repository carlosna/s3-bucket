package br.com.inmetrics.desafiokernel.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import br.com.inmetrics.desafiokernel.services.BucketService;
import br.com.inmetrics.desafiokernel.vo.S3ObjectVO;


@CrossOrigin(origins = "*")
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
	public ResponseEntity<S3Object> findOne(@RequestParam("key") S3ObjectVO key) {
		S3Object object = service.findById(key.getName());
		if (object.getKey().isEmpty()) {
			return new ResponseEntity<S3Object>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<S3Object>(object, HttpStatus.OK);
	}
	
	@PutMapping(path = {"/key"})
	public HttpStatus rename(@RequestParam("filename") String oldname, @RequestBody String name) {
		
		service.rename(oldname, name);
		
		return HttpStatus.OK;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(
			@RequestParam(value="file") MultipartFile file) throws IOException {
		return service.save(file);
	}
}
