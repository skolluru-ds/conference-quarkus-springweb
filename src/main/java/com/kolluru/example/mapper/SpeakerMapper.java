package com.kolluru.example.mapper;

import com.kolluru.example.domain.Speaker;
import com.kolluru.example.model.SpeakerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface SpeakerMapper {

    public Speaker mapSpeakerDtoToSpeaker(SpeakerDto speakerDto);
    public SpeakerDto mapSpeakerToSpeakerDto(Speaker speaker);

}
