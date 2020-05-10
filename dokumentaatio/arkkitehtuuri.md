# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria. Pääasiallinen rakenne sisältää "main"-paketin, joka puolestaan sisältää paketteja käyttöliittymälle, sovelluslogiikalle, ja DAO:ille. Rakenne kuvattuna alla:


<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Pakkaus_kaavio.jpg">

Pakkaus main.ui sisältää JavaFX:llä toteutetun käyttöliittymän joka käynnistää sovelluslogiikan. Sovelluslogiikka on toteutettu pakkauksessa main.logic, ja halitsee DAO:t joihin on toteutettu pysyväistallennuksesta vastaavan koodin. DAO:t on teoteutettu pakkaukseen main.dao.


## Käyttöliittymä

Sovellus toteuttaa graafisen käyttöliittymän. Käyttöliittymä sisältää neljä päänäkymää:
- Aloitussivu
- Sivu/lomake reseptien lisäämiselle
- Sivu reseptin lukemiselle
- Sivu hakutuloksille

Lisäksi käyttöliittymä sisältää useita varoitus-/varmistusviestejä käyttäjän toimille jotka muuten saattaisivat antaa käyttäjän tuhota oman työnsä vahingossa.

Päänäkymät, ja viestit ovat toteutettuna omilla "[Stage](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html)"-olioilla jotta käyttäjä voisi pitää useita näkymiä esillä samaan aikaan.

Kun reseptejä poistetaan tai lisätään, kutsuu käyttöliittymä tarvittavat sovelluslogiikan metodit ja päivittää aloitussivun reseptihakemistoa.

Käyttöliittymä on eristetty "[sovelluslogiikasta](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/Logic.java)", jonka metodeja käyttöliittymä ainoastaan kutsuu sopivin parametrein. Käyttöliittymä on rakennettu ohjelmallisesti luokassa main.domain.Logic.


## Sovelluslogiikka

Sovellusloogiikan hallitsee luokka "[Logic](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/Logic.java)". Tämä tapahtuu luokkia "[Recipe](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/Recipe.java)" (joka kuvaa reseptin), "[Ingredient](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/Ingredient.java)" (joka kuvaa resepteissä käytettävän aineksen), ja "[RecipeIngredient](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/domain/RecipeIngredient.java)" (joka kuvaa reseptikohtaiset aineksien käytöt, ja lisäksi helpottaa sovelluslogiikan ja tietokannan yhtenäisyyden hahmottamisen) käyttäen:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/looginen%20datamalli.jpg">

Sovelluksen osien suhdetta voi hahmottaa alla olevasta luokka-/pakkauskaaviosta:


<img src="´https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Pakkaus_kaavio.jpg">


## Tietojen pysyväistallennus

Sovellus toteuttaa [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia. Pakkauksen "main.dao" luokat "[IngredientDAO](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/dao/IngredientDAO.java)", "[RecipeDAO](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/dao/RecipeDAO.java)", & "[RecipeIngredientDAO](https://github.com/jrhel/ot-harjoitustyo/blob/master/otRecipeDatabase/src/main/java/main/dao/RecipeIngredientDAO.java)" huolehtivat tietojen tallettamisesta tietokantaan.


### Tietokantakaavio

Ohjelma käytää pysyväistallentamiseen tietokantaa. Sovelluksen olennaisin luokka on "Resepti", kun sovellushan tulisi toimia digitaalisena reseptikirjana. Kun sovelluksen yksi keskeinen toiminto on reseptin avaaminen suoraan toisen reseptin ainesluettelosta, (esim. jos vaikka jonkun padan reseptiin kuuluu [bouqet garni](https://www.youtube.com/watch?v=V35qP2dEywg)n, olisi kätevää vain avata linkkiä sen ohjeeseen suoraan padan reseptistä), niin on jokainen resepti tietokannassa aineksen erikoistapaus johon vaan liittyy mm. toimintaohje. Vaikka ainekselle siis on voitu tehdä resepti niin ainakin raakaaineet eivät edellytä tätä. Siksi taulu "Ingredient" ei sisällä viiteavaimia resepteihin. Reseptit voivat kuitenkin periaatteessa ainakin aina tuottaa toisen reseptin aineksen, joten jokaisen uuden reseptin myötä tallennetaan myös aina aines johon löytyy viiteavain resepti-taulusta. Näin resepti voi vain tuottaa yhden aineksen, ja ainekselle voi vain olla yksi resepti, ja niiden kesken on yhden suhde yhteen. Jos "samasta" asiasta löytyy monta eri reseptiä ovat nämä teknisesti eri aineksia joista tallennetaan eri ilmeentymät tietokantaan. Esim. bouqet garnin voi tehdä monella [eri](https://www.youtube.com/watch?v=CNy1Hzj3oDo) tavalla, mutta tiettyyn reseptiin liittyy oletettavasti tietty käsitys käyettävästä aineksesta, esim. minkälainen bouqet garni. Koska reseptiin kuitenkin (yleensä) kuuluu monta eri ainesta, ja aines voidaan käyttää monessa erilaisessa reseptissä tarvitaan näiden välille liitostaulu "RecipeIngredient". Sellaiseen ilmentymään kuuluu myös määrä, kun tarvittava aineksen määrä vaihtelee reseptikohtaisesti. Näin sovelluksen tietokantakaavio näytää seuraavalta:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Database%20Diagram.jpg">


## Päätoiminnallisuudet

Alla on kuvattu sovelluksen päätoiminnallisuuden toimintalogiikka sekvenssikaaviona:

###



## Ohjelman rakenteeseen jääneet heikkoudet




