package userInterFace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Drawer extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Background background = new Background(70, 20);
  private Dimension[] drawerSizes = { new Dimension(35, 35), new Dimension(150, 150) };
  private int state;
  private String location;
  private JPanel slots = new JPanel(new GridLayout(4, 2, 20, 20));

  public static final int CLOSED = 0;
  public static final int OPENED = 1;

  public Drawer(String location)
  {
    super(new BorderLayout());
    setBackground(new Color(0, 0, 0, 0));
    setState(CLOSED);
    this.location = location;

    add(slots);
    slots.setBackground(new Color(0, 0, 0, 0));
    slots.setOpaque(false);
    if (location.equals(BorderLayout.LINE_START))
    {
      slots.setBorder(new EmptyBorder(50, 10, 50, 25));
    } else
    {
      slots.setBorder(new EmptyBorder(50, 25, 50, 10));
    }

    addSlot();
    addSlot();
    addSlot();
    addSlot();
    addSlot();
    addSlot();
    addSlot();
    addSlot();
  }

  public void setState(int state)
  {
    this.state = state;
    slots.setVisible(state == OPENED);
    setPreferredSize(drawerSizes[state]);
  }

  public int getState()
  {
    return this.state;
  }

  private void addSlot()
  {
    Slot tempSlot = new Slot("X");
    tempSlot.setPreferredSize(new Dimension(50, 50));
    slots.add(tempSlot);

  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    int offset = 0;

    if (state == CLOSED)
      offset = -drawerSizes[OPENED].width;
    if (state == OPENED)
      offset = -drawerSizes[CLOSED].width;

    if (location.equals(BorderLayout.LINE_START))
    {
      background.paint(aGraphics, offset, 0, getWidth() - offset, getHeight());
    } else
    {
      background.paint(aGraphics, 0, 0, getWidth() - offset, getHeight());
    }

    super.paintComponent(aGraphics);
  }

}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//