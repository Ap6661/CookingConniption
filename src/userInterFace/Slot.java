package userInterFace;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class Slot extends JButton
{
  private static final long serialVersionUID = 1L;
  private Background background = new Background(30, 20);

  public Slot(String name)
  {
    super(name);
    setBackground(new Color(0,0,0,0));
    setOpaque(false);
    setBorderPainted(false);
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    background.paint(aGraphics, 0, 0, getWidth(), getHeight());
  }
}
