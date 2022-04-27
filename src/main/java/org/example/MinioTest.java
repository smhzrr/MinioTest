package org.example;

import io.minio.*;

public class MinioTest {
    public static void main(String[] args) throws Exception{
        //上传文件
        fileUpload();
        //下载文件
        //fileDownLoad();
    }
    private static void fileUpload() throws Exception{
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://114.116.198.57:9000")
                        .credentials("admin", "12345678")
                        .build();
        // 创建bucket
        String bucketName = "test";
        boolean exists =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
        // 不存在，创建bucket
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        // 上传文件
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object("upload1.txt")
                        .filename("D:\\work\\upload.txt")
                        .build());
        System.out.println("上传文件成功");
    }

    private static void fileDownLoad() throws Exception {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://122.9.38.71:9000")
                        .credentials("admin", "12345678")
                        .build();

        minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket("test")
                            .object("upload.txt")
                            .filename("upload.txt")
                            .build());
    }
}
