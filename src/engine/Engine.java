package engine;

import userInterFace.Cabinet;
import userInterFace.DisplayPanel;
import userInterFace.Freezer;
import userInterFace.Viewport;
import userInterFace.Window;

public class Engine
{
  public static void main(String[] args)
  {
    Window window = new Window();
    window.setVisible(true);

    DisplayPanel display = window.getGameFrame().getDisplayPanel();
    Viewport tempViewport = window.getGameFrame().getDisplayPanel().getViewport();
    SceneManager tempSceneManager = new SceneManager(tempViewport, window.getGameFrame().getTabBar());
    

    display.getDialogueBox(DisplayPanel.SOUTH).speak("This is a test");
    display.getDialogueBox(DisplayPanel.EAST).speak("~");
    tempSceneManager.addScene(new Cabinet());
    tempSceneManager.addScene(new Freezer());
    tempSceneManager.addScene(new Cabinet());
    
    tempSceneManager.getTab(2).setUnlocked(false);
    
    
    window.repaint();

  }

}
