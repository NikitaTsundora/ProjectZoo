1. Generics
   Wo helfen Ihnen die Generics im Zoo-Szenario, Fehler bereits zur Compile-Zeit zu vermeiden?
   •	Verhindern, dass ein Gehege Tiere falscher Art enthält
   •	Typgrenzen (T extends Animal) verhindern, dass Nicht Tiere in Gehege gelangen
   •	Spezialisierte Gehege (Aquarium extends Enclosure<Fish>) sind statisch sicher
   •	Der Compiler verhindert falsche Kombinationen bevor der Code ausgeführt wird
   Nennen Sie ein Beispiel aus Ihrer Implementierung, bei dem falsche Tier-Gehege-Kombinationen durch den Typchecker verhindert werden.
   •	MammalHouse extends Enclosure<Mammal>
   o	verhindert, dass z.B. ein Sunfish (Fisch) hinzugefügt wird
   o	mammalHouse.add(new Sunfish("Mond")); führt zu Compile Fehler
   •	CatHouse extends Enclosure<Lykoi>
   o	nur Lykoi erlaubt
   o	catHouse.add(new Shoebill("Dumbo")); ist Compile Fehler
2. Logging
   Warum ist systematisches Logging mit einem Logger und Log-Leveln für ein Zoo-Management-System sinnvoller als Ausgaben mit IO.println?
   •	Es gibt ein Log-Level, welches die Wichtigkeit einer Software-Meldung klassifiziert
   •	Logs können gefiltert, umgeleitet, formatiert werden
   •	println ist immer gleich wichtig, also hat keine Priorisierung
   •	Logger kann in Produktion auf Datei, Konsole, Netzwerk schreiben
   •	Ermöglicht Debugging, Monitoring, Fehleranalyse
   •	println ist nicht abschaltbar, dafür Logger schon
   In welchen Situationen würden Sie in Ihrem System die Log-Level INFO, WARNING und ggf. SEVERE verwenden?
   INFO
   •	Start einer Methode
   •	normale Aktionen: Tiere holen, Gehege hinzufügen
   •	Zusammenfassungen, Statusmeldungen
   WARNING
   •	Gehege nicht gefunden
   •	Tier nicht gefunden
   •	ungültige Eingaben, aber System läuft weiter
   SEVERE
   •	kritische Fehler
   •	Inkonsistenzen (z.B. null Gehege)
   •	Zoo Daten beschädigt
3. Streams
   Wo haben Ihnen Streams im Vergleich zu klassischen Schleifen beim Formulieren Ihrer Abfragen geholfen? Wo wurde es eher unübersichtlich?
   •	Kürzere, klarere Abfragen
   •	flatMap für „alle Tiere aus allen Gehegen“
   •	filter für Tierarten (z.B. nur Mammals)
   •	map für Typkonvertierung 
   •	collect(groupingBy) für Typ Zählung
   •	Bei Wildcards (? extends Animal)
   •	Bei groupingBy mit Generics
