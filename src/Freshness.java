import java.util.Calendar;

public interface Freshness {
    /**
     * @return true if is outdated and false otherwise
     */
    public boolean isOutDated(Calendar today);

    /**
     * @return true if is from today and false otherwise
     */
    public boolean isFromToday(Calendar today);
}
