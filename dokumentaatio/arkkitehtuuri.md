# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria. Pääasiallinen rakenne sisältää "main"-paketin, joka puolestaan sisältää paketteja käyttöliittymälle ja sovelluslogiikalle. Rakenne kuvattuna alla:


<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/packetStructure.jpg">


## Käyttöliittymä

On tarkoituksena toteuttaa graafinen käyttöliittymä. Toistaiseksi, sovelluslogiikan testaamiseksi on olemassa väliaikainen tekstikäyttöliittymä. Se toimii tulostamalla ohjeita ja kysymällä komentoja käyttäjältä jolla se voi hallita sovellusta.

Käyttöliittymä on eristetty sovelluslogiikasta ja ainoastaan kutsuu sopivin parametrein sovelluslogiikan ("Logic") metodeja.


## Sovelluslogiikka

Sovellusloogiikan hallitsee luokka "[Logic](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/Logic.java)". Tämä tapahtuu luokkia "[Recipe](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/Recipe.java)" (joka kuvaa reseptin), "[Ingredient](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/Ingredient.java)" (joka kuvaa resepteissä käytettävän aineksen), ja "[RecipeIngredient](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/RecipeIngredient.java)" (joka helpottaa sovelluslogiikan ja tietokannan yhtenäisyyden ja eri määrien käyttö eri resepteissä samasta aineksesta) käyttäen. Tietokantaan voi tutustua alla seruaavassa osassa. Tässä vielä alustava sovelluksen osien suhdetta kuvaava luokka/pakkauskaavio:


<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Pakkauskaavio.jpg">


## Tietojen pysyväistallennus

Pakkauksen "main.dao" luokat "IngredientDAO", "RecipeDAO", & "RecipeIngredientDAO" huolehtivat tietojen tallettamisesta tietokantaan.

Sovellus toteuttaa [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia.

### Tietokantakaavio

Ohjelma käytää pysyväistallentamiseen tietokantaa. Sovelluksen olennaisin luokka on "Resepti", kun sovellushan tulisi toimia digitaalisena reseptikirjana. Kun sovelluksen yksi keskeinen toiminto on reseptin avaaminen suoraan toisen reseptin ainesluettelosta, (esim. jos vaikka jonkun padan reseptiin kuuluu [bouqet garni](https://www.youtube.com/watch?v=V35qP2dEywg)n, olisi kätevää vain avata linkkiä sen ohjeeseen suoraan padan reseptistä), niin on jokainen resepti tietokannassa aineksen erikoistapaus johon vaan liittyy mm. toimintaohje. Vaikka ainekselle siis on voitu tehdä resepti niin ainakin raakaaineet eivät edellytä tätä. Siksi taulu "Ingredient" ei sisällä viiteavaimia resepteihin. Reseptit voivat kuitenkin periaatteessa ainakin aina tuottaa toisen reseptin aineksen, joten jokaisen uuden reseptin myötä tallennetaan myös aina aines johon löytyy viiteavain resepti-taulusta. Näin resepti voi vain tuottaa yhden aineksen, ja ainekselle voi vain olla yksi resepti, ja niiden kesken on yhden suhde yhteen. Jos "samasta" asiasta löytyy monta eri reseptiä ovat nämä teknisesti eri aineksia joista tallennetaan eri ilmeentymät tietokantaan. Esim. bouqet garnin voi tehdä monella [eri](https://www.youtube.com/watch?v=CNy1Hzj3oDo) tavalla, mutta tiettyyn reseptiin liittyy oletettavasti tietty käsitys käyettävästä aineksesta, esim. minkälainen bouqet garni. Koska reseptiin kuitenkin (yleensä) kuuluu monta eri ainesta, ja aines voidaan käyttää monessa erilaisessa reseptissä tarvitaan näiden välille liitostaulu "RecipeIngredient". Sellaiseen ilmentymään kuuluu myös määrä, kun tarvittava aineksen määrä vaihtelee reseptikohtaisesti. Näin sovelluksen tietokantakaavio näytää seuraavalta:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Database%20Diagram.jpg">


