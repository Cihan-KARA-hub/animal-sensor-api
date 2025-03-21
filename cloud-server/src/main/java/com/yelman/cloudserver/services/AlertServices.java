package com.yelman.cloudserver.services;

import com.yelman.cloudserver.services.impl.AlertsImpl;

public class AlertServices implements AlertsImpl {
    @Override
    public boolean postEmail(String email) {
        return false;
    }
}
