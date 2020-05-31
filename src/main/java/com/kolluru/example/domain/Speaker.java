package com.kolluru.example.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
@Table(name = "speaker")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Speaker {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID speakerId;
    @Column(name="firstname", length = 64)
    private String firstName;
    @Column(name="lastname", length = 64)
    private String lastName;
    @Column(name="jobtitle", length = 64)
    private String jobTitle;
    @Column(name="companyname", length = 64)
    private String companyName;
    @Column(name="email", length = 64)
    private String speakerEmail;

    @CreationTimestamp
    @Column(name = "createdat", updatable = false, nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "lastmodifiedat", nullable = false)
    private Timestamp lastModifiedAt;
}
