CREATE TABLE Recipe
(
    ID          UUID          DEFAULT RANDOM_UUID() PRIMARY KEY,
    NAME        VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(1024),
    IMAGEURL    VARCHAR(1024) DEFAULT 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg',
    RECIPE      VARCHAR(4000),
    POSITION    INT,
    DIFFICULTY  VARCHAR(40)
);

CREATE TABLE Ingredient
(
    ID          UUID          DEFAULT RANDOM_UUID() PRIMARY KEY,
    NAME        VARCHAR(255) NOT NULL,
    UNIT        VARCHAR(50)  NOT NULL -- Exemplo: "grams", "pieces", "cups"
);


CREATE TABLE Recipe_Ingredient (
                                   RECIPE_ID UUID NOT NULL,                    -- Chave estrangeira para a tabela Recipe
                                   INGREDIENT_ID UUID NOT NULL,                -- Chave estrangeira para a tabela Ingredient
                                   QUANTITY DECIMAL(10, 2) NOT NULL,           -- Quantidade do ingrediente usado na receita
                                   FOREIGN KEY (RECIPE_ID) REFERENCES Recipe(ID),    -- Relacionamento com a tabela Recipe
                                   FOREIGN KEY (INGREDIENT_ID) REFERENCES Ingredient(ID),  -- Relacionamento com a tabela Ingredient
                                   UNIQUE(RECIPE_ID, INGREDIENT_ID)            -- Garante que não haja duplicação de ingredientes por receita
);
