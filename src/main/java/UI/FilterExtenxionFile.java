package UI;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FilterExtenxionFile extends FileFilter {

    @Override
    public boolean accept(File f) {

        return f.isDirectory() || f.getName().endsWith("xml") || f.getName().endsWith("XML");
    }

    @Override
    public String getDescription() {
        return "XML File only";
    }
}
