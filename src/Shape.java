import java.awt.*;

public abstract class Shape {
    public abstract void draw(Graphics g);
    public abstract boolean pointIn(int x,int y);
    public abstract void relocation(int dx,int dy);
    public abstract void setColor(Color color);
}