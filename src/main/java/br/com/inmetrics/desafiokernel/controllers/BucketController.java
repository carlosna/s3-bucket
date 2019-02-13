package br.com.inmetrics.desafiokernel.controllers;

import br.com.inmetrics.desafiokernel.services.BucketService;
import br.com.inmetrics.desafiokernel.util.PaginationS3Objects;
import br.com.inmetrics.desafiokernel.vo.S3ObjectVO;
import com.amazonaws.services.s3.model.ObjectMetadata;
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

@RestController
@RequestMapping("/api")
public class BucketController {
	
	@Autowired
	private BucketService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<S3ObjectVO>> list() throws IOException {
		List<S3ObjectVO> result = service.list();
		return new ResponseEntity<List<S3ObjectVO>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findPage", method = RequestMethod.GET)
	public @ResponseBody List<S3ObjectVO> findPage(
			@RequestParam("page") int page){

		return service.paginating(page);

		//return ResponseEntity.ok().body(result);


	}

	@RequestMapping(value = "/findByKey", method = RequestMethod.GET)
	public ResponseEntity<S3ObjectVO> findOne(@RequestParam("key") String key) {
		S3Object object = service.findById(key);
		S3ObjectVO result = new S3ObjectVO(object.getKey(),
				                           object.getObjectMetadata().getContentLength(),
				                           object.getObjectMetadata().getLastModified());
		if (object.getKey().isEmpty()) {
			return new ResponseEntity<S3ObjectVO>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/filename", method = RequestMethod.PUT)
	public ResponseEntity<Void> rename(@RequestParam String oldname, @RequestParam String name) {
		
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
