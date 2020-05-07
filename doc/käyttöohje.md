# Käyttöohje

Projekti on toteutettu Java versiolla 8, mutta sen voi myös kääntää ja ajaa Java versiolla 11. Kääntämiseen suoritetaan 
maven komennoilla. 

Käännökseen tarvitaan
- Java 8+
- Maven 3.6.0+

# Kääntäminen
Projekti käännetään komennolla:
`mvn install`

Käännetty binääri näkyy `/target` hakemistossa. 

Testikattavuus löytyy tiedostota `/target/site/jacoco/index.html`

 
# Suorittaminen 
Projektin voi suorittaa komentoriviltä komennolla
`java -jar 2048-solver-0.0.1-SNAPSHOT.jar` target hakemistossa.

