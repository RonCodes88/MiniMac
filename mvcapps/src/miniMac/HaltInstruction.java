package miniMac;

public class HaltInstruction implements Instruction {


    @Override
    public void execute(MiniMac mac) {
        mac.setHalt(true);
    }
}
