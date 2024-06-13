package ua.com.alevel.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger(LoggerLevel.INFO.getValue());
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger(LoggerLevel.WARN.getValue());
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger(LoggerLevel.ERROR.getValue());

    @Override
    public void log(LoggerLevel level, String message) {
        switch (level) {
            case INFO -> LOGGER_INFO.info(message);
            case WARN -> LOGGER_WARN.warn(message);
            case ERROR -> LOGGER_ERROR.error(message);
        }
    }
}
