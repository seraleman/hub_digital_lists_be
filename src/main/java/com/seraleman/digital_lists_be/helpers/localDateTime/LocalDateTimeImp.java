package com.seraleman.digital_lists_be.helpers.localDateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

@Service
public class LocalDateTimeImp implements ILocalDateTime {

    @Override
    public LocalDateTime getLocalDateTime() {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/Bogota"));
        return zdt.toLocalDateTime();
    }

}
