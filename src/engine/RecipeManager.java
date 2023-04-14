package engine;

import java.util.ArrayList;
import java.util.HashMap;

import userInterFace.Scene;

public class RecipeManager
{
  private static RecipeManager recipeManager;

  private HashMap<Scene, ArrayList<Recipe>> recipes = new HashMap<Scene, ArrayList<Recipe>>();

  private RecipeManager()
  {

  }

  public static RecipeManager getCurrentRecipeManager()
  {
    if (recipeManager == null)
      recipeManager = new RecipeManager();
    return recipeManager;
  }

  public void addRecipe(Scene aScene, Recipe aRecipe)
  {
    ArrayList<Recipe> tempRecipeList = recipes.get(aScene);
    if (tempRecipeList == null)
    {
      tempRecipeList = new ArrayList<Recipe>();
      recipes.put(aScene, tempRecipeList);
    }
    tempRecipeList.add(aRecipe);
  }

  public boolean isValidRecipe(Scene aScene, Item[] aItemList)
  {
    ArrayList<Recipe> tempRecipeList = recipes.get(aScene);

    if (tempRecipeList == null)
      return false;

    ArrayList<Recipe> matches = new ArrayList<Recipe>();

    for (int r = 0; r < tempRecipeList.size(); r++)
    {
      boolean isIncorrect = false;
      int[] key = tempRecipeList.get(r).getIngredients();
      if (aItemList.length >= key.length)
      {
        for (int i = 0; i < key.length; i++)
        {
          if (aItemList[i] == null)
            isIncorrect = true;
          else if (aItemList[i].getID() != key[i])
            isIncorrect = true;
        }
        if (!isIncorrect)
          matches.add(tempRecipeList.get(r));
      }
    }

    return matches.size() > 0;
  }

}
