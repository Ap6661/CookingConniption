package engine;

import java.lang.reflect.InvocationTargetException;

import userInterFace.Cabinet;
import userInterFace.CraftingScene;
import userInterFace.DisplayPanel;
import userInterFace.Drawer;
import userInterFace.Freezer;
import userInterFace.Slot;
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

    Viewport tempViewport = window.getViewport();
    SceneManager tempSceneManager = new SceneManager(tempViewport, window.getGameFrame().getTabBar(), inventoryManager);
    inventoryManager.setHolder(window.getCursorPane());

    addScenes(tempSceneManager);
    Scene tempCraftingScene = addScene(tempSceneManager, CraftingScene.class, CraftingMonitor.class);
    Scene tempPentagramScene = addScene(tempSceneManager, PentagramScene.class, PentagramMonitor.class);

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

    {
      int[] r = { 0, 1, 2, 3, 4 };
      Recipe tempRecipe = new Recipe(r, false, 666);
      recipeManager.addRecipe(tempPentagramScene, tempRecipe);
    }

    tempSceneManager.setActive(0);

    Drawer tempDrawer = window.getViewport().getLineEndDrawer();
    Slot[] tempSlotList = tempDrawer.getSlots();
    Item[] tempItemList = ItemFactory.makeItemList(0, tempSlotList.length);

    inventoryManager.makeInventory(tempSlotList).setItem(tempItemList);
    inventoryManager.makeInventory(tempViewport.getLineStartDrawer().getSlots());

    window.setGameListener(gameHandler);
  }

  private static void addScenes(SceneManager aSceneManager)
  {
    aSceneManager.addScene(new Cabinet());
    aSceneManager.addScene(new Freezer());
  }

  private static <S extends Scene, M extends InventoryMonitor> S addScene(SceneManager aSceneManager, Class<S> aScene,
      Class<M> aMonitor)
  {
    S tempScene = null;

    try
    {
      tempScene = aScene.getConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e)
    {
      e.printStackTrace();
      return null;
    }

    aSceneManager.addScene(tempScene);
    @SuppressWarnings("rawtypes")
    Class[] paramaters = { Inventory.class, Scene.class };

    M tempMonitor;
    try
    {
      tempMonitor = aMonitor.getConstructor(paramaters).newInstance(aSceneManager.getInventory(tempScene), tempScene);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e)
    {
      e.printStackTrace();
      return null;
    }

    inventoryManager.addInventoryListener(tempMonitor);
    return tempScene;
  }

  public static InventoryManager getInventoryManager()
  {
    return inventoryManager;
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
      window.getGameFrame().getDisplayPanel().getDialogueBox(DisplayPanel.SOUTH)
          .speak("Recipe Number: " + aRecipe.getID());
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