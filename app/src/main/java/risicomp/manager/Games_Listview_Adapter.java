package risicomp.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.List;

import risicomp.manager.classes.Jeu;

/**
 * Created by PC on 27/10/2017.
 */

public class Games_Listview_Adapter extends ArrayAdapter<Jeu> {


    //tweets est la liste des models à afficher
    public Games_Listview_Adapter(Context context, List<Jeu> jeux) {
        super(context, 0, jeux);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.games_list_row_item, parent, false);
        }
        JeuxViewHolder viewHolder = (JeuxViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new JeuxViewHolder();
            viewHolder.item_game_name = (TextView) convertView.findViewById(R.id.item_game_name);
            viewHolder.item_game_image = (ImageView) convertView.findViewById(R.id.item_game_image);
            viewHolder.item_game_rating = (RatingBar) convertView.findViewById(R.id.item_game_rating);

            convertView.setTag(viewHolder);
        }
        Jeu mesJeux = getItem(position);
        System.out.println("ID: "+ mesJeux.getId());

        viewHolder.item_game_name.setText(mesJeux.getNom());
        viewHolder.item_game_rating.setRating(mesJeux.getRarete());
        viewHolder.item_game_image.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/manage_app_images/games/" + mesJeux.getImage_path()));

        return convertView;
    }

    private class JeuxViewHolder{
        public TextView item_game_name;
        public ImageView item_game_image;
        public RatingBar item_game_rating;
    }
}