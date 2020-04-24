# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria. Pääasiallinen rakenne sisältää "main"-paketin, joka puolestaan sisältää paketteja käyttöliittymälle ja sovelluslogiikalle. Rakenne kuvattuna alla:


<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/packetStructure.jpg">


## Tietokantakaavio

Ohjelma käytää pysyväistallentamiseen tietokantaa. Sovelluksen olennaisin luokka on "Resepti", kun sovellushan tulisi toimia digitaalisena reseptikirjana. Kun sovelluksen yksi keskeinen toiminto on reseptin avaaminen suoraan toisen reseptin ainesluettelosta, (esim. jos vaikka jonkun padan reseptiin kuuluu [bouqet garni](https://www.youtube.com/watch?v=V35qP2dEywg)n, olisi kätevää vain avata linkkiä sen ohjeeseen suoraan padan reseptistä), niin on jokainen resepti tietokannassa aineksen erikoistapaus johon vaan liittyy mm. toimintaohje. Vaikka ainekselle siis on voitu tehdä resepti niin ainakin raakaaineet eivät edellytä tätä. Siksi taulu "Ingredient" ei sisällä viiteavaimia resepteihin. Reseptit voivat kuitenkin periaatteessa ainakin aina tuottaa toisen reseptin aineksen, joten jokaisen uuden reseptin myötä tallennetaan myös aina aines johon löytyy viiteavain resepti-taulusta. Näin resepti voi vain tuottaa yhden aineksen, ja aineksesta voi vain olla yksi resepti ja niillä on yhden suhde yhteen. Jos "samasta" asiasta löytyy monta eri reseptiä ovat nämä teknisesti eri aineksia joista tallennetaan eri ilmeentymät tietokantaan. Esim. bouqet garnin voi tehdä monella [eri](https://www.youtube.com/watch?v=CNy1Hzj3oDo) tavalla, mutta eri resepteissä voidaan käyttää erinlaisia bouqet garnitä. Koska reseptiin kuitenkin (yleensä) kuuluu monta eri ainesta, ja aines voidaan käyttää monessa erilaisessa reseptissä tarvitaan näiden välille liitostaulu "RecipeIngredient". Sellaiseen ilmentyämmä kuuluu myös määrä, kun tarvittava aineksen määrä vaihtelee reseptikohtaisesti. Näin sovelluksen tietokantakaavio näytää seuraavalta:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Database%20Diagram.jpg">


