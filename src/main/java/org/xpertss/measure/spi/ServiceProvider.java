package org.xpertss.measure.spi;

import org.xpertss.measure.Dimension;

import java.util.List;

// TODO
public abstract class ServiceProvider {



    public static ServiceProvider current()
    {
        return null;
    }

    public static List<ServiceProvider> available()
    {
        return null;
    }

    public static void setCurrent(ServiceProvider provider)
    {
        // TODO Figure a better way to do this via dependency injection or something
    }


    public FormatService getFormatService()
    {
        return null;
    }

    public QuantityFactory getQuantityFactory(Dimension d)
    {
        return null;
    }

}
