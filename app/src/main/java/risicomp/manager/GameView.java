package risicomp.manager;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import risicomp.manager.bdd.JeuBDD;

public class GameView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);
        int id = getIntent().getIntExtra("game_id", 0);
        System.out.println("Game id : " + id);
        JeuBDD monJeu = new JeuBDD(this);
        //game_name
        TextView game_name =(TextView)findViewById(R.id.view_game_name);
        game_name.setText(monJeu.getJeu(id,this).getNom());
        //game_sous_categorie
        TextView game_sous_categorie =(TextView)findViewById(R.id.view_game_sous_categorie);
        game_sous_categorie.setText(monJeu.getJeu(id, this).getSous_categorie().getNom());

        //game_genre
        TextView game_genre =(TextView)findViewById(R.id.view_game_genre);
        game_genre.setText(monJeu.getJeu(id, this).getGenre().getNom());

        //game_developpeur
        TextView game_developpeur =(TextView)findViewById(R.id.view_game_developpeur);
        game_developpeur.setText(monJeu.getJeu(id, this).getDeveloppeur());

        //game_editeur
        TextView game_editeur =(TextView)findViewById(R.id.view_game_editeur);
        game_editeur.setText(monJeu.getJeu(id, this).getEditeur());

        //game_annee
        TextView game_annee =(TextView)findViewById(R.id.view_game_annee);
        game_annee.setText(String.valueOf(monJeu.getJeu(id, this).getAnnee()));

        //game_quantite
        TextView game_quantite =(TextView)findViewById(R.id.view_game_quantite);
        game_quantite.setText(String.valueOf(monJeu.getJeu(id, this).getQuantité()));


        ImageView game_image =  (ImageView)findViewById(R.id.view_game_image);
        game_image.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/manage_app_images/games/" + monJeu.getJeu(id, this).getImage_path()));

        ImageView game_categorie_image =  (ImageView)findViewById(R.id.view_game_categorie_image);
        Resources res = getResources();
        int resID = res.getIdentifier(monJeu.getJeu(id, this).getSous_categorie().getCategorie().getImage_path() , "drawable", getPackageName());
        game_categorie_image.setImageResource(resID);

    }
}
