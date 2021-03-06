package sdm.project.consolecomponents;

import sdm.project.core.entities.Symbol;
import sdm.project.core.exceptions.BoardIndexOutOfBoundsException;
import sdm.project.core.exceptions.IllegalSymbolException;
import sdm.project.core.exceptions.WrongNumberOfArgumentsException;

public class MoveParser {

    private  int column;
    private  int row;
    private  Symbol symbol;

    public  void setMoveLine(String moveLine)
            throws IllegalSymbolException, BoardIndexOutOfBoundsException, WrongNumberOfArgumentsException {

        String[] tokens = moveLine.trim().split(" ");

        if (tokens.length != 2 || tokens[0].length() < 2)
            throw new WrongNumberOfArgumentsException("Move bad syntax");

        checkMoveLineFormat(tokens);

        column = retrieveColumn(tokens[0].toLowerCase().charAt(0));
        row = retrieveRow(Integer.parseInt(tokens[0].substring(1)));
        symbol = retrieveSymbol(tokens[1]);

    }

    public  int getRow() {
        return row;
    }

    public  Symbol getSymbol() {
        return symbol;
    }

    public  int getColumn() {
        return column;
    }

    private  void checkMoveLineFormat(String[] tokens) throws BoardIndexOutOfBoundsException, IllegalSymbolException {

        if (!tokens[0].substring(0, 1).matches("[a-fA-F]")) {
            throw new BoardIndexOutOfBoundsException("Bad column value");
        } else if (!tokens[0].substring(1).matches("[1-6]")) {
            throw new BoardIndexOutOfBoundsException("Bad row value");
        } else if (!tokens[1].matches("[oOxX]")) {
            throw new IllegalSymbolException("Bad symbol value");
        }
    }


    private  int retrieveRow(int row) {
        return row - 1;
    }

    private  int retrieveColumn(char column) {
        // converting the character in the current column value
        return column - 'a';
    }

    private  Symbol retrieveSymbol(String symbol){
        return symbol.equalsIgnoreCase("X") ? Symbol.CROSS : Symbol.CIRCLE;
    }


}
