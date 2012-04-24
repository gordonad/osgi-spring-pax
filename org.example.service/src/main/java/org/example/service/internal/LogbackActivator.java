package org.example.service.internal;

import org.osgi.framework.*;
import org.osgi.service.log.LogReaderService;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Register a LogbackAdaptor as a LogListener, and
 * listens to service event, in case some LogReaderService are started or stopped.
 * <p/>
 * It registers the BacklogAdaptor to all the LogReaderService available on the
 * OSGi server.
 */
public class LogbackActivator implements BundleActivator, ServiceListener {
    private LogbackAdaptor logbackAdaptor = new LogbackAdaptor();
    private LinkedList<LogReaderService> logReaderServices = new LinkedList<LogReaderService>();
    private BundleContext bundleContext;

    public void start(BundleContext context) throws Exception {
        this.bundleContext = context;

        // Register this class as listener to service changes
        String filter = "(objectclass=" + LogReaderService.class.getName() + ")";
        try {
            context.addServiceListener(this, filter);
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }

        // Register LogbackAdaptor to available LogReaderServices
        ServiceReference[] serviceReferences = context.getServiceReferences(LogReaderService.class.getName(), null);
        if (serviceReferences != null) {
            for (ServiceReference serviceReference : serviceReferences) {
                LogReaderService service = (LogReaderService) context.getService(serviceReference);
                if (service != null) {
                    logReaderServices.add(service);
                    service.addLogListener(logbackAdaptor);
                }
            }
        }
    }

    public void stop(BundleContext context) throws Exception {
        for (Iterator<LogReaderService> i = logReaderServices.iterator(); i.hasNext(); ) {
            LogReaderService lrs = i.next();
            lrs.removeLogListener(logbackAdaptor);
            i.remove();
        }
    }

    //  ServiceListener to dynamically track the LogReaderServices registered or unregistered
    public void serviceChanged(ServiceEvent event) {
        LogReaderService lrs = (LogReaderService) bundleContext.getService(event.getServiceReference());
        if (lrs != null) {
            if (event.getType() == ServiceEvent.REGISTERED) {
                logReaderServices.add(lrs);
                lrs.addLogListener(logbackAdaptor);
            } else if (event.getType() == ServiceEvent.UNREGISTERING) {
                lrs.removeLogListener(logbackAdaptor);
                logReaderServices.remove(lrs);
            }
        }
    }
}
