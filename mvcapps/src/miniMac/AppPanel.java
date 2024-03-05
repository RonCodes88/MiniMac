package miniMac;

import tools.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class AppPanel extends JPanel implements ActionListener {
    private ControlPanel controls;
    private MiniMacView view;
    private MiniMac mac;


    public AppPanel() {
        mac = new MiniMac();
        controls = new ControlPanel();
        view = new MiniMacView(mac);
        this.setLayout((new GridLayout(1,2)));
        this.add(controls);
        this.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("MiniMac");
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

    protected JMenuBar createMenuBar(){
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Parse", "Run", "Clear"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        try {
            switch(cmd){
                case "Parse": {
                    String program = ReadFile.readUserFile();
                    mac.changeProgramAsStringArrayList(program);
                    ArrayList<Instruction> result = MiniMacParser.parse(program);
                    mac.putInstructionObjToList(result);
                    mac.notifySubscribers();
                    break;
                }
                case "Run": {
                    mac.setIp(0);
                    mac.execute();
                    mac.notifySubscribers();
                    view.update();
                    break;
                }

                case "Clear": {
                    mac.clear();
                    mac.setHalt(false);
                    mac.notifySubscribers();
                    view.update();
                    break;
                }

                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.mac);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        mac = (MiniMac) is.readObject();
                        view.setMac(mac);
                        is.close();
                    }

                    break;

                }

                case "New": {
                    mac = new MiniMac();
                    view.setMac(mac);
                    break;
                }

                case "Quit": {
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform("Designed By Ronald Li, 2024. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmds = new String[]{
                            "Parse: Prompts user for a program to parse.",
                            "Run: Executes the program and displays relevant information on the right.",
                            "Clear: Resets all memory cells to 0."
                    };
                    Utilities.inform(cmds);
                    break;

                }

                default: {
                    throw new Exception("Unrecognized command: " + cmd);
                }
            }
        }
        catch (Exception err){
            Utilities.error(err);
        }
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.BLUE);
            JPanel p = new JPanel();
            p.setBackground(Color.BLUE);
            p.setLayout(new GridLayout(3,1, 0, 180));
            JButton parse = new JButton("Parse");
            JButton run = new JButton("Run");
            JButton clear = new JButton("Clear");
            parse.addActionListener(AppPanel.this);
            run.addActionListener(AppPanel.this);
            clear.addActionListener(AppPanel.this);
            parse.setPreferredSize(new Dimension(100, 50));
            run.setPreferredSize(new Dimension(100, 50));
            clear.setPreferredSize(new Dimension(100, 50));
            p.add(parse);
            p.add(run);
            p.add(clear);
            add(p);
        }
    }

    public static void main(String[] args) {
        AppPanel view = new AppPanel();
    }
}