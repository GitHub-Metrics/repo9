package org.webonise.springboot.entities;

import org.springframework.stereotype.Component;

@Component
public class ResponceContent {

    private String content;

    public ResponceContent() {
    }

    public ResponceContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}