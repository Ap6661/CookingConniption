package engine;

import userInterFace.Scene;

public interface RecipeListener
{
  public void onCrafted(Scene aScene, Recipe aRecipe);
  public void onInvalidRecipe(Scene aScene);
}
