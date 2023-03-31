package userInterFace;

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

  public Scene(String path)
  {
    setBackgroundPath(path);
    this.setBorder(new EmptyBorder(40, 40, 40, 40));
  }

  public Scene()
  {
    this("res/Shelves.png");
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

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    aGraphics.drawImage(background, 40, 40, getWidth() - 80, getHeight() - 80, null);
    paintScene(aGraphics);
  }

  abstract void paintScene(Graphics aGraphics);
}