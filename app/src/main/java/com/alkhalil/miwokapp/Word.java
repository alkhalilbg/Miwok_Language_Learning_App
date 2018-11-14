package com.alkhalil.miwokapp;

public class Word {

    String mDefaultWord;
    String mMiwokTranslation;
    int mImageResorceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    int mAudioResourceId;



    public Word(String miwokTranslation , String defaultWord, int imageResorceId, int audioResourceId){

        this.mDefaultWord = defaultWord;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResorceId = imageResorceId;
        this.mAudioResourceId = audioResourceId;
    }

    public Word(String miwokTranslation, String defaultWord, int audioResourceId){

        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultWord = defaultWord;
        this.mAudioResourceId = audioResourceId;
    }

    public String getDefaultWord() {
        return mDefaultWord;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResorceId(){
        return mImageResorceId;
    }

    public boolean hasImage(){
        return mImageResorceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
