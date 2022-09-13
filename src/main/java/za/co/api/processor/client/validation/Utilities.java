package za.co.api.processor.client.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public static Boolean validatePhoneNumber (String pNumber) {
        String regex =  "^(\\+27|0)[1-8][0-9]{8}$"; //"(^[\+27][ 0-9]{14})|(^[\+27]( ?)[0-9]{11})|(^0[0-9]{9})|(^0[ 0-9]{11})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pNumber);
        return  matcher.matches();
    }
    public static Boolean validateId(Long identities){
        char[] idchars = identities.toString().toCharArray();
        int sum = 0;
        // loop over each digit right-to-left, including the check-digit
        for (int i = 1; i <= idchars.length; i++) {
            int digit = Character.getNumericValue(idchars[idchars.length - i]);
            if ((i % 2) != 0) {
                sum += digit;
            } else {
                sum += digit < 5 ? digit * 2 : digit * 2 - 9;
            }
        }
        return (sum % 10) == 0;
    }
}
