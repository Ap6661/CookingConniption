package userInterFace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Viewport extends JLayeredPane
{
  private static final long serialVersionUID = 1L;
  private JPanel drawerLayer = new JPanel(new BorderLayout(30, 30));
  private JPanel sceneLayer = new JPanel(new BorderLayout(30, 30));

  private Dimension[] drawerSizes = { new Dimension(20, 20), new Dimension(150, 150) };

  public Viewport()
  {
    super();
    setLayout(new ViewportLayout());

    add(drawerLayer, PALETTE_LAYER);
    add(sceneLayer, DEFAULT_LAYER);

    // sceneLayer.setBounds(0, 0, 500, 500);
    // drawerLayer.setBounds(0, 0, 500, 500);

    sceneLayer.setOpaque(true);
    drawerLayer.setOpaque(false);

    sceneLayer.setBackground(Color.red);
    JButton tempJButton = new JButton();
    tempJButton.setBackground(Color.red);
    sceneLayer.add(tempJButton);

    addDrawer(BorderLayout.LINE_START);
    addDrawer(BorderLayout.LINE_END);
    addDrawerSpacer(BorderLayout.PAGE_START);
    addDrawerSpacer(BorderLayout.PAGE_END);

  }

  private void addDrawerSpacer(String location)
  {
    JPanel tempJPanel = new JPanel();
    tempJPanel.setBackground(new Color(0, 0, 0, 0));
    drawerLayer.add(tempJPanel, location);
  }

  private void addDrawer(String location)
  {
    JPanel drawer = new JPanel();
    drawer.addMouseListener(new DrawerListener());
    drawer.setPreferredSize(drawerSizes[0]);
    drawerLayer.add(drawer, location);
  }

  private class DrawerListener implements MouseListener
  {
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
      JPanel aJPanel = (JPanel) e.getSource();
      aJPanel.setPreferredSize(drawerSizes[1]);
      aJPanel.revalidate();
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
      JPanel aJPanel = (JPanel) e.getSource();
      aJPanel.setPreferredSize(drawerSizes[0]);
      aJPanel.revalidate();
    }

  }

  private class ViewportLayout implements LayoutManager
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
      return new Dimension(parent.getWidth(), parent.getHeight());
    }

    @Override
    public Dimension minimumLayoutSize(Container parent)
    {
      return new Dimension(parent.getWidth(), parent.getHeight());
    }

    @Override
    public void layoutContainer(Container parent)
    {
      Component[] aComponentList = parent.getComponents();
      for (int i = 0; i < aComponentList.length; i++)
      {
        aComponentList[i].setBounds(0, 0, parent.getWidth(), parent.getHeight());
      }
    }
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