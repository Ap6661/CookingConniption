package userInterFace;

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
    getInsets().set(0, 0, 0, 0);

    setMargin(new Insets(0, -3, 0, 0));
    setPreferredSize(new Dimension(40, 40));
    setOpaque(false);
    addSeparator(new Dimension(0, 50));

  }

  public Tab addTab(String name, int height)
  {
    Tab button = new Tab(name, height);
    tags.add(button);
    add(button);
    addSeparator(new Dimension(0, 5));
    button.addMouseListener(this.tabListener);
    getParent().repaint();
    return button;
  }

  public Tab addTab(String name)
  {
    return addTab(name, 75);
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
      if (tab.isUnlocked())
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
