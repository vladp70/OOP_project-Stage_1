package utils;

import common.Constants;
import enums.AgeGroup;

public final class Utils {
    private Utils() {
    }

    public static AgeGroup nextAgeGroup(final AgeGroup ageGroup) {
        switch(ageGroup) {
            case BABY:
                return AgeGroup.KID;
            case KID:
                return AgeGroup.TEEN;
            case TEEN:
            case YOUNG_ADULT:
                return AgeGroup.YOUNG_ADULT;
            default:
                return AgeGroup.UNKNOWN;
        }
    }

    public static Integer upperLimitAgeGroup(final AgeGroup ageGroup) {
        switch(ageGroup) {
            case BABY:
                return Constants.UPPER_LIMIT_BABY;
            case KID:
                return Constants.UPPER_LIMIT_KID;
            case TEEN:
                return Constants.UPPER_LIMIT_TEEN;
            case YOUNG_ADULT:
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static AgeGroup ageToAgeGroup(final Integer age) {
        if (0 <= age && age <= Constants.UPPER_LIMIT_BABY) {
            return AgeGroup.BABY;
        } else if (Constants.UPPER_LIMIT_BABY < age && age <= Constants.UPPER_LIMIT_KID) {
            return AgeGroup.KID;
        } else if (Constants.UPPER_LIMIT_KID < age && age <= Constants.UPPER_LIMIT_TEEN) {
            return AgeGroup.TEEN;
        } else if (Constants.UPPER_LIMIT_TEEN < age) {
            return AgeGroup.YOUNG_ADULT;
        } else {
            return AgeGroup.UNKNOWN;
        }
    }
}
