package com.applications.Multi_Tenant.tenantmangement;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TenantContext {

    private TenantContext() {}

    private static final InheritableThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    public static void setTenantId(String tenantId) {
        log.info("Setting tenantId to:: {}", tenantId);
        currentTenant.set(tenantId);
    }

    public static String getTenantId() {
        String tenantId = currentTenant.get();

        if (StringUtils.isBlank(tenantId)) {
            log.warn("Tenant Id is not set, setting it to public");
            tenantId = "public";
        }
        log.info("Getting Tenant Id:: {}", tenantId);
        return tenantId;
    }

    public static void clear(){
        currentTenant.remove();
    }


}
