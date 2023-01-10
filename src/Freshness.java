import java.util.Calendar;
import java.util.Date;

public interface Freshness {
    /**
     * @return true if is outdated and false otherwise
     */
    boolean isOutDated();

    /**
     * @return true if is from today and false otherwise
     */
    boolean isFromToday();
}
