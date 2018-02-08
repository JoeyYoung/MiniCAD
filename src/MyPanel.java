import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel{
    // used for file read-write operations
    public ArrayList<Shape> l_shape = new ArrayList<>();
    public Shape current_shape;
    public MyPanel(){
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        for(Shape s : l_shape)
            s.draw(graphics);
    }
}
