package engine;

public class CraftingMonitor extends InventoryMonitor
{

  private Inventory inventory;
  private int[] key = { 0, 1, 2, 3, 4, 5, 6, 7 };

  public CraftingMonitor(Inventory aInventory)
  {
    super(aInventory);
    inventory = aInventory;
  }

  @Override
  void onItemAdded(Item aItem)
  {
    System.out.println("Item added: " + aItem.getID());

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
    System.out.println("Item removed: " + aItem.getID());
  }
}
