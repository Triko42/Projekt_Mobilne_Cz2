package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

/**
 * The type Navigator.
 */
public class Navigator {

    private static Stack<Fragment> backstack = new Stack<>();

    /**
     * Navigate to.
     * @param fragmentManager the fragment manager for transactions
     * @param fragment        the fragment
     */
    public static void navigateTo(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.replace(R.id.details, fragment);
        fragmentTransaction.commit();
        backstack.push(fragment);
    }

    /**
     * Current screen string.
     * @return the string class name
     */
    public static String currentScreen() {
        return backstack.peek().getClass().getSimpleName();
    }

    /**
     * Pop back stack.
     * @param fragmentManager the fragment manager for back stack
     */
    public static void popBackStack(FragmentManager fragmentManager) {
        backstack.pop();
        fragmentManager.popBackStack();
    }

    /**
     * Back stack entries int.
     * @param fragmentManager the fragment manager for back stack entry count
     * @return the int
     */
    public static int backStackEntries(FragmentManager fragmentManager) {
        return fragmentManager.getBackStackEntryCount();
    }
}
