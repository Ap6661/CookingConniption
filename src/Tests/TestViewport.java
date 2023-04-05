package Tests;

import java.awt.Dimension;

import javax.swing.JFrame;

import userInterFace.Cabinet;
import userInterFace.Scene;
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

    jframe.setSize(500, 500);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.setResizable(false);
    jframe.setLocationRelativeTo(null);
    jframe.setUndecorated(true);

    Viewport tempViewport = new Viewport();
    jframe.add(tempViewport);
    tempViewport.setPreferredSize(new Dimension(400,400));
    jframe.setVisible(true);
    
    
    tempViewport.setScene(new Cabinet());
  }
}