package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import userInterFace.Cabinet;
import userInterFace.CraftingScene;
import userInterFace.DisplayPanel;
import userInterFace.Drawer;
import userInterFace.Freezer;
import userInterFace.Slot;
import userInterFace.TextRenderer;
import userInterFace.Viewport;
import userInterFace.Window;
import userInterFace.GameListener;
import userInterFace.PentagramScene;

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
    SceneManager tempSceneManager = new SceneManager(tempViewport, window.getGameFrame().getTabBar(), inventoryManager);
    inventoryManager.setHolder(window.getCursorPane());

    display.getDialogueBox(DisplayPanel.SOUTH).speak("This is a test");
    display.getDialogueBox(DisplayPanel.EAST).speak("~");

    Cabinet cabinet = new Cabinet();

    tempSceneManager.addScene(cabinet);
    tempSceneManager.addScene(new Freezer());
    tempSceneManager.addScene(new Cabinet());



    CraftingMonitor tempCraftingMonitor = new CraftingMonitor(tempSceneManager.addScene(new CraftingScene()));
    PentagramMonitor tempPentagramMonitor = new PentagramMonitor(tempSceneManager.addScene(new PentagramScene()));
    
    
    inventoryManager.addInventoryListener(tempCraftingMonitor);
    inventoryManager.addInventoryListener(tempPentagramMonitor);


    tempSceneManager.setActive(0);
    tempSceneManager.getTab(2).setUnlocked(false);

    Drawer tempDrawer = window.getViewport().getLineEndDrawer();
    Slot[] tempSlotList = tempDrawer.getSlots();
    Item[] tempItemList = new Item[tempSlotList.length];

    for (int i = 0; i < tempSlotList.length; i++)
    {
      tempItemList[i] = new Item(Color.green, i);
    }

    for (int i = 0; i < tempSlotList.length; i += 2)
      tempItemList[i] = new Item(Color.red, i);

    for (int i = 0; i < tempSlotList.length; i++)
    {
      BufferedImage tempBufferedImage = (BufferedImage) tempItemList[i].getImage();
      Graphics tempGraphics = tempBufferedImage.getGraphics();
      TextRenderer.draw((Graphics2D) tempGraphics, "" + i, 64, 64, 30);
      tempGraphics.dispose();
      tempItemList[i].setImage(tempBufferedImage);
    }

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