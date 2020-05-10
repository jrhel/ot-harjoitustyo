# Käyttöohje

Lataa tiedosto [otRecipeDatabase.jar](https://github.com/jrhel/ot-harjoitustyo/releases)


## Konfigurointi & yleiset tiedot

Ohjelma luo tarvittavat tiedostot. Sovelluksen käyttöliittymä on englanniksi.


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar otRecipeDatabase-1.0-SNAPSHOT.jar
```


## Sovelluksen aloitussivu

Sovellus käynnistyy avausnäkymään, jonka vasemmassa laidassa on valikko, jonka oikealla puolella on lista sovelluksessa tellennetuista resepteistä. Alla olevassa esimerkissä on tallennettuna viisi reseptiä: "Bechamel sauce", "Lasagna", "Blueberry pie", "Mushroom risotto", & "Ragu". Jokaista reseptiä pääsee lukemaan painamalla sitä tästä listalta.

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Alustettu%20aloitusnakyma.jpg">

Vasemmalla laidalla olevan valikon ylemmässä rivissä on nappi jonka painamalla pääsee lisäämään uuden reseptin. Tämä avaa lomakkeen, jota selitetään tarkemmin tämän käyttöohjeen seuraavassa osassa. 

Valikosta löytyy vielä napit "**by name :**", "**by ingredients :**", & "**Reset recipe book**". 

Ensimmäisen, eli "**by name :**", alla on heti textikenttä. Painamalla "**by name :**"-nappia listaa sovellus kaikki tallennetut reseptit jotka sisältävät sen textikentän painaushetkellä sisältämä merkkijono. Esim. tekstikentän syötteellä "*ag*" saisi nappia "**by name :**" painamalla avattua uuden näkymän jossa olisi yllä olevasta kuvasta vain "Las*ag*na" ja "R*ag*u" listattuna.

Toisen napin, eli "**by ingredients :**", alla on useampi textikenttä. Painamalla "**by ingredients :**"-nappia on tarkoitus pystyä listamaan kaikki reseptit johon kuuluu tekstikenttiin syötettyjen merkkijonojen vastaavat ainekset. Esim. jos "**by ingredients :**"-napin allaolevista tekstikentistä on yhteen syötetty "jauhelihaa" ja toiseen "tomaatti", ja nämä kuuluvat tellennetuista resepteistä vain Lasagnan ja Ragun tekoon, niin pitäisi avata näkymä jossa nämä kahdet reseptit ovat listattuna. Tämä toiminto ei kuitenkaan toimi vielä.

Viimeinen nappi, eli "**Reset recipe book**", poistaa kaikki tallennetut reseptit ja asettaa sovelluksen alkutilaansa. (Tämä on hyödyllistä sovelluksen testammisessa, tai muuten vaan.) Alkutilassa, eli kun sovelluksen käynnistää ensimmäisen kerran, avausnäkymä näyttää tältä:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Tyhja%20avausnakyma.jpg">


## Uuden reseptin luominen

Toistaiseksi tilapäinen tekstikäyttöliittymä ohjeistaa käyttäjää reseptin luomiseen.


## Reseptien listaaminen
Toistaiseksi tilapäinen tekstikäyttöliittymä ohjeistaa käyttäjää reseptien listaamiseen.


## Tietokannan tyhjentäminen ja palauttaminen alkutilaan

Tietokantaa voidaan tyhjentää ja palauttaa alkutilaan, esim. sovellusta testatessa tai muuten vaan. 
