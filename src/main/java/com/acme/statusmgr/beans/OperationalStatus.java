package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;
/**
 * Decorator class to decorate basicServerStatus
 * Adds operational status
 */
public class OperationalStatus extends ServerStatusDecorator{

    public OperationalStatus(ServerStatus status) {
        super(status);
    }
    @Override
    public long getId() {
        return super.getId();
    }
    @Override
    public String getContentHeader() {
        return super.getContentHeader();
    }
    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + getOperationalStatus();
    }
    private String getOperationalStatus() {
        return ", and is " + ServerManager.getCurrentOperationalStatus();
    }
}
