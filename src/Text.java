import javax.swing.*;
import java.awt.*;

public class Text extends Shape{
    public static final String id = "Text";
    private int x;
    private int y;
    private Color color_text;
    private Graphics2D g2d;
    String text;
    Text(int x, int y){
        this.x = x;
        this.y = y;
        Object jop = JOptionPane.showInputDialog(null,"Type the String：\n","Text");
        color_text = Color.black;
        text = jop.toString();
    }
    @Override
    public void setColor(Color color){
        color_text = color;
    }
    @Override
    public void draw(Graphics g){
        g2d = (Graphics2D) g;
        g2d.setColor(color_text);
        g2d.setFont(new Font("Console", Font.PLAIN, 50));
        g2d.drawString(text, x, y);
    }
    @Override
    public boolean pointIn(int xin, int yin){
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int width = fontMetrics.stringWidth(text);
        int height = fontMetrics.getHeight();
        if(xin >= x && xin <= x+width &&
                yin >= y && yin <= y+height)
            return true;
        else
            return false;
    }
    @Override
    public void relocation(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
