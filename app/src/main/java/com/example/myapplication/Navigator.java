package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

public class Navigator {

    private static Stack<Fragment> backstack = new Stack<>();

    public static void navigateTo(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.replace(R.id.details, fragment);
        fragmentTransaction.commit();
        backstack.push(fragment);
    }

    public static String currentScreen() {
        return backstack.peek().getClass().getSimpleName();
    }

    public static void popBackStack(FragmentManager fragmentManager) {
        backstack.pop();
        fragmentManager.popBackStack();
    }

    public static int backStackEntries(FragmentManager fragmentManager) {
        return fragmentManager.getBackStackEntryCount();
    }
}
