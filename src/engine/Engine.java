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
import userInterFace.Scene;

public class Engine
{

  private static GameHandler gameHandler = new GameHandler();
  private static InventoryManager inventoryManager = new InventoryManager();
  private static Window window = new Window();

  public static void main(String[] args)
  {
    window.setVisible(true);

    DisplayPanel display = window.getGameFrame().getDisplayPanel();
    Viewport tempViewport = window.getViewport();
    SceneManager tempSceneManager = new SceneManager(tempViewport, window.getGameFrame().getTabBar(), inventoryManager);
    inventoryManager.setHolder(window.getCursorPane());

    display.getDialogueBox(DisplayPanel.EAST).speak("~");

    Cabinet cabinet = new Cabinet();

    tempSceneManager.addScene(cabinet);
    tempSceneManager.addScene(new Freezer());
    tempSceneManager.addScene(new Cabinet());

    CraftingScene tempCraftingScene = new CraftingScene();
    tempSceneManager.addScene(tempCraftingScene);
    CraftingMonitor tempCraftingMonitor = new CraftingMonitor(tempSceneManager.getInventory(tempCraftingScene),
        tempCraftingScene);

    RecipeManager recipeManager = RecipeManager.getCurrentRecipeManager();
    recipeManager.addListener(new GameHandler());
    {
      int[] r = { 0, 1, 2, 3, 4, 5, 6, 7 };
      Recipe tempRecipe = new Recipe(r, false, 0);
      recipeManager.addRecipe(tempCraftingScene, tempRecipe);
    }
    {
      int[] r = { 0, 1, 2 };
      Recipe tempRecipe = new Recipe(r, true, 1);
      recipeManager.addRecipe(tempCraftingScene, tempRecipe);
    }

    PentagramScene tempPentagramScene = new PentagramScene();
    tempSceneManager.addScene(tempPentagramScene);
    PentagramMonitor tempPentagramMonitor = new PentagramMonitor(tempSceneManager.getInventory(tempPentagramScene),
        tempPentagramScene);

    {
      int[] r = { 0, 1, 2, 3, 4 };
      Recipe tempRecipe = new Recipe(r, false, 666);
      recipeManager.addRecipe(tempPentagramScene, tempRecipe);
    }

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

  private static class GameHandler implements GameListener, RecipeListener
  {
    @Override
    public void slotPressed(Slot aSlot)
    {
      Inventory tempInventory = inventoryManager.findContainingInventory(aSlot);
      Item tempItem = tempInventory.getItem(aSlot);
      inventoryManager.remove(tempInventory, aSlot, tempItem);
    }

    @Override
    public void onCrafted(Scene aScene, Recipe aRecipe)
    {
      window.getGameFrame().getDisplayPanel().getDialogueBox(DisplayPanel.SOUTH).speak("Recipe Number: " + aRecipe.getID());
    }

    @Override
    public void onInvalidRecipe(Scene aScene)
    {
      window.getGameFrame().getDisplayPanel().getDialogueBox(DisplayPanel.SOUTH).clear();
      
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