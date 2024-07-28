package dialogue.system.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ValidationService {

    private static final String TEN_DIGIT_PATTERN = "^(2\\d{9}|69\\d{8})$";
    private static final String FOURTEEN_DIGIT_PATTERN = "^(00302\\d{9}|003069\\d{8})$";

    //validates that each input number is between 1 and 3 digits so that they represents numbers a user might pronounce
    public static boolean isValidInput(Optional<String> input){

        if(input.isEmpty()) return false;
        if(input.get().replace(" ","").length()<10) return false;

        String[] numbers = input.get().split(" ");

        for (String n : numbers) {
            if (!n.matches("\\d{1,3}")) {
                log.error("Invalid input: " + n);
                System.out.println("Invalid input. Input must be a sequence of numbers between 1 and 3 digits, separated by a space.");
                return false;
            }
        }
        return  true;
    }

    //validates if a number is a valid Greek telephone number
    public static boolean isValidGreekPhoneNumber(String input){
        //check if it matches one of the greek phone number patterns
        if (input.matches(TEN_DIGIT_PATTERN) || input.matches(FOURTEEN_DIGIT_PATTERN)) {
            return true;
        }
        return false;
    }
}
