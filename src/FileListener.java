import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class FileListener implements ActionListener{
    private CadPaint cad;
    private MyPanel panel;
    public FileListener(CadPaint c, MyPanel p){
        this.cad = c;
        this.panel = p;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Create")){
            cad.setTitle("new-miniCAD");
            panel.l_shape.clear();
            panel.current_shape = null;
            panel.repaint();
        }else if (e.getActionCommand().equals("Open")){
            FileDialog file = new FileDialog(cad, "Open", FileDialog.LOAD);
            file.setVisible(true);
            cad.setTitle("opened-miniCAD");
            openFile(file.getDirectory() + file.getFile());
            panel.repaint();
        }else if(e.getActionCommand().equals("Save"))
            saveFile();
        else if(e.getActionCommand().equals("Quit"))
            System.exit(0);
    }

    public void openFile(String path){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            for(int i = 0; i < ois.readInt(); i++){
                try{
                    Shape shape = (Shape)ois.readObject();
                    panel.l_shape.add(shape);
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
            ois.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveFile(){
        FileDialog fileDialog = new FileDialog(cad, "Save", FileDialog.SAVE);
        fileDialog.setVisible(true);
        String path = fileDialog.getDirectory() + fileDialog.getFile();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            ArrayList<Shape> list = panel.l_shape;
            oos.writeInt(list.size());
            for(Shape s : list)
                oos.writeObject(s);
            oos.flush();
            oos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        cad.setTitle("saved-miniCAD");
    }
}
