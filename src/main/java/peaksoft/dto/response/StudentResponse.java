package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record StudentResponse (Long id, String fullName,
        String lastName,
        String phoneNumber,
        String email,
        String studyFormat){
}
