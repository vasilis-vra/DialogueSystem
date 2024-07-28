package dialogue.system.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
@Slf4j
public class TerminalInputScanner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }

    public Optional<String> scan() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Enter a sequence of numbers separated by spaces. Each number may be up to a three digit number.");

        return Optional.of(scanner.nextLine());
    }
}