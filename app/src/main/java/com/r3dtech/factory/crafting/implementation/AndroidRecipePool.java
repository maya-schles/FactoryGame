package com.r3dtech.factory.crafting.implementation;



import android.support.annotation.NonNull;
import android.util.Pair;

import com.r3dtech.factory.crafting.Recipe;
import com.r3dtech.factory.crafting.RecipePool;
import com.r3dtech.factory.inventory.GameItem;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class implements the RecipePool interface.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public class AndroidRecipePool implements RecipePool {
    private static final String RECIPES_FILE = "/res/raw/recipes.txt";
    private Recipe[] recipes;

    public AndroidRecipePool() {
        InputStream in = this.getClass().getResourceAsStream(RECIPES_FILE);
        Scanner scanner = new Scanner(in);
        recipes = new Recipe[scanner.nextInt()];
        scanner.nextLine();
        for (int i = 0; i < recipes.length; i++) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("[=:, ]");
            GameItem product = GameItem.fromInt(lineScanner.nextInt());
            Pair<GameItem, Integer>[] components = new Pair[lineScanner.nextInt()];
            for (int j = 0; j < components.length; j++) {
                GameItem item = GameItem.fromInt(lineScanner.nextInt());
                int amount = lineScanner.nextInt();
                components[j] = new Pair<>(item, amount);
            }
            lineScanner.close();
            recipes[i] = new RecipeImplementation(product, components);
        }
        scanner.close();
    }

    public AndroidRecipePool(Recipe[] recipes) {
        this.recipes = recipes;
    }

    @Override
    public Recipe[] getRecipes() {
        return recipes;
    }

    @NonNull
    @Override
    public Iterator<Recipe> iterator() {
        return Arrays.asList(recipes).iterator();
    }
}
