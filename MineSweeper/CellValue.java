/**
 * Created by Maxie on 2017-01-20.
 */
public enum CellValue {
    OPEN(-1), EMPTY(0), MINE(100), MAYBEMINE(1000);

    private final int value;

    CellValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
