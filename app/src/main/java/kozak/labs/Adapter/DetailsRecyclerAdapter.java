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
import kozak.labs.Entity.VoiceActor;
import kozak.labs.R;

public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.ViewHolder>{
    private List<VoiceActor> voiceActors;
    private Context mContext;

    public void setItems(List<VoiceActor> voiceActors){
        this.voiceActors = voiceActors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_row_view_actors, viewGroup, false);
        mContext = viewGroup.getContext();
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Glide.with(mContext);

        final VoiceActor voiceActor = voiceActors.get(position);

        Picasso.get().load(voiceActor.getActorImageUrl()).into(viewHolder.actorImage);

        viewHolder.actorName.setText(voiceActor.getActorName());
        viewHolder.language.setText(voiceActor.getLanguage());
    }

    @Override
    public int getItemCount() {
        return voiceActors == null ? 0 : voiceActors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.actor_image)
        protected ImageView actorImage;
        @BindView(R.id.actor_name)
        protected TextView actorName;
        @BindView(R.id.language)
        protected TextView language;
        @BindView(R.id.parent_layout_actors)
        protected ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
