CREATE TABLE Recipe
(
    ID          UUID          DEFAULT gen_random_uuid() PRIMARY KEY,  -- Em PostgreSQL, usamos gen_random_uuid() para gerar UUIDs
    NAME        VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(1024),
    IMAGEURL    VARCHAR(1024) DEFAULT 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
    RECIPE      VARCHAR(4000),
    POSITION    INT,
    DIFFICULTY  VARCHAR(40)
);

CREATE TABLE Ingredient
(
    ID          UUID          DEFAULT gen_random_uuid() PRIMARY KEY,  -- Usamos gen_random_uuid() também aqui
    NAME        VARCHAR(255) NOT NULL,
    UNIT        VARCHAR(50)  NOT NULL -- Exemplo: "grams", "pieces", "cups"
);

CREATE TABLE Recipe_Ingredient (
                                   RECIPE_ID UUID NOT NULL,                    -- Chave estrangeira para a tabela Recipe
                                   INGREDIENT_ID UUID NOT NULL,                -- Chave estrangeira para a tabela Ingredient
                                   QUANTITY DECIMAL(10, 2) NOT NULL,           -- Quantidade do ingrediente usado na receita
                                   FOREIGN KEY (RECIPE_ID) REFERENCES Recipe(ID) ON DELETE CASCADE,  -- Adicionado ON DELETE CASCADE para garantir a exclusão em cascata
                                   FOREIGN KEY (INGREDIENT_ID) REFERENCES Ingredient(ID) ON DELETE CASCADE, -- Adicionado ON DELETE CASCADE também aqui
                                   UNIQUE(RECIPE_ID, INGREDIENT_ID)            -- Garante que não haja duplicação de ingredientes por receita
);

INSERT INTO Ingredient (ID, NAME, UNIT)
VALUES
    (gen_random_uuid(), 'Ground Beef', 'grams'),
    (gen_random_uuid(), 'Cheddar Cheese', 'grams'),
    (gen_random_uuid(), 'Lettuce', 'pieces'),
    (gen_random_uuid(), 'Tomato', 'pieces'),
    (gen_random_uuid(), 'Pickles', 'pieces'),
    (gen_random_uuid(), 'Bacon', 'grams'),
    (gen_random_uuid(), 'BBQ Sauce', 'ml'),
    (gen_random_uuid(), 'Onion Rings', 'pieces'),
    (gen_random_uuid(), 'Swiss Cheese', 'grams'),
    (gen_random_uuid(), 'Mushrooms', 'grams'),
    (gen_random_uuid(), 'Jalapeños', 'pieces'),
    (gen_random_uuid(), 'Pepper Jack Cheese', 'grams'),
    (gen_random_uuid(), 'Black Beans', 'grams'),
    (gen_random_uuid(), 'Quinoa', 'grams'),
    (gen_random_uuid(), 'Avocado', 'pieces'),
    (gen_random_uuid(), 'Blue Cheese', 'grams'),
    (gen_random_uuid(), 'Pineapple', 'pieces'),
    (gen_random_uuid(), 'Ham', 'grams');

