package userInterFace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class CraftingScene extends Scene
{
  private static final long serialVersionUID = 1L;

  public CraftingScene()
  {
    setName("Craft");
    setTabColor(Color.orange);
    slotPanel = new JPanel(new GridLayout(3, 3, 20, 20));
    slotPanel.setOpaque(false);
    add(slotPanel);
    addSlots(9);
    isCrafting = true;
  }

  @Override
  void paintScene(Graphics aGraphics)
  {
  }
}