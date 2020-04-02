# Määrittely
Tämän projektin tehtävänä on selvittää miten 2048 peli saataisiin ratkaistua algoritmisesti. 
Työssä vertaillaan vähintään kahta eri algoritmia, joista toinen on algoritmi, joka tekee satunnaisia 
päätöksiä ja toinen on monte carlon pelipuu. 
# Ohjelman syötteet
Ohjelmalle voidaan antaa main -metodissa käytettävä luokka, mitä käytetään pelin ratkaisemiseen. Tämä mahdollistaa
pelille erilaisten algoritmien tekemisen.
# Aika ja tilavaatimukset
Monte carlon algoritkin aikavaatimus O(mkI), jossa `m = ajojen lukumäärä`, `k = läpikäytyjen polkujen lukumäärä`. 
Algoritmissa ajetaan m-kappaletta peliä ja valitaan näistä todennäköisesti parhaaseen lopputulokseen päässyt reitti.

Tilavaatimus on O(m), koska algorimi luo jokaisella iteraatiolla uuden pelin ja tallentaa tuloksen muistiin.   
# Lähteet
http://stanford.edu/~rezab/classes/cme323/S15/projects/montecarlo_search_tree_report.pdf
https://towardsdatascience.com/2048-solving-2048-with-monte-carlo-tree-search-ai-2dbe76894bab
  
