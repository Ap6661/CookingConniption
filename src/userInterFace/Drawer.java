package userInterFace;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Drawer extends JPanel implements MouseListener
{
  private static final long serialVersionUID = 1L;
  private Background background = new Background(70, 20);
  private Dimension[] drawerSizes = { new Dimension(35, 35), new Dimension(150, 150) };
  private int state;
  private String location;
  private JPanel slotPanel = new JPanel(new GridLayout(4, 2, 10, 10));
  private Slot[] slots = { addSlot(), addSlot(), addSlot(), addSlot(), addSlot(), addSlot(), addSlot(), addSlot() };

  private DrawerListener drawerListener;

  public static final int CLOSED = 0;
  public static final int OPENED = 1;

  public Drawer(String location)
  {
    super(new BorderLayout());
    setBackground(new Color(0, 0, 0, 0));
    setState(CLOSED);
    this.location = location;

    add(slotPanel);
    slotPanel.setBackground(new Color(0, 0, 0, 0));
    slotPanel.setOpaque(false);
  }

  public void setState(int state)
  {
    this.state = state;
    slotPanel.setVisible(state == OPENED);
    setPreferredSize(drawerSizes[state]);
  }

  public int getState()
  {
    return this.state;
  }

  public void setDrawerListener(DrawerListener aDrawerListener)
  {
    this.drawerListener = aDrawerListener;
  }

  private Slot addSlot()
  {
    Slot tempSlot = new Slot("X");
    tempSlot.setPreferredSize(new Dimension(50, 50));
    slotPanel.add(tempSlot);
    tempSlot.addMouseListener(this);
    return tempSlot;
  }

  public Slot[] getSlots()
  {
    return slots;
  }

  private void setSlotAreaBorder()
  {
    int slotAreaHeight = (50 + 10) * 4;
    int vPadding = (getHeight() - slotAreaHeight) / 2;

    if (location.equals(BorderLayout.LINE_START))
    {
      slotPanel.setBorder(new EmptyBorder(vPadding, 10, vPadding, 25));
    } else
    {
      slotPanel.setBorder(new EmptyBorder(vPadding, 25, vPadding, 10));
    }
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    int offset = 0;
    if (slotPanel.getBorder() == null)
      setSlotAreaBorder();

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

  @Override
  public void mouseClicked(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    if (drawerListener != null)
      drawerListener.slotPressed(this, (Slot) e.getSource());

  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    // TODO Auto-generated method stub

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