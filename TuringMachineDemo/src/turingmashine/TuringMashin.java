package turingmashine;

import java.io.*;
import java.util.*;

public class TuringMashin {
    List<String> line;
    Set<String> alphabit;
    int position;
    int stopState;
    List<Element> program;

    public TuringMashin(String name){
        program = new ArrayList<>();
        try {
            BufferedReader fl = new BufferedReader(new FileReader(new File(name)));
            String str = fl.readLine();
            line = new ArrayList<>(Arrays.asList(str.split("\\s+")));
            str = fl.readLine();
            alphabit = new HashSet<>(Arrays.asList(str.split("\\s+")));
            alphabit.add(" ");
            position = Integer.parseInt(fl.readLine());
            stopState = Integer.parseInt(fl.readLine());

            while((str = fl.readLine()) != null){
                String[] buffer = str.split("\\s+");
                for(int i = 0; i < buffer.length; i++) {
                    Element parse = parseElement(buffer[i]);
                    if(parse != null)
                        program.add(parseElement(buffer[i]));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public Collection<String> runProgram(){
        List<String> result = new ArrayList<>();
        var state = 0;
        while(state != stopState){
            for(var p : program){
                if(state == p.currentState && line.get(position).equals(p.currentSymbol)){
                    state = p.nextState;
                    line.remove(position);
                    line.add(position, p.newSymbol);
                    position += p.direction;
                    result.add("Operation: " + p.toString());
                    result.add("Positoin: " + String.valueOf(position + "\n"));
                    result.add(line.toString() + "\n");
                }
            }
        }
        return result;
    }

    private Element parseElement(String str){
        //q01>q01R
        if(str.equals("nan")) return null;

        Element result = new Element();
        result.currentState = Character.getNumericValue(str.charAt(1));
        result.currentSymbol = String.valueOf(str.charAt(2));
        result.nextState = Character.getNumericValue(str.charAt(5));
        result.newSymbol = String.valueOf(str.charAt(6));
        if(str.charAt(7) == 'R')
            result.direction = 1;
        else if(str.charAt(7) == 'L')
            result.direction = -1;
        else
            result.direction = 0;
        return result;
    }

    public void logger(Collection<String> programLog, String fileName){
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter("log"));
            for(var log : programLog){
                bf.write(log);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
