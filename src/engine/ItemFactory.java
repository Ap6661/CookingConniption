package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import userInterFace.TextRenderer;

public class ItemFactory
{

  private static String[] resources = { "res/knife.png", "res/toast.png", "res/lettuce.png", "res/tomato.png",
      "res/sliced_tomato.png", "res/uncooked_bacon.png", "res/bacon.png", "res/blt.png", "res/plate.png", "res/plated_blt.png" };

  public static Item makeItem(int aID)
  {
    if (aID < resources.length)
    {
      return new Item(resources[aID], aID);
    } else
    {
      Color tempColor = aID % 2 == 0 ? Color.red : Color.green;
      Item tempItem = new Item(tempColor, aID);

      BufferedImage tempBufferedImage = (BufferedImage) tempItem.getImage();
      Graphics tempGraphics = tempBufferedImage.getGraphics();
      TextRenderer.draw((Graphics2D) tempGraphics, "" + aID, 64, 64, 30);
      tempGraphics.dispose();

      tempItem.setImage(tempBufferedImage);
      return tempItem;
    }
  }

  public static Item[] makeItemList(int[] aIntList)
  {
    Item[] tempItemList = new Item[aIntList.length];

    for (int i = 0; i < aIntList.length; i++)
      if (aIntList[i] != -1)
        tempItemList[i] = makeItem(aIntList[i]);

    return tempItemList;
  }

  public static Item[] makeItemList(int minID, int maxID)
  {
    if (minID > maxID)
      return null;

    Item[] tempItemList = new Item[maxID - minID + 1];

    for (int i = minID; i <= maxID; i++)
      tempItemList[i - minID] = makeItem(i);

    return tempItemList;
  }
}
