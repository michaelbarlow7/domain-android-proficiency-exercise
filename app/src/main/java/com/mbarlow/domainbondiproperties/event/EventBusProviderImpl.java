package com.mbarlow.domainbondiproperties.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by michael on 2/09/16.
 */
public class EventBusProviderImpl implements EventBusProvider{
    @Override
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
