package spotify.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorRest implements Serializable {

    private static final long serialVersionUID = 6067791672383133605L;

    private String code;

    private String message;
}
