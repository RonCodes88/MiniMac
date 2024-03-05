package miniMac;

public class Mov implements Instruction {
    private int src;
    private int dest;

    public Mov(int src, int dest){
        this.src = src;
        this.dest = dest;
    }
    @Override
    public void execute(MiniMac mac) {
        Integer[] memory = mac.getMemoryArray();
        memory[dest] = memory[src];
    }
}