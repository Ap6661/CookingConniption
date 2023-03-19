package engine;

import userInterFace.DisplayPanel;
import userInterFace.Tab;
import userInterFace.Window;

public class Engine
{
  public static void main(String[] args)
  {
   Window window = new Window();
   window.setVisible(true);

   DisplayPanel display = window.getGameFrame().getDisplayPanel();
   display.getDialogueBox(DisplayPanel.SOUTH).speak("This is a test");;
   display.getDialogueBox(DisplayPanel.NORTH).speak("~");;

  Tab locked = window.getGameFrame().getTabBar().addTab("Locked");
  locked.setUnlocked(false);


   window.repaint();
   
   
  }

}
