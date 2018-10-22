package kozak.labs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kozak.labs.Entity.Character;
import kozak.labs.R;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{
    private List<Character> mCharactersInfo;
    private Context mContext;

    public FavoritesAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setItems(List<Character> mCharactersInfo){
        this.mCharactersInfo = mCharactersInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_row_view, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Glide.with(mContext);

        final Character characters = mCharactersInfo.get(position);

        Picasso.get().load(characters.getImageUrl()).into(viewHolder.characterImage);

        viewHolder.characterName.setText(characters.getName());
        viewHolder.characterRole.setText(characters.getRole());
    }

    @Override
    public int getItemCount() {
        if(mCharactersInfo != null) {
            return mCharactersInfo.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.char_image)
        ImageView characterImage;
        @BindView(R.id.char_name)
        TextView characterName;
        @BindView(R.id.char_role)
        TextView characterRole;
        @BindView(R.id.parent_layout)
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
