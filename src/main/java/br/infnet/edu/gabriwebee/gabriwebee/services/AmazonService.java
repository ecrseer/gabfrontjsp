package br.infnet.edu.gabriwebee.gabriwebee.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class AmazonService {
    private AmazonS3 amazonS3;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        var provider = new AWSStaticCredentialsProvider(credentials);

        this.amazonS3 = AmazonS3ClientBuilder
                .standard().withRegion(Regions.SA_EAST_1)
                .withCredentials(provider).build();
    }

    public List<Bucket> listOfBuckets() {
        // amazonS3.upload(presignedUrlUploadRequest)
        return amazonS3.listBuckets();

    }

    public File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(convFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return convFile;

    }

    public String uploadFile(String bucketName, MultipartFile multiPartFile) {
        PutObjectResult result;
        try {
            File file = convertMultiPartToFile(multiPartFile);
            result = amazonS3.putObject(bucketName, file.getName(), file);
            System.out.println("resulttttt " + result);
            return result.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return "err";
        }

    }

    public String listBucketFiles(String bucketName) {
        ObjectListing nsei = amazonS3.listObjects(bucketName, "");
        return nsei.toString();
    }

    public List<S3ObjectSummary> listObjects(String bucketName) {
        ListObjectsV2Result listResults = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> list = listResults.getObjectSummaries();
        return list;
    }
}
