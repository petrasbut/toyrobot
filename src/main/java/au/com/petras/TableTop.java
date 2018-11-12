package au.com.petras;

public class TableTop {
    int rows;
    int columns;

    public TableTop(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public boolean isValidPosition(int col, int row) {
        return !(
                col > this.columns || col < 0
                        || row > this.rows || col < 0
        );
    }
}
