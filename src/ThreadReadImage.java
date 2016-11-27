import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yirugao on 11/27/16.
 */
public class ThreadReadImage extends Thread{
//    private Thread thread;
    private static final int READWIDTH = 100;
    private static final int READHEIGHT = 50;
    private BufferedImage images;
    private List<Word> words;
    private ITesseract tesseract;
    private ArrayList<Room> rooms;
    ThreadReadImage(BufferedImage images, ITesseract tesseract){
        this.images= images;
        this.tesseract = tesseract;
    }
    public void run(){
        words = new ArrayList<>();
        rooms = new ArrayList<>();
        words.addAll(tesseract.getWords(images,3));
        //Here make it check with database
        //if it does not contain database data, abort thread or image to save memory
    }

    public static void main(String[] args) {
        File imageFile = new File("First_Floor.jpg");
        try {
            BufferedImage img = ImageIO.read(imageFile);
            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();
            int imageWidthTimes = imageWidth/READWIDTH;
            int imageHeightTimes = imageWidth/READHEIGHT;
            ITesseract tesseract = new Tesseract();
            tesseract.setLanguage("eng");
            tesseract.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
            BufferedImage subImg;
            for (int i = 0;i < imageWidthTimes;i++){
                for(int j = 0;j < imageHeightTimes;j++){
                    int width,height;
                    if (i==imageWidthTimes-1){
                        width = imageWidth - i*READWIDTH;
                    }else {
                        width = READWIDTH;
                    }
                    if (j==imageHeightTimes-1){
                        height = imageHeight - j*READHEIGHT;
                    }else {
                        height = READHEIGHT;
                    }
                    subImg = img.getSubimage(i*READWIDTH,j*READHEIGHT,width,height);
                    Thread thread = new ThreadReadImage(subImg,tesseract);
                    thread.start();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
