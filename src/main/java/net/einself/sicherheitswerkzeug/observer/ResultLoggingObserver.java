package net.einself.sicherheitswerkzeug.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultLoggingObserver implements ResultObserver {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void update(Result result) {
        LOGGER.info(result.getMessage());
    }

}
