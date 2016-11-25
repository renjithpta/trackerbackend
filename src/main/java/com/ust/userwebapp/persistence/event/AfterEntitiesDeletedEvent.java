package com.ust.userwebapp.persistence.event;

import org.springframework.context.ApplicationEvent;

import com.google.common.base.Preconditions;
import com.ust.userwebapp.persistence.common.interfaces.IEntity;

/**
 * This event is fired after all entities are deleted.
 */
public final class AfterEntitiesDeletedEvent<T extends IEntity> extends ApplicationEvent {

    private final Class<T> clazz;

    public AfterEntitiesDeletedEvent(final Object sourceToSet, final Class<T> clazzToSet) {
        super(sourceToSet);

        Preconditions.checkNotNull(clazzToSet);
        clazz = clazzToSet;
    }

    // API

    public final Class<T> getClazz() {
        return clazz;
    }

}
