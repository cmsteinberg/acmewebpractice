package com.acme.servermgr;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project.
 * Treat this as a 'black box' that gives back indicators like up, running etc for
 * various 'services' that are being managed.
 */
public class ServerManager {

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    static public String getCurrentServerStatus() {
        return "up";  // The server is up
    }

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    static public Boolean isOperatingNormally()
    {
        return true;
    }

    /**
     * Gets the Extension names
     * @return a string containing the extension names
     */

    public static String getCurrentExtensionNames() {
        return "[Hypervisor, Kubernetes, RAID-6]";
    }

    /**
     * Gets the memory status
     * @return a descriptive string about the memory status
     */
    public static String getCurrentMemoryStatus(){
        return "running low";
    }
    /**
     * Gets the operational status
     * @return a descriptive string about the operational status
     */
    public static String getCurrentOperationalStatus(){
        return "operating normally";
    }
}
