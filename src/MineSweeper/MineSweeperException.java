/**
 * Created by bartek on 2017-01-27.
 */
package MineSweeper;

public class MineSweeperException extends Exception {

    public MineSweeperException()
    {
    }

    public MineSweeperException(String message)
    {
        super(message);
    }

    public MineSweeperException(Throwable cause)
    {
        super(cause);
    }

    public MineSweeperException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MineSweeperException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
