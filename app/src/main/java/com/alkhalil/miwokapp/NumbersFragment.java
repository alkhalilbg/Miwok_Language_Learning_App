package com.alkhalil.miwokapp;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    MediaPlayer.OnCompletionListener mCompletionListener = new  MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){

            releas();

        }
    };

    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releas();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }

        }
    };


    public NumbersFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);



        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> wordsArrayList = new ArrayList<Word>();
        wordsArrayList.add(new Word("lutti", "one", R.drawable.number_one, R.raw.number_one));
        wordsArrayList.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        wordsArrayList.add(new Word("tolookosu","three", R.drawable.number_three, R.raw.number_three));
        wordsArrayList.add(new Word( "oyyisa","four", R.drawable.number_four, R.raw.number_four));
        wordsArrayList.add(new Word("massokka","five",  R.drawable.number_five, R.raw.number_five));
        wordsArrayList.add(new Word( "temmokka","six", R.drawable.number_six, R.raw.number_six));
        wordsArrayList.add(new Word( "kenekaku","seven", R.drawable.number_seven,R.raw.number_seven));
        wordsArrayList.add(new Word( "kawinta","eight", R.drawable.number_eight, R.raw.number_eight));
        wordsArrayList.add(new Word( "wo'e","nine", R.drawable.number_nine, R.raw.number_nine));
        wordsArrayList.add(new Word( "na'aacha","ten", R.drawable.number_ten, R.raw.number_ten));



        WordAdapter itemsAdapter = new WordAdapter(getActivity(), wordsArrayList, R.color.numbers_color);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word currentPosition = wordsArrayList.get(position);

                releas();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), currentPosition.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }



            }
        });

        return rootView;

    }


    public void releas(){
        if(mMediaPlayer != null){

            mMediaPlayer.release();

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        releas();
    }
}
