package com.rentcar.service.aws;

import java.io.IOException;

public interface AmazonUploadingFileService  {
    String uploadFile(byte[] imageBytes, Long userId) throws IOException;


}