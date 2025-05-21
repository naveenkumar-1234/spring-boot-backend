    package com.codelab.backend.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import lombok.*;

    import java.util.List;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Staff {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank(message = "Department is required")
        @Column(name = "department", nullable = false)
        private String department;

        @ElementCollection
        @CollectionTable(name = "staff_subject_codes", joinColumns = @JoinColumn(name = "staff_id"))
        @Column(name = "subject_code")
        private List<String> subjectCodes;


        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private Users user;
    }