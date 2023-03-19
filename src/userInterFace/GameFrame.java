package userInterFace;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class GameFrame extends JPanel
{
  private static final long serialVersionUID = 1L;

  private TabBar tabBar;
  private DisplayPanel displayPanel;

  public GameFrame()
  {
    super(new BorderLayout());

    setBackground(Color.BLACK);

    displayPanel = new DisplayPanel();
    tabBar = new TabBar();

    add(tabBar, BorderLayout.LINE_END);
    add(displayPanel, BorderLayout.CENTER);
  }

  public DisplayPanel getDisplayPanel()
  {
    return displayPanel;
  }

  public TabBar getTabBar()
  {
    return tabBar;
  }
}
