package userInterFace;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JPanel;

public class Menu extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Background background = new Background(100, 20, "res/Cover.png");
  private JPanel items = new JPanel();
  private JPanel menu = new JPanel(new BorderLayout());
  private int margin = 2;

  public static final int NORTH = 0;
  public static final int EAST = 1;
  public static final int SOUTH = 2;
  public static final int WEST = 3;

  public Menu()
  {
    super(new BorderLayout());
    setOpaque(false);
    items.setOpaque(false);
    menu.setOpaque(false);

    GridLayout tempGridLayout = new GridLayout(0, 1);

    for (int i = 0; i < 4; i++)
    {
      addSpacer(this, i, 50);
    }

    add(menu, BorderLayout.CENTER);

    addSpacer(menu, EAST, 100);
    addSpacer(menu, WEST, 100);
    addSpacer(items, SOUTH, 1);

    MenuItem title = new MenuItem(200, 100, 40);
    title.setName("Cooking Conniption");
    menu.add(title, BorderLayout.PAGE_START);
    menu.add(items, BorderLayout.CENTER);
    items.setLayout(tempGridLayout);
    tempGridLayout.setVgap(30);
  }

  private static void addSpacer(Container aContainer, int direction, int size)
  {
    String[] directions = { "North", "East", "South", "West" };
    Dimension tempDimension = new Dimension(size, size);
    JPanel tempJPanel = new JPanel();
    aContainer.add(tempJPanel, directions[direction]);
    tempJPanel.setPreferredSize(tempDimension);
    tempJPanel.setOpaque(false);
  }

  public MenuItem addMenuItem(String name, Action aAction)
  {
    MenuItem tempMenuItem = new MenuItem(50, 39);
    tempMenuItem.setName(name);
    tempMenuItem.addActionListener(aAction);
    tempMenuItem.addMouseListener(new MenuListener());
    tempMenuItem.setMargin(margin);
    items.add(tempMenuItem);
    return tempMenuItem;
  }

  public MenuItem addMenuItem(Action aAction)
  {
    return addMenuItem(null, aAction);
  }

  public MenuItem addMenuItem(String name)
  {
    return addMenuItem(name, null);
  }

  public MenuItem addMenuItem()
  {
    return addMenuItem(null, null);
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    background.paint(aGraphics, 0, 0, getWidth(), getHeight());
  }

  private class MenuListener implements MouseListener
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
      MenuItem aMenuItem = (MenuItem) e.getSource();
      aMenuItem.setMargin(0);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
      MenuItem aMenuItem = (MenuItem) e.getSource();
      aMenuItem.setMargin(margin);
    }

  }

}