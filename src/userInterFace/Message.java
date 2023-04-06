package userInterFace;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Message
{
  private String message;
  private int xPosition;
  private int yPosition;
  private int size = 15;
  private Background background;

  public Message(String message)
  {
    this.setMessage(message);
    this.background = new Background(30, 20);
    this.xPosition = 0;
    this.yPosition = 0;
  }

  public void draw(Graphics2D aGraphics2D, int aWidth, int aHeight)
  {
    int xMargin = 20;
    int yMargin = 20;

    Rectangle2D tempRectangle2D = TextRenderer.getBounds(aGraphics2D, message, size);
    Rectangle2D backgroundRectangle2D = (Rectangle2D) tempRectangle2D.clone();

    backgroundRectangle2D.add(tempRectangle2D.getMinX() - xMargin, tempRectangle2D.getMinY() - yMargin);
    backgroundRectangle2D.add(tempRectangle2D.getMaxX() + xMargin, tempRectangle2D.getMaxY() + yMargin);

    // Center the text
    int textX = (int) (aWidth - tempRectangle2D.getMaxX()) / 2;
    int textY = (int) (aHeight - tempRectangle2D.getMaxY()) / 2;
    int textW = (int) tempRectangle2D.getMaxX();
    int textH = (int) tempRectangle2D.getMaxY();

    aGraphics2D.translate(textX + xPosition, textY + yPosition);
    this.background.paint(aGraphics2D, -xMargin, -yMargin, textW + (xMargin * 2), textH + (yMargin * 2));
    TextRenderer.draw(aGraphics2D, message, textW, textH, size);
    aGraphics2D.translate(-textX + xPosition, -textY + yPosition);
  }

  public int getXPosition()
  {
    return xPosition;
  }

  public void setXPosition(int xPosition)
  {
    this.xPosition = xPosition;
  }

  public int getYPosition()
  {
    return yPosition;
  }

  public void setYPosition(int yPosition)
  {
    this.yPosition = yPosition;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

}
