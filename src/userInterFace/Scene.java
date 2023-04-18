package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class Scene extends JPanel implements MouseListener
{
  private static final long serialVersionUID = 1L;
  protected BufferedImage background;
  private Color tabColor = Color.cyan;
  private SceneListener sceneListener;
  protected Slot[] slots;
  protected JPanel slotPanel;
  protected boolean isCrafting = false;

  public Scene()
  {
    this.setBorder(new EmptyBorder(40, 40, 40, 40));
  }

  public void setBackgroundPath(String path)
  {
    loadImage(path);
  }

  private void loadImage(String path)
  {
    BufferedImage image = null;
    try
    {
      image = (BufferedImage) ImageIO.read(new File(path));
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    this.background = image;
  }

  public boolean isCrafting()
  {
    return this.isCrafting;
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

  protected void addSlots(int length)
  {
    slots = new Slot[length];
    for (int i = 0; i < length; i++)
      slots[i] = addSlot();

  }

  public Color getTabColor()
  {
    return tabColor;
  }

  public void setTabColor(Color tabColor)
  {
    this.tabColor = tabColor;
  }

  public void setSceneListener(SceneListener aSceneListener)
  {
    this.sceneListener = aSceneListener;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    if (background != null)
      aGraphics.drawImage(background, 40, 40, getWidth() - 80, getHeight() - 80, null);
    paintScene(aGraphics);
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    if (sceneListener != null)
      sceneListener.slotPressed((Slot) e.getSource());

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

  abstract void paintScene(Graphics aGraphics);

}