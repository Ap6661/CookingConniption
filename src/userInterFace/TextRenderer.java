package userInterFace;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class TextRenderer
{
  private static final Font FONT = new Font("Hack NF", Font.BOLD, 15);

  public static void draw(Graphics2D aGraphics2D, String text, int width, int height, int size)
  {
    Color oldPaint = aGraphics2D.getColor();
    aGraphics2D.setColor(Color.black);
    
    Font tempFont = FONT.deriveFont((float) size);
    aGraphics2D.setFont(tempFont);

    // Calculate "Center"
    FontMetrics tempFontMeterics = aGraphics2D.getFontMetrics();
    int textW = tempFontMeterics.stringWidth(text);
    int textH = tempFontMeterics.getAscent();
    int textX = (width - textW) / 2;
    int textY = (height + textH) / 2;

    aGraphics2D.drawString(text, textX, textY);
    aGraphics2D.setColor(oldPaint);
  }

  public static void draw(Graphics2D aGraphics2D, String text, int width, int height)
  {
    draw(aGraphics2D, text, width, height, 15);
  }

}
