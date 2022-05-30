package spotify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpotifyApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpotifyApplication.class, args);
    }

}