INSERT INTO Recipe (ID, NAME, DESCRIPTION, IMAGEURL, RECIPE, POSITION, DIFFICULTY)
VALUES
    (gen_random_uuid(), 'Classic Cheeseburger',
     'A classic cheeseburger with a juicy beef patty, cheddar cheese, lettuce, tomato, and pickles.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties. 2. Grill the patties on medium-high heat until cooked to desired doneness. 3. Top with cheddar cheese slices. 4. Assemble with lettuce, tomato, and pickles in a toasted bun.',
     1, 'easy'),
    (gen_random_uuid(), 'Bacon Cheeseburger', 'A delicious cheeseburger topped with crispy bacon.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Cook bacon until crispy. 2. Form ground beef into patties and grill. 3. Top patties with cheddar cheese and bacon. 4. Serve with lettuce, tomato, and your favorite condiments.',
     2, 'medium'),
    (gen_random_uuid(), 'BBQ Burger', 'A burger with a smoky BBQ sauce, cheddar cheese, and onion rings.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and season with salt and pepper. 2. Grill and brush with BBQ sauce. 3. Top with cheddar cheese and crispy onion rings. 4. Serve in a toasted bun with extra BBQ sauce.',
     3, 'medium'),
    (gen_random_uuid(), 'Mushroom Swiss Burger', 'A savory burger topped with sautéed mushrooms and Swiss cheese.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Sauté mushrooms in butter until soft. 2. Form ground beef into patties and grill. 3. Top patties with Swiss cheese and sautéed mushrooms. 4. Serve on a bun with lettuce and tomato.',
     4, 'medium'),
    (gen_random_uuid(), 'Spicy Jalapeño Burger',
     'A burger with a kick, featuring spicy jalapeños and pepper jack cheese.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and season with cayenne pepper. 2. Grill patties and top with pepper jack cheese. 3. Add sliced jalapeños and serve on a bun with lettuce and spicy mayo.',
     5, 'hard'),
    (gen_random_uuid(), 'Veggie Burger', 'A flavorful veggie burger with black beans, quinoa, and spices.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Mash black beans and mix with cooked quinoa, breadcrumbs, and spices. 2. Form into patties and bake or grill. 3. Serve on a bun with avocado, lettuce, and tomato.',
     6, 'medium'),
    (gen_random_uuid(), 'Blue Cheese Burger', 'A gourmet burger with blue cheese and caramelized onions.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Caramelize onions in butter and sugar. 2. Form beef into patties and grill. 3. Top with blue cheese and caramelized onions. 4. Serve on a bun with arugula.',
     7, 'hard'),
    (gen_random_uuid(), 'Hawaiian Burger', 'A tropical burger with grilled pineapple, ham, and Swiss cheese.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Grill pineapple slices. 2. Form beef into patties and grill. 3. Top with Swiss cheese, ham, and grilled pineapple. 4. Serve on a bun with lettuce.',
     8, 'easy'),
    (gen_random_uuid(), 'Double Patty Burger', 'A towering burger with two beef patties, cheese, and special sauce.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form double the number of patties and grill. 2. Stack patties with cheese slices between. 3. Add special sauce, lettuce, and tomato. 4. Serve in a toasted bun.',
     9, 'hard'),
    (gen_random_uuid(), 'Turkey Burger', 'A healthier option with a seasoned turkey patty, avocado, and sprouts.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground turkey into patties and season with herbs. 2. Grill until fully cooked. 3. Top with avocado slices and sprouts. 4. Serve on a whole grain bun.',
     10, 'medium'),
    (gen_random_uuid(), 'Avocado Burger', 'A juicy burger topped with fresh avocado slices and creamy ranch dressing.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and season with salt and pepper. 2. Grill the patties to desired doneness. 3. Top with avocado slices and a dollop of ranch dressing. 4. Serve on a bun with lettuce and tomato.',
     11, 'easy'),
    (gen_random_uuid(), 'Tex-Mex Burger', 'A spicy burger with pepper jack cheese, guacamole, and tortilla strips.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and season with chili powder. 2. Grill the patties and top with pepper jack cheese. 3. Add guacamole and crispy tortilla strips. 4. Serve on a bun with lettuce.',
     12, 'medium'),
    (gen_random_uuid(), 'Teriyaki Burger',
     'A sweet and savory burger with teriyaki sauce, grilled pineapple, and Swiss cheese.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and grill. 2. Brush patties with teriyaki sauce. 3. Top with grilled pineapple and Swiss cheese. 4. Serve on a bun with lettuce.',
     13, 'medium'),
    (gen_random_uuid(), 'Portobello Mushroom Burger',
     'A vegetarian burger featuring a grilled portobello mushroom cap as the patty.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Marinate portobello mushrooms in balsamic vinegar and olive oil. 2. Grill until tender. 3. Serve on a bun with goat cheese, arugula, and roasted red peppers.',
     14, 'hard'),
    (gen_random_uuid(), 'Italian Burger',
     'A burger with mozzarella, marinara sauce, and fresh basil for an Italian twist.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and grill. 2. Top with mozzarella cheese and warm marinara sauce. 3. Add fresh basil leaves. 4. Serve on a bun with a sprinkle of parmesan cheese.',
     15, 'medium'),
    (gen_random_uuid(), 'Fried Egg Burger', 'A breakfast-inspired burger with a fried egg, bacon, and cheddar cheese.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Cook bacon until crispy and fry an egg sunny-side up. 2. Form ground beef into patties and grill. 3. Top with cheddar cheese, bacon, and the fried egg. 4. Serve on a bun with lettuce and tomato.',
     16, 'hard'),
    (gen_random_uuid(), 'Chipotle Burger',
     'A smoky and spicy burger with chipotle mayo, pepper jack cheese, and crispy onions.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and season with smoked paprika. 2. Grill patties and top with pepper jack cheese. 3. Add chipotle mayo and crispy fried onions. 4. Serve on a bun with lettuce.',
     17, 'hard'),
    (gen_random_uuid(), 'Greek Burger', 'A Mediterranean-inspired burger with feta cheese, tzatziki sauce, and olives.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and mix in oregano and garlic. 2. Grill patties and top with feta cheese. 3. Add tzatziki sauce, sliced cucumbers, and olives. 4. Serve on a bun with lettuce.',
     18, 'medium'),
    (gen_random_uuid(), 'Buffalo Chicken Burger',
     'A spicy chicken burger with buffalo sauce, blue cheese, and celery slaw.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground chicken into patties and grill. 2. Toss patties in buffalo sauce. 3. Top with blue cheese crumbles and celery slaw. 4. Serve on a bun.',
     19, 'hard'),
    (gen_random_uuid(), 'Pesto Burger',
     'A flavorful burger with pesto sauce, mozzarella cheese, and sun-dried tomatoes.',
     'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
     '1. Form ground beef into patties and grill. 2. Top with mozzarella cheese. 3. Add pesto sauce and sun-dried tomatoes. 4. Serve on a bun with fresh spinach.',
     20, 'medium');

