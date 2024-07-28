package dialogue.system.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateCombinationServiceTest {

    private GenerateCombinationService service;

    @BeforeEach
    public void setUp() {
        service = new GenerateCombinationService();
    }

    @Test
    public void testGenerateCombinationList_Simple_TypeA() {
        //only type A ambiguity, 1 times on "10 6"
        //Expected results are 2, original and 1 alternative for the type A ambiguity
        String phoneNumber = "2 10 6 9";
        List<String> result = service.generateCombinationList(phoneNumber);

        Set<String> expected = new HashSet<>();
        expected.add("21069");
        expected.add("2169");

        assertEquals(expected.size(), result.size());
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    public void testGenerateCombinationList_Simple_TypeB() {
        //only type B ambiguity, 1 times on "75"
        //Expected results are 2, original and 1 alternative for the type B ambiguity
        String phoneNumber = "2 75 9";
        List<String> result = service.generateCombinationList(phoneNumber);

        Set<String> expected = new HashSet<>();
        expected.add("2759");
        expected.add("27059");

        assertEquals(expected.size(), result.size());
        assertEquals(expected, new HashSet<>(result));
    }


    @Test
    public void testGenerateCombinationList_AmbiguityTypeA() {
        //only type A ambiguity, 2 times on "10 6" and "30 6"
        //Expected results are 4
        String phoneNumber = "2 10 6 9 30 6 6 4";
        List<String> result = service.generateCombinationList(phoneNumber);

        assertEquals(4, result.size());
    }

    @Test
    public void testGenerateCombinationList_Complex() {
        //complex ambiguity test
        String phoneNumber = "2 10 69 30 6 6 4";
        List<String> result = service.generateCombinationList(phoneNumber);

        Set<String> expected = new HashSet<>();
        expected.add("2106930664");
        expected.add("210693664");
        expected.add("2106093664");
        expected.add("21060930664");

        assertEquals(expected.size(), result.size());
        assertEquals(expected, new HashSet<>(result));
    }
}
