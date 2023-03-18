package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
  private static final Font TAB_FONT = new Font("Hack NF", Font.BOLD, 15);
  private String name;
  private BufferedImage background;
  private String path;
  private int xOffset; 

  public Tab(String name, int height)
  {
    super();
    this.name = name;
    this.xOffset = -8;
    int width = 30;
    setPreferredSize(new Dimension(width, height));
    setMinimumSize(new Dimension(width, height));
    setMaximumSize(new Dimension(width, height));

    setOpaque(false);
    setBorderPainted(false);

    loadBackground("res/Tab.png", Color.CYAN);
  }

  public Tab(String name)
  {
    this(name, 75);
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
    
    //coloring picture
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

  public void loadBackground(Color color)
  {
    loadBackground(this.path, color);
  }

  @Override
  public void paintComponent(Graphics g)
  {
    // super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D g2dimg = (Graphics2D) img.getGraphics();

    paintBackground(g2dimg);
    paintText(g2dimg);
    g2dimg.dispose();

    transpose(img, g2d);

  }
  
  public void setOffsetX( int offX )
  {
    this.xOffset = offX; 
  }
  
  private void transpose(BufferedImage image, Graphics2D g2d)
  {
    g2d.setClip(-4, 0, this.getWidth(), this.getHeight());
    g2d.drawImage(image, this.xOffset, 0, this.getWidth(), this.getHeight(), null);
  }

  private void paintText(Graphics2D g2d)
  {
    Color oldPaint = g2d.getColor();

    g2d.setColor(Color.black);

    g2d.setFont(TAB_FONT);

    FontMetrics fm = g2d.getFontMetrics();

    int textW = fm.stringWidth(name);
    int textH = fm.getAscent();
    int textX = (this.getWidth() - textH) / 2;
    int textY = (this.getHeight() - textW) / 2;

    g2d.translate(textX, textY);
    g2d.rotate(Math.PI / 2);
    g2d.drawString(this.name, 0, -1);
    g2d.rotate(-Math.PI / 2);
    g2d.translate(-textX, -textY);

    g2d.setColor(oldPaint);
  }

  private void paintBackground(Graphics2D g2d)
  {
    g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
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
