package com.ust.userwebapp.web.mvc.controller.common;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import com.ust.userwebapp.persistence.common.interfaces.INameableEntity;
import com.ust.userwebapp.web.common.event.AfterResourceCreatedEvent;
import com.ust.userwebapp.web.dto.IDto;
import com.ust.userwebapp.web.mvc.util.RestPreconditions;

public abstract class AbstractController<D extends IDto, E extends INameableEntity> extends AbstractReadOnlyController<D, E> {

    @Autowired
    public AbstractController(final Class<D> clazzToSet) {
        super(clazzToSet);
    }

    // save/create/persist

    protected final void createInternal(final E resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        final E existingResource = getService().create(resource);
        eventPublisher.publishEvent(new AfterResourceCreatedEvent<D>(clazz, uriBuilder, response, existingResource.getId().toString()));
      
      
    }

    // update

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
    protected final void updateInternal(final long id, final E resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        RestPreconditions.checkNotNull(getService().findOne(resource.getId()));
       
        getService().update(resource);
    }

    // delete/remove

    protected final void deleteByIdInternal(final long id) {
       
        getService().delete(id);
    }

}
