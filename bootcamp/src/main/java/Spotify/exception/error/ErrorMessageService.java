package Spotify.exception.error;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spotify.error")
@Data
public class ErrorMessageService {

	@NotNull
	private Map<String, String> codes;

}
