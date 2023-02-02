import turingmashine.TuringMashin;

public class Main {
    public static void main(String[] args) {
        TuringMashin test = new TuringMashin("data.t");
        test.logger(test.runProgram(), "log.txt");
    }
}