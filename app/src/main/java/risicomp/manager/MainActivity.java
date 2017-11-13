package risicomp.manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import risicomp.manager.bdd.CategorieBDD;
import risicomp.manager.bdd.TypeBDD;
import risicomp.manager.classes.Categorie;


public class MainActivity extends AppCompatActivity {

    GridView androidGridView;


    List<String> mesCategoriesImagePath= new ArrayList<String>();
    List<Categorie> categories = new ArrayList<Categorie>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEST METHODES PART
        TypeBDD typeBDD = new TypeBDD(this);
        System.out.println(typeBDD.getType(1).getNom());

        //Recuperer list image
        final CategorieBDD catBDD = new CategorieBDD(this);
        //List d'objet Categories
        categories = catBDD.getAllCategories();;
        //List des noms de categories


        for (Categorie cat : categories) {
            String log = "Name: "+cat.getNom();
            // Writing Contacts to log
            mesCategoriesImagePath.add(cat.getImage_path());
        }

        androidGridView = (GridView) findViewById(R.id.gridviewcategorie);
        androidGridView.setAdapter(new ImageAdapterGridView(this));

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                Intent i = new Intent(getApplicationContext(),GameList.class);
                i.putExtra("id_categorie", catBDD.getAllCategories().get(position).getId());
                startActivity(i);
            }
        });

        Button button_add_new_game =(Button)findViewById(R.id.button_add_new_game);
        button_add_new_game.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GameForm.class);
                startActivity(i);
            }
        });
    }



    public class ImageAdapterGridView extends BaseAdapter {
        private Context mContext;

        DisplayMetrics metrics =getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;

        public ImageAdapterGridView(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mesCategoriesImagePath.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView mImageView;
            if (convertView == null) {
                mImageView = new ImageView(mContext);
                mImageView.setLayoutParams(new GridView.LayoutParams(screenWidth/3, screenWidth/3));
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mImageView.setPadding(16, 16, 16, 16);

            } else {
                mImageView = (ImageView) convertView;
            }

            Resources res = getResources();
            //Get image with the image name
            String mDrawableName = mesCategoriesImagePath.get(position);
            int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
            mImageView.setImageResource(resID);

            return mImageView;
        }
    }

}

