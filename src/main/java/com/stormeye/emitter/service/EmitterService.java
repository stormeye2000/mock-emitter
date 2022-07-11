package com.stormeye.emitter.service;

import org.springframework.stereotype.Service;
import com.stormeye.emitter.domain.Topics;

import java.util.Random;
import java.util.UUID;
/**
 * TEST JAVADOC
 */
@Service
public class EmitterService {

    public String generateEvent(final Topics topic){

        return """
                { "%s" : { "id":"%s"}}
                """.formatted(topic.toCamelCase(topic), UUID.randomUUID());

    }


    public String randomMainEvent(){

        String topic;
        int i = new Random().nextInt(5);
        switch (i) {
            case 0 -> topic = Topics.DEPLOY_PROCESSED.toCamelCase(Topics.DEPLOY_PROCESSED);
            case 1 -> topic = Topics.DEPLOY_EXPIRED.toCamelCase(Topics.DEPLOY_EXPIRED);
            case 2 -> topic = Topics.BLOCK_ADDED.toCamelCase(Topics.BLOCK_ADDED);
            case 3 -> topic = Topics.FAULT.toCamelCase(Topics.FAULT);
            case 4 -> topic = Topics.STEP.toCamelCase(Topics.STEP);
            default -> throw new IllegalStateException("Unexpected value: " + i);
        }

        return """
                { "%s" : { "id":"%s"}}
                """.formatted(topic, UUID.randomUUID());

    }


}
