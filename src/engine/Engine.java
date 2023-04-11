package engine;

import java.awt.Color;

import userInterFace.Cabinet;
import userInterFace.DisplayPanel;
import userInterFace.Drawer;
import userInterFace.Freezer;
import userInterFace.Slot;
import userInterFace.Viewport;
import userInterFace.Window;
import userInterFace.GameListener;

public class Engine
{

  private static GameHandler gameHandler = new GameHandler();
  private static InventoryManager inventoryManager = new InventoryManager();

  public static void main(String[] args)
  {
    Window window = new Window();
    window.setVisible(true);

    DisplayPanel display = window.getGameFrame().getDisplayPanel();
    Viewport tempViewport = window.getViewport();
    SceneManager tempSceneManager = new SceneManager(tempViewport, window.getGameFrame().getTabBar());

    display.getDialogueBox(DisplayPanel.SOUTH).speak("This is a test");
    display.getDialogueBox(DisplayPanel.EAST).speak("~");
    

    tempSceneManager.addScene(new Cabinet());
    tempSceneManager.setActive(0);
    tempSceneManager.addScene(new Freezer());

    tempSceneManager.addScene(new Cabinet());
    tempSceneManager.getTab(2).setUnlocked(false);

    window.repaint();

    Drawer tempDrawer = window.getViewport().getLineEndDrawer();
    Slot[] tempSlotList = tempDrawer.getSlots();
    Item[] tempItemList = new Item[tempSlotList.length];

    for (int i = 0; i < tempSlotList.length; i++)
    {
      tempItemList[i] = new Item(Color.green);
    }

    for (int i= 0; i < tempSlotList.length; i += 2) 
      tempItemList[i] = new Item(Color.red);
    
    inventoryManager.setHolder(window.getCursorPane());
    
    inventoryManager.makeInventory(tempSlotList).setItem(tempItemList);
    inventoryManager.makeInventory(tempViewport.getLineStartDrawer().getSlots());

    window.setGameListener(gameHandler);
  }

  private static class GameHandler implements GameListener
  {
    @Override
    public void slotPressed(Slot aSlot)
    {
      Inventory tempInventory = inventoryManager.findContainingInventory(aSlot);
      Item tempItem = tempInventory.getItem(aSlot);
      inventoryManager.remove(tempInventory, aSlot, tempItem);
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