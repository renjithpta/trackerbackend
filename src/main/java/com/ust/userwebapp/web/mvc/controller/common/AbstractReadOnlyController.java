package com.ust.userwebapp.web.mvc.controller.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ust.userwebapp.common.util.QueryConstants;
import com.ust.userwebapp.common.util.WebConstants;
import com.ust.userwebapp.persistence.common.interfaces.INameableEntity;
import com.ust.userwebapp.persistence.model.Privilege;
import com.ust.userwebapp.services.common.BaseService;
import com.ust.userwebapp.web.common.event.MultipleResourcesRetrievedEvent;
import com.ust.userwebapp.web.common.event.PaginatedResultsRetrievedEvent;
import com.ust.userwebapp.web.common.event.SingleResourceRetrievedEvent;
import com.ust.userwebapp.web.dto.IDto;
import com.ust.userwebapp.web.exception.UserWebAppResourceNotFoundException;
import com.ust.userwebapp.web.mvc.util.RestPreconditions;


public abstract class AbstractReadOnlyController<D extends IDto, E extends INameableEntity> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<D> clazz;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    public AbstractReadOnlyController(final Class<D> clazzToSet) {
        super();

        Preconditions.checkNotNull(clazzToSet);
        clazz = clazzToSet;
    }


    public List<E> searchAllInternal(@RequestParam(QueryConstants.Q_PARAM) final String queryString) {
        return getService().searchAll(queryString);
    }

    public List<E> searchAllPaginatedInternal(@RequestParam(QueryConstants.Q_PARAM) final String queryString, final int page, final int size) {
        return getService().searchPaginated(queryString, page, size);
    }

    // find - one

    protected final E findOneInternal(final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final E resource = findOneInternal(id);
        eventPublisher.publishEvent(new SingleResourceRetrievedEvent<D>(clazz, uriBuilder, response));
        return resource;
    }

    protected final E findOneInternal(final Long id) {
        return RestPreconditions.checkNotNull(getService().findOne(id));
    }

    // find - all

    protected final List<E> findAllInternal(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new UserWebAppResourceNotFoundException();
        }
        eventPublisher.publishEvent(new MultipleResourcesRetrievedEvent<D>(clazz, uriBuilder, response));
       
        return getService().findAll();
    }

    protected final void findAllRedirectToPagination(final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final String resourceName = clazz.getSimpleName().toString().toLowerCase();
        final String locationValue = uriBuilder.path(WebConstants.PATH_SEP + resourceName).build().encode().toUriString() + QueryConstants.QUESTIONMARK + "page=0&size=10";

        response.setHeader(HttpHeaders.LOCATION, locationValue);
    }

    protected final List<E> findPaginatedAndSortedInternal(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<E> resultPage = getService().findAllPaginatedAndSortedRow(page, size, sortBy, sortOrder);
        if (page > resultPage.getTotalPages()) {
            throw new UserWebAppResourceNotFoundException();
        }
      
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<D>(clazz, uriBuilder, response, page, resultPage.getTotalPages(), size));
        return Lists.newArrayList(resultPage.getContent());
    }

    protected final List<E> findPaginatedInternal(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<E> resultPage = getService().findAllPaginatedAndSortedRow(page, size, sortBy, sortOrder);
        if (page > resultPage.getTotalPages()) {
            throw new UserWebAppResourceNotFoundException();
        }
      
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<D>(clazz, uriBuilder, response, page, resultPage.getTotalPages(), size));
        return Lists.newArrayList(resultPage.getContent());
    }

    protected final List<E> findAllSortedInternal(final String sortBy, final String sortOrder) {
        final List<E> resultPage = getService().findAllSorted(sortBy, sortOrder);
        return resultPage;
    }

    // count

    protected final long countInternal() {
     
        return getService().count();
    }

    // generic REST operations

    // count

    /**
     * Counts all {@link Privilege} resources in the system
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long count() {
        return countInternal();
    }

    // template method

    protected abstract BaseService<E> getService();

}
