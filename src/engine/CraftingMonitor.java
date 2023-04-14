package engine;

import java.util.ArrayList;

public class CraftingMonitor extends InventoryMonitor
{

  private Inventory inventory;
  private ArrayList<int[]> recipies = new ArrayList<int[]>();
  private int[] key = { 0, 1, 2, 3, 4, 5, 6, 7 };
  private int maxSize;
  private boolean shapeless = true;

  public CraftingMonitor(Inventory aInventory)
  {
    super(aInventory);
    inventory = aInventory;
    maxSize = aInventory.getLength();
  }

  public void addRecipe(int[] aIntList)
  {
    if (aIntList.length <= maxSize)
    {

      if (shapeless)
      {
      }
      else
        recipies.add(aIntList);
    }
  }

  public boolean isValidRecipe(int[] aIntList)
  {

    return false;
  }

  @Override
  void onItemAdded(Item aItem)
  {

    boolean isIncorrect = false;

    for (int i = 0; i < key.length; i++)
    {
      if (inventory.getItem(i) == null)
        isIncorrect = true;
      else if (inventory.getItem(i).getID() != key[i])
        isIncorrect = true;
    }

    if (!isIncorrect)
    {
      System.out.println("Correct Recipe!");
    }

  }

  @Override
  void onItemRemoved(Item aItem)
  {
  }
}
