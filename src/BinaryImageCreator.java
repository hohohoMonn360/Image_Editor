import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class BinaryImageCreator  {

    public static void main(String[] args) throws IOException {
        URL url  = new URL("https://hips.hearstapps.com/hmg-prod/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=1200:*");
        InputStream is = url.openStream();
        BufferedImage img = ImageIO.read(is);
        int h=img.getHeight();
        int w=img.getWidth();


        BufferedImage bufferedImage = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
        if (img == null) {
            System.out.println("No image loaded");
        }
        else {
            for(int i=0;i<w;i++)
            {
                for(int j=0;j<h;j++)
                {
                    int val = img.getRGB(i, j);
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m=(r+g+b);
                    if(m>=383)
                    {
                        bufferedImage.setRGB(i, j, Color.WHITE.getRGB());
                    }
                    else{
                        bufferedImage.setRGB(i, j, 0);
                    }
                }
            }
        }


        File file = new File("MyImage.jpg");
        ImageIO.write(bufferedImage, "jpg", file);

    }
}