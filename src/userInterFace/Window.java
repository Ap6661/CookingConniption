package userInterFace;

import javax.swing.*;

public class Window extends JFrame
{
  private static final long serialVersionUID = 1L;

  private GameFrame frame;

  public Window()
  {
    super("Cooking Conniption!");
    setSize(740, 700);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
    setUndecorated(true);
    
    this.frame = new GameFrame();

    this.getContentPane().add(this.frame);
    this.invalidate();
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