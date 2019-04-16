package br.com.mdr.youtube.activity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import br.com.mdr.youtube.R;
import br.com.mdr.youtube.helper.YoutubeConfig;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView player;
    private String idVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        player = findViewById(R.id.player);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            idVideo = bundle.getString("idVideo");
        }

        player.initialize(YoutubeConfig.BASE_URL, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.i("Youtube", "Vídeo Iniciado.");
        youTubePlayer.setFullscreen(true);
        youTubePlayer.setShowFullscreenButton(false);
        youTubePlayer.loadVideo(idVideo);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.e("Youtube", "Erro na execução do vídeo.");
    }
}
