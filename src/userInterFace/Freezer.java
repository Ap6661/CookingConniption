package userInterFace;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Freezer extends Scene
{
  private static final long serialVersionUID = 1L;

  public Freezer()
  {
    setBackgroundPath("res/Freezer.png");
    setName("Freezer");

    slotPanel = new JPanel();
    slotPanel.setOpaque(false);
    add(slotPanel);
    addSlots(2);
  }

  @Override
  void paintScene(Graphics aGraphics)
  {
  }
  
}
