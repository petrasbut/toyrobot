package au.com.petras.tableTop;

import au.com.petras.robot.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class TableTopTest {

    @Before
    public void setUp() {
    }

    @Test
    public void setupTableLimitPredicates() {
        TableTop table = new TableTop(2, 2);

        assertEquals(4, table.tablePredicates.size());

        List<Predicate<Position>> predicates = table.tablePredicates;

        List<Position> positions = new ArrayList<>();

        Position illegalPositionNegX = new Position(-1, 0);
        Position illegalPositionNegY = new Position(0, -1);
        Position illegalPositionOverX = new Position(3, 1);
        Position illegalPositionOverY = new Position(1, 3);

        Position legalPosition = new Position(1, 1);

        positions.add(illegalPositionNegX);
        positions.add(illegalPositionNegY);
        positions.add(illegalPositionOverX);
        positions.add(illegalPositionOverY);
        positions.add(legalPosition);

        int errs = 0;

        for (Predicate<Position> positionPredicate : predicates) {
            for (Position position : positions) {
                if (positionPredicate.test(position)) {
                    errs++;
                }
            }
        }

        assertEquals(4, errs);

    }
}