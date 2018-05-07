package utils;

/**
 * Created by marcin.bracisiewicz
 */
public class StringUtils {

    public static boolean isUpperCase(String word) {
        for(char c : word.toCharArray()) {
            if(Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
}
