package net.einself.hingucker.databus;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class DataBusModule extends AbstractModule {

    @Provides @Singleton
    static DataBus provideDataBut() {
        return new DataBus();
    }

}
