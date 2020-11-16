package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * Decorator class to decorate basicServerStatus
 * Adds memory information
 */
public class MemoryStatus extends ServerStatusDecorator{

    public MemoryStatus(ServerStatus status) {
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
        return super.getStatusDesc() + getMemoryStatus();
    }
    private String getMemoryStatus() {
        return ", and its memory is " + ServerManager.getCurrentMemoryStatus();
    }
}
