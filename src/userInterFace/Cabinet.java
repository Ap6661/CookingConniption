package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Cabinet extends Scene
{
  private static final long serialVersionUID = 1L;
  private JPanel slotPanel = new JPanel(new GridLayout(2, 2, 75, 75));
  private Slot[] slots = { addSlot(), addSlot(), addSlot(), addSlot() };

  public Cabinet()
  {
    setBackgroundPath("res/Shelves.png");
    setName("Cabinet");
    setTabColor(Color.red);
    slotPanel.setOpaque(false);
    add(slotPanel);
  }

  private Slot addSlot()
  {
    Slot tempSlot = new Slot("X");
    tempSlot.setPreferredSize(new Dimension(100, 100));
    slotPanel.add(tempSlot);
    tempSlot.addMouseListener(this);
    return tempSlot;
  }

  public Slot[] getSlots()
  {
    return slots;
  }

  @Override
  void paintScene(Graphics aGraphics)
  {
  }
}
