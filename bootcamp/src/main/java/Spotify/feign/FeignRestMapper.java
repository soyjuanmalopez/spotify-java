package Spotify.feign;

import java.io.Serializable;

public interface FeignRestMapper<F extends Serializable, D extends Serializable> {

	F mapToFeignRest(D dto);

	D mapToDto(F rest);

}
