package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import userInterFace.Cabinet;
import userInterFace.CraftingScene;
import userInterFace.Freezer;
import userInterFace.Slot;
import userInterFace.Viewport;
import userInterFace.Window;
import userInterFace.GameListener;
import userInterFace.PentagramScene;
import userInterFace.Scene;
import userInterFace.SkilletScene;

public class Engine
{

  private static GameHandler gameHandler = new GameHandler();
  private static InventoryManager inventoryManager = new InventoryManager();
  private static Window window = new Window();
  private static SceneManager sceneManager;

  public static void main(String[] args)
  {
    window.setVisible(true);

    Viewport tempViewport = window.getViewport();
    sceneManager = new SceneManager(tempViewport, window.getGameFrame().getTabBar(), inventoryManager);
    inventoryManager.setHolder(window.getCursorPane());

    addScenes(sceneManager);
    Scene tempCraftingScene = addScene(sceneManager, CraftingScene.class, CraftingMonitor.class);
    Scene tempSkilletScene = addScene(sceneManager, SkilletScene.class, CraftingMonitor.class);
    Scene tempPentagramScene = addScene(sceneManager, PentagramScene.class, PentagramMonitor.class);

    RecipeManager recipeManager = RecipeManager.getCurrentRecipeManager();
    recipeManager.addListener(new GameHandler());
    {
      int[] r = { 0, 3 };
      Recipe tempRecipe = new Recipe(r, true, 1);
      recipeManager.addRecipe(tempCraftingScene, tempRecipe);
    }
    {
      int[] r = { 1, 1, 2, 4, 6 };
      Recipe tempRecipe = new Recipe(r, true, 666);
      recipeManager.addRecipe(tempPentagramScene, tempRecipe);
    }
    {
      int[] r = { 5 };
      Recipe tempRecipe = new Recipe(r, false, 2);
      recipeManager.addRecipe(tempSkilletScene, tempRecipe);
    }
    {
      int[] r = new int[9];
      Arrays.fill(r, -1);
      r[4] = 7;
      r[7] = 8;
      Recipe tempRecipe = new Recipe(r, false, 4);
      recipeManager.addRecipe(tempCraftingScene, tempRecipe);
    }

    sceneManager.setActive(0);

    inventoryManager.makeInventory(tempViewport.getLineEndDrawer().getSlots());
    inventoryManager.makeInventory(tempViewport.getLineStartDrawer().getSlots()).setItem(0, ItemFactory.makeItem(8));

    window.setGameListener(gameHandler);

    sceneManager.getTab(1).setUnlocked(false);
    sceneManager.getTab(3).setUnlocked(false);
    sceneManager.getTab(4).setUnlocked(false);
  }

  private static void addScenes(SceneManager aSceneManager)
  {
    {
      Scene tempScene = new Cabinet();
      aSceneManager.addScene(tempScene);
      Inventory tempInventory = aSceneManager.getInventory(tempScene);
      int[] tempIDList = { 1, 1, 0, 3 };
      tempInventory.setItem(ItemFactory.makeItemList(tempIDList));
    }
    {
      Scene tempScene = new Freezer();
      aSceneManager.addScene(tempScene);
      Inventory tempInventory = aSceneManager.getInventory(tempScene);
      int[] tempIDList = { 2, 5, -1, -1 };
      tempInventory.setItem(ItemFactory.makeItemList(tempIDList));
    }
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
      window.getGameFrame().getDisplayPanel().getViewport().getCraftButton().setVisible(true);
    }

    @Override
    public void onInvalidRecipe(Scene aScene)
    {
      window.getGameFrame().getDisplayPanel().getViewport().getCraftButton().setVisible(false);
    }

    @Override
    public void craftPressed(Scene aScene)
    {
      Inventory tempInventory = sceneManager.getInventory(aScene);
      Recipe recipe = RecipeManager.getCurrentRecipeManager().getValidRecipe(aScene, tempInventory.asIDArray());

      if (recipe != null)
      {

        // Sliced Tomato
        if (recipe.getID() == 1)
        {
          int index1 = tempInventory.indexOfItem(0);
          int index2 = tempInventory.indexOfItem(3);

          tempInventory.clearItems();
          tempInventory.setItem(index1, ItemFactory.makeItem(0));
          tempInventory.setItem(index2, ItemFactory.makeItem(4));

          sceneManager.getTab(1).setUnlocked(true);
          ;
          sceneManager.getTab(3).setUnlocked(true);
          ;
        }

        // Cooking Bacon
        if (recipe.getID() == 2)
        {
          tempInventory.setItem(0, ItemFactory.makeItem(6));
          sceneManager.getTab(4).setUnlocked(true);
          ;
        }

        // BLT making Test
        if (recipe.getID() == 666)
        {
          tempInventory.clearItems();
          tempInventory.setItem(0, ItemFactory.makeItem(7));
        }

        // Plating the BLT
        if (recipe.getID() == 4)
        {
          tempInventory.clearItems();
          tempInventory.setItem(7, ItemFactory.makeItem(9));
        }
      }
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