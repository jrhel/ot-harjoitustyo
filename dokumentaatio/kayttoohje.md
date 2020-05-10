# Käyttöohje

Lataa tiedosto [otRecipeDatabase.jar](https://github.com/jrhel/ot-harjoitustyo/releases)

## Konfigurointi

Ohjelma luo tarvittavat tiedostot,


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar otRecipeDatabase-1.0-SNAPSHOT.jar
```

## Sovelluksen aloitussivu

Sovellus käynnistyy avausnäkymään, jonka vasemmassa laidassa on valikko, jonka oikealla puolella on lista sovelluksessa tellennetuista resepteistä. Alla olevassa esimerkissä on tallennettuna viisi reseptiä: "Bechamel sauce", "Lasagna", "Blueberry pie", "Mushroom risotto", & "Ragu". Jokaista reseptiä pääsee lukemaan painamalla sitä tästä listalta.

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Alustettu%20aloitusnakyma.jpg">

Vasemmalla laidalla olevan valikon ylemmässä rivissä on nappi jonka painamalla pääsee lisäämään uuden reseptin. Tämä avaa lomakkeen, jota selitetään tarkemmin tämän käyttöohjeen seuraavassa osassa. Valikosta löytyy vielä napit "**by name :**", "**by ingredients :**", & "**Reset recipe book**". Ensimmäisen, eli "**by name :**", alla on heti textikenttä. Painamalla "**by name :**"-nappia listaa sovellus kaikki tallennetut reseptit jotak sisältävät sen textikentän painaushetkellä sisältämä merkkijono. Esim. tekstikentän syötteellä "*ag*" saisi nappia "**by name :**" painamalla avattua uuden näkymän jossa olisi yllä olevasta kuvasta vain "Las*ag*na" ja "R*ag*u" listattuna.

## Uuden reseptin luominen

Toistaiseksi tilapäinen tekstikäyttöliittymä ohjeistaa käyttäjää reseptin luomiseen.


## Reseptien listaaminen
Toistaiseksi tilapäinen tekstikäyttöliittymä ohjeistaa käyttäjää reseptien listaamiseen.


## Tietokannan tyhjentäminen ja palauttaminen alkutilaan

Tietokantaa voidaan tyhjentää ja palauttaa alkutilaan, esim. sovellusta testatessa tai muuten vaan. 
