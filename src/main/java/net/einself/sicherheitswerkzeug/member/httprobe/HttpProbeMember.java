package net.einself.sicherheitswerkzeug.member.httprobe;

import net.einself.sicherheitswerkzeug.core.data.UrlData;
import net.einself.sicherheitswerkzeug.member.Member;
import net.einself.sicherheitswerkzeug.core.data.Data;
import net.einself.sicherheitswerkzeug.member.httprobe.handler.UrlHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpProbeMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public void accept(Data data) {
        LOGGER.debug("Receiving data");

        if (data instanceof UrlData) {
            final var url = (UrlData) data;
            LOGGER.debug("Resolve data as Url type: {}", url.get());
            new UrlHandler(url).run();
        }
    }

}
