package userInterFace;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

public class MenuItem extends JButton
{
  private static final long serialVersionUID = 1L;

  private Background background;

  public MenuItem(int size, int scale)
  {
    super();
    this.setMinimumSize(new Dimension(size, size));
    this.setPreferredSize(new Dimension(size, size));
    this.setMaximumSize(new Dimension(size, size));
    this.setSize(new Dimension(size, size));

    background = new Background(scale , 24, "res/MenuItem.png");
    setBorderPainted(false);
    setOpaque(false);
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    //super.paintComponent(aGraphics);
    //aGraphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    background.paint(aGraphics, 0, 0, getWidth(), getHeight());
  }
}
