package kozak.labs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import kozak.labs.Entity.Character;
import kozak.labs.FragmentNavigation.FragmentNavigation;

public class ApplicationEx extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static Character mCharacter;
    private static FragmentNavigation mFragmentNavigation;

    public static FragmentNavigation getFragmentNavigation() {
        return mFragmentNavigation;
    }

    public static void setFragmentNavigation(FragmentNavigation fragmentNavigation) {
        mFragmentNavigation = fragmentNavigation;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Character getCharacter() {
        return mCharacter;
    }

    public static void setCharacter(Character character) {
        mCharacter = character;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
