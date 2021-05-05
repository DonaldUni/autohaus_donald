package Repository;

import java.io.File;
import java.util.ArrayList;

public interface ImportStrategie {

    public File[] importDirectoryWithSwing();

    //public  File importDirectoryWith();

    public  ArrayList extractClients(File[] files);

    public  ArrayList extractVehicles(File[] files);

    public  Boolean storeVehiclesInDatenbank(ArrayList arrayList);

    public  Boolean storeClientsInDatenbank(ArrayList arrayList);
}
