package engine;

public interface InventoryListener
{
  void onItemAdded(Inventory aInventory, Item aItem);
  void onItemRemoved(Inventory aInventory, Item aItem);
}