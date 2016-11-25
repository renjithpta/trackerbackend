package com.ust.userwebapp.web.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

import com.ust.userwebapp.persistence.common.interfaces.INameableEntity;
import com.ust.userwebapp.persistence.model.Principal;
import com.ust.userwebapp.persistence.model.Role;

public class UserDto implements INameableDto,INameableEntity {
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    private String password;

    private Set<Role> roles;

    public UserDto() {
        super();
    }

    public UserDto(final String name, final String email, final String password, final Set<Role> roles) {
        super();

        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserDto(final Principal principal) {
        super();

        name = principal.getName();
        roles = principal.getRoles();
        email = principal.getEmail();
        id = principal.getId();
    }

    // API


    public Long getId() {
        return id;
    }


    public void setId(final Long idToSet) {
        id = idToSet;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameToSet) {
        name = nameToSet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String passwordToSet) {
        password = passwordToSet;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> rolesToSet) {
        roles = rolesToSet;
    }

    //

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserDto other = (UserDto) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).toString();
    }


}
