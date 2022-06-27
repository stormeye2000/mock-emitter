package com.stormeye.emitter.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stormeye.emitter.domain.Topics;
import com.stormeye.emitter.service.EmitterService;

import java.time.Duration;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/test-emitter/events")
public class EmitterController {

    private final EmitterService service;

    public EmitterController(final EmitterService service) {
        this.service = service;
    }

    @GetMapping(value = "/deploy", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> streamDeploy() {
        return Flux.interval(Duration.ofSeconds(1)).map(i -> service.generateEvent(Topics.DEPLOY_ACCEPTED) );
    }

    @GetMapping(value = "/sig", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> streamSig() {
        return Flux.interval(Duration.ofSeconds(1)).map(i -> service.generateEvent(Topics.FINALITY_SIGNATURE) );
    }

    @GetMapping(value = "/main", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> streamMain() {
        return Flux.interval(Duration.ofSeconds(1)).map(i -> service.randomMainEvent() );
    }

}
