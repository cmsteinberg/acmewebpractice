package com.acme.statusmgr.beans;

/**
 * Supertype interface that the decorators and base implementation all share
 */
public interface ServerStatus {
    long getId();
    String getContentHeader();
    String getStatusDesc();
}
