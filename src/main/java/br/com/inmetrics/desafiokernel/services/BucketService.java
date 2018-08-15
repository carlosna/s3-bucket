package br.com.inmetrics.desafiokernel.services;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import br.com.inmetrics.desafiokernel.vo.S3ObjectVO;

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

	public S3Object findById(String key) {
		
		return s3client.getObject(bucketName, key);
				
	}
	
	
	
}
