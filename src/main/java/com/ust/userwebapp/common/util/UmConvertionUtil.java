package  com.ust.userwebapp.common.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.jboss.jandex.Main;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ust.userwebapp.persistence.model.Privilege;
import com.ust.userwebapp.persistence.model.Role;

public final class UmConvertionUtil {

    private UmConvertionUtil() {
        throw new AssertionError();
    }

    // API

    public static Set<Privilege> convertRolesToPrivileges(final Iterable<Role> roles) {
        final Set<Privilege> privileges = Sets.<Privilege> newHashSet();
        for (final Role roleOfUser : roles) {
            privileges.addAll(roleOfUser.getPrivileges());
        }
        return privileges;
    }

    public static Collection<String> convertPrivilegesToPrivilegeNames(final Collection<Privilege> privileges) {
        final Function<Object, String> toStringFunction = Functions.toStringFunction();
        return Collections2.transform(privileges, toStringFunction);
    }

    public static Collection<String> convertRolesToPrivilegeNames(final Iterable<Role> roles) {
        final Set<Privilege> privileges = convertRolesToPrivileges(roles);
        return convertPrivilegesToPrivilegeNames(privileges);
    }
    
    public static void main(String[] args) {
		
    	Privilege pr  = new Privilege();
    	pr.setDescription("test");
    	pr.setId(1L);
    	pr.setName("Test");
    	
    	Role role  = new Role();
    	role.setId(1L);
    	role.setName("test role");
    	role.setPrivileges(Sets.newHashSet(pr));
    	
    	Role role1  = new Role();
    	role1.setId(2L);
    	role1.setName("test role1");
    	role1.setPrivileges(Sets.newHashSet(pr));
    	System.out.println(convertRolesToPrivilegeNames(Lists.newArrayList(role,role1)));
    	
	}

}
