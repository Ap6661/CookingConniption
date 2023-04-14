package engine;

import userInterFace.Scene;

public class CraftingMonitor extends InventoryMonitor
{

  private Inventory inventory;
  private Scene scene;

  public CraftingMonitor(Inventory aInventory, Scene aScene)
  {
    super(aInventory);
    inventory = aInventory;
    scene = aScene;
  }

  @Override
  void onItemAdded(Item aItem)
  {
    RecipeManager.getCurrentRecipeManager().checkRecipe(scene, inventory.asIDArray());
  }

  @Override
  void onItemRemoved(Item aItem)
  {
    RecipeManager.getCurrentRecipeManager().checkRecipe(scene, inventory.asIDArray());
  }
}
