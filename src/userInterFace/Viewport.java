package userInterFace;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

interface DrawerListener
{
  void slotPressed(Drawer aDrawer, Slot aSlot);
}

interface ViewportListener
{
  void slotPressed(Drawer aDrawer, Slot aSlot);

  void slotPressed(Slot aSlot);

  void craftPressed(Scene aScene);
}

public class Viewport extends JLayeredPane implements DrawerListener, SceneListener
{
  private static final long serialVersionUID = 1L;
  private JPanel drawerLayer = new JPanel(new BorderLayout(40, 40));
  private JPanel sceneLayer = new JPanel(new CardLayout(30, 30));
  private JPanel buttonLayer = new JPanel(new BorderLayout(40, 40));
  private CraftButton craftButton = new CraftButton();
  private Scene activeScene;

  private Drawer lineStartDrawer;
  private Drawer lineEndDrawer;

  private ViewportListener viewportListener;
  private Background background = new Background(100, 20);

  public Viewport()
  {
    super();
    setLayout(new ViewportLayout());

    add(drawerLayer);
    add(sceneLayer);

    add(buttonLayer);
    buttonLayer.add(craftButton, BorderLayout.PAGE_END);
    craftButton.setSceneListener(this);

    setOpaque(false);
    sceneLayer.setOpaque(false);
    buttonLayer.setOpaque(false);
    drawerLayer.setOpaque(false);

    lineStartDrawer = addDrawer(BorderLayout.LINE_START);
    lineEndDrawer = addDrawer(BorderLayout.LINE_END);
    addDrawerSpacer(BorderLayout.PAGE_START);
    addDrawerSpacer(BorderLayout.PAGE_END);

  }

  private void addDrawerSpacer(String location)
  {
    JPanel tempJPanel = new JPanel();
    tempJPanel.setBackground(new Color(0, 0, 0, 0));
    drawerLayer.add(tempJPanel, location);
  }

  private Drawer addDrawer(String location)
  {
    Drawer tempDrawer = new Drawer(location);
    tempDrawer.addMouseListener(new DrawerListener());
    tempDrawer.setDrawerListener(this);
    drawerLayer.add(tempDrawer, location);
    return tempDrawer;
  }

  public CraftButton getCraftButton()
  {
    return craftButton;
  }

  public JPanel getDrawerLayer()
  {
    return drawerLayer;
  }

  public Drawer getLineStartDrawer()
  {
    return lineStartDrawer;
  }

  public Drawer getLineEndDrawer()
  {
    return lineEndDrawer;
  }

  public JPanel getSceneLayer()
  {
    return sceneLayer;
  }

  public void setScene(Scene aScene)
  {
    if (activeScene != null)
    {
      activeScene.setSceneListener(null);
      sceneLayer.remove(activeScene);
    }
    activeScene = aScene;
    activeScene.setSceneListener(this);
    sceneLayer.add(activeScene);

    craftButton.setVisible(false);

    getParent().revalidate();
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    background.paint(aGraphics, 0, 0, getWidth(), getHeight());
    // super.paintComponent(aGraphics);
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
      Drawer aDrawer = (Drawer) e.getSource();
      aDrawer.setState(aDrawer.getState() != Drawer.OPENED ? Drawer.OPENED : Drawer.CLOSED);
      aDrawer.revalidate();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
      // Drawer aDrawer = (Drawer) e.getSource();
      // aDrawer.setState(Drawer.OPENED);
      // aDrawer.revalidate();
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
      // Drawer aDrawer = (Drawer) e.getSource();
      // aDrawer.setState(Drawer.CLOSED);
      // aDrawer.revalidate();
    }

  }

  private class ViewportLayout implements LayoutManager
  {
    private int margin = 10;

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
      return new Dimension(parent.getWidth() - (2 * margin), parent.getHeight() - (2 * margin));
    }

    @Override
    public Dimension minimumLayoutSize(Container parent)
    {
      return new Dimension(parent.getWidth() - (2 * margin), parent.getHeight() - (2 * margin));
    }

    @Override
    public void layoutContainer(Container parent)
    {
      Component[] aComponentList = parent.getComponents();
      for (int i = 0; i < aComponentList.length; i++)
      {
        aComponentList[i].setBounds(margin, margin, parent.getWidth() - (margin * 2),
            parent.getHeight() - (margin * 2));
      }
    }
  }

  @Override
  public void slotPressed(Drawer aDrawer, Slot aSlot)
  {
    if (viewportListener != null)
      this.viewportListener.slotPressed(aDrawer, aSlot);
  }

  public void setViewportListener(ViewportListener aViewportListener)
  {
    this.viewportListener = aViewportListener;
  }

  @Override
  public void slotPressed(Slot aSlot)
  {
    if (viewportListener != null)
      this.viewportListener.slotPressed(aSlot);
  }

  @Override
  public void craftPressed()
  {
    if (viewportListener != null)
      this.viewportListener.craftPressed(activeScene);
  }
}