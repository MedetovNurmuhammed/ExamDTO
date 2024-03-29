package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record InstructorResponse( Long id, String firstName, String lastName,
         String phoneNumber,
        String specialization) {
}
