package com.android.demo.prashant.recyclerview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 5/23/2016.
 */
@SuppressWarnings("FieldCanBeLocal")
public class ModelClass {
    private String name;
    private String tagline;
    private String photoId;

    public ModelClass() {
    }

    public ModelClass(String name, String tagline) {
        this.name = name;
        this.tagline = tagline;
    }

    public ModelClass(String name, String tagline, String photoId) {
        this.name = name;
        this.tagline = tagline;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public List<ModelClass> initializeData() {
        List<ModelClass> modelList = new ArrayList<>();
        modelList.add(new ModelClass("Emma Wilson", "Born to win", "http://api.learn2crack.com/android/images/marshmallow.png"));
        modelList.add(new ModelClass("Lavery Maiss", "No pain no gain", "http://api.learn2crack.com/android/images/lollipop.png"));
        modelList.add(new ModelClass("Lillie Watts", "keep clam and win the game", "http://api.learn2crack.com/android/images/kitkat.png"));
        modelList.add(new ModelClass("Prashant", "just do it", "http://api.learn2crack.com/android/images/jellybean.png"));
        modelList.add(new ModelClass("ABC", "be original", "http://api.learn2crack.com/android/images/icecream.png"));
        modelList.add(new ModelClass("XYZ", "go alone", "http://api.learn2crack.com/android/images/honey.png"));
        modelList.add(new ModelClass("ZCCV", "no status", "http://api.learn2crack.com/android/images/ginger.png"));
        modelList.add(new ModelClass("123", "go alone", "http://api.learn2crack.com/android/images/froyo.png"));
        modelList.add(new ModelClass("456", "no status", "http://api.learn2crack.com/android/images/eclair.png"));
        return modelList;
    }
}
