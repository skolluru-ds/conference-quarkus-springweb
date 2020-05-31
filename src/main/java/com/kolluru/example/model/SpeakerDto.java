package com.kolluru.example.model;

import lombok.*;

import javax.enterprise.inject.New;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SpeakerDto {
    @Null(groups = New.class, message ="The speaker id is automatically created by the system.")
    // @NotNull(groups = Existing.class)
    private UUID speakerId;
    @NotBlank(message="The firstname of the speaker can't be blank.")
    private String firstName;
    @NotBlank(message="The lastname of the speaker can't be blank.")
    private String lastName;

    private String jobTitle;
    @NotBlank(message="The company name can't be blank.")
    private String companyName;
    @Email(message="Invalid email address.")
    private String speakerEmail;
}
