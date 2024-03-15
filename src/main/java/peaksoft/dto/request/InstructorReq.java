package peaksoft.dto.request;

import lombok.Data;

@Data
public class InstructorReq {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
}
