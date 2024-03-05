package miniMac;

public class Blt implements Instruction {
    private int location;
    private int offset;

    public Blt(int location, int offset) {
        this.location = location;
        this.offset = offset;
    }

    @Override
    public void execute(MiniMac mac) {
        Integer[] memory = mac.getMemoryArray();
        if (0 > memory[location]){
            int total = mac.getIp() + offset;
            mac.setIp(total);
        }
    }
}
