package userInterFace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

import engine.Item;

public class Slot extends JButton
{
  private static final long serialVersionUID = 1L;
  private Background background = new Background(30, 20);

  private Image itemImage;

  public Slot(String name)
  {
    super(name);
    setBackground(new Color(0, 0, 0, 0));
    setOpaque(false);
    setBorderPainted(false);
  }

  public void setItem(Item aItem)
  {
    itemImage = null;
    if (aItem != null)
      itemImage = aItem.getImage();
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    background.paint(aGraphics, 0, 0, getWidth(), getHeight());

    if (itemImage != null)
      aGraphics.drawImage(itemImage, 10, 10, getWidth() - 20, getHeight() - 20, null);


  }
}
