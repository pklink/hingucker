package net.einself.hingucker.member.url.handler;

import net.einself.hingucker.core.MessageHandler;
import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.message.UrlMessage;
import net.einself.hingucker.core.messagebus.MessageBus;

import javax.inject.Inject;

public class DomainResultMessageHandler implements MessageHandler<DomainResultMessage> {

    private final MessageBus messageBus;

    @Inject
    public DomainResultMessageHandler(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void accept(DomainResultMessage message) {
        publishUrl(message.getDomain());
    }

    private void publishUrl(String domain) {
        publishUrl("http", domain);
        publishUrl("https", domain);
    }

    private void publishUrl(String protocol, String domain) {
        final var url = String.format("%s://%s", protocol, domain);
        messageBus.publish(new UrlMessage(url));
    }

}
