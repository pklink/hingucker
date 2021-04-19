package net.einself.hingucker.core.messagebus;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class MessageBusModule extends AbstractModule {

    @Provides @Singleton
    static MessageBus provideDataBut() {
        return new MessageBus();
    }

}
