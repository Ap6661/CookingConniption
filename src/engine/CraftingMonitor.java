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
    if (RecipeManager.getCurrentRecipeManager().isValidRecipe(scene, inventory.asArray()))
      System.out.println("Recipe Correct");
  }

  @Override
  void onItemRemoved(Item aItem)
  {
  }
}
