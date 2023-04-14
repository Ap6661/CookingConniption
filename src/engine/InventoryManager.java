package engine;

import java.util.ArrayList;

import userInterFace.CursorPane;
import userInterFace.Scene;
import userInterFace.Slot;

public class InventoryManager
{
  // private HashMap<Inventory,Scene> inventories = new
  // HashMap<Inventory,Scene>();
  private ArrayList<Inventory> inventories = new ArrayList<Inventory>();
  private ArrayList<InventoryListener> listeners = new ArrayList<InventoryListener>();
  private CursorPane holder;
  private Item heldItem;
  private InventoryHandler inventoryHandler = new InventoryHandler();

  public void setHolder(CursorPane aCursorPane)
  {
    this.holder = aCursorPane;
  }

  public void addInventory(Inventory aInventory)
  {
    this.addInventory(aInventory, null);
  }

  public void addInventory(Inventory aInventory, Scene aScene)
  {
    inventories.add(aInventory);
    aInventory.setInventoryListener(inventoryHandler);
  }

  public Inventory makeInventory(Slot[] aSlotList)
  {
    Inventory tempInventory = new Inventory(aSlotList.length);
    tempInventory.setSlot(aSlotList);
    addInventory(tempInventory);
    return tempInventory;
  }

  public Inventory findContainingInventory(Slot aSlot)
  {
    for (int i = 0; i < inventories.size(); i++)
    {
      if (inventories.get(i).contains(aSlot))
        return inventories.get(i);
    }
    return null;
  }

  public void remove(Inventory aInventory, Slot aSlot, Item aItem)
  {
    aInventory.setItem(aSlot, heldItem);
    heldItem = aItem;
    holder.setItem(aItem);
  }

  public void addInventoryListener(InventoryListener aInventoryListener)
  {
    listeners.add(aInventoryListener);
  }

  public void removeInventoryListener(InventoryListener aInventoryListener)
  {
    listeners.remove(aInventoryListener);
  }

  public class InventoryHandler implements InventoryListener
  {
    @Override
    public void onItemAdded(Inventory aInventory, Item aItem)
    {
      for (int i = 0; i < listeners.size(); i++)
      {
        listeners.get(i).onItemAdded(aInventory, aItem);
      }
    }

    @Override
    public void onItemRemoved(Inventory aInventory, Item aItem)
    {
      for (int i = 0; i < listeners.size(); i++)
      {
        listeners.get(i).onItemRemoved(aInventory, aItem);

      }
    }
  }
}
