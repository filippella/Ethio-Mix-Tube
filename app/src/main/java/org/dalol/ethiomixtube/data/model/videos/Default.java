package org.dalol.ethiomixtube.data.model.videos;

/**
 * Created by filippo on 05/11/2017.
 */

public class Default {

    private String height;

    private String width;

    private String url;

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "Default [height = "+height+", width = "+width+", url = "+url+"]";
    }
}
