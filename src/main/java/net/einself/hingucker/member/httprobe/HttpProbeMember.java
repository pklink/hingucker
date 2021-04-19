package net.einself.hingucker.member.httprobe;

import net.einself.hingucker.core.message.UrlMessage;
import net.einself.hingucker.core.messagebus.MessageBus;
import net.einself.hingucker.core.Member;
import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.member.httprobe.handler.UrlHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class HttpProbeMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();

    private final MessageBus messageBus;

    @Inject
    public HttpProbeMember(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void accept(Message message) {
        LOGGER.debug("Receiving data");

        if (message instanceof UrlMessage) {
            final var url = (UrlMessage) message;
            LOGGER.debug("Resolve data as Url type: {}", url.get());
            new UrlHandler(messageBus, url).run();
        }
    }

}
