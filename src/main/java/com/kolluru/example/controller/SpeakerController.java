package com.kolluru.example.controller;

import com.kolluru.example.domain.Speaker;
import com.kolluru.example.exception.SpeakerNotFoundException;
import com.kolluru.example.model.SpeakerDto;
import com.kolluru.example.service.SpeakerService;
import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@Path("/api/speakers")
public class SpeakerController {

    private final SpeakerService speakerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{speakerId}")
    public Response getSpeakerById(@PathParam("speakerId") final String speakerId) {
        System.out.println("***SpeakerController 1*** SpeakerID is " + speakerId.toString());
        try {
            if (speakerId == null || speakerId.trim().length() == 0) {
                return Response.serverError().entity("SpeakerID cannot be blank").build();
            }
            System.out.println("***SpeakerController 2*** SpeakerID is " + speakerId);
            SpeakerDto speakerDto = speakerService.getSpeakerById(speakerId);
            if (speakerDto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + speakerId).build();
            }
            return Response.ok(speakerDto, MediaType.APPLICATION_JSON).build();

/*
            Speaker speaker = speakerService.getSpeakerById(speakerId);
            if (speaker == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + speakerId).build();
            }
            return Response.ok(speaker, MediaType.APPLICATION_JSON).build();
*/
        }
        catch(SpeakerNotFoundException e){
            System.out.println("***SpeakerController 3***  " + "SpeakerNotFoundException Caught.... ");
            System.out.println("***SpeakerController 3***  " + "Exception is.... " + e);
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + speakerId).build();
        }
        catch(Exception e) {
            System.out.println("***SpeakerController 4***  " + "Exception Caught.... ");
            System.out.println("***SpeakerController 4***  " + "Exception is.... " + e.toString());
            // e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + speakerId).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addSpeaker(Speaker speaker, @Context UriInfo uriInfo){
        System.out.println("*** SpeakerController-addSpeaker..." + speaker.toString());
        try {
            final Speaker newSpeaker = speakerService.addSpeaker(speaker);

            URI createdURI = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newSpeaker.getSpeakerId())).build();
            return Response.created(createdURI).build();
        }
        catch(Exception e) {
            System.out.println("***SpeakerController 5***  " + "Exception Caught.... ");
            System.out.println("***SpeakerController 5***  " + "Exception is.... " + e.toString());
            // e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{speakerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSpeaker(@PathParam("speakerId") final String speakerId,Speaker speaker){
        if(speakerId == null || speakerId.toString().trim().length() == 0) {
            return Response.serverError().entity("SpeakerID cannot be blank").build();
        }
        try {
            speakerService.updateSpeaker(speakerId, speaker);
            return Response.noContent().build();
        }
        catch(SpeakerNotFoundException e){
            System.out.println("***SpeakerController 6***  " + "SpeakerNotFoundException Caught.... ");
            System.out.println("***SpeakerController 6***  " + "Exception is.... " + e);
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + speakerId).build();
        }
        catch(Exception e) {
            System.out.println("***SpeakerController 7***  " + "Exception Caught.... ");
            System.out.println("***SpeakerController 7***  " + "Exception is.... " + e.toString());
            // e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for UUID: " + speakerId).build();
        }
    }

}
