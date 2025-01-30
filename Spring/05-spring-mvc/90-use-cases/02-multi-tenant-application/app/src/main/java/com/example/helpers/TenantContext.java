package com.example.helpers;

import java.lang.ThreadLocal;
import java.lang.InheritableThreadLocal;

public class TenantContext {

    private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    static public String getCurrentTenantId() {
        return currentTenant.get();
    }

    static public void setCurrentTenantId(String tenant) {
        currentTenant.set(tenant);
    }

    static public void clear() {
        currentTenant.set(null);
    }
}
