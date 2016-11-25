package com.ust.userwebapp.web.event.listner.common;

import static com.ust.userwebapp.common.util.LinkUtil.REL_FIRST;
import static com.ust.userwebapp.common.util.LinkUtil.REL_LAST;
import static com.ust.userwebapp.common.util.LinkUtil.REL_NEXT;
import static com.ust.userwebapp.common.util.LinkUtil.REL_PREV;
import static com.ust.userwebapp.common.util.LinkUtil.createLinkHeader;
import static com.ust.userwebapp.common.util.WebConstants.PATH_SEP;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.ust.userwebapp.common.util.IUriMapper;
import com.ust.userwebapp.common.util.LinkUtil;
import com.ust.userwebapp.setup.UserWebApplicationContextInitializer;
import com.ust.userwebapp.web.common.event.PaginatedResultsRetrievedEvent;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
class PaginatedResultsRetrievedDiscoverabilityListener implements ApplicationListener<PaginatedResultsRetrievedEvent> {
	  private final Logger logger = LoggerFactory.getLogger(UserWebApplicationContextInitializer.class);
    private static final String PAGE = "page";

    @Autowired
    private IUriMapper uriMapper;

    public PaginatedResultsRetrievedDiscoverabilityListener() {
        super();
    }

    // API

    @Override
    public final void onApplicationEvent(final PaginatedResultsRetrievedEvent ev) {
    	logger.info("******************inside PaginatedResultsRetrievedDiscoverabilityListener*********************");
        Preconditions.checkNotNull(ev);

        addLinkHeaderOnPagedResourceRetrieval(ev.getUriBuilder(), ev.getResponse(), ev.getClazz(), ev.getPage(), ev.getTotalPages(), ev.getPageSize());
    }

    //

    // - note: at this point, the URI is transformed into plural (added `s`) in a hardcoded way - this will change in the future
    final void addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final Class clazz, final int page, final int totalPages, final int pageSize) {
        plural(uriBuilder, clazz);

        final StringBuilder linkHeader = new StringBuilder();
        if (hasNextPage(page, totalPages)) {
            final String uriForNextPage = constructNextPageUri(uriBuilder, page, pageSize);
            linkHeader.append(createLinkHeader(uriForNextPage, REL_NEXT));
        }
        if (hasPreviousPage(page)) {
            final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForPrevPage, REL_PREV));
        }
        if (hasFirstPage(page)) {
            final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForFirstPage, REL_FIRST));
        }
        if (hasLastPage(page, totalPages)) {
            final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForLastPage, REL_LAST));
        }

        if (linkHeader.length() > 0) {
            response.addHeader(HttpHeaders.LINK, linkHeader.toString());
        }
    }

    final String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page + 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page - 1).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, 0).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, totalPages).replaceQueryParam("size", size).build().encode().toUriString();
    }

    final boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1;
    }

    final boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    final boolean hasFirstPage(final int page) {
        return hasPreviousPage(page);
    }

    final boolean hasLastPage(final int page, final int totalPages) {
        return totalPages > 1 && hasNextPage(page, totalPages);
    }

    final void appendCommaIfNecessary(final StringBuilder linkHeader) {
        if (linkHeader.length() > 0) {
            linkHeader.append(", ");
        }
    }

    // template

    protected void plural(final UriComponentsBuilder uriBuilder, final Class clazz) {
        final String resourceName = uriMapper.getUriBase(clazz);
        uriBuilder.path(PATH_SEP + resourceName);
    }

}
