# Aufgabe: Serienanschreiben-Generator für ein Automobilhaus

## Szenario
Ein Autohaus möchte in unregelmäßigen Abständen seine Kunden über die aktuelle Modelliste informieren. Die Kunden werden direkt per Post angeschrieben (kein E-Mail-Versand, da viele Kunden ein bestimmtes Alter überschritten haben). Der Kundenstamm wächst kontinuierlich und muss stetig erweitert werden. Die Fahrzeugdaten selbst stammen aus der Regionalzentrale des Autohauses und werden in unregelmäßigen Abständen im XML-Format geliefert. Bisher wurden die Anschreiben „von Hand“ erzeugt, den Sachbearbeitern ist das aber viel zu anstrengend, daher wird eine Softwarelösung gesucht für dieses Problem.

## Zielgruppe
* Potenzielle Kandidat:Innen ohne Java-Vorkenntnisse
* Kolleg:Innen, die sich in Java-Entwicklung einarbeiten möchten
* Absolvent:Innen ohne Java-Kenntnisse

## Technologien
Java SE (kein JEE, kein Spring Framework o.ä.), eine geeignete relationale DB (h2), ggf. Maven, Bibliotheken zum einlesen und verarbeiten von XML-Dateien sowie zum Erzeugen von PDF-Dokumenten.

### Anforderungen
Die Software soll folgende Anforderungen erfüllen:
* Import von Fahrzeugen(Einlesen von XML-Dateien mit Fahrzeugdaten aus einem Verzeichnis, auf welches die Anwendung Zugriff hat. Bonus: Konfigurierbarer Verzeichnispfad)
* Import von Kunden(Einlesen von XML-Dateien mit Kundendaten aus einem Verzeichnis, auf welches die Anwendung Zugriff hat. Bonus: Konfigurierbarer Verzeichnispfad)
* Bereitstellung CRUD-Funktionalität zum Anlegen, Lesen, Ändern und Löschen von Kundendatensätzen und Fahrzeugdatensätzen bereitstellen.
* Erzeugen von Anschreiben im PDF-Format (die erzeugten Anschreiben werden in ein lok

Die Daten sollen in einer Datenbank persistiert werden. 

### Entitäten
Im Grunde existieren in dieser Anwendung folgende Entitäten mit mindestens folgenden Attributen:
- Fahrzeug
  -  Fahrzeugtyp (PKW, LKW, Motorrad)
  -  Fahrzeugbezeichnung
  -  Hersteller
  -  Leistung in KW
  -  Verkaufspreise

- Kunde
  - Nachname
  - Vorname
  - Anschrift

Aus diesen Entitäten ergeben sich die zu implementierenden Klassen. D


### Eingabedaten
Die Fahrzeugdaten werden als XML geliefert, das Format könnte wie folgt aussehen:

```
<Fahrzeug> 
  <Fahrzeugtyp>PKW</Fahrzeugtyp> 
  <Hersteller>Skoda</Hersteller> 
  ...
</Fahrzeug>
```

Die Kundendaten werden ebenfalls als XML geliefert, welches vergleichbar sein kann mit dem Format, mit welchem Fahrzeuge geliefert werden.

Die Daten sind durch die Bewerber:In zu erstellen.

### Ausgabedaten

Das Anschreiben muss nicht besonders fancy gestaltet sein. Oben links die Anschrift des Kunden, dann erfolgt Anrede und Name des Kunden, ein kurzer einführender Satz und anschließend die Liste der aktuellen Modelle, die den aktuellen Datenbankstand beinhaltet.

### Teile der Anwendung im Detail

#### Import
Aus einem Verzeichnis, in welche die zu importierende(n) XML-Datei(en) liegen werden eben diese von der Software ausgelesen. Nach dem einlesen der Daten werden diese in die Datenbank geschrieben. Es ist zu prüfen, ob die zu importierenden Datensätze (Fahrzeuge wie Kunden) bereits in der DB vorliegen. Wenn ja, muss kein Import erfolgen. Es macht daher ggf. auch sinn, die einzelnen Import-Ausführungen auch in der DB irgendwo zu speichern.

#### Export (Anschreibenerzeugung)
Der Export der in der DB persistierten Datensätze in Form von PDF-Anschreiben soll nur unter bestimmten Umständen erfolgen.
Ob man dies über eine, vom Import getrennte, zusätzliche Anwendung realisiert oder alles über eine Anwendung, die über Start-Argumente gesteuert wird, realisiert, ist freigestellt. Grundsätzlich ist es notwendig, die Daten aus der DB abzuholen, aufzubreiten und in ein PDF zu exportieren.



### Hinweise

Die Software soll nach den Prinzipien der Objektorientieren Softwareentwicklung implementiert werden:
* Abstraktion
* Hierachie
* Kapselung, Klassenbildung, Objekte, Vererbung

Es ist auf die Wiederverwendbarkeit von Klassen zu achten.
