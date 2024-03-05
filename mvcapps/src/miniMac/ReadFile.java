package miniMac;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {

    public static String readUserFile() {
        String fileName = JOptionPane.showInputDialog(null, "Enter program file name:");

        if (fileName == null || fileName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No file name entered.");
             return "Can't find file";
        }
        StringBuilder program = new StringBuilder();
        try (
            BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                program.append(line).append("\n");
                line = reader.readLine();
            }
            System.out.println(program);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return program.toString();
    }
}


