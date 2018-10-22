package kozak.labs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kozak.labs.Fragments.FavoritesFragment;
import kozak.labs.Fragments.ListFragment;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout frameLayout;
    @BindView(R.id.go_favorite)
    ImageView goFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setFragment(new ListFragment());
    }

    @OnClick(R.id.go_favorite)
    void goFavorite() {
        setFragment(new FavoritesFragment());
    }

    public void setFragment(Fragment fragment) {
        frameLayout.removeAllViews();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
