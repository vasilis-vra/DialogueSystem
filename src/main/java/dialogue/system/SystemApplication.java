package dialogue.system;

import dialogue.system.service.InputRecService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemApplication {

	private final InputRecService inputRecService;

	@Autowired
	public SystemApplication(InputRecService inputRecService) {

		this.inputRecService = inputRecService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	//start the service after initialization
	@PostConstruct
	public void init() {
		inputRecService.recognizeInput();
	}

}
