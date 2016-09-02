package com.mbarlow.domainbondiproperties.event;

import org.greenrobot.eventbus.EventBus;

/**
 * A wrapper around EventBus allowing for mocks
 *
 * Created by michael on 2/09/16.
 */
public class EventBusProviderImpl implements EventBusProvider{
    @Override
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
