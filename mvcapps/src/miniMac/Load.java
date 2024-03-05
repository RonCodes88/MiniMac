package miniMac;

public class Load implements Instruction {
    private int location;
    private int value;

    public Load(int location, int value){
        this.location = location;
        this.value = value;
    }
    @Override
    public void execute(MiniMac mac) {
        Integer[] memory = mac.getMemoryArray();
        memory[location] = value;
    }
}
