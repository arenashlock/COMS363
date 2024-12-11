-- Q3.1 --
SELECT Ingredient.form
FROM Ingredient;

-- Q3.2 --
SELECT Ingredient.form
FROM Ingredient
WHERE Ingredient.iid=1;

-- Q3.3 --
SELECT Food.Fname, Ingredient.Iname, Recipe.amount
FROM Recipe
JOIN Ingredient ON Recipe.iid = Ingredient.iid
JOIN Food ON Recipe.fid = Food.fid;

-- Q3.4 --
SELECT *
FROM (SELECT * FROM Ingredient WHERE Ingredient.form="fresh") as T1
CROSS JOIN Food;

-- Q3.5 --
SELECT Ingredient.Iname, Food.Fname
FROM Ingredient JOIN Food 
ON Ingredient.iid!=Food.fid;