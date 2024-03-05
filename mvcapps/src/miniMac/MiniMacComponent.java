package miniMac;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MiniMacComponent extends JPanel{
    private MiniMac mac;
    private JList<String> list;
    private JScrollPane pane;


    public MiniMacComponent(MiniMac mac, ArrayList<String> program){
        this.mac = mac;
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String item : program) {
            model.addElement(item);
        }

        list = new JList<>(model);
        setLayout(new BorderLayout());
        pane = new JScrollPane(list);
        setPreferredSize(new Dimension(700,400));
        add(pane);
    }

    public JScrollPane getScrollPane(){
        return pane;
    }

    public void paintComponent(Graphics gc) {

        // gc2D uses double coordinates instead of ints
        Graphics2D gc2d = (Graphics2D)gc;

        Color oldColor = gc2d.getColor();

        gc2d.setColor(oldColor);
    }

}
