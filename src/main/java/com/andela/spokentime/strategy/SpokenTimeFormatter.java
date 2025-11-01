package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;

public interface SpokenTimeFormatter {
    SpokenTimeResponse format(String time);
}
