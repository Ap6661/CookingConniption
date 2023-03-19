package userInterFace;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Menu extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Background background = new Background(100, 20, "res/Cover.png");
  private JPanel items = new JPanel();
  private JPanel menu = new JPanel(new BorderLayout());

  public Menu()
  {
    super(new BorderLayout());
    setOpaque(false);
    GridLayout tempGridLayout = new GridLayout(0, 1);

    for (int i = 0; i < 4; i++)
    {
      addSpacer(this, i, 50);
    }

    add(menu, BorderLayout.CENTER);

    
    addSpacer(menu, 1, 100);
    addSpacer(menu, 3, 100);
    addSpacer(items, 2, 1);
    
    menu.add(new MenuItem(200, 100), BorderLayout.PAGE_START);
    menu.add(items, BorderLayout.CENTER);
    items.setLayout(tempGridLayout);
    items.setOpaque(false);
    menu.setOpaque(false);
    tempGridLayout.setVgap(30);

    addMenuItem();
    addMenuItem();
    addMenuItem();

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

  public MenuItem addMenuItem()
  {
    MenuItem tempMenuItem = new MenuItem(50, 39);
    items.add(tempMenuItem);
    return tempMenuItem;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    background.paint(aGraphics, 0, 0, getWidth(), getHeight());
  }
}
