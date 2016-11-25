package com.ust.userwebapp.web.event.listner.common;



import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.ust.userwebapp.common.util.IUriMapper;
import com.ust.userwebapp.common.util.LinkUtil;
import com.ust.userwebapp.common.util.WebConstants;
import com.ust.userwebapp.web.common.event.MultipleResourcesRetrievedEvent;

@SuppressWarnings("rawtypes")
@Component
final class MultipleResourcesRetrievedDiscoverabilityListener implements ApplicationListener<MultipleResourcesRetrievedEvent> {

    @Autowired
    private IUriMapper uriMapper;

    public MultipleResourcesRetrievedDiscoverabilityListener() {
        super();
    }

    //

    @Override
    public final void onApplicationEvent(final MultipleResourcesRetrievedEvent ev) {
        Preconditions.checkNotNull(ev);

        discoverOtherRetrievalOperations(ev.getUriBuilder(), ev.getResponse(), ev.getClazz());
    }

    @SuppressWarnings("unchecked")
    final void discoverOtherRetrievalOperations(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final Class clazz) {
        final String uriForResourceCreation = uriBuilder.path(WebConstants.PATH_SEP + uriMapper.getUriBase(clazz) + "/q=name=something").build().encode().toUriString();

        final String linkHeaderValue = LinkUtil.createLinkHeader(uriForResourceCreation, LinkUtil.REL_COLLECTION);
        response.addHeader(HttpHeaders.LINK, linkHeaderValue);
    }

}
