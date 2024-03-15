package peaksoft.dto.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class InstructorInfosResponse {
    private Long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String specialization;
    private Map<String, Integer> groupNameWithStudent = new HashMap<>();

    public InstructorInfosResponse(Long id, String lastName, String firstName, String phoneNumber, String specialization) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }


}
