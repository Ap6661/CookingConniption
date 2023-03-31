package userInterFace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{
  private static final long serialVersionUID = 1L;

  private Viewport viewport = new Viewport();
  private Background background;
  private DialogueBox[] dialogueBoxes = { new DialogueBox(), new DialogueBox(), new DialogueBox(), new DialogueBox() };
  private String[] directions = { "North", "East", "South", "West" };

  public static final int NORTH = 0;
  public static final int EAST = 1;
  public static final int SOUTH = 2;
  public static final int WEST = 3;

  public DisplayPanel()
  {
    super(new BorderLayout());
    setOpaque(false);

    for (int i = 0; i < 4; i++)
    {
      add(this.dialogueBoxes[i], this.directions[i]);
    }

    viewport.setBackground(Color.cyan);
    add(this.viewport, BorderLayout.CENTER);

    background = new Background(100, 20);

  }

  public DialogueBox getDialogueBox(int direction)
  {

    return this.dialogueBoxes[direction];
  }

  public Viewport getViewport()
  {
    return this.viewport;
  }

  @Override
  public void paintComponent(Graphics g)
  {
    background.paint(g, 0, 0, this.getHeight(), this.getWidth());
    super.paintComponent(g);
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
//
//
//