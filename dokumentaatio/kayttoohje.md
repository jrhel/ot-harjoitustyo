# Käyttöohje

Lataa tiedosto [otRecipeDatabase.jar](https://github.com/jrhel/ot-harjoitustyo/releases)


## Konfigurointi

Ohjelma luo tarvittavat tiedostot.


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar otRecipeDatabase-1.0-SNAPSHOT.jar
```


## Sovelluksen käyttöliittymä

Sovelluksen käyttöliittymä on englanniksi.


### Aloitussivu

Sovellus käynnistyy aloitussivulla, jonka vasemmassa laidassa on valikko, jonka oikealla puolella on lista sovelluksessa tellennetuista resepteistä. Alla olevassa esimerkissä on tallennettuna viisi reseptiä: "Bechamel sauce", "Lasagna", "Blueberry pie", "Mushroom risotto", & "Ragu". Jokaista reseptiä pääsee lukemaan painamalla sitä tästä listalta.

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Alustettu%20aloitusnakyma.jpg">

Vasemmalla laidalla olevan valikon ylemmässä rivissä on "**add a new recipe**"-nappi jonka painamalla pääsee lisäämään uuden reseptin. Tämä avaa lomakkeen, jota selitetään tarkemmin tämän käyttöohjeen seuraavassa osassa. 

Valikosta löytyy vielä napit "**by name :**", "**by ingredients :**", & "**Reset recipe book**". 

Ensimmäisen, eli "**by name :**", alla on heti textikenttä. Painamalla "**by name :**"-nappia listaa sovellus kaikki tallennetut reseptit jotka sisältävät sen textikentän painaushetkellä sisältämä merkkijono. Esim. tekstikentän syötteellä "*ag*" saisi nappia "**by name :**" painamalla avattua sivun jossa olisi yllä olevasta kuvasta vain "Las*ag*na" ja "R*ag*u" listattuna.

Toisen napin, eli "**by ingredients :**", alla on useampi textikenttä. Painamalla "**by ingredients :**"-nappia on tarkoitus pystyä listamaan kaikki reseptit johon kuuluu tekstikenttiin syötettyjen merkkijonojen vastaavat ainekset. Esim. jos "**by ingredients :**"-napin allaolevista tekstikentistä on yhteen syötetty "jauhelihaa" ja toiseen "tomaatti", ja nämä kuuluvat tellennetuista resepteistä vain Lasagnan ja Ragun tekoon, niin pitäisi avata sivun jossa nämä kahdet reseptit ovat listattuna. Tämä toiminto ei kuitenkaan toimi vielä.

Viimeinen nappi, eli "**Reset recipe book**", poistaa kaikki tallennetut reseptit ja asettaa sovelluksen alkutilaansa. (Tämä on hyödyllistä sovelluksen testammisessa, tai muuten vaan.) Jotta tämä oikeasti tapahtuisi, joutuu kuitenkin vahvistamaan valintansa, eli nappi ei tee sitä suoraan. Ainoastaan painamalla vielä seuraavaksi "**Yes**" restoidaan sovellus. Alkutilassa, eli kun sovelluksen käynnistää ensimmäisen kerran, avaussivu näyttää tältä:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Tyhja%20avausnakyma.jpg">


### Uuden reseptin lisääminen

Uuden reseptin voi siis lisätä painamalla "**add a new recipe**"-nappia sovelluksen aloitussivulta, jolloin avautuu seuraava sivu/lomake:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/New%20recipeForm.png">

Esitetyn sivun ylempään tekstikenttään on tarkoitus syöttää reseptin nimi. Tämän alla on oikealla laidalla teksti alue johon voi syöttää vapaamuotoinen kuvaus reseptistä, ohje sen tekemiseen, tai mitä ikinäkin jota toivoo pystyä lukemaan reseptiä luetessa tulevaisuudessa. Tekstialueen alla on vielä tekstikenttä johon voi lisätä reseptin lähde, jos haluaa. Oikealla laidalla on taas kaksi saraketta tekstialueita. Näistä vasempiin on tarkoitus syöttää reseptiin tarvittavat ainekset ja oikeisiin, vasemmalla olevaan tekstikentään syötetyn aineksen, määrä. Alla on sivu/lomake täytetty tarvittavila tiedoilla jotta reseptin voisi tallentaa:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/roux%20recipe.jpg">

Reseptin saa viimein tallennettua painamalla oikeassa alakulmassa olevaa "**Save recipe**"-nappia. Mikäli samannimistä reseptiä on jo tallennettu, estää sovellus tallentamasta toisen samannimisen reseptin. Tällöin joutuu muokkaaman toisen reseptin nimeä, (ellei halua  poistaa ensimmäisen reseptin tai muokata sen nimeä).

### Reseptien selaaminen, avaaminen, & lukeminen

Tallennettuja reseptejä voi selata joko aloitussivun reseptilistalla, tai reseptihaun tuloksilla. Kaikki näissä esiintyvät reseptit voidaan avata lukemista varten yksinkertaisesti painamalla reseptin nimeä listalta. Mikäli resepti on ainesosana toisessa reseptissä voi ainesosan resepti avata samoin, yksinkertaisesti painamalla sitä toisen reseptin aineslistalta. Alla esimerkki reseptistä bechamel-kastikkeen valmistamiseen, josta voi avata reseptin rouxin valmistukseen:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/bechamel.jpg">

Aloitussivun reseptilista päivittyy reaaliaikaisesti kun reseptejä lisätään ja positetaan.


### Reseptien poistaminen ja muokkaaminen

Reseptin voi poistaa sen omalta sivulta, eli kun reseptin on ensin saatu avattua. Reseptin oikeasta alakulmasta löytyy nappi "**Delete recipe**" jonka painamalla avattu resepti poistetaan sovelluksesta. Nappi näkyy yllä olevasta kuvasta.

Reseptejä voi muokata lisäämällä uuden reseptin ja poistamalla vanhan. Helpompi tapa on suunnitteilla muttei ole toteutettu vielä, koska se ei ole sovelluksen käytölle kriittinen ominaisuus.

