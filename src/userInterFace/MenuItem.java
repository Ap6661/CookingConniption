package userInterFace;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class MenuItem extends JButton
{
  private static final long serialVersionUID = 1L;
  private static final Font MENU_FONT = new Font("Hack NF", Font.BOLD, 40);

  private Background background;
  private int fontSize;
  private int margin;

  public MenuItem(int size, int scale, int fontSize)
  {
    super();
    this.setMinimumSize(new Dimension(size, size));
    this.setPreferredSize(new Dimension(size, size));
    this.setMaximumSize(new Dimension(size, size));
    this.setSize(new Dimension(size, size));
    
    this.fontSize = fontSize;
    background = new Background(scale, 24, "res/MenuItem.png");
    setBorderPainted(false);
    setOpaque(false);
  }

  public MenuItem(int size, int scale)
  {
    this(size, scale, 30);
  }
  
  public void setMargin(int margin) { 
    this.margin = margin;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    // super.paintComponent(aGraphics);
    // aGraphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    Graphics2D aGraphics2D = (Graphics2D) aGraphics;

    BufferedImage tempBufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D tempGraphics2D = (Graphics2D) tempBufferedImage.getGraphics();
    background.paint(tempGraphics2D, 0, 0, getWidth(), getHeight());
    tempGraphics2D.setFont(MENU_FONT);
    if (getName() != null)
      TextRenderer.draw(tempGraphics2D, getName(), getWidth(), getHeight(), fontSize);
    tempGraphics2D.dispose();

    aGraphics2D.drawImage(tempBufferedImage, margin, margin, getWidth() - (margin * 2), getHeight() - (margin * 2), null);
  }
}