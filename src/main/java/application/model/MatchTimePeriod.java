package application.model;

public class MatchTimePeriod {

    private String periodShortForm;
    private String periodLongForm;
    private String minTimeStamp;
    private String maxTimeStamp;
    private boolean extraTimeAllowed;

    public String getPeriodShortForm() {
        return periodShortForm;
    }

    public void setPeriodShortForm(String periodShortForm) {
        this.periodShortForm = periodShortForm;
    }

    public String getPeriodLongForm() {
        return periodLongForm;
    }

    public void setPeriodLongForm(String periodLongForm) {
        this.periodLongForm = periodLongForm;
    }

    public String getMinTimeStamp() {
        return minTimeStamp;
    }

    public void setMinTimeStamp(String minTimeStamp) {
        this.minTimeStamp = minTimeStamp;
    }

    public String getMaxTimeStamp() {
        return maxTimeStamp;
    }

    public void setMaxTimeStamp(String maxTimeStamp) {
        this.maxTimeStamp = maxTimeStamp;
    }

    public boolean isExtraTimeAllowed() {
        return extraTimeAllowed;
    }

    public void setExtraTimeAllowed(boolean extraTimeAllowed) {
        this.extraTimeAllowed = extraTimeAllowed;
    }
}
