package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import userInterFace.Scene;

public class RecipeManager
{
  private static RecipeManager recipeManager;

  private HashMap<Scene, ArrayList<Recipe>> recipes = new HashMap<Scene, ArrayList<Recipe>>();
  private ArrayList<RecipeListener> listeners = new ArrayList<RecipeListener>();

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

  public void checkRecipe(Scene scene, int[] aIDArray)
  {
    Recipe tempRecipe = getValidRecipe(scene, aIDArray);
    if (tempRecipe != null)
      this.onCrafted(scene, tempRecipe);
    else
      this.onInvalidRecipe(scene);
  }

  private void onInvalidRecipe(Scene aScene)
  {
    for (RecipeListener tempRecipeListener : listeners)
    {
      tempRecipeListener.onInvalidRecipe(aScene);
    }
  }

  public void addListener(RecipeListener ARecipeListener)
  {
    this.listeners.add(ARecipeListener);
  }

  public void onCrafted(Scene aScene, Recipe aRecipe)
  {
    for (RecipeListener tempRecipeListener : listeners)
    {
      tempRecipeListener.onCrafted(aScene, aRecipe);
    }
  }

  public Recipe getValidRecipe(Scene aScene, int[] aIDList)
  {
    // Get the recipes for aScene
    ArrayList<Recipe> tempRecipeList = recipes.get(aScene);

    // Exit if no recipes
    if (tempRecipeList == null)
      return null;

    Recipe tempMatchingRecipe = null;

    for (Recipe tempRecipe : tempRecipeList)
    {

      // Padding the Recipe to fit the inventory
      // -1 are empty slots
      int[] tempIDListPadded = Arrays.copyOf(tempRecipe.getIngredients(), aIDList.length);
      Arrays.fill(tempIDListPadded, tempRecipe.getIngredients().length, aIDList.length, -1);

      // Clone to not change the list in place
      int[] tempIDList = aIDList;
      if (tempRecipe.isShapeless())
      {
        tempIDList = tempIDList.clone();
        Arrays.sort(tempIDList);
        Arrays.sort(tempIDListPadded);
      }

      if (Arrays.equals(tempIDList, tempIDListPadded))
      {
        tempMatchingRecipe = tempRecipe;
        break;
      }
    }
    return tempMatchingRecipe;
  }

}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//