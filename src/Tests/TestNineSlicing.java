package Tests;

import java.awt.Graphics;

import javax.swing.*;

import userInterFace.Background;

public class TestNineSlicing
{
  public class MyPanel extends JPanel
  {
    private static final long serialVersionUID = 1L;
    private Background background;
    private Background inner;
    private Background item1;

    public MyPanel()
    {
      super();
      this.background = new Background(100, 20, "res/Cover.png");
      this.inner = new Background(100, 24, "res/MenuItem.png");
      this.item1 = new Background(75, 24, "res/MenuItem.png");
    }

    @Override
    public void paintComponent(Graphics g)
    {
      background.paint(g, 0, 0, getWidth(), getHeight());
      inner.paint(g, 30, 100, getWidth() - 60, 210);
      item1.paint(g, 200, 350, getWidth() - 400, 160);
    }

  }

  public static void main(String[] args)
  {
    TestNineSlicing t = new TestNineSlicing();
  }

  public TestNineSlicing()
  {
    JFrame jframe = new JFrame("9-slicing");
    MyPanel panel = new MyPanel();
    jframe.add(panel);
    jframe.setVisible(true);
  }

}