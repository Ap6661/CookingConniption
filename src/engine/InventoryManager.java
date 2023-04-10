package engine;

import java.util.ArrayList;

import userInterFace.Holder;
import userInterFace.Slot;

public class InventoryManager
{
  private ArrayList<Inventory> inventories = new ArrayList<Inventory>();
  private Holder holder = new Holder();
  private Item heldItem;

  public void addInventory(Inventory aInventory)
  {
    inventories.add(aInventory);
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
  }

}
