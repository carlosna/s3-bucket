package br.com.inmetrics.desafiokernel.services;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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

	public String save(MultipartFile file) {
		
		try {
			s3client.putObject(bucketName, file.getOriginalFilename(), "Upload Object");
			PutObjectRequest request = new PutObjectRequest(bucketName, 
											file.getOriginalFilename(), 
											new File(file.getName()));
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType("plain/text");
			metadata.addUserMetadata("x-amz-meta-title", "aws-kernel");
			request.setMetadata(metadata);
			s3client.putObject(request);
			
			
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		return file.getName() + "uploaded";
	}
	
}
