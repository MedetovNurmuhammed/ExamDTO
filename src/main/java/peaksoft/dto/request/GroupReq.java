package peaksoft.dto.request;

import lombok.Data;

@Data
public class GroupReq {
    private String groupName;
    private String imageLink;
    private String description;
}
