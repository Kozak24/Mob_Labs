package kozak.labs.FragmentNavigation;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import kozak.labs.MainActivity;
import kozak.labs.R;

public class FragmentNavigation {
    FragmentManager fragmentManager;

    public FragmentNavigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setFragment(final Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace( R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
