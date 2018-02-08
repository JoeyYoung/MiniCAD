import java.awt.*;

public class Rectangle extends Shape{
    public static final String id = "Rectangle";
    private int x1, y1, x2, y2;
    private Color color_rec;
    Rectangle(int x1, int y1, int x2, int y2){
        color_rec = Color.black;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(color_rec);
        int width = x2 - x1;
        int height = y2 - y1;
        g2d.drawRect(x1, y1, width, height);
    }

    @Override
    public void relocation(int dx, int dy){
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
    }
    @Override
    public void setColor(Color color){
        this.color_rec = color;
    }

    @Override
    public boolean pointIn(int xin, int yin){
        if (xin >= x1 && xin <= x2 &&
                yin >= y1 && yin <= y2)
            return true;
        else
            return false;
    }

}
