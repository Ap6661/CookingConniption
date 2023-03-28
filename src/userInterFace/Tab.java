package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Tab extends JButton
{
  private static final long serialVersionUID = 1L;
  private String name;
  private BufferedImage background;
  private String path;
  private int xOffset = -8;
  private Color unlockedColor = Color.cyan;
  private Color lockedColor = Color.gray;
  private boolean unlocked = true;

  public Tab(String name, int height)
  {
    super();
    this.name = name;
    int width = 30;

    setPreferredSize(new Dimension(width, height));
    setMinimumSize(new Dimension(width, height));
    setMaximumSize(new Dimension(width, height));

    setOpaque(false);
    setBorderPainted(false);

    loadBackground("res/Tab.png", unlockedColor);
  }

  public void loadBackground(String path, Color color)
  {
    BufferedImage image = null;
    try
    {
      image = (BufferedImage) ImageIO.read(new File(path));
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    // coloring picture
    for (int x = 0; x < image.getWidth(); x++)
    {
      for (int y = 0; y < image.getHeight(); y++)
      {
        Color pixelColor = new Color(image.getRGB(x, y), true);
        int r = (pixelColor.getRed() + color.getRed()) / 2;
        int g = (pixelColor.getGreen() + color.getGreen()) / 2;
        int b = (pixelColor.getBlue() + color.getBlue()) / 2;
        int a = pixelColor.getAlpha();
        int rgba = (a << 24) | (r << 16) | (g << 8) | b;
        image.setRGB(x, y, rgba);
      }
    }
    this.background = image;
    this.path = path;
  }

  public void setColor(Color color)
  {
    unlockedColor = color;
    loadBackground(this.path, color);
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    Graphics2D aGraphics2d = (Graphics2D) aGraphics;

    BufferedImage tempBufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D tempGraphics2D = (Graphics2D) tempBufferedImage.getGraphics();

    paintBackground(tempGraphics2D);
    paintText(tempGraphics2D);
    tempGraphics2D.dispose();

    transpose(tempBufferedImage, aGraphics2d);
  }

  public void setOffsetX(int offX)
  {
    this.xOffset = offX;
  }

  public boolean isUnlocked()
  {
    return unlocked;
  }

  public void setUnlocked(boolean unlocked)
  {
    this.unlocked = unlocked;
    this.setEnabled(unlocked);
    loadBackground(path, unlocked ? unlockedColor : lockedColor);
    getParent().getParent().repaint();
  }

  private void transpose(BufferedImage image, Graphics2D g2d)
  {
    g2d.setClip(-4, 0, getWidth(), getHeight());
    g2d.drawImage(image, xOffset, 0, getWidth(), getHeight(), null);
  }

  private void paintText(Graphics2D aGraphics2D)
  {
    int offset = -(getWidth() + 1);
    aGraphics2D.rotate(Math.PI / 2);
    aGraphics2D.translate(0, offset);

    TextRenderer.draw(aGraphics2D, name, getHeight(), getWidth());

    aGraphics2D.translate(0, -offset);
    aGraphics2D.rotate(-Math.PI / 2);
  }

  private void paintBackground(Graphics2D g2d)
  {
    g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
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
