package UI;

import Services.FilterExtenxionFile;

import javax.swing.*;
import java.io.File;

public class View {

    public static File[] importDirectoryWithSwing() {

        JFileChooser directory = new JFileChooser();
        directory.setMultiSelectionEnabled(true);     // erlaubt mehrfachige Auswahl
        directory.addChoosableFileFilter(new FilterExtenxionFile());  //erstellt Filter für Erweiterungen
        directory.showDialog(directory, "Select");

        // Datei holen
        File[] xml_files = directory.getSelectedFiles();

        return xml_files;
    }
}
