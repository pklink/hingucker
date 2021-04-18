package net.einself.hingucker.member.httprobe;

import net.einself.hingucker.core.data.UrlData;
import net.einself.hingucker.databus.DataBus;
import net.einself.hingucker.core.Member;
import net.einself.hingucker.core.data.Data;
import net.einself.hingucker.member.httprobe.handler.UrlHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class HttpProbeMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();

    private final DataBus dataBus;

    @Inject
    public HttpProbeMember(DataBus dataBus) {
        this.dataBus = dataBus;
    }

    @Override
    public void accept(Data data) {
        LOGGER.debug("Receiving data");

        if (data instanceof UrlData) {
            final var url = (UrlData) data;
            LOGGER.debug("Resolve data as Url type: {}", url.get());
            new UrlHandler(dataBus, url).run();
        }
    }

}
