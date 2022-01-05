package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Age {

    @JsonProperty("Baby")
    BABY("Baby"),

    @JsonProperty("Kid")
    KID("Kid"),

    @JsonProperty("Teen")
    TEEN("Teen"),

    @JsonProperty("Young Adult")
    YOUNG_ADULT("Young Adult");

    private String value;

    Age(final String value) {
        this.value = value;
    }
}

