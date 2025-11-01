package com.andela.spokentime.dto;

public record SpokenTimeRequest(String time, String locale) {

    public SpokenTimeRequest {
        if (locale == null) {
            locale = "british";
        }
    }
}
