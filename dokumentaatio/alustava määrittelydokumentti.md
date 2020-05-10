# Requirements specification

## Purpose of the application

The purpose of the application is to offer users a way to keep all their recipes in a single place,
instead of having them spread out over a range of sources, including various physical cookbooks & blog posts,
and notes containing recipies for the users very own secret sauces. In essence, the user experience would be a digital recipe book 
where they could keep all their favorite recipes.

People also tend to deviate from original recipes. E.g. some hate garlic and leave it out, others love it
and add it to everything. Some might try vegeterian adaptations of traditional meat recipes, others need to
adapt a recipe because of intolerance to a certain ingredient. Thus, the app strives to allow users to be able 
to have the recipes THEY need, not just those according to someone else's instructions.


## Users

The core application is primarily aimed at single users, for whom the app offers a custom tailored recipe book, 
which they themselves build up as they add to it over time.


## User interface

The user interface implements a "browser"-like user experience, where significant operations open a new "page". All-in-all, the user interface consists of a home page, a page/form for adding ne recipies, a page for each recipe, and a page for search results. The implementation of "pages" allows users to have multiple resipes open at the same time, as well as always having the home page and potential search reuslts in the background, while doin other stuff on their computer - like browsing the internet for other references. The home page allows the user to e.g. browse recipies, add new ones, and search for old ones: 

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/Alustettu%20aloitusnakyma.jpg">


A new recipe is added by filling out the following form/page:

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/New%20recipeForm.png">


A recipe is shown on a following page (the recipe is an example):

<img src="https://github.com/jrhel/ot-harjoitustyo/blob/master/dokumentaatio/illustrations/bechamel.jpg">


The page for search results is similar to the home page, but without the menu on the left side of the home page.


## Functinality

- User can add new recipes,
- User can list/see overview of all recipes,
- User can "open"/read recipes from a list, 
- User can open a recipe straight from the list of ingredients in another recipe, if the recipe is an ingredient of another one,
- User can search for recipes,
- User can delete recipes,
- User can empty their recipe book/reset application
- Application updates home screen on the fly as recipes are added and deleted,
- Application warns user before resetting application,
- Application doesn't delete an ingredient from the applications database when deleting a recipe, if another recipe uses given ingredient,
- Application prevents user from saving multiple recipes with the same name.
- Application implements a "browser"-like user experience, through the opening and closing of "pages", allowing users to open multiple pages at a time, as well as doing other stuff.

## Restrictions on application

- The application must work on the Linux- & OSX desktop, 
- The application cannot be dependent on an internet connection to work,
- All app data will be saved locally on device.
- The app has to use a database.


## Future development

- Dynamically increase possible amount of ingredients in a recipe,
- Add vertical scrollbar to recipe list on home screen and search results to more easily browse large recipe books,
- Allow users to edit recipes as they read them,
- Allow users to search for recipes containing specific ingredients,
- Categorize recipes, and search for recipes based on category - e.g. "fish",
- Allow users to add pictures to recipies,
- Allow users to import recipes from other formats, e.g. pdf-files, photos, websites etc.

**Would require transition from purely "offline" state:**
- Sync personal recipes accross devices,
- Make recipes public to other users to see,
- Search for/browse public recipes by other users,
- Send/Share recipies to other users,
