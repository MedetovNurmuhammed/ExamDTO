package peaksoft.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class TaskReq {
    private String taskName;
    private String taskText;
    private ZonedDateTime deadLine;
}
