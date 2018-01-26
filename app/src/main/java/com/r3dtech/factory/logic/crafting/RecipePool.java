package com.r3dtech.factory.logic.crafting;

import android.support.annotation.NonNull;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class is used to manage several recipes.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class RecipePool implements Iterable<Recipe> {
    private static final String RECIPES_FILE = "/res/raw/recipes.txt";
    private Recipe[] recipes;

    RecipePool() {
        InputStream in = this.getClass().getResourceAsStream(RECIPES_FILE);
        Scanner scanner = new Scanner(in);
        recipes = new Recipe[scanner.nextInt()];
        scanner.nextLine();
        for (int i = 0; i < recipes.length; i++) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("[=: ]");
            GameItem product = GameItem.fromInt(lineScanner.nextInt());
            ItemStack[] components = new ItemStack[lineScanner.nextInt()];
            for (int j = 0; j < components.length; j++) {
                GameItem item = GameItem.fromInt(lineScanner.nextInt());
                int amount = lineScanner.nextInt();
                components[j] = new ItemStack(item, amount);
            }
            lineScanner.close();
            recipes[i] = new Recipe(product, components);
        }
        scanner.close();
    }

    RecipePool(Recipe[] recipes) {
        this.recipes = recipes;
    }


    public Recipe[] getRecipes() {
        return recipes;
    }

    @NonNull
    @Override
    public Iterator<Recipe> iterator() {
        return Arrays.asList(recipes).iterator();
    }
}
