package com.codelab.backend.request;

import com.codelab.backend.enums.UserRoles;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AddStudentReq {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotNull(message = "SPR is required")
    private Integer spr;
    @NotNull(message = "User role is required")
    private UserRoles userRole;
    @NotBlank(message = "Department is required")
    private String department;
    @NotBlank(message = "RegisterNumber is required")
    private String registerNumber;
    @NotNull(message = "Year is required")
    private Integer year;
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    private Integer semester;
    @NotEmpty(message = "Subject codes cannot be empty")
    private List<@NotBlank(message = "Subject code cannot be blank") String> subjectCodes;
}
