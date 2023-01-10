import java.util.Calendar;

public interface Freshness {
    /**
     * @return true if is outdated and false otherwise
     */
    boolean isOutDated(Calendar today);

    /**
     * @return true if is from today and false otherwise
     */
    boolean isFromToday(Calendar today);
}
