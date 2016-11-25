package com.ust.userwebapp.mvc.web.controller;

import java.util.Collection;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.ust.userwebapp.persistence.model.Privilege;
import com.ust.userwebapp.persistence.model.Role;
import com.ust.userwebapp.web.dto.UserDto;
import com.ust.userwebapp.web.mvc.util.UmMappings;

/**
 * - note: this controller will start working with the User model and, if necessary, will move to a Authentication resource (which is the way it should work)
 */
@Controller
public class AuthenticationController {

    public AuthenticationController() {
        super();
    }

    // API
/*
    @RequestMapping(method = RequestMethod.GET, value = UmMappings.AUTHENTICATION)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto createAuthentication() {
        final Authentication auth = SpringSecurityUtil.getCurrentAuthentication();

        final Function<GrantedAuthority, Privilege> springAuthorityToPrivilegeFunction = new Function<GrantedAuthority, Privilege>() {
            @Override
            public final Privilege apply(final GrantedAuthority springAuthority) {
                return new Privilege(springAuthority.getAuthority());
            }
        };
        final Collection<Privilege> privileges = Collections2.transform(auth.getAuthorities(), springAuthorityToPrivilegeFunction);
        final Role defaultRole = new Role("defaultRole", Sets.<Privilege> newHashSet(privileges));

        final UserDto authenticationResource = new UserDto(auth.getName(), auth.getName(), (String) auth.getCredentials(), Sets.<Role> newHashSet(defaultRole));
        return authenticationResource;
    }*/

}
