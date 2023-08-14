package studia.inz.inzynierka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import studia.inz.inzynierka.Security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@CrossOrigin(origins = "http://localhost:3000")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
