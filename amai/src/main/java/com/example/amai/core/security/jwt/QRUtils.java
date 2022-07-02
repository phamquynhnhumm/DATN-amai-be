package com.example.amai.core.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Base64;

public class QRUtils {
    public static String prettyObject(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String generateQrCode(String data, int width, int height, String imagePath) {
        StringBuilder resultImage = new StringBuilder();

        if (!data.isEmpty()) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            try {
                QRCodeWriter writer = new QRCodeWriter();
//                BarcodeFormat.QR_CODE Kiểu dữ liệu sinh ra
                BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height);

                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageIO.write(bufferedImage, "png", os);

                resultImage.append("data:image/png;base64,");
                resultImage.append(new String(Base64.getEncoder().encode(os.toByteArray())));

                Path path = FileSystems.getDefault().getPath(imagePath);
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultImage.toString();
    }
}