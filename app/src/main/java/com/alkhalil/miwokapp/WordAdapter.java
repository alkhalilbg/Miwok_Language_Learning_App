package com.alkhalil.miwokapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> wordsArrayList, int colorResourceId){
        super(context, 0, wordsArrayList);

        this.mColorResourceId = colorResourceId;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Word currentWord = getItem(position);

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView defultWord = listItemView.findViewById(R.id.default_word_id);
        defultWord.setText(currentWord.getDefaultWord());

        TextView miwokWord = listItemView.findViewById(R.id.miwok_word_id);
        miwokWord.setText(currentWord.getMiwokTranslation());

        ImageView imageView = listItemView.findViewById(R.id.image);

        if(currentWord.hasImage()){

            imageView.setImageResource(currentWord.getImageResorceId());
            imageView.setVisibility(View.VISIBLE);

        }
        else {

            imageView.setVisibility(View.GONE);

        }


        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        View textContainer = listItemView.findViewById(R.id.text_container);
        textContainer.setBackgroundColor(color);

        return listItemView;

    }
}
