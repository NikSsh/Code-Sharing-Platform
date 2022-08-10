package code_sharing_platform.platform.model.businessLayer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents model of the CodeSnippet
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "code_snippet")
public class CodeSnippet {

    /**
     * All enabled restrictions that can be set when creating a new code snippet
     */
    public enum RestrictionType {
        TIME_RESTRICTION,
        VIEWS_RESTRICTION,
        TIME_VIEWS_RESTRICTION,
        UNRESTRICTED
    }

    @Id
    private String UUID;

    @Column
    private String code = "";

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @Column(name = "time")
    private int accessTime;

    @Column(name = "views")
    private int accessViews;

    @Enumerated(EnumType.STRING)
    private RestrictionType restriction;

    public CodeSnippet() {

    }

    public CodeSnippet(String code, int accessTime, int accessViews) {
        this.code = code;
        this.accessTime = accessTime;
        this.accessViews = accessViews;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @JsonIgnore
    public String getUUID() {
        return UUID;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        System.out.println("Set code");
        this.code = code;
    }

    @JsonProperty("date")
    public String getLastUpdateTime() {
        return formatLastUpdateTime(lastUpdateTime);
    }


    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @JsonProperty("time")
    public int getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    @JsonProperty("views")
    public int getAccessViews() {
        return accessViews;
    }

    public void setAccessViews(int accessViews) {
        this.accessViews = accessViews;
    }
    @JsonIgnore
    public RestrictionType getRestriction() {
        return restriction;
    }

    public void setRestriction(RestrictionType restriction) {
        this.restriction = restriction;
    }

    /**
     * Sets appropriate enum object to value accessTime in accordance with the established restrictions
     */
    @JsonIgnore
    void setUpRestrictions() {
        if (accessTime > 0 && accessViews > 0) {
            restriction = RestrictionType.TIME_VIEWS_RESTRICTION;
        } else if (accessTime > 0) {
            restriction = RestrictionType.TIME_RESTRICTION;
        } else if (accessViews > 0) {
            restriction = RestrictionType.VIEWS_RESTRICTION;
        } else {
            restriction = RestrictionType.UNRESTRICTED;
        }
    }

    /**
     * Checks whether any of limits is reached
     * @return boolean value that tells if code snippet needs to be deleted
     */
    @JsonIgnore
    boolean updateRestrictions() {
        if (restriction != RestrictionType.UNRESTRICTED) {
            long secondsDiff = Math.abs(ChronoUnit.SECONDS.between(LocalDateTime.now(), lastUpdateTime));

            if (restriction == RestrictionType.TIME_VIEWS_RESTRICTION) {
                accessTime -= secondsDiff;
                accessViews -= 1;
            } else if (restriction == RestrictionType.TIME_RESTRICTION) {
                accessTime -= secondsDiff;
            } else {
                accessViews -= 1;
            }
        }

        return isToBeDeleted();
    }

    @JsonIgnore
    private String formatLastUpdateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    @JsonIgnore
    private boolean isToBeDeleted() {
        return restriction != RestrictionType.UNRESTRICTED && (accessViews < 0 || accessTime < 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeSnippet that = (CodeSnippet) o;

        if (accessTime != that.accessTime) return false;
        if (accessViews != that.accessViews) return false;
        if (!UUID.equals(that.UUID)) return false;
        if (!code.equals(that.code)) return false;
        if (!lastUpdateTime.equals(that.lastUpdateTime)) return false;
        return restriction == that.restriction;
    }

    @Override
    public int hashCode() {
        int result = UUID.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + lastUpdateTime.hashCode();
        result = 31 * result + accessTime;
        result = 31 * result + accessViews;
        result = 31 * result + restriction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CodeSnippet{" +
                "code='" + code + '\'' +
                ", date=" + lastUpdateTime +
                ", time left =" + accessTime +
                ", views left =" + accessViews +
                '}';
    }
}
