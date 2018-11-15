package kozak.labs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import kozak.labs.FragmentNavigation.FragmentNavigation;
import kozak.labs.Fragments.FavoritesFragment;
import kozak.labs.Fragments.ListFragment;


public class MainActivity extends AppCompatActivity {
    FragmentNavigation fragmentNavigation;

    @BindView(R.id.fragment_container)
    protected FrameLayout frameLayout;
    @BindView(R.id.navigation_menu)
    protected BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ApplicationEx.setContext(getApplicationContext());

        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);

        fragmentNavigation = new FragmentNavigation(getSupportFragmentManager());
        fragmentNavigation.setFragment(new ListFragment());
        ApplicationEx.setFragmentNavigation(fragmentNavigation);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home_button:
                            selectedFragment = new ListFragment();
                            break;
                        case R.id.favorites_button:
                            selectedFragment = new FavoritesFragment();
                            break;
                    }
                    fragmentNavigation.setFragment(selectedFragment);
                    return true;
                }
            };
}
