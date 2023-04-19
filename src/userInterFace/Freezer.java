package userInterFace;

import java.awt.Color;
import java.awt.Graphics;

public class Freezer extends Cabinet
{
  private static final long serialVersionUID = 1L;

  public Freezer()
  {
    setBackgroundPath("res/Freezer.png");
    setName("Fridge");
    setTabColor(Color.cyan);
  }

  @Override
  void paintScene(Graphics aGraphics)
  {
  }
  
}
