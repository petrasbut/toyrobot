package au.com.petras;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TableTop {
    int rows;
    int columns;
    List<Predicate<Position>> tablePredicates = new ArrayList<>();

    public TableTop(int rows, int columns, String... forbiddenList) {
        this.rows = rows;
        this.columns = columns;
        parsePredicates(forbiddenList);
    }

    private List<Predicate<Position>> parsePredicates(String... forbidenList) {
        for (String s : forbidenList) {
            String[] xy = StringUtils.split(s, ';');
            Predicate<Position> predx =
        }
    }
}
