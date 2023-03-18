package userInterFace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JToolBar;

public class TabBar extends JToolBar
{
  private static final long serialVersionUID = 1L;

  private ArrayList<Tab> tags;
  private TabListener tabListener = new TabListener();

  public TabBar()
  {
    super();
    this.tags = new ArrayList<Tab>();
    setFloatable(false);
    setOrientation(VERTICAL);
    getInsets().set(0, 0, 0, 0);;
    //setRollover(true);

    setMargin(new Insets(4, -9, 0, 0));
    setPreferredSize(new Dimension(40, 40));
    setBackground(Color.black);

    addSeparator(new Dimension(0,45));
    // Funtion-ize this VV
    // Also this will eventually be the menu button to switch to the menu
    // Currently just a QOL exit button
    Tab button = new Tab("X", 30);
    button.addActionListener((event) -> System.exit(0));
    button.addMouseListener( this.tabListener );
    tags.add(button);
    add(button);
    addSeparator();

    addTab("Test");
    addTab("0000");
    addTab("Test");
    addTab("Test");
    
    tags.get(0).loadBackground(Color.red);
  }


  public void addTab(String name)
  {
    Tab button = new Tab(name);
    tags.add(button);
    add(button);
    addSeparator();
    button.addMouseListener( this.tabListener );
  }

  private class TabListener implements MouseListener
  {

    @Override
    public void mouseClicked(MouseEvent e)
    {
      // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
      Tab tab = (Tab) e.getSource();
      tab.setOffsetX(-4);
      TabBar.this.repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
      Tab tab = (Tab) e.getSource();
      tab.setOffsetX(-8);
      TabBar.this.repaint();
    }
    
  }

}
