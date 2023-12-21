package com.altimetrik.trainschedule.exception;



import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.util.Date;
        import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiException {

    private Date apiErrorDate;
    private String path;
    private List<String> message;
    private String status;
}