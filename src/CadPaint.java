import javax.swing.*;
import java.awt.*;

public class CadPaint extends JFrame{

    // need to overload
    private FileListener fileListener;
    private PaintListener paintListener;
    private MyPanel myPanel;
    private Font ft = new Font("Console", Font.BOLD,20);

    public CadPaint(String title){

        Container container = this.getContentPane();
        myPanel = new MyPanel();
        paintListener = new PaintListener(myPanel);
        myPanel.addMouseListener(paintListener);
        myPanel.addMouseMotionListener(paintListener);

        container.setLayout(new BorderLayout());
        container.add(myPanel, BorderLayout.CENTER);
        // JFrame is a framework, not container


        //set menu bar
        JMenuBar menuBar = new JMenuBar();
        fileListener = new FileListener(this, myPanel);
        String[] file_operation = {"Create", "Open", "Save", "Quit"};
        JMenu file_menu = CreateMenu(file_operation, "File"); // file operation
        file_menu.setFont(ft);
        menuBar.add(file_menu);

        String[] draw_operation = {"Line", "Circle", "Rectangle", "Text"};
        JMenu draw_menu = CreateMenu(draw_operation, "Draw"); // shapes
        draw_menu.setFont(ft);
        menuBar.add(draw_menu);

        String[] color_selection = {"Black", "Red", "Blue", "Green", "Yellow"};
        JMenu color_menu = CreateMenu(color_selection, "Color");    // change colors
        color_menu.setFont(ft);
        menuBar.add(color_menu);

        menuBar.setFont(ft);
        this.setJMenuBar(menuBar);
        this.setTitle(title);
        this.setVisible(true);
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocation(300,150);

    }

    private JMenu CreateMenu(String[] items, String title){
        JMenu menu = new JMenu();
        menu.setText(title);
        for(int i = 0; i<items.length; i++){
            JMenuItem menuItem = new JMenuItem();
            menuItem.setText(items[i]);
            menuItem.setFont(ft);
            if(title.equals("File"))
                menuItem.addActionListener(fileListener);
            else if(title.equals("Draw"))
                menuItem.addActionListener(paintListener);
            else if(title.equals("Color"))
                menuItem.addActionListener(paintListener);
            menu.add(menuItem);
            if (i == items.length-1)
                break;
            else
                menu.addSeparator();
        }
        return menu;
    }
}
