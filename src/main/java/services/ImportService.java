package services;

import java.io.File;
import java.util.ArrayList;

public interface ImportService {

      ArrayList extractData(File[] files);

      void storeDataInDB(ArrayList list);

      ArrayList importDataFromDB();

      void deleteTableFromDB();
}
