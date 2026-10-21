package org.example.atlasrh.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DepartmentUpdateRequest(

        @NotBlank(message = "Department name is required")
        @Size(
                min = 2,
                max = 100,
                message = "Department name must be between 2 and 100 characters"
        )
        @Pattern(
                regexp = "^\\S(?:.*\\S)?$",
                message = "Department name must not start or end with spaces"
        )
        String name,

        String description
) {
}
