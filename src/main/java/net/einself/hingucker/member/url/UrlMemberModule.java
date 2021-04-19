package net.einself.hingucker.member.url;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import net.einself.hingucker.core.messagebus.MessageBus;
import net.einself.hingucker.member.url.handler.DomainResultMessageHandler;

public class UrlMemberModule extends AbstractModule {

    @Provides @Singleton
    public DomainResultMessageHandler provideDomainResultMessageHandler(MessageBus messageBus) {
        return new DomainResultMessageHandler(messageBus);
    }

}
