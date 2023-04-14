package engine;

public class PentagramMonitor extends InventoryMonitor
{
  
  
  //  4   0
  //
  // 3     1
  //    2
  
  
  private Inventory inventory;
  private int[] key = { 0, 1, 2, 3, 4 };

  public PentagramMonitor(Inventory aInventory)
  {
    super(aInventory);
    inventory = aInventory;
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
