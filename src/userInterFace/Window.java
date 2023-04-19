package userInterFace;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Window extends JFrame
{
  private static final long serialVersionUID = 1L;

  private GameFrame frame = new GameFrame();
  private Menu menu = new Menu();
  private ViewportHandler viewportHandler = new ViewportHandler();
  private CursorPane cursorPane;

  private GameListener gameListener;

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

    cursorPane = new CursorPane(getContentPane());
    setGlassPane(cursorPane);
    cursorPane.setVisible(true);

    menu.addMenuItem("Play", new ToggleMenuAction());
    menu.addMenuItem("Settings");
    menu.addMenuItem("Exit").addActionListener((event) -> System.exit(0));

    Tab button = frame.getTabBar().addTab("Menu");
    button.setAction(new ToggleMenuAction());
    button.setColor(Color.green);

    frame.setViewportListener(viewportHandler);

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

  public Viewport getViewport()
  {
    return frame.getDisplayPanel().getViewport();
  }

  private class ToggleMenuAction extends AbstractAction
  {
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent e)
    {
      menu.setVisible(!menu.isVisible());
      frame.setVisible(!frame.isVisible());
      cursorPane.setVisible(frame.isVisible());
    }
  }

  public void setGameListener(GameListener aGameListener)
  {
    gameListener = aGameListener;
  }

  private class ViewportHandler implements ViewportListener
  {
    @Override
    public void slotPressed(Drawer aDrawer, Slot aSlot)
    {
      if (gameListener != null)
        gameListener.slotPressed(aSlot);
    }

    @Override
    public void slotPressed(Slot aSlot)
    {
      if (gameListener != null)
        gameListener.slotPressed(aSlot);
    }

    @Override
    public void craftPressed(Scene aScene)
    {
      if (gameListener != null)
        gameListener.craftPressed(aScene);
    }
  }

  public CursorPane getCursorPane()
  {
    return this.cursorPane;
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