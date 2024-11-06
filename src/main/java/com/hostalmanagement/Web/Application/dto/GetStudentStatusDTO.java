package com.hostalmanagement.Web.Application.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStudentStatusDTO {

    private Long student_id;
    private String tg_no;
    private String department;
    private String email;
    private boolean isRegisterd;
    private String fullname;

}
