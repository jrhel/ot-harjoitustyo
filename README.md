# Reseptitietokanta
Tämä on opiskeluprojekti kurssilla "Ohjelmistotekniikka" Helsingin yliopistolla. Lopputuloksena pitäisi olla sovellus jonka käyttäjäkokemus vastaa digitaalista reseptikirjaa, jossa käyttäjä omalla konellaan voi pitää kaikki suosikkireseptinsä sen sijaan että ne olisivat hajautettuna eri keittokirjoissa, ruokablogeissa, ja käyttäjän kansioissa.


## Dokumentaatio

[Määrittelydokumentti](https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/alustava%20m%C3%A4%C3%A4rittelydokumentti.md)

[Käyttöohje](https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Arkkitehtuurikuvaus](https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/Ty%C3%B6aikakirjanpito.md)


## Releaset

[Viikko 5](https://github.com/jrhel/ot-harjoitustyo/releases)

[Viikko 6](https://github.com/jrhel/ot-harjoitustyo/releases/tag/viikko6)


## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _otRecipeDatabase-1.0-SNAPSHOT_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
