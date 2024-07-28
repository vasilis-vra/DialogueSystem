package dialogue.system.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class GenerateCombinationService {

    private static final String AMB_TYPE_A = "^[1-9]0$";
    private static final String AMB_TYPE_B = "^[1-9][1-9]$";

    public List<String> generateCombinationList(String phoneNumber){
        HashSet<String> combinations = new HashSet<>();

        combinations.add(phoneNumber.replace(" ", ""));

        generateListWithAmbiguityTypeA(phoneNumber, combinations, 0);
        generateListWithAmbiguityTypeB(phoneNumber, combinations, 0);

        return combinations.stream().toList();
    }
    private static void generateListWithAmbiguityTypeA(String phoneNumber, HashSet<String> combinations, int index){

        for(int i=index; i<phoneNumber.length()-3; i++){
            if(phoneNumber.substring(i,i+2).matches(AMB_TYPE_A) && phoneNumber.charAt(i+2) == ' '){
                if((i+4 > phoneNumber.length() && phoneNumber.charAt(i+3)!='0')||(phoneNumber.substring(i+3, i+5).matches("[1-9]\\s"))){
                    String newCombination = phoneNumber.substring(0, i+1) + phoneNumber.substring(i+3);
                    combinations.add(newCombination.replace(" ",""));
                    generateListWithAmbiguityTypeA(newCombination, combinations,i+1);
                    generateListWithAmbiguityTypeB(newCombination, combinations,i+1);
                }
            }
        }
    }

    private static void generateListWithAmbiguityTypeB(String phoneNumber, HashSet<String> combinations, int index){

        for(int i=index; i<phoneNumber.length()-3; i++){
            if(phoneNumber.substring(i,i+2).matches(AMB_TYPE_B) && phoneNumber.charAt(i+2) == ' '){
                String newCombination = phoneNumber.substring(0, i+1)+'0' + phoneNumber.substring(i+1);
                combinations.add(newCombination.replace(" ",""));
                generateListWithAmbiguityTypeA(newCombination, combinations,i+1);
                generateListWithAmbiguityTypeB(newCombination, combinations,i+1);
            }
        }
    }
}
