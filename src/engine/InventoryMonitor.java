package engine;

public abstract class InventoryMonitor implements InventoryListener
{
  private Inventory monitoredInventory;

  public InventoryMonitor(Inventory aInventory)
  {
    monitoredInventory = aInventory;
  }

  abstract void onItemAdded(Item aItem);

  abstract void onItemRemoved(Item aItem);

  @Override
  public void onItemAdded(Inventory aInventory, Item aItem)
  {
    if (aInventory == monitoredInventory)
      onItemAdded(aItem);
  }

  @Override
  public void onItemRemoved(Inventory aInventory, Item aItem)
  {
    if (aInventory == monitoredInventory)
      onItemRemoved(aItem);
  }
}
