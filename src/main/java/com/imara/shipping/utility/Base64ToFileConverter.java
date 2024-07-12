package com.imara.shipping.utility;

import com.imara.shipping.config.AppConstants;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Base64ToFileConverter {


    public static String saveImage(String base64Image, String fileExtension, String fileNamePrefix){


        // Save the image
        String uploadDir = AppConstants.IMAGE_UPLOAD_DIR;

        // Decode Base64 string to byte array
        byte[] imageData = Base64.decodeBase64(base64Image);

        try {
            Path uploadPath = Paths.get(uploadDir);

            if (Files.notExists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

            //Set a name for the image
            String fileName = fileNamePrefix + formattedDateTime + "." + fileExtension;

            // Path to save the image file
            Path filePath = uploadPath.resolve(fileName);

            // Save the image file
            Files.write(filePath, imageData, StandardOpenOption.CREATE);


            return AppConstants.IMAGE_UPLOAD_FOLDER_URL_PATH + fileName;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String saveAudio(String base64Audio, String fileExtension, String fileNamePrefix){


        // Save the image
        String uploadDir = AppConstants.AUDIO_UPLOAD_DIR;

        // Decode Base64 string to byte array
        byte[] imageData = Base64.decodeBase64(base64Audio);

        try {
            Path uploadPath = Paths.get(uploadDir);

            if (Files.notExists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

            //Set a name for the image
            String fileName = fileNamePrefix + formattedDateTime + "." + fileExtension;

            // Path to save the image file
            Path filePath = uploadPath.resolve(fileName);

            // Save the image file
            Files.write(filePath, imageData, StandardOpenOption.CREATE);


            return AppConstants.AUDIO_UPLOAD_FOLDER_URL_PATH + fileName;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
