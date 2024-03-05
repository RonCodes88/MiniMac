package miniMac;

public class Loop implements Instruction{
    private int count;
    private Instruction instruction;

    public Loop(int count, Instruction instruction){
        this.count = count;
        this.instruction = instruction;
    }
    @Override
    public void execute(MiniMac mac) {
        while (count > 0){
            instruction.execute(mac);
            count--;
        }
    }

}
