package br.com.mdr.youtube.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.mdr.youtube.R;
import br.com.mdr.youtube.model.Item;
import br.com.mdr.youtube.model.Snippet;
import br.com.mdr.youtube.model.Video;

/**
 * Created by ${USER_NAME} on 15/04/2019.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private List<Item> videos = new ArrayList<>();
    private Context context;

    public VideoAdapter(List<Item> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Item video = videos.get(position);
        myViewHolder.txtTitulo.setText(video.getSnippet().getTitle());
        String url = video.getSnippet().getThumbnails().getHigh().getUrl();
        Picasso.get().load(url).into(myViewHolder.imgCapa);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;
        ImageView imgCapa;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            imgCapa = itemView.findViewById(R.id.imgVideo);
        }
    }
}