-- Classic Cheeseburger
INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 200.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Classic Cheeseburger'
  AND i.NAME = 'Ground Beef';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 50.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Classic Cheeseburger'
  AND i.NAME = 'Cheddar Cheese';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 1.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Classic Cheeseburger'
  AND i.NAME = 'Lettuce';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 1.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Classic Cheeseburger'
  AND i.NAME = 'Tomato';

-- Bacon Cheeseburger
INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 200.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Bacon Cheeseburger'
  AND i.NAME = 'Ground Beef';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 50.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Bacon Cheeseburger'
  AND i.NAME = 'Cheddar Cheese';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 2.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Bacon Cheeseburger'
  AND i.NAME = 'Bacon';

-- BBQ Burger
INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 200.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'BBQ Burger'
  AND i.NAME = 'Ground Beef';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 50.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'BBQ Burger'
  AND i.NAME = 'Cheddar Cheese';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 2.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'BBQ Burger'
  AND i.NAME = 'Onion Rings';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 30.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'BBQ Burger'
  AND i.NAME = 'BBQ Sauce';

-- Mushroom Swiss Burger
INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 200.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Mushroom Swiss Burger'
  AND i.NAME = 'Ground Beef';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 50.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Mushroom Swiss Burger'
  AND i.NAME = 'Swiss Cheese';

INSERT INTO Recipe_Ingredient (RECIPE_ID, INGREDIENT_ID, QUANTITY)
SELECT r.ID, i.ID, 100.00
FROM Recipe r, Ingredient i
WHERE r.NAME = 'Mushroom Swiss Burger'
  AND i.NAME = 'Mushrooms';
;
