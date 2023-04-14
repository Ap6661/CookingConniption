package engine;

import userInterFace.Scene;

public class Recipe implements RecipeListener
{
  private int[] ingredients;
  private boolean isShapeless;
  private RecipeListener listener;

  public Recipe(int[] aItemList, boolean isShapeless)
  {
    ingredients = aItemList;
    this.isShapeless = isShapeless;
  }

  public boolean isShapeless()
  {
    return isShapeless;
  }

  public int[] getIngredients()
  {
    return ingredients.clone();
  }

  public void setListener(RecipeListener listener)
  {
    this.listener = listener;
  }

  @Override
  public void onCrafted(Scene aScene, Recipe aRecipe)
  {
    if (listener != null)
      listener.onCrafted(aScene, aRecipe);
  }
}
