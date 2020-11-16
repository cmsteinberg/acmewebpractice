package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;
/**
 * Decorator class to decorate basicServerStatus
 * Adds the extension names
 */
public class ExtensionsStatus extends ServerStatusDecorator{

    public ExtensionsStatus(ServerStatus status) {
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
        return super.getStatusDesc() + getExtensionsStatus();
    }
    private String getExtensionsStatus() {
        return ", and is using these extensions - " + ServerManager.getCurrentExtensionNames();
    }
}
