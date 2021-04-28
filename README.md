# ProjectePandemiaEnricLluis

Esquema principal del joc  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/esquema.png)  
El joc es divideix en 7 opcions donades per un menú.  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/1.png)  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/2.png)  
<span style="color:blue">*menú del joc, amb restriccions.*</span>  

Opció 1 -> Crear un taulell amb dimensions donades per l'usuari però amb malalts a cada posició aleatoris.  

Opció 2 -> Crear un taulell buit amb dimensions donades per l'usuari.  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/3.png)  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/4.png)  
*opció 2 del menú, amb restriccions.*  
Opció 3 -> Introduir malalts a les posicions que l'usuari desitji.  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/5.png)  
*opció 3 del menú, amb restriccions.*  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/6.png)  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/7.png)  
*afegir posicions bloquejades al taulell.*  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/8.png)  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/9.png)  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/10.png)  
*testeig a l'hora de posar malalts en una posició bloquejada i la versió correcte.*  
Opció 4 -> Transmetre el virus a totes les posicions del taulell mitjançant un valor float que dona l'usuari.  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/15.png)  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/16.png)  
*opció 4 del menú, amb un RT del 0.5%.*  
Opció 5 -> Curar malalts. Diferents opcions per curar als malalts que estan actualment al taulell.  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/17.png)  
*opció 5 del menú, amb un 5% de taxa de curació.*  
Opció 6 -> Desplaçar malalts. En aquesta opció podem desplaçar diferents malalts d'una posició a una altra mitjançant un petit menú de direccions.  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/11.png)  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/12.png)  
*opció 6 del menú, desplaçant malalts a dalt.*  
Opció 7 -> Mostrar informació. En aquesta opció es pot mostrar la situació actual del taulell amb diferents dades i podem veure el taulell actual.  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/13.png)  
*opció 7 del menú, mostrant tota la informació i printat del taulell.*  
![Alt text](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/imatges/14.png)  
*opció 0 del menú, per a sortir del joc.*  

Explicació de les decisions de disseny preses  

Hem decidit utilitzar diferents colors per al printat.  

Principalment hem utilitzat el vermell pels missatges d'error, el groc pels missatges d'advertencia i hem optat pel lila per als missatges més estandars.  

El menú té un printat diferent amb asteriscs al voltant.  


Explicació de les distribuicions de funcions  

Hem optat per treballar amb els diferents fitxers de classe (BoardManager, Board, Interficie, Utils, Game), per tal de tenir ben repartides les funcions i que no sigui complicat trobar-les o que no ens poguem perdre pel programa.  

Al fitxer de classe BoardManager és on més funcions em utilitzat ja que és el fitxer on hem treballat principalment, fent que totes les opcions possibles del menú
estiguin en aquest fitxer.  


Al fitxer Board tenim la creació dels dos taulells i l'opció de printar-los. També estan els getters i els setters de les respectives variables (Files, columnes, etc.)  


Al fitxer Interficie tenim les funcions per printar en diferents colors els diferents missatges del programa i les diferents opcions per printar el menú,
els missatges normals i els missatges de capçalera.  


Al fitxer Utils tenims les funcions de validacio del programa. Tenim una funció de validació d'Enters amb diferents opcions que s'ajusten a les necessaries
per validar totes les entrades del usuari que són Enters. També tenim una funció de validació de Floats i una funció de la validació per les persones que es
mouen pel taulell.  


Finalment el fitxer de classe Game és el fitxer des d'on executarem el programa i es posaran en funció totes les funcions creades als altres fitxers de classe.  


**Link javadoc**  

[JavaDoc](https://github.com/ltrincado/ProjectePantemiaEnricLluis/blob/master/src/prjuf2/docs)  



