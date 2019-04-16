package br.com.mdr.youtube.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import br.com.mdr.ifood.listener.RecyclerItemClickListener;
import br.com.mdr.youtube.R;
import br.com.mdr.youtube.adapter.VideoAdapter;
import br.com.mdr.youtube.api.YoutubeService;
import br.com.mdr.youtube.helper.RetrofitConfig;
import br.com.mdr.youtube.helper.YoutubeConfig;
import br.com.mdr.youtube.model.Item;
import br.com.mdr.youtube.model.QueryResult;
import br.com.mdr.youtube.model.Snippet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private MaterialSearchView searchView;
    private RecyclerView recyclerVideos;
    private ProgressBar progress;
    private List<Item> videos = new ArrayList<>();
    private VideoAdapter adapter;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar(toolbar);

        progress = findViewById(R.id.progress);
        recyclerVideos = findViewById(R.id.recyclerVideos);
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));

        //Configura o Retrofit
        retrofit = RetrofitConfig.getRetrofit();
        getVideos("");

        //Configura o SearchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getVideos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                getVideos("");
            }
        });

        recyclerVideos.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                recyclerVideos, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item video = videos.get(position);
                String videoId = video.getId().getVideoId();
                Intent i = new Intent(MainActivity.this, VideoActivity.class);
                i.putExtra("idVideo", videoId);
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.item_busca);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    private void getVideos(String query) {
        String formatedQuery = query.replaceAll(" ", "+");

        progress.setVisibility(View.VISIBLE);
        YoutubeService service = retrofit.create(YoutubeService.class);
        service.getVideos("snippet", "date", "20", YoutubeConfig.YOUTUBE_API_KEY,
                YoutubeConfig.CHANNEL_URL, formatedQuery).enqueue(new Callback<QueryResult>() {
                    @Override
                    public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
                        QueryResult result = response.body();
                        if (result != null && response.isSuccessful()) {
                            videos = result.getItems();
                            adapter = new VideoAdapter(videos, getApplicationContext());
                            recyclerVideos.setAdapter(adapter);
                        } else {
                            mostraMensagem("ERRO: " + response.errorBody().toString());
                        }
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<QueryResult> call, Throwable t) {
                        mostraMensagem("Erro ao buscar vídeos: " + t.getLocalizedMessage());
                    }
                });
    }

    private void mostraMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
