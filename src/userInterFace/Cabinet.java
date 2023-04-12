package userInterFace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Cabinet extends Scene
{
  private static final long serialVersionUID = 1L;

  public Cabinet()
  {
    slotPanel = new JPanel(new GridLayout(2, 2, 75, 75));
    slotPanel.setOpaque(false);
    add(slotPanel);
    addSlots(4);

    setBackgroundPath("res/Shelves.png");
    setName("Cabinet");
    setTabColor(Color.red);
  }

  @Override
  void paintScene(Graphics aGraphics)
  {
  }
}
