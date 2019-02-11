package br.com.inmetrics.desafiokernel.services;

import br.com.inmetrics.desafiokernel.util.PaginationS3Objects;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BucketService {

	@Autowired 
	private AmazonS3Client s3client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	private ObjectListing listing(){
		return s3client.listObjects(new ListObjectsRequest().withBucketName(bucketName));
	}

	public List<S3ObjectSummary> list() {
		ObjectListing objectListing = listing();
		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
	
		return s3ObjectSummaries;
	}
	
	public S3Object rename(String sourceKey, String destinationKey) {
		S3Object file = findById(sourceKey);
		
		if (!file.getKey().isEmpty()) {
			boolean exceptionThrown = false;
			try {
				CopyObjectRequest copyObjRequest = new CopyObjectRequest
													(bucketName, sourceKey, bucketName, destinationKey);
				s3client.copyObject(copyObjRequest);
			} catch (SdkClientException e) {
				exceptionThrown = true;
				e.printStackTrace();
			} finally {
				if (exceptionThrown == false) {
					s3client.deleteObject(new DeleteObjectRequest(bucketName, sourceKey));
				}
			}
		}
		
		return findById(destinationKey);
	}

	public URI save(MultipartFile file) throws URISyntaxException {

		String key = file.getOriginalFilename();

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
		return s3client.getUrl(bucketName, key).toURI();
	}

	public S3Object findById(String key) {
		
		return s3client.getObject(bucketName, key);
				
	}


	public ArrayList<S3ObjectSummary> paginating(int currentPage) {
		ObjectListing objectListing = listing();
		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
		PaginationS3Objects<S3ObjectSummary> page = new PaginationS3Objects<S3ObjectSummary>(s3ObjectSummaries, currentPage);

		return (ArrayList<S3ObjectSummary>) page.iterator();


	}
}
