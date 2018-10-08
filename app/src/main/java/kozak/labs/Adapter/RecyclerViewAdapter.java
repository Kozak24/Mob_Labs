package kozak.labs.Adapter;

import android.content.Context;
import android.graphics.Typeface;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kozak.labs.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    
    private ArrayList<String> mCharacterNames;
    private ArrayList<String> mCharacterImages;
    private ArrayList<String> mCharactersRoles;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mCharacterNames,
                               ArrayList<String> mCharacterImages,
                               ArrayList<String> mCharactersRoles){
        this.mContext = mContext;
        this.mCharacterNames = mCharacterNames;
        this.mCharacterImages = mCharacterImages;
        this.mCharactersRoles = mCharactersRoles;
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

        Picasso.get().load(mCharacterImages.get(position)).into(viewHolder.characterImage);

        viewHolder.characterName.setText(mCharacterNames.get(position));

        viewHolder.characterRole.setText(mCharactersRoles.get(position));
        
        if(mCharactersRoles.get(position).equals(mContext.getString(R.string.role_main))) {
            viewHolder.characterName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewHolder.characterRole.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            viewHolder.characterRole.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }
    }

    @Override
    public int getItemCount() {
        return mCharacterImages.size();
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
