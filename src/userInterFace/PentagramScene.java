package userInterFace;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class PentagramScene extends Scene
{
  private static final long serialVersionUID = 1L;

  public PentagramScene()
  {
    this.setLayout(new CardLayout());
    this.setBackgroundPath("res/Pentagram.png");
    this.setName("pent");

    slotPanel = new JPanel(new PentagramLayout());
    slotPanel.setOpaque(false);
    add(slotPanel);
    addSlots(5);

  }

  @Override
  void paintScene(Graphics aGraphics)
  {
  }

  private class PentagramLayout implements LayoutManager
  {

    @Override
    public void addLayoutComponent(String name, Component comp)
    {
    }

    @Override
    public void removeLayoutComponent(Component comp)
    {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent)
    {
      return new Dimension(64, 64);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent)
    {
      return new Dimension(64, 64);
    }

    @Override
    public void layoutContainer(Container parent)
    {
      int componentSize = 80;

      Component[] aComponentList = parent.getComponents();
      for (int i = 0; i < aComponentList.length; i++)
      {
        double angle = ((double) (i - 3) / aComponentList.length) * 360;
        angle -= 18;
        angle -= 180;
        angle = angle * Math.PI / 180;

        // Set the magnitude of Vector
        double vectorX = Math.cos(angle) * 0.35;
        double vectorY = Math.sin(angle) * 0.35;

        // Apply it into the local space of the parent
        int x = (int) (parent.getWidth() * vectorX);
        int y = (int) (parent.getHeight() * vectorY);

        // Set origin to be the center of the parent
        x = x + (parent.getWidth() / 2);
        y = y + (parent.getHeight() / 2);

        aComponentList[i].setBounds(x - (componentSize / 2), y - (componentSize / 2), componentSize, componentSize);
      }
    }

  }
}
