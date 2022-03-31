package spotify.exception.error;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spotify.error")
@Data
public class ErrorMessageService {

    @NotNull
    private Map<String, String> codes;

}
