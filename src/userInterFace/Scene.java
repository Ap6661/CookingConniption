package userInterFace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class Scene extends JPanel
{
  private static final long serialVersionUID = 1L;
  private BufferedImage background;
  private Color tabColor = Color.cyan;

  public Scene()
  {
    this.setBorder(new EmptyBorder(40, 40, 40, 40));
  }

  public void setBackgroundPath(String path)
  {
    loadImage(path);
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
    this.background = image;
  }

  public Color getTabColor()
  {
    return tabColor;
  }

  public void setTabColor(Color tabColor)
  {
    this.tabColor = tabColor;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    if (background != null)
      aGraphics.drawImage(background, 40, 40, getWidth() - 80, getHeight() - 80, null);
    paintScene(aGraphics);
  }

  abstract void paintScene(Graphics aGraphics);

}