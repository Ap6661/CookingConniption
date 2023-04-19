package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SkilletScene extends Scene
{
  private static final long serialVersionUID = 1L;

  public SkilletScene()
  {
    this.setBackgroundPath("res/Skillet.png");
    this.setName("Skillet");
    setTabColor(Color.green);
    this.isCrafting = true;

    slotPanel = new JPanel();
    slotPanel.setBorder(new EmptyBorder(85,85,85,85));
    slotPanel.setOpaque(false);
    add(slotPanel);
    addSlots(1);
    slots[0].setPreferredSize(new Dimension(150,150));
  }

  @Override
  void paintScene(Graphics aGraphics)
  {
    // TODO Auto-generated method stub

  }

}
