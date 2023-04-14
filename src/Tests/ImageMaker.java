package Tests;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import userInterFace.Background;


// The only purpose for this is to make the pentagram layouts background 
// and get exactly where the elements would go

public class ImageMaker
{
  public static void main(String[] args)
  {
    writeImage(80);
  }

  public static void writeImage(int componentSize)
  {
    BufferedImage image = new BufferedImage(340, 340, BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.getGraphics();
    // int componentSize = 4;

    // g.drawRect(0, 0, 47, 47);
    System.out.println("WIDTH: " + image.getWidth());
    System.out.println("HEIGHT: " + image.getHeight());

    for (int i = 0; i < 5; i++)
    {
      double angle = ((double) i / 5) * 360;
      angle -= 18;
      angle -= 180;
      angle = angle * Math.PI / 180;

      // Set the magnitude of Vector
      double vectorX = Math.cos(angle) * 0.35;
      double vectorY = Math.sin(angle) * 0.35;

      // Apply it into the local space of the image
      int x = (int) (image.getWidth() * vectorX);
      int y = (int) (image.getHeight() * vectorY);

      // Set origin to be the center of the image
      x = x + (image.getWidth() / 2);
      y = y + (image.getHeight() / 2);

      System.out.println(i + ": " + x);
      System.out.println("\t X: " + x);
      System.out.println("\t Y: " + y);

      Background bg = new Background(componentSize / 3, 20);

      // bg.paint(g, x - (componentSize / 2), y - (componentSize / 2), componentSize,
      // componentSize);
      g.fillRect(x - (componentSize / 2), y - (componentSize / 2), componentSize, componentSize);
    }

    try
    {
      File outputfile = new File("saved.png");
      ImageIO.write(image, "png", outputfile);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
