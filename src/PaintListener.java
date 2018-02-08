import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PaintListener extends MouseAdapter implements ActionListener, MouseMotionListener{
    private JPanel panel;
    private ArrayList<Shape> shapes;
    private boolean drawing = false;
    private Shape nowshape = null;
    private String shape2p = null;
    private int lastx, lasty;
    private int xpress, ypress, xdrag, ydrag;

    public PaintListener(JPanel p){
        this.panel = p;
        this.shapes = ((MyPanel)panel).l_shape;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        // deal with the color
        HashMap<String, Color> color_map = new HashMap<>();
        color_map.put("Black", Color.BLACK);
        color_map.put("Red", Color.RED);
        color_map.put("Green", Color.GREEN);
        color_map.put("Yellow", Color.YELLOW);
        color_map.put("Blue", Color.BLUE);

        HashSet shape_set = new HashSet();
        shape_set.add("Line");
        shape_set.add("Circle");
        shape_set.add("Text");
        shape_set.add("Rectangle");

        if(color_map.containsKey(ae.getActionCommand())){
            Color color = color_map.get(ae.getActionCommand());
            nowshape.setColor(color);
        }else if(shape_set.contains(ae.getActionCommand())){
            shape2p = ae.getActionCommand();
            drawing = true;
        } else {
            System.out.println("ERROR in ActionCommand");
        }
    }

    @Override
    public void mousePressed(MouseEvent me){
        xpress = me.getX();
        ypress = me.getY();
        if(drawing == false){
            for(Shape s : shapes){
                if(s.pointIn(xpress, ypress)){
                    nowshape = s;
                    lastx = xpress;
                    lasty = ypress;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent me){
        xdrag = me.getX();
        ydrag = me.getY();
        if(drawing == false){
            if (nowshape != null){
                int dx = xdrag - lastx;
                int dy = ydrag - lasty;
                nowshape.relocation(dx, dy);
                lastx = xdrag;
                lasty = ydrag;
                panel.repaint();
            }
        }else{  // drawing
            if(shape2p.equals("Line"))
                nowshape = new Line(xpress, ypress, xdrag, ydrag);
            else if (shape2p.equals("Circle")){
                int w = xdrag - xpress;
                int h = ydrag - ypress;
                nowshape = new Circle(xpress, ypress, w, h);
            }else if(shape2p.equals("Rectangle"))
                nowshape = new Rectangle(xpress, ypress, xdrag, ydrag);
            else if(shape2p.equals("Text"))
                nowshape = new Text(xpress, ypress);
            lastx = xdrag;
            lasty = ydrag;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me){
        if(drawing){
            if(nowshape != null){
                ((MyPanel)panel).l_shape.add(nowshape);
                ((MyPanel)panel).current_shape = nowshape;
                panel.repaint();
            }
            drawing = false;
        }
        panel.repaint();
        lastx = me.getX();
        lasty = me.getY();
    }

}
