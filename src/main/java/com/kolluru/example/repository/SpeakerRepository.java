package com.kolluru.example.repository;

import com.kolluru.example.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.Id;
import java.util.UUID;

public interface SpeakerRepository extends JpaRepository<Speaker, String> {

    //Speaker findBySpeakerId(UUID speakerId);
}
