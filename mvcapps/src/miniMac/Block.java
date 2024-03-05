package miniMac;

import java.util.ArrayList;
import java.util.List;

public class Block implements Instruction {
    ArrayList<Instruction> body;
    public Block(ArrayList<Instruction> body){
        this.body = body;
    }
    @Override
    public void execute(MiniMac mac) {
        for (Instruction i : body){
            i.execute(mac);
        }

    }
}
