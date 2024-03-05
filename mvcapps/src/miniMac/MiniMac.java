package miniMac;
import java.io.Serializable;
import java.util.ArrayList;


import tools.Publisher;

public class MiniMac extends Publisher implements Serializable{
    private int ip = 0;
    private boolean halt = false; //stop program
    private int size = 32;

    private Integer[] memory = new Integer[size];

    private ArrayList<Instruction> program = new ArrayList<>();

    private ArrayList<String> programAsStrings = new ArrayList<>();

    public void execute(){
        while (ip < program.size() && !halt) {
            Instruction next = program.get(ip++);
            next.execute(this);
            notifySubscribers();
        }
    }

    public Integer[] getMemoryArray(){
        return memory;
    }

    public void changeProgramAsStringArrayList(String program){
        String[] linesArray = program.split("\n");
        for (String line : linesArray) {
            programAsStrings.add(line);
        }
    }

    public ArrayList<String> getProgramAsStrings(){
        return programAsStrings;
    }
    public int getIp(){
        return ip;
    }

    public void setIp(int newIp){
        ip = newIp;
    }
    public void putInstructionObjToList(ArrayList<Instruction> result){
        program = result;
    }

    public void setHalt(boolean halt){
        this.halt = halt;
    }

    public void clear(){
        for (int i = 0; i < memory.length; i++){
            memory[i] = 0;
            System.out.println("memory at index " + i + " is cleared.");
        }
        notifySubscribers();
    }
}
