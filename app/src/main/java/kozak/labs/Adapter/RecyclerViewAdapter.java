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

import java.util.ArrayList;

import kozak.labs.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    
    private ArrayList<String> mCharacterNames;
    private ArrayList<String> mCharacterImages;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mCharacterNames,
                               ArrayList<String> mCharacterImages){
        this.mCharacterNames = mCharacterNames;
        this.mCharacterImages = mCharacterImages;
        this.mContext = mContext;
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
    }

    @Override
    public int getItemCount() {
        return mCharacterImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView characterImage;
        TextView characterName;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            characterImage = itemView.findViewById(R.id.char_image);
            characterName = itemView.findViewById(R.id.char_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
