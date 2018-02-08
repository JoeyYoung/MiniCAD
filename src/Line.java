import java.awt.*;

public class Line extends Shape{
    public static final String id = "Line";
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color line_color;

    public Line(int x1, int y1, int x2, int y2)
    {
        // two points to determine line
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        line_color = Color.black; // default
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(line_color);
        g2d.drawLine(x1, y1, x2, y2);
    }

    // to select a shape
    @Override
    public boolean pointIn(int xin, int yin) {
        if(xin >= x1 && xin <= x2 &&
                yin >= y1 && yin <=y2)
            return true;
        else
            return false;
    }

    // drag to a new position
    @Override
    public void relocation(int dx, int dy) {
        x1+=dx;
        y1+=dy;
        x2+=dx;
        y2+=dy;
    }

    // set color
    @Override
    public void setColor(Color color) {
        line_color = color;
    }
}
