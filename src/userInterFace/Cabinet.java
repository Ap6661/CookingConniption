package userInterFace;

import java.awt.Color;
import java.awt.Graphics;

public class Cabinet extends Scene
{
  private static final long serialVersionUID = 1L;

  public Cabinet()
  {
    setBackgroundPath("res/Shelves.png");
    setName("Cabinet");
    setTabColor(Color.red);
  }

  @Override
  void paintScene(Graphics aGraphics)
  {
  }
  
}
