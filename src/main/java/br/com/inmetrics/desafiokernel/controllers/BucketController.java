package br.com.inmetrics.desafiokernel.controllers;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import br.com.inmetrics.desafiokernel.services.BucketService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BucketController {
	
	@Autowired
	private BucketService service;
	
//	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@GetMapping
	public List<S3ObjectSummary> list() throws IOException {
		return service.list();
	}	
}
