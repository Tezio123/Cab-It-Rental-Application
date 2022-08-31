package com.cabit.Cab_It.helper;

import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ImageProcessingHelper extends DaoSetupMysqlImpl
{
    /*
     * Helper class to perform processing related to images
     * */
    private final int IMAGE_WIDTH = 200;
    private final int IMAGE_HEIGHT = 200;
    private final String TEMP_FILE_PATH = "E:/Cab-It-Workplace/Cab_It/src/main/java/com/cabit/Cab_It/multimedia/temp.jpg";
    private final String LOGO_FILE_PATH = "E:/Cab-It-Workplace/Cab_It/src/main/java/com/cabit/Cab_It/multimedia/logo.jpg";

    public byte[] preprocess(File file)
    {
        try
        {
            BufferedImage image;

            if(file == null)
                image = ImageIO.read(new File(LOGO_FILE_PATH));
            else
                image = ImageIO.read(file);

            Image scaledImage = image.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byte[] scaledBytes = byteArrayOutputStream.toByteArray();

            return compressBytes(scaledBytes);
        }
        catch (IOException ioException)
        {
            loginHelper.fatal(ioException.getMessage());
        }
        return null;
    }

    public byte[] postprocess(Blob blob)
    {
        byte[] bytes = toBytes(blob);

        return decompressBytes(bytes);
    }

    public byte[] toBytes(Blob blob)
    {
        byte[] bytes = null;

        try
        {
            bytes = blob.getBytes(1, (int) blob.length());
        }
        catch (SQLException sqlException) {
            loginHelper.fatal(sqlException.getMessage());
        }
        return bytes;
    }

    public File toFile(byte[] bytes)
    {
        File file = new File(TEMP_FILE_PATH);

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            
            fileOutputStream.close();
        }
        catch (IOException ioException)
        {
            loginHelper.fatal(ioException.getMessage());
        }
        return file;
    }
    public byte[] compressBytes(byte[] bytes){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try{
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(bytes);
            
            gzipOutputStream.close();

        } catch(IOException exception){
            loginHelper.fatal(exception.getMessage());
        }

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] decompressBytes(byte[] bytes){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try{
            IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(bytes)), byteArrayOutputStream);

        } catch(IOException e){
            throw new RuntimeException(e);
        }

        return byteArrayOutputStream.toByteArray();
    }
}
