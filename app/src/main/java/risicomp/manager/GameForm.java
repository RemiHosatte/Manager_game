package risicomp.manager;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.FloatRange;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import risicomp.manager.R;
import risicomp.manager.bdd.CategorieBDD;
import risicomp.manager.bdd.GenreBDD;
import risicomp.manager.bdd.JeuBDD;
import risicomp.manager.bdd.Sous_CategorieBDD;
import risicomp.manager.classes.Categorie;
import risicomp.manager.classes.Genre;
import risicomp.manager.classes.Jeu;
import risicomp.manager.classes.Sous_Categorie;

public class GameForm extends AppCompatActivity {
    int spinner_game_genre_selected_id;
    int spinner_game_categorie_selected_id;
    int spinner_game_sous_categorie_selected_id;
    private static int RESULT_LOAD_IMAGE = 1;
    String picturePath;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Permission request
        ActivityCompat.requestPermissions(GameForm.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        //Spinner Genre part
            GenreBDD genreBDD = new GenreBDD(this);
            ArrayList<Genre> genres = genreBDD.getAllGenres();

            final Spinner spinner_genre = (Spinner)findViewById(R.id.spinner_genre);

            ArrayAdapter<Genre> array_adapter_genre = new ArrayAdapter<Genre>(this, android.R.layout.simple_spinner_dropdown_item, genres);
            array_adapter_genre.setDropDownViewResource(R.layout.spinner_item_sub_cat);

        spinner_genre.setAdapter(array_adapter_genre);
            spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Genre genre = (Genre) parent.getSelectedItem();
                spinner_game_genre_selected_id = genre.getId();
            }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
        });

        //Spinner Categorie/Sous_categorie part
            CategorieBDD categorieBDD = new CategorieBDD(this);
            ArrayList<Categorie> categories = categorieBDD.getAllCategories();
            //Init spinner
            final Spinner spinner_categorie = (Spinner)findViewById(R.id.spinner_game_categorie);
            final Spinner spinner_sous_categorie = (Spinner)findViewById(R.id.spinner_game_sous_categorie);
            //Load spinner sous_categorie
            ArrayAdapter<Categorie> array_adapter_categorie = new ArrayAdapter<Categorie>(this, android.R.layout.simple_spinner_dropdown_item, categories);
            array_adapter_categorie.setDropDownViewResource(R.layout.spinner_item_sub_cat);
            spinner_categorie.setAdapter(array_adapter_categorie);

            spinner_categorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Categorie categorie = (Categorie) parent.getSelectedItem();
                    spinner_game_categorie_selected_id = categorie.getId();

                    Sous_CategorieBDD sous_categorieBDD = new Sous_CategorieBDD(getApplicationContext());
                    ArrayList<Sous_Categorie> sous_categories = sous_categorieBDD.getSous_categoryByCategory_id(spinner_game_categorie_selected_id, getApplicationContext());
                    //Load spinner sous_categorie when an item is selected
                    ArrayAdapter<Sous_Categorie> array_adapter_sous_categorie = new ArrayAdapter<Sous_Categorie>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, sous_categories);
                    array_adapter_sous_categorie.setDropDownViewResource(R.layout.spinner_item_sub_cat);
                    spinner_sous_categorie.setAdapter(array_adapter_sous_categorie);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        //Spinner Sous_categorie part
        spinner_sous_categorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sous_Categorie sous_categorie = (Sous_Categorie) parent.getSelectedItem();
                spinner_game_sous_categorie_selected_id = sous_categorie.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Button add image
        final Button button_add_image = (Button) findViewById(R.id.button_add_image);
        button_add_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.game_rating);

        final EditText game_name = (EditText) findViewById(R.id.game_name);

        final EditText game_developpeur = (EditText)findViewById(R.id.game_developpeur);

        final EditText game_editeur = (EditText)findViewById(R.id.game_editeur);

        final EditText game_annee = (EditText)findViewById(R.id.game_annee);

        final EditText game_quantite = (EditText)findViewById(R.id.game_quantite);


        //Button create part
        final Button button_add_new_game = (Button) findViewById(R.id.button_create_new_game);
        button_add_new_game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                boolean state = true;
                if( game_name.getText().toString().trim().equals("")){

                    state = false;
                    game_name.setError( "Un nom est requis" );
                }
                if( game_quantite.getText().toString().trim().equals("")){

                    state = false;
                    game_quantite.setError( "Une quantité est requise" );
                }
                if (filename == null)
                {
                    state = false;
                    Toast.makeText(getApplicationContext(), "Sélectionner une image",
                            Toast.LENGTH_LONG).show();
                }
                if (state == true) {
                        Genre my_genre = new Genre();
                        my_genre.setId(spinner_game_genre_selected_id);

                        Sous_Categorie my_sous_categorie = new Sous_Categorie();
                        my_sous_categorie.setId(spinner_game_sous_categorie_selected_id);
                        Integer game_annee_int = null;
                        try
                        {
                            if(game_annee.getText().toString() != null)
                                game_annee_int = Integer.parseInt(game_annee.getText().toString());
                        }
                        catch (NumberFormatException e)
                        {
                            game_annee_int = null;
                        }
                        System.out.println("Rating: " + (int)ratingBar.getRating());
                        filename = game_name.getText().toString().toLowerCase();
                        Jeu new_game = new Jeu(
                        0,
                        game_name.getText().toString(),
                        filename+".png",
                        my_genre,
                        game_developpeur.getText().toString(),
                        game_editeur.getText().toString(),
                        game_annee_int,
                        (int)ratingBar.getRating(),
                        Integer.parseInt(game_quantite.getText().toString()),
                        my_sous_categorie);

                        JeuBDD jeuBDD = new JeuBDD(getApplicationContext());

                        Savefile(filename, picturePath);

                        jeuBDD.insertJeu(new_game);
                    //System.out.println(jeuBDD.getJeuBySous_categorie(1, getApplicationContext()).get(1).getNom());
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             picturePath = cursor.getString(columnIndex);
            System.out.println("Picture path: " + picturePath);
            File f = new File(picturePath);
            filename = f.getName();
            System.out.println("File name: " + filename);

            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.image_jeu);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));


        }


    }

    public void Savefile(String name, String path) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/manage_app_images/games");
        File file = new File(Environment.getExternalStorageDirectory() + "/manage_app_images/games/" + name + ".png");
        boolean success = true;
        if (!direct.exists()) {
            success = direct.mkdirs();
        }
        if (success) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileChannel src = new FileInputStream(path).getChannel();
                FileChannel dst = new FileOutputStream(file).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
