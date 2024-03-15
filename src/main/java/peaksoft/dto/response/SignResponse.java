package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.Role;


/**
 * @author Mukhammed Asantegin
 */
@Builder
public record SignResponse(
        Long id,
        Role role,
        String email
){
}
