# 2048 peli 
2048 peli on yksinpelattava pulmanratkaisu peli, jossa liikutellaan numeroituja lattooja ja kun kaksi 
saman arvoista laattaa liikutetaan samaan suuntaan ne yhdisyy ja saa arvokseen niiden summan. 
 Pelilauta on 4*4 jossa on alussa satunnaiset 2 laattaa pistearvoilla 2. Pelissä voi liikuttaa 
  jokaista laattaa ylös, alas ja sivuille. Jokainen siirto lisää laudalle uuden laatan.
  Peli päättyy joko voittoon kun saavutetaan pistearvo 2048 tai häviöön jos siirtoja ei voida enää tehdä.
  

# Määrittely
Tämän projektin tehtävänä on selvittää miten 2048 peli saataisiin ratkaistua algoritmisesti. 
Työssä vertaillaan vähintään kahta eri algoritmia, joista toinen on algoritmi, joka tekee satunnaisia 
päätöksiä ja toinen on monte carlon pelipuu. Tuloksia vertaillaan 10 pelin otannalla vertailemalla 
tuloksia algoritmien ja ihmispelaajan kesken. Tuloksissa merkitsevin on korkein pistemäärä, jolla voidaan 
tehdä johtopäätöksiä algoritmin soveltuvuudesta pelin ratkaisemiseen. Testauksessa mitataan algoritmin
käyttämä aika, joka voi olla esteenä algoritmin käyttämiselle.  

# Aika ja tilavaatimukset
Monte carlon algoritkin aikavaatimus O(mkI), jossa `m = ajojen lukumäärä`, `k = läpikäytyjen polkujen lukumäärä`. 
Algoritmissa ajetaan m-kappaletta peliä ja valitaan näistä todennäköisesti parhaaseen lopputulokseen päässyt reitti.

Tilavaatimus on O(m), koska algorimi luo jokaisella iteraatiolla uuden pelin ja tallentaa tuloksen muistiin.   
# Lähteet
http://stanford.edu/~rezab/classes/cme323/S15/projects/montecarlo_search_tree_report.pdf
https://towardsdatascience.com/2048-solving-2048-with-monte-carlo-tree-search-ai-2dbe76894bab
  
