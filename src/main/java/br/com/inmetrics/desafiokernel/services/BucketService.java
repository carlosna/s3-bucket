package br.com.inmetrics.desafiokernel.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class BucketService {

	@Autowired 
	private AmazonS3Client s3client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	public List<S3ObjectSummary> list() {
		ObjectListing objectListing = s3client.listObjects(new ListObjectsRequest().withBucketName(bucketName));		
		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

		return s3ObjectSummaries;
	}
	
}
