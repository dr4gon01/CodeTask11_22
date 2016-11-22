package digitalturbine.com.codetest_01.model;

import java.util.List;

/**
 * Created by Nirmesh on 11/21/2016.
 */

public class AppsData
{
    private List<Ad> adObjectsList;

    public List<Ad> getAd ()
    {
        return adObjectsList;
    }

    public void setAd (List<Ad> adObjectsList)
    {
        this.adObjectsList = adObjectsList;
    }
}
