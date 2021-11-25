package com.diary.common.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import static org.reflections.Reflections.log;

@Component
public class S3Util {
    private final String S3_IMAGE_URL = "https://papers-bucket.s3.amazonaws.com/";
    private static String ACCESS_KEY;
    private static String SECRET_KEY;

    @Value("${cloud.aws.credentials.access-key}")
    public void setAccessKey(String key) {
        ACCESS_KEY = key;
    }

    @Value("${cloud.aws.credentials.secret-key}")
    public void setSecretKey(String key) {
        SECRET_KEY = key;
    }

    public static Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        System.out.println(convertFile.getAbsoluteFile());

        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    public static String putS3(File uploadFile, String fileName) {
//        String ACCESS_KEY = "AKIATQKDETXTBZLNXCWM";
//        String SECRET_KEY = "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";

        AWSCredentials crd = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonS3Client amazonS3Client = new AmazonS3Client(crd);;
        amazonS3Client.putObject(new PutObjectRequest("papers-bucket", fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl("papers-bucket", fileName).toString();
    }

    public static void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }
}
