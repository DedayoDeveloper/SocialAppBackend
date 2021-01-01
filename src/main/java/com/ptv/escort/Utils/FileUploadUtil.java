package com.ptv.escort.Utils;


import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }


    }


    public static String saveFile2(String fileName,
                                   MultipartFile multipartFile) throws IOException {

        String attachement = "";

        if(!multipartFile.isEmpty ()) {
            try {
                InputStream in = multipartFile.getInputStream ();
                File currDir = new File (".");
                String path = currDir.getAbsolutePath ();
                FileOutputStream f = new FileOutputStream (
                        path.substring ( 0 , path.length () - 1 ) + multipartFile.getOriginalFilename () );
                int ch = 0;

                while ( ( ch = in.read () ) != - 1 ) {
                    f.write ( ch );
                }

                f.flush ();
                f.close ();
                 attachement = multipartFile.getResource().getFilename();
            }
            catch ( Exception e ) {
                e.printStackTrace ();
            }
        }
        return attachement;

    }

}
