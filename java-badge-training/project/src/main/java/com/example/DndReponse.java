package com.example;

import java.util.ArrayList;
import java.util.Map;

public class DndReponse {

    private String _id;
    private double index;
    private String name;
    private ArrayList<String> desc;
    private ArrayList<String> higher_level;
    private String page;
    private String range;
    private ArrayList<String> components;
    private String material;
    private String ritual;
    private String duration;
    private String concentration;
    private String casting_time;
    private double level;
    private Map school;
    private ArrayList<String> classes;
    private ArrayList<String> subclasses;
    private String url;

    public DndReponse() { }

    public DndReponse(Map<String, Object> map) {
        this._id = (String)map.get("_id");
        this.index = Double.parseDouble(map.get("index").toString());
        this.name = (String)map.get("name");
        this.desc = (ArrayList<String>)map.get("desc");
        this.higher_level = (ArrayList<String>)map.get("higher_level");
        this.page = (String)map.get("page");
        this.range = (String)map.get("range");
        this.components = (ArrayList<String>)map.get("components");
        this.material = (String)map.get("material");
        this.ritual = (String)map.get("ritual");
        this.duration = (String)map.get("duration");
        this.concentration = (String)map.get("concentration");
        this.casting_time = (String)map.get("casting_time");
        this.level = (Double)map.get("level");
        this.school = (Map<String, String>)map.get("school");
        this.classes = (ArrayList<String>)map.get("classes");
        this.subclasses = (ArrayList<String>)map.get("subclasses");
        this.url = (String)map.get("url");
    }

    @Override
    public String toString() {
        String stringify = "";
        stringify += "\nResponse Object from " + this.url;
        stringify += "\n\tid: " + this._id;
        stringify += "\n\tindex: " + this.index;
        stringify += "\n\tname: " + this.name;
        stringify += "\n\tdescription: " + this.desc;
        stringify += "\n\thigher level stats: " + this.higher_level;
        stringify += "\n\tpage: " + this.page;
        stringify += "\n\trange: " + this.range;
        stringify += "\n\tcomponents: " + this.components;
        stringify += "\n\tmaterial: " + this.material;
        stringify += "\n\tritual: " + this.ritual;
        stringify += "\n\tduration: " + this.duration;
        stringify += "\n\tconcentration: " + this.concentration;
        stringify += "\n\tcasting_time: " + this.casting_time;
        stringify += "\n\tlevel: " + this.level;
        stringify += "\n\tschool: " + this.school;
        stringify += "\n\tclasses: " + this.classes;
        stringify += "\n\tsubclasses: " + this.subclasses;
        return stringify;
    }
}
