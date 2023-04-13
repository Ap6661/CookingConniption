package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item
{
  private Image image;
  private int itemID;

  public Item(Color aColor, int itemID)
  {
    BufferedImage tempBufferedImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
    Graphics tempGraphics = tempBufferedImage.getGraphics();
    tempGraphics.setColor(aColor);
    tempGraphics.fillRect(0, 0, 64, 64);
    tempGraphics.dispose();
    image = (Image) tempBufferedImage;
    this.itemID = itemID;
  }

  public Item(Color aColor)
  {
    this(aColor, -1);
  }

  public Item(String path)
  {
    loadImage(path);
  }

  private void loadImage(String path)
  {
    Image image = null;
    try
    {
      image = ImageIO.read(new File(path));
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    this.image = image;
  }

  public Image getImage()
  {
    return image;
  }

  public void setImage(Image aImage)
  {
    image = aImage;
  }

  public int getID()
  {
    return this.itemID;
  }

}
