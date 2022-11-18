package com.example.dailybread.data
import android.content.Context
import androidx.compose.runtime.Composable
import java.io.IOException

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>
)


val spaghetti = Recipe(
    1,
    "Spaghetti",
    listOf(
        "1 pound ground beef (or ½ lb ground Italian sausage and ½ lb ground beef)",
        "salt and freshly ground black pepper , to taste",
        "1 medium onion , chopped",
        "15 ounces tomato sauce",
        "6 ounces tomato paste",
        "1/2 teaspoon Italian seasoning",
        "1 Tablespoon dried parsley flakes",
        "1 teaspoon garlic powder",
        "crushed red pepper flakes , to taste",
        "1 Tablespoon Worcestershire sauce",
        "1 Tablespoon granulated sugar",
        "1 cup water",
        "1/4 cup fresh basil leaves (optional)",
        "spaghetti noodles , for serving"
    ),
    listOf(
        "1. Season ground beef with salt and pepper. ",
        "2. In a large skillet, add the beef and chopped onion and brown.  Drain excess grease. ",
        "3. Add tomato sauce, tomato paste, Italian seasoning, parsley, garlic powder, crushed red pepper, worcestershire, and sugar to the skillet. ",
        "4. Stir well to combine and bring to a boil. Add water and stir well. ",
        "5. Reduce heat and simmer for 30 minutes. Add chopped basil before serving, if desired.."
    )
)
val fettuccine = Recipe(
    2,
    "Fettuccine Alfredo",
    listOf(
        "1 lb Fettuccine Pasta",
        "6 Tablespoons Butter",
        "1 Garlic Clove (minced)",
        "1 ½ cups Heavy Cream",
        "¼ teaspoon Salt",
        "1 ¼ cup Shredded Parmesan Cheese",
        "¼ teaspoon Pepper",
        "2 Tablespoons Italian Parsley (optional)"
    ),
    listOf(
        "1. In a large pot, heat water over high heat until boiling. Add salt to season the water. Once it is boiling, add fettuccine and cook according to package instructions. ",
        "2. In a large skillet or pan, heat butter over medium heat. Add minced garlic and cook for 1 to 2 minutes. Stir in heavy cream. ",
        "3. Let heavy cream reduce and cook for 5 to 8 minutes. Add half of the parmesan cheese to the mixture and whisk well until smooth. Keep over heat and whisk well until cheese is melted.",
        "4. Save some pasta water. The pasta water is full of flavor and can be used to thin out the sauce.",
        "5. Toss alfredo sauce with fettuccine pasta and add half of the parmesan cheese. Once it is tossed, garnish with the remaining parmesan cheese. Add a little pasta water if it needs to be thinned out.",
        "6. Garnish with Italian parsley, if so desired."
    )
)
val biryani = Recipe(
    2,
    "Biryani",
    listOf(
        "2 pounds (910g) boneless leg of lamb, trimmed of excess fat and cut into 1-inch (2cm) cubes",
        "1 cup plain, unsweetened full-fat yogurt",
        "6 medium garlic cloves, peeled and finely grated",
        "One 2–inch piece fresh ginger, peeled and grated",
        "3 teaspoons Diamond Crystal kosher salt, divided; if using table salt, use half as much by volume",
        "1/4 cup (60 ml) plus 2 teaspoons (10ml) ghee or neutral oil, such as grapeseed, divided",
        "2 large yellow or white onions (26 ounces; 750g total), sliced thinly",
        "5 green cardamom pods, lightly cracked, divided",
        "1 cinnamon stick",
        "4 cloves",
        "2 dried bay leaves",
        "1 teaspoon garam masala, store-bought or homemade",
        "1 teaspoon ground Kashmiri chili",
        "1/2 teaspoon ground mace",
        "1/2 teaspoon ground turmeric",
        "1 cup (240ml) water or low-sodium stock (lamb, beef, or chicken)",
        "2 cups (400g) basmati rice",
        "1/4 cup (60ml) fresh lemon or lime juice",
        "1 bunch fresh cilantro leaves and tender stems, chopped",
        "1 bunch fresh mint leaves, chopped",
        "1 fresh green chile, such as a serrano or Thai bird, minced",
        "1/4 cup (60ml) whole milk",
        "20 strands of saffron, divided",
        "2 teaspoons rosewater",
        "2 teaspoons pandan (kewra/screwpine) water"
    ),
    listOf(
        "1. Place the lamb in a 1 gallon (3.8L) ziptop bag. In a medium bowl, mix the yogurt, garlic, ginger, and 1 1/2 teaspoons salt until combined, pour the yogurt mixture over the lamb, seal the bag, and shake the bag to coat the lamb well. Leave the ziptop bag in the refrigerator to marinate overnight.",
        "2. Heat 1/4 cup (60ml) ghee or oil in a Dutch oven or saucepan with a heavy bottom over medium heat. Add the onions, season with a pinch of salt, and cook, stirring occasionally, until the onions caramelize and turn dark brown (but not black), about 25 to 30 minutes.",
        "3. Reduce the heat to low. Remove half of the caramelized onions and reserve to use as garnish for the biryani. Add 3 green cardamom pods, cinnamon, cloves, bay leaves, garam masala, Kashmiri chili, mace, and turmeric, and sauté just until spices become fragrant, 30 to 45 seconds. Add the lamb along with the yogurt marinade, the water or stock, and the cilantro, mint, and green chili. Stir to mix well, increase the heat to medium-high, and bring liquid to a boil. Reduce heat to low, cover with lid, and cook for 30 to 45 minutes, stirring occasionally, until the lamb is completely tender.",
        "4. Meanwhile, as the lamb cooks, prepare the rice. Pick over the the rice for any debris, then place it in a fine mesh strainer and rinse under cold running water, until the runoff is no longer cloudy; drain well. Place the rice in a bowl and cover with 4 cups (960ml) water and let stand for 30 minutes. Strain the rice, discarding soaking water. In a large saucepan, combine rice with 4 cups cold water, lemon juice, the remaining 1 1/2 teaspoons kosher salt, the remaining 2 green cardamom pods, and the remaining 2 teaspoons of ghee or oil and bring to a boil over medium heat. Boil for 2 minutes and then strain the rice; discard cooking water. It will be partially cooked—if you break a grain of rice, you will see an outer translucent ring and a tiny, opaque inner ring.",
        "5. When the lamb is tender, remove lid and increase the heat to medium, stirring often to prevent scorching, and cook until the liquid starts to thicken and reduces to about 1 1/2 cups (360ml), about 5 minutes. Remove from heat. Using a clean, large wooden spoon or spatula, spread the rice out in an even layer over the meat in the Dutch oven.",
        "6. Set oven rack at the middle position and heat the oven to 350°F (180°C). Place half the saffron threads in a mortar and pestle and grind to a fine powder. In a small saucepan placed over low heat, warm the milk just until it starts to bubble, then turn off the heat. Add the ground saffron and remaining saffron strands to the hot milk and let steep for 10 minutes.",
        "7. Sprinkle the saffron-infused milk over the rice, followed by the rosewater and the pandan water. Garnish the top of the rice with the reserved caramelized onions. Cover the Dutch oven with two sheets of aluminum foil and crimp the overhang to form a tight seal, place the lid over the foil, and place the Dutch oven in the preheated oven for 20 minutes to let the rice steam. Remove from the oven and let stand for 5 minutes. Uncover the pot and carefully peel off the aluminum seal. Use a fork to loosen the rice a little and serve immediately straight from the Dutch oven. Alternatively, biryani can be transferred to a platter and served."
    )
)
val recipeList =
    listOf(spaghetti, fettuccine, biryani)