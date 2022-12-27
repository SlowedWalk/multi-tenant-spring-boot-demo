package com.hidetora.multitenant.interceptor;

public class TenantContext {
    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();
    public static String getCurrentTenant() {
        return currentTenant.get();
    }
    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }
    public static void clear() {
        currentTenant.remove();
    }

}
