# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria. Pääasiallinen rakenne sisältää "main"-paketin, joka puolestaan sisältää paketteja käyttöliittymälle ja sovelluslogiikalle. Rakenne kuvattuna alla:


<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/packetStructure.jpg">


## Tietokantakaavio

Ohjelma käytää pysyväistallentamiseen tietokantaa. Sovelluksen olennaisin luokka on "Resepti" kun sovellushan tulisi toimia digitaalisena reseptikirjana. Kun tämän yksi keskeinen toiminto on reseptin avaaminen suoraan toisen reseptin ainesluettelosta, (esim. jos jonkun padan reseptiin kuuluu [bouqet garni](https://www.youtube.com/watch?v=V35qP2dEywg)n, olisi kätevää vain avata linkkiä sen ohjeeseen suoraan padan reseptistä)


