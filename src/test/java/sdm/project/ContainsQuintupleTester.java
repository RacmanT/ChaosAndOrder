package sdm.project;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import sdm.project.core.entities.Cell;
import sdm.project.core.entities.Symbol;
import sdm.project.core.utils.predicates.ContainsQuintuple;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ContainsQuintupleTester {

    private final ContainsQuintuple containsQuintuple = new ContainsQuintuple();
    private Cell[] cells;


    @ParameterizedTest
    @CsvSource({"CIRCLE, CROSS", "CROSS, CIRCLE"})
    public void testSixCellsArray(Symbol matchSymbol, Symbol unmatchSymbol) {

        cells = new Cell[6];
        cells = Arrays.stream(cells).map(cell -> cell = new Cell(1, 1)).toArray(Cell[]::new);

        assertFalse(containsQuintuple.test(cells));
        Arrays.stream(cells).forEach(cell -> cell.setSymbol(matchSymbol));
        assertTrue(containsQuintuple.test(cells));

        cells[0].setSymbol(unmatchSymbol);
        assertTrue(containsQuintuple.test(cells));
        cells[0].setSymbol(matchSymbol);

        cells[5].setSymbol(unmatchSymbol);
        assertTrue(containsQuintuple.test(cells));
        cells[5].setSymbol(matchSymbol);

        cells[3].setSymbol(unmatchSymbol);
        assertFalse(containsQuintuple.test(cells));

    }

    @ParameterizedTest
    @CsvSource({"CIRCLE, CROSS", "CROSS, CIRCLE"})
    public void testFiveCellsArray(Symbol matchSymbol, Symbol unmatchSymbol) {

        cells = new Cell[5];
        cells = Arrays.stream(cells).map(cell -> cell = new Cell(1, 1)).toArray(Cell[]::new);

        assertFalse(containsQuintuple.test(cells));
        Arrays.stream(cells).forEach(cell -> cell.setSymbol(matchSymbol));
        assertTrue(containsQuintuple.test(cells));

        cells[3].setSymbol(unmatchSymbol);
        assertFalse(containsQuintuple.test(cells));

    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 7, 10})
    public void testNotFiveAndNotSixCellsArray(int length) {
        cells = new Cell[length];
        cells = Arrays.stream(cells).map(cell -> cell = new Cell(2, 4)).peek(cell -> cell.setSymbol(Symbol.CIRCLE)).toArray(Cell[]::new);
        assertFalse(containsQuintuple.test(cells));
    }

}
