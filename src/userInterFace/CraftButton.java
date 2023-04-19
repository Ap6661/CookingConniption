package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CraftButton extends JPanel
{
  private static final long serialVersionUID = 1L;

  private Background background = new Background(20, 20);
  private Craft button = new Craft("Craft");
  private SceneListener listener;

  public CraftButton()
  {
    setOpaque(false);
    setPreferredSize(new Dimension(60, 60));
    add(button);
    button.addMouseListener(new CraftHandler());
  }

  public void setSceneListener(SceneListener aSceneListener)
  {
    this.listener = aSceneListener;
  }

  private class CraftHandler implements MouseListener
  {
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
      button.updateColor(new Color(200, 200, 200));
      if (listener != null)
        listener.craftPressed();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
      button.updateColor(new Color(220, 220, 220));
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
      button.updateColor(new Color(220, 220, 220));
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
      button.updateColor(Color.white);
    }

  }

  private class Craft extends JButton
  {
    private static final long serialVersionUID = 1L;
    private Image image;

    public Craft(String aString)
    {
      super(aString);
      setBorderPainted(false);
      setPreferredSize(new Dimension(200, 50));
    }

    public void updateColor(Color aColor)
    {
      BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
      Graphics tempGraphics = image.getGraphics();
      background.paint(tempGraphics, 0, 0, this.getWidth(), this.getHeight());

      // coloring picture
      for (int x = 0; x < image.getWidth(); x++)
      {
        for (int y = 0; y < image.getHeight(); y++)
        {
          Color pixelColor = new Color(image.getRGB(x, y), true);
          int r = (int) (pixelColor.getRed() * ((double) aColor.getRed() / 255));
          int g = (int) (pixelColor.getGreen() * ((double) aColor.getGreen() / 255));
          int b = (int) (pixelColor.getBlue() * ((double) aColor.getBlue() / 255));
          int a = pixelColor.getAlpha();
          int rgba = (a << 24) | (r << 16) | (g << 8) | b;
          image.setRGB(x, y, rgba);
        }
      }
      this.image = image;
    }

    @Override
    public void paintComponent(Graphics aGraphics)
    {
      if (image == null)
        updateColor(Color.white);

      aGraphics.drawImage(image, 0, 0, null);
      TextRenderer.draw(aGraphics, "Craft", this.getWidth(), this.getHeight());
    }
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