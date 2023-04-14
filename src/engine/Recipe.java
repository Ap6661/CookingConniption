package engine;

import java.util.Arrays;

public class Recipe
{
  private int[] ingredients;
  private boolean isShapeless;
  private int ID;

  public Recipe(int[] aItemList, boolean isShapeless, int ID)
  {
    this.isShapeless = isShapeless;
    ingredients = aItemList;

    if (isShapeless)
      Arrays.sort(ingredients);

    this.ID = ID;
  }

  public boolean isShapeless()
  {
    return isShapeless;
  }

  public int[] getIngredients()
  {
    return ingredients.clone();
  }

  public int getID()
  {
    return ID;
  }
}
