package dialogue.system.service.implementation;

import dialogue.system.service.InputRecService;
import dialogue.system.util.GenerateCombinationService;
import dialogue.system.util.TerminalInputScanner;
import dialogue.system.validation.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InputRecServiceImpl implements InputRecService {

    private final TerminalInputScanner terminalInputScanner;
    private final GenerateCombinationService generateCombinationService;

    @Autowired
    public InputRecServiceImpl(TerminalInputScanner terminalInputScanner, GenerateCombinationService generateCombinationService) {
        this.terminalInputScanner = terminalInputScanner;
        this.generateCombinationService = generateCombinationService;
    }


    @Override
    public void recognizeInput() {
        Optional<String> input = Optional.empty();
        List<String> combinations;
        List<String> validInterpretations = new ArrayList<>();

        //keeps scanning while input is invalid
        while(!ValidationService.isValidInput(input)){
            log.error("Invalid input. Please retry.");
            input = terminalInputScanner.scan();
        }

        if(input.isPresent()){
            combinations = generateCombinationService.generateCombinationList(input.get());

            for (String combination : combinations) {
                if (ValidationService.isValidGreekPhoneNumber(combination)) {
                    validInterpretations.add(combination);
                    System.out.println("Interpretation: " + combination + "[phone number: VALID]");
                } else {
                    System.out.println("Interpretation: " + combination + "[phone number: INVALID]");
                }
            }
        }
        else{
            log.error("Could not get input from user.");
        }
    }


}
