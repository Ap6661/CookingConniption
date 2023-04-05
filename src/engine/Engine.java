package engine;

import userInterFace.Cabinet;
import userInterFace.DisplayPanel;
import userInterFace.Freezer;
import userInterFace.Tab;
import userInterFace.Viewport;
import userInterFace.Window;

public class Engine
{
  public static void main(String[] args)
  {
    Window window = new Window();
    window.setVisible(true);

    DisplayPanel display = window.getGameFrame().getDisplayPanel();
    display.getDialogueBox(DisplayPanel.SOUTH).speak("This is a test");
    display.getDialogueBox(DisplayPanel.NORTH).speak("~");

    Tab locked = window.getGameFrame().getTabBar().addTab("Locked");
    locked.setUnlocked(false);
    
    
    Viewport tempViewport = window.getGameFrame().getDisplayPanel().getViewport();

    SceneManager tempSceneManager = new SceneManager(tempViewport, window.getGameFrame().getTabBar());
    
    
    tempSceneManager.addScene(new Cabinet());
    tempSceneManager.addScene(new Freezer());
    tempSceneManager.addScene(new Cabinet());
    
    
    window.repaint();

  }

}
