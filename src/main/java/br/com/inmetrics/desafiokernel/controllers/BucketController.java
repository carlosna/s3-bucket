package br.com.inmetrics.desafiokernel.controllers;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import br.com.inmetrics.desafiokernel.services.BucketService;

@RestController
@RequestMapping("/api/bucket")
public class BucketController {
	
	@Autowired
	private BucketService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Iterable<S3ObjectSummary> list() throws IOException {
		return service.list();
	}	
}
