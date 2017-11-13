package risicomp.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import risicomp.manager.R;
import risicomp.manager.bdd.CategorieBDD;
import risicomp.manager.bdd.JeuBDD;
import risicomp.manager.bdd.Sous_CategorieBDD;
import risicomp.manager.classes.Categorie;
import risicomp.manager.classes.Genre;
import risicomp.manager.classes.Jeu;
import risicomp.manager.classes.Sous_Categorie;

/**
 * Created by PC on 24/10/2017.
 */

public class GameList extends AppCompatActivity {
    Spinner spinner;
    int spinner_game_categorie_selected_id;
    private ListView game_listview;
    Games_Listview_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        //Add back arrow
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Define title of ActionBar
            int id = getIntent().getIntExtra("id_categorie", 0);
            CategorieBDD catBDD = new CategorieBDD(this);
            this.setTitle("Games : " + catBDD.getCategorie(id).getNom());

        //Load spinner
        load_spinner_sous_categories(id);

        //Load game's ListView
        load_list_games(id);
        //Load this event if sous_categorie change
        update_list_games();

        //OnClick Game
        game_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),GameView.class);
                int id_game = (int)adapter.getItemId(position);
                i.putExtra("game_id", id_game);
                startActivity(i);
            }
        });
    }

    public void load_spinner_sous_categories(int id)
    {
        //Spinner with sous_categorie
        spinner = (Spinner) findViewById(R.id.spinner_sous_categories);
        //Call sous_categorie object
        Sous_CategorieBDD sous_catBDD = new Sous_CategorieBDD(this);
        List<Sous_Categorie> sous_categories = sous_catBDD.getSous_categoryByCategory_id(id,this);
        //Array Adapter
            ArrayAdapter<Sous_Categorie> dataAdapter = new ArrayAdapter<Sous_Categorie>(this, android.R.layout.simple_spinner_dropdown_item, sous_categories);
            dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);

    }


    public void load_list_games(int id) {
        JeuBDD jeuBDD = new JeuBDD(this);
        List<Jeu> games = jeuBDD.getJeuBySous_categorie(id,this);
        game_listview = (ListView) findViewById(R.id.games_list);

         adapter = new Games_Listview_Adapter(
                 this,
                 games);
        game_listview.setAdapter(adapter);

    }

    public void update_list_games() {

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Sous_Categorie sous_categorie = (Sous_Categorie) parent.getSelectedItem();
                    spinner_game_categorie_selected_id = sous_categorie.getId();
                    load_list_games(spinner_game_categorie_selected_id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

    }
}