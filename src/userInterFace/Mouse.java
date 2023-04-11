package userInterFace;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Mouse 
{
  private JFrame frame;

  public Mouse(JFrame aJFrame)
  {
    frame = aJFrame;
  }

  public void setCursor(Image aImage)
  {
    Toolkit tempToolkit = Toolkit.getDefaultToolkit();
    frame.setCursor(tempToolkit.createCustomCursor(aImage, new Point(0,0), "Img"));
  }
}