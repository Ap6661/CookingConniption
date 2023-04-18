package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JToolTip;

import engine.Item;

public class Slot extends JButton
{
  private static final long serialVersionUID = 1L;
  private Background background = new Background(30, 20);

  private Image itemImage;
  private String text;

  public Slot(String name)
  {
    super(name);
    setBackground(new Color(0, 0, 0, 0));
    setOpaque(false);
    setBorderPainted(false);

    createToolTip();
  }

  public void setItem(Item aItem)
  {
    itemImage = null;
    if (aItem != null)
    {
      itemImage = aItem.getImage();
      this.setToolTipText("ITEM: " + aItem.getID());
      this.text = "ITEM: " + aItem.getID();
    } else
    {
      this.setToolTipText(null);
      this.text = null;
    }

  }

  @Override
  public JToolTip createToolTip()
  {
    JToolTip tempJToolTip = new ToolTip();
    tempJToolTip.setComponent(this);
    return tempJToolTip;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    background.paint(aGraphics, 0, 0, getWidth(), getHeight());

    if (itemImage != null)
      aGraphics.drawImage(itemImage, 10, 10, getWidth() - 20, getHeight() - 20, null);

  }

  private class ToolTip extends JToolTip
  {
    private static final long serialVersionUID = 1L;
    private Background background = new Background(30, 20, "res/cover.png");

    public ToolTip()
    {
      this.setPreferredSize(new Dimension(100, 100));
      this.setBackground(new Color(0, 0, 0, 0));

    }

    @Override
    public void paintComponent(Graphics aGraphics)
    {
      background.paint(aGraphics, 0, 0, getWidth() - 1, getHeight() - 1);
      if (text != null)
        TextRenderer.draw(aGraphics, text, getWidth(), getHeight());
    }
  }
}
