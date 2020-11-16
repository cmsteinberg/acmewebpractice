package com.acme.statusmgr.beans;
/**
 * Abstract base class that all Decorators of basicServerStatus extend
 */
public abstract class ServerStatusDecorator implements ServerStatus{

    private ServerStatus status;

    public ServerStatusDecorator(ServerStatus status){
        this.status = status;
    }

    @Override
    public long getId() {
        return status.getId();
    }

    @Override
    public String getContentHeader() {
        return status.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        return status.getStatusDesc();
    }
}
