package com.kolluru.example.service;

import com.kolluru.example.domain.Speaker;
import com.kolluru.example.exception.SpeakerNotFoundException;
import com.kolluru.example.mapper.SpeakerMapper;
import com.kolluru.example.model.SpeakerDto;
import com.kolluru.example.repository.SpeakerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

// @ApplicationScoped
@RequiredArgsConstructor
//@Data
@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;
    @Inject
    SpeakerMapper speakerMapper;

    public SpeakerDto getSpeakerById(String speakerId) {
        System.out.println("***SpeakerService 1*** SpeakerID is " + speakerId);
        Speaker speaker = speakerRepository.findById(speakerId).orElseThrow(SpeakerNotFoundException::new);
        System.out.println("***SpeakerService 2*** Speaker is " + speaker.toString());
        return speakerMapper.mapSpeakerToSpeakerDto(speaker);
    }

/*
    public Speaker getSpeakerById(String speakerId){
        System.out.println("***SpeakerService 1*** SpeakerID is " + speakerId);
        return speakerRepository.findById(speakerId).orElseThrow(SpeakerNotFoundException::new);
    }
*/
    public Speaker addSpeaker(Speaker speaker) {
        System.out.println("*** SpeakerService-addSpeaker..." + speaker.toString());
        return speakerRepository.save(speaker);
    }

    public Speaker updateSpeaker(String speakerId, Speaker speaker){
        Speaker mySpeaker = speakerRepository.findById(speakerId).orElseThrow(SpeakerNotFoundException::new);

        mySpeaker.setFirstName(speaker.getFirstName());
        mySpeaker.setLastName(speaker.getLastName());
        mySpeaker.setJobTitle(speaker.getJobTitle());
        mySpeaker.setCompanyName(speaker.getCompanyName());
        mySpeaker.setSpeakerEmail(speaker.getSpeakerEmail());

        return speakerRepository.save(mySpeaker);
    }


/*
    private final SpeakerMapper speakerMapper;

    public SpeakerDto getSpeakerById(UUID speakerId){
        System.out.println("***SpeakerService 1*** SpeakerID is " + speakerId.toString());
        return speakerMapper.speakerToSpeakerDto(
                speakerRepository.findById(speakerId).orElseThrow(SpeakerNotFoundException::new));
    }


    public SpeakerDto addSpeaker(SpeakerDto speakerDto) {
        System.out.println("*** SpeakerService-addSpeaker..." + speakerDto.toString());
        Speaker speaker = speakerRepository.save(speakerMapper.speakerDtoToSpeaker(speakerDto));

        return speakerMapper.speakerToSpeakerDto(speaker);
    }

    public SpeakerDto updateSpeaker(UUID speakerId, SpeakerDto speakerDto){
        Speaker speaker = speakerRepository.findById(speakerId).orElseThrow(SpeakerNotFoundException::new);

        speaker.setFirstName(speakerDto.getFirstName());
        speaker.setLastName(speakerDto.getLastName());
        speaker.setJobTitle(speakerDto.getJobTitle());
        speaker.setCompanyName(speakerDto.getCompanyName());
        speaker.setSpeakerEmail(speakerDto.getSpeakerEmail());

        return speakerMapper.speakerToSpeakerDto(speakerRepository.save(speaker));
    }
    */
}
