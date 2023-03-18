package userInterFace;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background
{
  private BufferedImage image;
  private BufferedImage backdrop;
  private String src = "res/Background.png";
  private int dstBorder;
  private int srcBorder;

  public Background(int dstBorder, int srcBorder)
  {
    this.dstBorder = dstBorder;
    this.srcBorder = srcBorder;
  }

  private void loadImage(String path)
  {
    BufferedImage image = null;
    try
    {
      image = (BufferedImage) ImageIO.read(new File(path));
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    this.image = image;
  }

  public void updateBackground()
  {
    Graphics2D bg2d = (Graphics2D) this.backdrop.getGraphics();
    for (int i = 0; i < 9; i++)
    {
      drawSlice(i, bg2d);
    }
    bg2d.dispose();
  }

  private void drawSlice(int slice, Graphics2D bg2d)
  {
    int[][] dstBounds = getSliceBounds(slice, this.backdrop, this.dstBorder);
    int[][] srcBounds = getSliceBounds(slice, this.image, this.srcBorder);
    bg2d.drawImage(this.image, dstBounds[0][0], dstBounds[0][1], dstBounds[1][0], dstBounds[1][1], srcBounds[0][0],
        srcBounds[0][1], srcBounds[1][0], srcBounds[1][1], null);
  }

  private int[][] getSliceBounds(int slice, BufferedImage image, int borderWidth)
  {
    int x = slice % 3;
    int y = slice / 3;
    int width = image.getWidth();
    int height = image.getHeight();
    int bWidth = width - borderWidth;
    int bHeight = height - borderWidth;

    int[][] xSlices = { { 0, borderWidth }, { borderWidth, bWidth }, { bWidth, width } };
    int[][] ySlices = { { 0, borderWidth }, { borderWidth, bHeight }, { bHeight, height } };
    int[][] output = { { xSlices[x][0], ySlices[y][0] }, { xSlices[x][1], ySlices[y][1] } };
    return output;
  }

  public void paint(Graphics g, int w, int h)
  {
    if (this.backdrop == null)
    {
      this.backdrop = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
      loadImage(this.src);
      updateBackground();
    }
    if (this.backdrop.getHeight() != h || this.backdrop.getWidth() != w)
      updateBackground();

    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(this.backdrop, 0, 0, w, h, null);
  }

  public void setSrc(String path)
  {
    this.src = path;
    loadImage(this.src);
    updateBackground();
  }

}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//