package br.com.inmetrics.desafiokernel.controllers;

import br.com.inmetrics.desafiokernel.services.BucketService;
import br.com.inmetrics.desafiokernel.vo.S3ObjectVO;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BucketController {
	
	@Autowired
	private BucketService service;
	
	@RequestMapping
	public @ResponseBody List<S3ObjectSummary> list() throws IOException {
		return service.list();
	}

	@RequestMapping(value = "/page")
	public @ResponseBody List<S3ObjectSummary> findPage(
			@RequestParam("page") int page){

		List<S3ObjectSummary> result = service.paginating(page);

		return (List<S3ObjectSummary>) ResponseEntity.ok().body(result);

	}

	@RequestMapping(path = {"/findByKey"})
	public ResponseEntity<S3Object> findOne(@RequestParam("key") S3ObjectVO key) {
		S3Object object = service.findById(key.getName());
		if (object.getKey().isEmpty()) {
			return new ResponseEntity<S3Object>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(object);
	}
	
	@RequestMapping(value = {"/filename"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> rename(@RequestBody String name, @PathVariable String oldname) {
		
		service.rename(oldname, name);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<Void> handleFileUpload(
			@RequestParam(value="file") MultipartFile file) throws URISyntaxException {
		URI uri = service.save(file);
		return ResponseEntity.created(uri).build();
	}
}
