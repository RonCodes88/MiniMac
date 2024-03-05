package miniMac;

import tools.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MiniMacView extends JPanel implements Subscriber {
       private MiniMac mac;

    public MiniMacView(MiniMac mac){
        this.mac = mac;
        mac.subscribe(this);
        setLayout(new GridLayout(2,1));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setBackground(Color.LIGHT_GRAY);

        update();

    }

    public void update(){
        removeAll();

        ArrayList<String> topList = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            topList.add("memory[" + i + "] = " + mac.getMemoryArray()[i]);
        }

        ArrayList<String> bottomList = new ArrayList<>(mac.getProgramAsStrings());

        MiniMacComponent macComponent1 = new MiniMacComponent(mac, topList);
        MiniMacComponent macComponent2 = new MiniMacComponent(mac, bottomList);

        add(macComponent1.getScrollPane());
        add(macComponent2.getScrollPane());

        revalidate();
        repaint();

    }

    public void setMac(MiniMac newMac){
        mac.unsubscribe(this);
        mac = newMac;
        mac.subscribe(this);
        mac.notifySubscribers();
        repaint();

    }

}
