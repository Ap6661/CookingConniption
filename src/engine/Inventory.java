package engine;

import java.util.Arrays;

import userInterFace.Slot;

public class Inventory
{
  private Slot[] slots;
  private Item[] items;
  private int length;

  public Inventory(int length)
  {
    items = new Item[length];
    slots = new Slot[length];
    this.length = length;
  }

  public boolean contains(Slot aSlot)
  {
    return Arrays.asList(slots).contains(aSlot);
  }

  public Item getItem(int index)
  {
    return items[index];
  }

  public Item getItem(Slot aSlot)
  {
    return getItem(Arrays.asList(slots).indexOf(aSlot));
  }

  public int indexOfItem(Item aItem)
  {
    return Arrays.asList(items).indexOf(aItem);
  }

  public int indexOfSlot(Slot aSlot)
  {
    return Arrays.asList(slots).indexOf(aSlot);
  }

  public void setSlot(int index, Slot aSlot)
  {
    slots[index] = aSlot;
    updateOutput();
  }

  public void setSlot(Slot[] aSlotList)
  {
    if (aSlotList.length == slots.length)
      for (int i = 0; i < aSlotList.length; i++)
        setSlot(i, aSlotList[i]);
  }

  public void setItem(int index, Item aItem)
  {
    items[index] = aItem;
    slots[index].setItem(aItem);
    updateOutput();
  }
  
  public void setItem(Slot aSlot, Item aItem) {
    items[indexOfSlot(aSlot)] = aItem;
    aSlot.setItem(aItem);
    updateOutput();
  }

  public void setItem(Item[] aItemList)
  {
    if (aItemList.length == items.length)
      for (int i = 0; i < aItemList.length; i++)
        setItem(i, aItemList[i]);
  }

  public void updateOutput()
  {
    for (int i = 0; i < length; i++)
    {
      if (slots[i] != null && items[i] != null)
      {
        slots[i].setItem(items[i]);
        slots[i].invalidate();
      }
    }
  }

}