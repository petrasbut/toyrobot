package au.com.petras.tableTop;

import au.com.petras.robot.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TableTop {
    public int rows;
    public int columns;
    public List<Predicate<Position>> tablePredicates = new ArrayList<>();

    public TableTop(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        setupTableLimitPredicates(rows, columns);
        // TODO - Specific no go places on the table
        // parsePredicates(forbiddenList);
    }

    // TODO
//    private List<Predicate<Position>> parsePredicates(String... forbidenList) {
//        for (String s : forbidenList) {
//            String[] xy = StringUtils.split(s, ';');
//        }
//        return null;
//    }

    public void setupTableLimitPredicates(int rows, int cols) {
        tablePredicates.add((pos) -> pos.col < 0);
        tablePredicates.add((pos) -> pos.row < 0);
        tablePredicates.add((pos) -> pos.col > cols);
        tablePredicates.add((pos) -> pos.row > rows);
    }
}
