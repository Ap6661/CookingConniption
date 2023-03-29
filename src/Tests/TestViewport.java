package Tests;

import java.awt.Dimension;

import javax.swing.JFrame;

import userInterFace.Viewport;

public class TestViewport
{
  public static void main(String[] args)
  {
    TestViewport t = new TestViewport();
  }

  public TestViewport()
  {
    JFrame jframe = new JFrame("Test Viewport");
    Viewport tempViewport = new Viewport();
    jframe.add(tempViewport);
    tempViewport.setPreferredSize(new Dimension(400,400));
    jframe.setVisible(true);
  }

}
