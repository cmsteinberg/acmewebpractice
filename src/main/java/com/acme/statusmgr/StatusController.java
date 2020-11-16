package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach
 *
 *
 *
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();


    
    @RequestMapping("/status")
    public BasicServerStatus serverStatusRequest(@RequestParam(value="name", defaultValue="Anonymous")
                                 String name,
                                                 @RequestParam(required = false) List<String> details)
    {
        System.out.println("*** DEBUG INFO *** " + details);
        return new BasicServerStatus(counter.incrementAndGet(),
                            String.format(template, (name)));
    }

    @RequestMapping("/status/detailed")
    public ServerStatus detailedStatusRequest(@RequestParam(value="name", defaultValue="Anonymous")
                                                String name,
                                              @RequestParam(required = false) List<String> details)

    {
        BasicServerStatus basicStatus = new BasicServerStatus(counter.incrementAndGet(),
                String.format(template, (name)));
        return decoratedStatus(basicStatus, details);
    }

    /**
     * Passes the fully ready ServerStatus so that it can be returned to Spring
     * @param basicStatus
     * @param details
     * @return
     */
    private ServerStatus decoratedStatus(ServerStatus basicStatus, List<String> details){
        checkForNoDetails(details);
        return assignDecorators(basicStatus, details);
    }

    /**
     * Checks if details is null or empty
     * @param details
     * throws an exception if it is either
     */
    private void checkForNoDetails(List<String> details){

        if(details == null || details.size() == 0)
        {
            throw new NoDetailsException();
        }
    }

    /**
     * Assigns the correct decorators based off inputs
     * @param basicStatus
     * @param details
     * @return the fully decorated ServerStatus
     * Loops through details and assigns a decorator for each valid input.
     * Inputs could be used more than once
     * Throws an exception if anything other than Operations, Memory,
     * or Extensions gets passed in
     */
    private ServerStatus assignDecorators(ServerStatus basicStatus, List<String> details) {
        for (String detail : details) {
            if (detail.equalsIgnoreCase("Memory")) {
                basicStatus = new MemoryStatus(basicStatus);
            } else if (detail.equalsIgnoreCase("Operations")) {
                basicStatus = new OperationalStatus(basicStatus);
            } else if (detail.equalsIgnoreCase("Extensions")) {
                basicStatus = new ExtensionsStatus(basicStatus);
            } else {
                throw new BadDetailsArgumentsException();
            }
        }
        return basicStatus;
    }

}
