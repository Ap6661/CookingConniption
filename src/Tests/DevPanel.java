package Tests;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import userInterFace.Tab;
import userInterFace.Window;

public class DevPanel
{
  private Window window;

  public static void main(String[] args)
  {
    DevPanel d = new DevPanel();
  }

  public DevPanel()
  {
    JFrame dev = new JFrame("DevPanel");
    dev.setVisible(true);
    dev.setSize(new Dimension(500, 500));
    dev.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JPanel pane = new JPanel();
    dev.getContentPane().add(pane);

    window = new Window();
    window.setVisible(true);

    Tab locked = window.getGameFrame().getTabBar().addTab("Locked");
    locked.setUnlocked(false);

    pane.add(new JButton(new ToggleLockedAction(locked)));
    pane.add(new JButton(new CreateTabAction()));
    pane.add(new JButton(new ToggleComponentAction(locked)));
    pane.add(new JButton(new ToggleComponentAction(window.getMenu())));
    pane.add(new JButton(new ToggleComponentAction(window.getGameFrame())));

  }

  private class ToggleLockedAction extends AbstractAction
  {
    private static final long serialVersionUID = 1L;
    private Tab locked;

    public ToggleLockedAction(Tab t)
    {
      locked = t;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      locked.setUnlocked(!locked.isUnlocked());
    }
  }

  private class CreateTabAction extends AbstractAction
  {
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent e)
    {
      Tab button = window.getGameFrame().getTabBar().addTab("Button");
      button.setAction(new Remove());
    }

    private class Remove extends AbstractAction
    {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e)
      {
        Component c = (Component) e.getSource();
        Component p = c.getParent();
        c.getParent().remove(c);
        p.getParent().repaint();
      }
    }
  }

  private class ToggleComponentAction extends AbstractAction
  {
    private static final long serialVersionUID = 1L;
    private Component c;

    public ToggleComponentAction(Component c)
    {
      this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      c.setVisible(!c.isVisible());
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