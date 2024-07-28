package dialogue.system.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    public void setUp() {
        validationService = new ValidationService();
    }

    @Test
    public void testIsValidInput_EmptyInput() {
        Optional<String> input = Optional.empty();
        boolean result = ValidationService.isValidInput(input);
        assertFalse(result);
    }

    @Test
    public void testIsValidInput_ValidInput() {
        Optional<String> input = Optional.of("123 456 789 0");
        boolean result = ValidationService.isValidInput(input);
        assertTrue(result);
    }

    @Test
    public void testIsValidInput_InvalidFourDigits() {
        Optional<String> input = Optional.of("1234 567 89");
        boolean result = ValidationService.isValidInput(input);
        assertFalse(result);
    }

    @Test
    public void testIsValidInput_InvalidInputWithNonDigits() {
        Optional<String> input = Optional.of("123 ab 789");
        boolean result = ValidationService.isValidInput(input);
        assertFalse(result);
    }

    @Test
    public void testIsValidInput_InvalidInputTooShort() {
        Optional<String> input = Optional.of("12 34");
        boolean result = ValidationService.isValidInput(input);
        assertFalse(result);
    }

    @Test
    public void testIsValidGreekPhoneNumber_ValidTenDigit() {
        String input = "2101234567";
        boolean result = ValidationService.isValidGreekPhoneNumber(input);
        assertTrue(result);
    }

    @Test
    public void testIsValidGreekPhoneNumber_ValidFourteenDigit() {
        String input = "00302101234567";
        boolean result = ValidationService.isValidGreekPhoneNumber(input);
        assertTrue(result);
    }

    @Test
    public void testIsValidGreekPhoneNumber_InvalidFormat() {
        String input = "0030210123456A";
        boolean result = ValidationService.isValidGreekPhoneNumber(input);
        assertFalse(result);
    }
}