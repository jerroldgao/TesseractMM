import net.sourceforge.tess4j.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yirugao on 11/7/16.
 */
public class TesseractExample {
    Mat resizeImage;
    private static final int RESIZEWIDTH = 6600;
    private static final int RESIZEHEIGHT = 5100;
    private static final int DIVIDEWIDTH = 2;
    private static final int DIVIDEHEIGHT = 2;
    public static void main(String[] args) throws IOException {
        File imageFile = new File("First_Floor.jpg");
//        File resizedImage = resize(imageFile);
        BufferedImage img = ImageIO.read(imageFile);
        List<Word> test = new ArrayList<>();
        System.out.println(img.getWidth());
        System.out.println(img.getHeight());
//        ITesseract instance = new Tesseract();  // JNA Interface Mapping
//        for (int col=0; col<DIVIDEWIDTH;col++){
//            for (int row=0; row<DIVIDEHEIGHT;row++){
////                try {
////                    String result = instance.doOCR(new File(""+(col+1)+(row+1)+".jpg"));
////                    System.out.println(result);
////                } catch (TesseractException e) {
////                    System.err.println(e.getMessage());
////                }
//            }
//
//        }
//        instance.setLanguage("eng");
//
//        instance.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
////        instance.setDatapath("tessdata");
//        test.addAll(instance.getWords(img,3));
//        for (Word word : test){
////            System.out.println(word.getText()+ " has position at: " +word.getBoundingBox());
//        }

    }

    private static BufferedImage[][] divideImage(File imageFile,int row,int col) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        BufferedImage[][] result = new BufferedImage[row][col];
        try {
            BufferedImage img = ImageIO.read(imageFile);
            byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer())
                    .getData();
            Mat matImg = new Mat(img.getHeight(), img.getWidth(), CvType.CV_8UC3);
            matImg.put(0,0,pixels);
            int height = img.getHeight()/DIVIDEHEIGHT;
            int width = img.getWidth()/DIVIDEWIDTH;
            for (int i =0; i<col; i++){
                for (int j = 0; j<row;j++) {
                    Mat outputMat = new Mat(matImg,new Rect(j*width,i*height,width,height));
                    String name = ""+(j+1)+(i+1)+".jpg";
//                    Imgcodecs.imwrite(name,outputMat);
                    result[i][j] = new BufferedImage(matImg.width(),matImg.height(),BufferedImage.TYPE_3BYTE_BGR);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    private static File resize(File imageFile) {
        Mat resizeImage;
        BufferedImage img;
        File resizedImageFile;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        try {
            img = ImageIO.read(imageFile);
            byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer())
                    .getData();
            Mat matImg = new Mat(img.getHeight(), img.getWidth(), CvType.CV_8UC3);
            matImg.put(0, 0, pixels);

//            resizeImage = new Mat();
//            Size sz = new Size(RESIZEWIDTH, RESIZEHEIGHT);
//            Imgproc.resize(matImg, resizeImage, sz);
//
//            byte[] data = new byte[RESIZEWIDTH * RESIZEHEIGHT * (int)resizeImage.elemSize()];
//            int type;
//            resizeImage.get(0, 0, data);
            Imgcodecs.imwrite("resizedImage.jpg",matImg);
            resizedImageFile = new File("resizedImage.jpg");
            return resizedImageFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }
}
