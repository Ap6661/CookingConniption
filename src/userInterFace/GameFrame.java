package userInterFace;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class GameFrame extends JPanel 
{
  private static final long serialVersionUID = 1L;

  private TabBar tabBar;
  private DisplayPanel displayPanel;

  public GameFrame() 
  {
    super(new BorderLayout());

    this.displayPanel = new DisplayPanel();
    this.tabBar = new TabBar();
    
    this.add(tabBar, BorderLayout.LINE_END);
    this.add(displayPanel, BorderLayout.CENTER);
  }

}
