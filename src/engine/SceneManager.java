package engine;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import userInterFace.Scene;
import userInterFace.Tab;
import userInterFace.TabBar;
import userInterFace.Viewport;

public class SceneManager
{
  private HashMap<Scene, Inventory> inventoryList = new HashMap<Scene, Inventory>();
  private ArrayList<Scene> sceneList = new ArrayList<Scene>();
  private ArrayList<Tab> tabList = new ArrayList<Tab>();
  private TabBar tabbar;
  private Scene activeScene;
  private Viewport viewport;
  private InventoryManager inventoryManager;

  public SceneManager(Viewport aViewport, TabBar aTabBar, InventoryManager aInventoryManager)
  {
    tabbar = aTabBar;
    viewport = aViewport;
    inventoryManager = aInventoryManager;
  }

  public void addScene(Scene aScene)
  {
    sceneList.add(aScene);
    Tab tempTab = tabbar.addTab(aScene.getName());
    tempTab.setAction(new SwapScene(aScene));
    tempTab.setColor(aScene.getTabColor());
    tabList.add(tempTab);
    if (aScene.getSlots() != null)
      inventoryList.put(aScene, inventoryManager.makeInventory(aScene.getSlots()));
  }

  public void setActive(Scene aScene)
  {
    this.activeScene = aScene;
    viewport.setScene(aScene);
  }

  public void setActive(int index)
  {
    this.activeScene = sceneList.get(index);
    viewport.setScene(sceneList.get(index));
  }

  public Scene getActive()
  {
    return activeScene;
  }

  public Tab getTab(int index)
  {
    return tabList.get(index);
  }

  public Tab getTab(Scene aScene)
  {
    return getTab(sceneList.indexOf(aScene));

  }

  public Inventory getInventory(Scene aScene)
  {
    return (Inventory) inventoryList.get(aScene);
  }

  private class SwapScene extends AbstractAction
  {
    private static final long serialVersionUID = 1L;
    private Scene scene;

    public SwapScene(Scene aScene)
    {
      this.scene = aScene;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      setActive(scene);
    }

  }

}
