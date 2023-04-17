package Tests;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

import engine.Engine;
import engine.Inventory;
import engine.Item;
import userInterFace.Slot;

public class DebugItem
{
  public static void main(String[] args)
  {
    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener()
    {
      @Override
      public void eventDispatched(AWTEvent event)
      {
        MouseEvent tempMouseEvent = (MouseEvent) event;
        boolean isPressed = tempMouseEvent.getID() == MouseEvent.MOUSE_PRESSED;

        if (isPressed)
        {
          if (tempMouseEvent.getSource() instanceof Slot)
          {
            Slot tempSlot = (Slot) tempMouseEvent.getSource();
            Inventory tempInventory = Engine.getInventoryManager().findContainingInventory(tempSlot);
            Item tempItem = tempInventory.getItem(tempSlot);
            if (tempItem != null)
              System.out.println(tempItem.getID());
          }
        }
      }
    }, AWTEvent.MOUSE_EVENT_MASK);

    Engine.main(args);
  }
}