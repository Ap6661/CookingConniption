package userInterFace;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DialogueBox extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Message message;

  public DialogueBox()
  {
    super();
    this.setPreferredSize(new Dimension(100, 100));
  }
  
  public void speak(String message)
  {
    speak(new Message(message));
  }

  public void speak(Message aMessage)
  {
    this.message = aMessage;
  }

  @Override
  public void paintComponent(Graphics aGraphics)
  {
    Graphics2D aGraphics2D = (Graphics2D) aGraphics;
    if (message != null)
    {
      message.draw(aGraphics2D, getWidth(), getHeight());
    }
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