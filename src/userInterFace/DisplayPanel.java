package userInterFace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{
  private static final long serialVersionUID = 1L;

  private JPanel viewport;
  private Background background;
  private DialogueBox[] dialogueBoxes = { new DialogueBox(), new DialogueBox(), new DialogueBox(), new DialogueBox() };
  private String[] directions = { "North", "East", "South", "West" };

  public DisplayPanel()
  {
    super(new BorderLayout());
    this.setOpaque( false );

    for (int i = 0; i < 4; i++)
    {
      add(this.dialogueBoxes[i], this.directions[i]);
    }

    this.viewport = new JPanel();
    this.viewport.setBackground(Color.cyan);
    add(this.viewport, BorderLayout.CENTER);
    
    this.background = new Background(100, 20);
    
  }

  @Override
  public void paintComponent(Graphics g)
  {
    background.paint(g, this.getHeight(), this.getWidth());
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