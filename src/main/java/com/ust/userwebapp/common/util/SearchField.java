package com.ust.userwebapp.common.util;

public enum SearchField {
	id, name, // common
    uuid, // for Tenant only
    loginName, email, tenant, locked, // for User only
    description // for Privilege only
}
