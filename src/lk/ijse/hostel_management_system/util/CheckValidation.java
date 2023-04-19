package lk.ijse.hostel_management_system.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValidation {
    public static boolean validation(ValidationType type, String value) {
        Pattern pattern;
        Matcher matcher;
        switch (type) {
            case USERNAME:
                pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9-_]{3,32}");
                matcher = pattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case PASSWORD:
                pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
                matcher = pattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case DATE:
                pattern = Pattern.compile("[01-12]{2}\\/[01-31]{2}\\/[1-2][0-9]{3}");
                matcher = pattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case NAME:
                pattern = Pattern.compile("[a-zA-Z]{2,}");
                matcher = pattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case CONTACT:
                pattern = Pattern.compile("^(?:7|0|(?:\\\\+94))[0-9]{9,10}$");
                matcher = pattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;

            case MONEY:
                pattern = Pattern.compile("^(?!(?:^[-+]?[0.]+(?:[Ee]|$)))(?!(?:^-))(?:(?:[+-]?)(?=[0123456789.])(?:(?:(?:[0123456789]+)(?:(?:[.])(?:[0123456789]*))?|(?:(?:[.])(?:[0123456789]+))))(?:(?:[Ee])(?:(?:[+-]?)(?:[0123456789]+))|))$");
                matcher = pattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
            case QTY:
                pattern = Pattern.compile("^[1-9]\\d*$");
                matcher = pattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
        }
        return false;
    }
}
