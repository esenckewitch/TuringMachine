package turingmashine;

public class Element {
    int currentState;
    int nextState;
    String currentSymbol;
    String newSymbol;
    int direction;

    //q01>q01R
    @Override
    public String toString(){
        return "q" + currentState +  currentSymbol + ">q" + nextState + newSymbol + direction + '\n';
    }
}
