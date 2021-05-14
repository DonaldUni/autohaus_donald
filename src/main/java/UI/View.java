package UI;

import javax.swing.*;
import java.io.File;

public class View {

    // Anzeigt ein Fenster, um den Auswahlt von XML-Datei zu ermöglischen
    public static File[] importDirectoryWithSwing() {

        JFileChooser directory = new JFileChooser();
        directory.setMultiSelectionEnabled(true);     // erlaubt mehrfachige Auswahl
        directory.addChoosableFileFilter(new FilterExtenxionFile());  //erstellt Filter für Erweiterungen
        directory.showDialog(directory, "Select");

        // Datei holen und zuruckgeben
        return directory.getSelectedFiles();
    }
}
