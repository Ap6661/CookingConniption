package userInterFace;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Window extends JFrame
{
  private static final long serialVersionUID = 1L;

  private GameFrame frame = new GameFrame();
  private Menu menu = new Menu();

  public Window()
  {
    super("Cooking Conniption!");

    setLayout(new OverlayLayout(getContentPane()));

    setSize(740, 700);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
    setUndecorated(true);

    getContentPane().add(frame);
    frame.setVisible(false);

    getContentPane().add(menu);
    // menu.add(new JButton(new ToggleMenuAction()));

    menu.addMenuItem("Play", new ToggleMenuAction());
    menu.addMenuItem();
    menu.addMenuItem("Exit").addActionListener((event) -> System.exit(0));

    Tab button = frame.getTabBar().addTab("Menu");
    button.setAction(new ToggleMenuAction());
    button.setColor(Color.green);

    invalidate();
  }

  public GameFrame getGameFrame()
  {
    return frame;
  }

  public Menu getMenu()
  {
    return menu;
  }

  private class ToggleMenuAction extends AbstractAction
  {
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent e)
    {
      menu.setVisible(!menu.isVisible());
      frame.setVisible(!frame.isVisible());
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