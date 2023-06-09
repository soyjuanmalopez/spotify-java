package spotify.controller.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class D4iPageRest<T> implements Serializable {

    private static final long serialVersionUID = 1741899248080904930L;

    private T[] content;
    private D4iPaginationInfo page;

}
