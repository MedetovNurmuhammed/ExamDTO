package peaksoft.dto.request;

import lombok.Data;

@Data
public class StudentReg {
    private String fullName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String studyFormat;
}
