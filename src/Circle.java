import java.awt.*;

public class Circle extends Shape{
    public static final String id = "Circle";
    private int x;
    private int y;
    private int width;
    private int height;
    private Color circle_color = Color.BLACK;

    public Circle(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(circle_color);
        g2d.drawOval(x, y, width, height);
    }

    @Override
    public boolean pointIn(int xin, int yin) {
        //
        double a2 = Math.pow(width/2, 2);
        double b2 = Math.pow(height/2, 2);
        double test = Math.pow(xin, 2)/a2 + Math.pow(yin, 2)/b2;
        if(test <= 1)
            return true;
        else
            return false;
    }

    @Override
    public void relocation(int dx, int dy) {
        x+=dx;
        y+=dy;
    }

    @Override
    public void setColor(Color color) {
        circle_color = color;
    }

}

