package kozak.labs.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
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


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Character> mCharactersInfo;
    private Context mContext;
    private FragmentManager fragmentManager;
    private OnCharacterClickListener listener;

    /*
    public RecyclerViewAdapter(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }*/

    /*
    public RecyclerViewAdapter(){

    }*/

    public void setOnCharacterClickListener(OnCharacterClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<Character> mCharactersInfo){
        this.mCharactersInfo = mCharactersInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_row_view, viewGroup, false);
        mContext = viewGroup.getContext();
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

        if(characters.getRole().equals(mContext.getString(R.string.role_main))) {
            viewHolder.characterName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewHolder.characterRole.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            viewHolder.characterRole.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCharacterClick(characters);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharactersInfo == null ? 0 : mCharactersInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.char_image)
        protected ImageView characterImage;
        @BindView(R.id.char_name)
        protected TextView characterName;
        @BindView(R.id.char_role)
        protected TextView characterRole;
        @BindView(R.id.parent_layout)
        protected ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
