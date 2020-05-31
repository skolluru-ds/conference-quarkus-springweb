package com.kolluru.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeakerDto {

    // @Null(groups = New.class, message ="The speaker id is automatically created by the system.")
    // @NotNull(groups = Existing.class)
    private String speakerId;
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

