package engine;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import userInterFace.Scene;
import userInterFace.Tab;
import userInterFace.TabBar;
import userInterFace.Viewport;

public class SceneManager
{
  private ArrayList<Scene> sceneList = new ArrayList<Scene>();
  private TabBar tabbar;
  private Scene activeScene;
  private Viewport viewport;

  public SceneManager(Viewport aViewport, TabBar aTabBar)
  {
    tabbar = aTabBar;
    viewport = aViewport;
  }

  public void addScene(Scene aScene)
  {
    sceneList.add(aScene);
    Tab tempTab = tabbar.addTab(aScene.getName());
    tempTab.setAction(new SwapScene(aScene, this));
    tempTab.setColor(aScene.getTabColor());
  }

  public void setActive(Scene aScene)
  {
    this.activeScene = aScene;
    viewport.setScene(aScene);
  }

  public Scene getActive()
  {
    return activeScene;
  }

  private class SwapScene extends AbstractAction
  {
    private static final long serialVersionUID = 1L;
    private Scene scene;
    private SceneManager sceneManager;

    public SwapScene(Scene aScene, SceneManager aSceneManager)
    {
      this.scene = aScene;
      this.sceneManager = aSceneManager;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      sceneManager.setActive(scene);
    }

  }

}
