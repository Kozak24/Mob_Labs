package kozak.labs.FragmentNavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import kozak.labs.Fragments.DetailsFragment;
import kozak.labs.R;

public class FragmentNavigation {
    FragmentManager fragmentManager;

    public FragmentNavigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setFragment(final Fragment fragment, final boolean addToBackStack) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace( R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack( null );
        }
        transaction.commit();
    }

    public void showDetailsFragment() {
        setFragment(new DetailsFragment(), true);
    }
}
