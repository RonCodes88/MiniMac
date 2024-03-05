package miniMac;
import java.io.Serializable;

public interface Instruction extends Serializable {
    public void execute(MiniMac mac);
}

