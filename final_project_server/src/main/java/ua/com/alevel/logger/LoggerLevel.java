package ua.com.alevel.logger;

import lombok.Getter;

@Getter
public enum LoggerLevel {

    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private final String value;

    LoggerLevel(String value) {
        this.value = value;
    }
}
