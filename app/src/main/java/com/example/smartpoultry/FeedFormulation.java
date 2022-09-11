package com.example.smartpoultry;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeedFormulation extends AppCompatActivity {
    String feedsToFormulate;
    Spinner feedsspinner;
    Button generateFormula;
    EditText editTextKgs;
    TextView feedsnote,feedformula;
    double markKgs = 100;
    double inputKgs;
    double ratio;
    String newKgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_formulation);

        feedsspinner = findViewById(R.id.feeds_type_spinner);
        generateFormula = findViewById(R.id.btn_generate_formula);
        editTextKgs = findViewById(R.id.txt_feeds_amount);
        feedsnote = findViewById(R.id.tv_formula_note);
        feedformula = findViewById(R.id.tv_formula);




        // spinner array

        final ArrayList<String> feedsTypes = new ArrayList<>();
        feedsTypes.add("Select Feeds Type");
        feedsTypes.add("Layers Chick Mash");
        feedsTypes.add("Layers Growers Mash");
        feedsTypes.add("Layers Mash");
        feedsTypes.add("Broiler Starter Feed");
        feedsTypes.add("Broiler Finisher Feed");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,feedsTypes);
        feedsspinner.setAdapter(adapter);
        feedsspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 feedsToFormulate = feedsTypes.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        generateFormula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // generate formula
                if(feedsToFormulate == "Layers Chick Mash"){
                    showMessage("Layers Chick Mash", "Generate formula for : " + editTextKgs.getText().toString() + " of Layers Chick Mash");
                   double LCMwholemaize = 46;
                   double LCMwheatbran = 13;
                    double LCMwheatpollard = 10;
                    double LCMsunflowermeal = 25;
                    double LCMfishmeal = 2.6;
                    double LCMlime = 2.5;
                    // grams
                    double LCMsalt = 70;
                    double LCMpremix = 40;
                    // acids
                    double LCMtryptophan = 140;
                    double LCMlysine = 20;
                    double LCMmethionine = 20;
                    double LCMthreonine = 140;
                    double LCMgrowthenhancer = 100;
                    double LCMcoccidiostat = 120;
                    double LCMtoxinbinder = 100;
                    // new
                    double NewLCMwholemaize;
                    double NewLCMwheatbran;
                    double NewLCMwheatpollard;
                    double NewLCMsunflowermeal;
                    double NewLCMfishmeal;
                    double NewLCMlime;
                    // grams
                    double NewLCMsalt;
                    double NewLCMpremix;
                    // acids
                    double NewLCMtryptophan;
                    double NewLCMlysine;
                    double NewLCMmethionine;
                    double NewLCMthreonine;
                    double NewLCMgrowthenhancer;
                    double NewLCMcoccidiostat;
                    double NewLCMtoxinbinder;
                    newKgs = editTextKgs.getText().toString();
                    inputKgs = Integer.parseInt(newKgs);
                    ratio = markKgs/inputKgs;

                    // formula
                     NewLCMwholemaize = LCMwholemaize/ratio;
                     NewLCMwheatbran = LCMwheatbran/ratio;
                   NewLCMwheatpollard = LCMwheatpollard/ratio;
                   NewLCMsunflowermeal = LCMsunflowermeal/ratio;
                    NewLCMfishmeal = LCMfishmeal/ratio;
                    NewLCMlime = LCMlime/ratio;
                    // grams
                    NewLCMsalt = LCMsalt/ratio;
                    NewLCMpremix = LCMpremix/ratio;
                    // acids
                    NewLCMtryptophan = LCMtryptophan/ratio;
                    NewLCMlysine = LCMlysine/ratio;
                    NewLCMmethionine = LCMmethionine/ratio;
                     NewLCMthreonine = LCMthreonine/ratio;
                     NewLCMgrowthenhancer = LCMgrowthenhancer/ratio;
                    NewLCMcoccidiostat = LCMcoccidiostat/ratio;
                    NewLCMtoxinbinder = LCMtoxinbinder/ratio;
                    feedsnote.setText("Make sure to mix the ingredients carefully and with accurate measurements ");
                    feedformula.setText(" Formula of "+ newKgs +" kgs of Layers Chick Mash (1-4 weeks old)" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "1. Whole maize : " +  String.format("%.1f",NewLCMwholemaize) + "Kgs" + System.getProperty("line.separator") +
                            "2. Sunflower meal: " + String.format("%.1f",NewLCMsunflowermeal) + "Kgs" + System.getProperty("line.separator") +
                            "3. Wheat bran : " + String.format("%.1f",NewLCMwheatbran) + "Kgs" + System.getProperty("line.separator") +
                            "4. Wheat pollard : " + String.format("%.1f",NewLCMwheatpollard) + "Kgs" + System.getProperty("line.separator") +
                            "5. Fish meal : " + String.format("%.1f",NewLCMfishmeal) + "Kgs"+ System.getProperty("line.separator") +
                            "6. Lime : " + String.format("%.1f",NewLCMlime) + "Kgs"+ System.getProperty("line.separator") +
                            "7. Salt : " + String.format("%.1f",NewLCMsalt) + "g" + System.getProperty("line.separator")+
                            "8. Premix : " + String.format("%.1f",NewLCMpremix) + "g" + System.getProperty("line.separator")+
                            "9. Tryptophan : " + String.format("%.1f",NewLCMtryptophan) + "g" + System.getProperty("line.separator")+
                            "10. Lysine : " + String.format("%.1f",NewLCMlysine) + "g" + System.getProperty("line.separator")+
                            "11. Methionine : " + String.format("%.1f",NewLCMmethionine) + "g" + System.getProperty("line.separator")+
                            "12. Threonine : " + String.format("%.1f",NewLCMthreonine) + "g" + System.getProperty("line.separator")+
                            "13. Growth Enhancer : " + String.format("%.1f",NewLCMgrowthenhancer) + "g" + System.getProperty("line.separator")+
                            "14. Coccidiostat : " + String.format("%.1f",NewLCMcoccidiostat) + "g" + System.getProperty("line.separator")+
                            "15. Toxin Binder : " + String.format("%.1f",NewLCMtoxinbinder) + "g" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "TOTAL : " + newKgs + "Kgs" + System.getProperty("line.separator"));



                }else if(feedsToFormulate == "Layers Growers Mash"){
                    showMessage("Layers Growers Mash", "Generate formula for : " + editTextKgs.getText().toString() + " of Layers Growers Mash");
                    double LGMwholemaize = 14.3;
                    double LGMmaizegerm = 24.3;
                    double LGMwheatbran = 14.3;
                    double LGMwheatpollard = 18.6;
                    double LGMsunflowercake = 15.7;
                    double LGMsoyacake = 4.9;
                    double LGMlime = 2.9;
                    double LGMbonemeal= 1;
                    double LGMfishmeal = 4.3;
                    // grams
                    double LGMsalt = 40;
                    double LGMpremix = 30;
                    double LGMcoccidiostat = 5;
                    double LGMtoxinbinder = 10;
                    double LGMzincbacitracin = 5;
                    // new
                    double NewLGMwholemaize;
                    double NewLGMmaizegerm;
                    double NewLGMwheatbran;
                    double NewLGMwheatpollard;
                    double NewLGMsunflowercake;
                    double NewLGMsoyacake;
                    double NewLGMlime;
                    double NewLGMbonemeal;
                    double NewLGMfishmeal;
                    // grams
                    double NewLGMsalt;
                    double NewLGMpremix ;
                    double NewLGMcoccidiostat;
                    double NewLGMtoxinbinder;
                    double NewLGMzincbacitracin;
                    newKgs = editTextKgs.getText().toString();
                    inputKgs = Integer.parseInt(newKgs);
                    ratio = markKgs/inputKgs;
                    //formula
                    NewLGMwholemaize = LGMwholemaize/ratio;
                    NewLGMmaizegerm = LGMmaizegerm/ratio;
                    NewLGMwheatbran = LGMwheatbran/ratio;
                    NewLGMwheatpollard = LGMwheatpollard/ratio;
                    NewLGMsunflowercake = LGMsunflowercake/ratio;
                    NewLGMsoyacake = LGMsoyacake/ratio;
                    NewLGMlime = LGMlime/ratio;
                    NewLGMbonemeal = LGMbonemeal/ratio;
                    NewLGMfishmeal = LGMfishmeal/ratio;
                    // grams
                    NewLGMsalt = LGMsalt/ratio;
                    NewLGMpremix = LGMpremix/ratio;
                    NewLGMcoccidiostat = LGMcoccidiostat/ratio;
                    NewLGMtoxinbinder = LGMtoxinbinder/ratio;
                    NewLGMzincbacitracin = LGMzincbacitracin/ratio;
                    feedsnote.setText("Make sure to mix the ingredients carefully and with accurate measurements ");
                    feedformula.setText(" Formula of "+ newKgs +" kgs of Layers Growers Mash (4-18 weeks old)" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "1. Maize germ : " +  String.format("%.1f",NewLGMmaizegerm) + "Kgs" + System.getProperty("line.separator") +
                            "2. Wheat pollar: " + String.format("%.1f",NewLGMwheatpollard) + "Kgs" + System.getProperty("line.separator") +
                            "3. Sunflower cake : " + String.format("%.1f",NewLGMsunflowercake) + "Kgs" + System.getProperty("line.separator") +
                            "4. Whole maize : " + String.format("%.1f",NewLGMwholemaize) + "Kgs" + System.getProperty("line.separator") +
                            "5. Wheat bran : " + String.format("%.1f",NewLGMwheatbran) + "Kgs"+ System.getProperty("line.separator") +
                            "6. Soya cake : " + String.format("%.1f",NewLGMsoyacake) + "Kgs"+ System.getProperty("line.separator") +
                            "7. Fish meal : " + String.format("%.1f",NewLGMfishmeal) + "Kgs" + System.getProperty("line.separator")+
                            "8. Lime : " + String.format("%.1f",NewLGMlime) + "Kgs" + System.getProperty("line.separator")+
                            "9. Bone meal : " + String.format("%.1f",NewLGMbonemeal) + "Kgs" + System.getProperty("line.separator")+
                            "10. Salt : " + String.format("%.1f",NewLGMsalt) + "g" + System.getProperty("line.separator")+
                            "11. Premix : " + String.format("%.1f",NewLGMpremix) + "g" + System.getProperty("line.separator")+
                            "12. Toxin Binder : " + String.format("%.1f",NewLGMtoxinbinder) + "g" + System.getProperty("line.separator")+
                            "13. Zinc bacitracin : " + String.format("%.1f",NewLGMzincbacitracin) + "g" + System.getProperty("line.separator")+
                            "14. Coccidiostat : " + String.format("%.1f",NewLGMcoccidiostat) + "g" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "TOTAL  : " + newKgs.toString() + " Kgs" + System.getProperty("line.separator"));


                }else if(feedsToFormulate == "Layers Mash"){
                    showMessage("Layers Mash", "Generate formula for : " + editTextKgs.getText().toString() + " of Layers Mash");

                    double LMmaizegrain = 59;
                    double LMsoyameal = 20;
                    double LMgroundnut = 6;
                    double LMbonemeal = 5;
                    double LMlime = 5;
                    double LMwheatoffals = 4;
                    //grams
                    double LMmethionine = 200;
                    double LMlysine = 200;
                    double LMpremix = 200;
                    double LMsalt = 200;
                    double LMtoxinbinder = 100;
                    double LMlayersmix = 100;
                    //new
                    double NewLMmaizegrain ;
                    double NewLMsoyameal;
                    double NewLMgroundnut;
                    double NewLMbonemeal;
                    double NewLMlime;
                    double NewLMwheatoffals;
                    //grams
                    double NewLMmethionine;
                    double NewLMlysine;
                    double NewLMpremix;
                    double NewLMsalt;
                    double NewLMtoxinbinder;
                    double NewLMlayersmix;
                    newKgs = editTextKgs.getText().toString();
                    inputKgs = Integer.parseInt(newKgs);
                    ratio = markKgs/inputKgs;

                    NewLMmaizegrain = LMmaizegrain/ratio ;
                    NewLMsoyameal = LMsoyameal/ratio;
                    NewLMgroundnut = LMgroundnut/ratio;
                    NewLMbonemeal = LMbonemeal/ratio;
                    NewLMlime = LMlime/ratio;
                    NewLMwheatoffals = LMwheatoffals/ratio;
                    //grams
                    NewLMmethionine = LMmethionine/ratio;
                    NewLMlysine = LMlysine/ratio;
                    NewLMpremix = LMpremix/ratio;
                    NewLMsalt = LMsalt/ratio;
                    NewLMtoxinbinder = LMtoxinbinder/ratio;
                    NewLMlayersmix = LMlayersmix/ratio;
                    feedsnote.setText("Make sure to mix the ingredients carefully and with accurate measurements ");
                    feedformula.setText(" Formula of "+ newKgs +" kgs of Layers  Mash (above 18 weeks old)" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "1. Maize grains: " +  String.format("%.1f",NewLMmaizegrain) + "Kgs" + System.getProperty("line.separator") +
                            "2. Soya meal: " + String.format("%.1f",NewLMsoyameal) + "Kgs" + System.getProperty("line.separator") +
                            "3. Groundnut : " + String.format("%.1f",NewLMgroundnut) + "Kgs" + System.getProperty("line.separator") +
                            "4. Bone meal : " + String.format("%.1f",NewLMbonemeal) + "Kgs" + System.getProperty("line.separator") +
                            "5. Lime : " + String.format("%.1f",NewLMlime) + "Kgs"+ System.getProperty("line.separator") +
                            "6. Wheat offals : " + String.format("%.1f",NewLMwheatoffals) + "Kgs"+ System.getProperty("line.separator") +
                            "7. Methionine : " + String.format("%.1f",NewLMmethionine) + "g" + System.getProperty("line.separator")+
                            "8. Lysine : " + String.format("%.1f",NewLMlysine) + "g" + System.getProperty("line.separator")+
                            "9. Premix : " + String.format("%.1f",NewLMpremix) + "g" + System.getProperty("line.separator")+
                            "10. Salt : " + String.format("%.1f",NewLMsalt) + "g" + System.getProperty("line.separator")+
                            "11. Layers mix : " + String.format("%.1f",NewLMlayersmix) + "g" + System.getProperty("line.separator")+
                            "12. Toxin Binder : " + String.format("%.1f",NewLMtoxinbinder) + "g" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "TOTAL  : " + newKgs.toString() + " Kgs" + System.getProperty("line.separator"));




                }else if(feedsToFormulate == "Broiler Starter Feed"){

                    showMessage("Broiler Starter Feed", "Generate formula for : " + editTextKgs.getText().toString() + " of Broiler Starter Feed");
                    double Bmwholemaize = 57.2;
                    double BMfishmeal = 17.2;
                    double BMsoyabean = 20;
                    double BMlime = 5;
                    // grams
                    double BMpewmix = 140;
                    // Acids
                    double BMlysine = 70;
                    double BMthreonine = 70;
                    // new figures
                    double NewBmwholemaize ;
                    double NewBMfishmeal;
                    double NewBMsoyabean;
                    double NewBMlime;
                    // grams
                    double NewBMpewmix;
                    // Acids
                    double NewBMlysine;
                    double NewBMthreonine;
                    newKgs = editTextKgs.getText().toString();
                    inputKgs = Integer.parseInt(newKgs);
                    ratio = markKgs/inputKgs;
                    NewBmwholemaize =  Bmwholemaize/ratio;
                    NewBMfishmeal = BMfishmeal/ratio;
                    NewBMsoyabean = BMsoyabean/ratio;
                    NewBMlime = BMlime/ratio;
                    // grams
                    NewBMpewmix = BMpewmix/ratio;
                    NewBMlysine = BMlysine/ratio;
                    NewBMthreonine = BMthreonine/ratio;
                    feedsnote.setText("Make sure to mix the ingredients carefully and with accurate measurements ");
                    feedformula.setText(" Formula of "+ newKgs +" kgs of Broiler starter feeds (1-4 weeks old)" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "1. Whole maize : " +  String.format("%.1f",NewBmwholemaize) + "Kgs" + System.getProperty("line.separator") +
                            "2. Fish meal : " + String.format("%.1f",NewBMfishmeal)  + "Kgs" + System.getProperty("line.separator") +
                            "3. Soya beans : " + String.format("%.1f",NewBMsoyabean)  + "Kgs" + System.getProperty("line.separator") +
                            "4. Lime : " + String.format("%.1f",NewBMlime)  + "Kgs" + System.getProperty("line.separator") +
                            "5. Premix : " + String.format("%.1f",NewBMpewmix)  + "g"+ System.getProperty("line.separator") +
                            "6. Lysine : " + String.format("%.1f",NewBMlysine)  + "g"+ System.getProperty("line.separator") +
                            "7. Threonine : " + String.format("%.1f",NewBMthreonine)  + "g" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "TOTAL  : " + newKgs.toString() + " Kgs" + System.getProperty("line.separator"));


                }else if(feedsToFormulate == "Broiler Finisher Feed") {


                        showMessage("Broiler Finisher Feed", "Generate formula for : " + editTextKgs.getText().toString() + " of Broiler Finisher Feed");

                    double BFFwholemaize = 14.5;
                    double BFFmaizegerm = 24;
                    double BFFwheatbran = 14.4;
                    double BFFwheatpollard = 19;
                    double BFFgroundnutcake = 16;
                    double BFFfishmeal = 2.2;
                    double BFFsoyameal = 5;
                    double BFFlime = 2.8;
                    //grams
                    double BFFbonemeal = 90;
                    double BFFsalt = 30;
                    double BFFpremix = 20;
                    double BFFcoccidiostat = 10;
                    double BFFzincbacitracin = 10;
                    //new
                    double NewBFFwholemaize;
                    double NewBFFmaizegerm;
                    double NewBFFwheatbran;
                    double NewBFFwheatpollard;
                    double NewBFFgroundnutcake;
                    double NewBFFfishmeal;
                    double NewBFFsoyameal;
                    double NewBFFlime;
                    //grams
                    double NewBFFbonemeal;
                    double NewBFFsalt;
                    double NewBFFpremix;
                    double NewBFFcoccidiostat;
                    double NewBFFzincbacitracin;
                    newKgs = editTextKgs.getText().toString();
                    inputKgs = Integer.parseInt(newKgs);
                    ratio = markKgs/inputKgs;
                    //formula
                    NewBFFwholemaize = BFFwholemaize/ratio;
                    NewBFFmaizegerm = BFFmaizegerm/ratio;
                    NewBFFwheatbran = BFFwheatbran/ratio;
                    NewBFFwheatpollard = BFFwheatpollard/ratio;
                    NewBFFgroundnutcake = BFFgroundnutcake/ratio;
                    NewBFFfishmeal = BFFfishmeal/ratio;
                    NewBFFsoyameal = BFFsoyameal/ratio;
                    NewBFFlime = BFFlime/ratio;
                    //grams
                    NewBFFbonemeal = BFFbonemeal/ratio;
                    NewBFFsalt = BFFsalt/ratio;
                    NewBFFpremix = BFFpremix/ratio;
                    NewBFFcoccidiostat = BFFcoccidiostat/ratio;
                    NewBFFzincbacitracin = BFFzincbacitracin/ratio;
                    feedsnote.setText("Make sure to mix the ingredients carefully and with accurate measurements ");
                    feedformula.setText(" Formula of "+ newKgs +" kgs of Broiler finisher" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "1. Maize gern: " +  String.format("%.1f",NewBFFmaizegerm) + "Kgs" + System.getProperty("line.separator") +
                            "2. Wheat pollard: " + String.format("%.1f",NewBFFwheatpollard) + "Kgs" + System.getProperty("line.separator") +
                            "3. Groundnut : " + String.format("%.1f",NewBFFgroundnutcake) + "Kgs" + System.getProperty("line.separator") +
                            "4. Whole maize : " + String.format("%.1f",NewBFFwholemaize) + "Kgs" + System.getProperty("line.separator") +
                            "5. Wheat bran : " + String.format("%.1f",NewBFFwheatbran) + "Kgs"+ System.getProperty("line.separator") +
                            "6.Soya mela : " + String.format("%.1f",NewBFFsoyameal) + "Kgs"+ System.getProperty("line.separator") +
                            "7. Lime : " + String.format("%.1f",NewBFFlime) + "Kgs" + System.getProperty("line.separator")+
                            "8. Fish meal : " + String.format("%.1f",NewBFFfishmeal) + "Kgs" + System.getProperty("line.separator")+
                            "9. Bone meal : " + String.format("%.1f",NewBFFbonemeal) + "g" + System.getProperty("line.separator")+
                            "10. Salt : " + String.format("%.1f",NewBFFsalt) + "g" + System.getProperty("line.separator")+
                            "11. Premix : " + String.format("%.1f",NewBFFpremix) + "g" + System.getProperty("line.separator")+
                            "12. Coccidiostat : " + String.format("%.1f",NewBFFcoccidiostat) + "g" + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                            "TOTAL  : " + newKgs.toString() + " Kgs" + System.getProperty("line.separator"));




                }else{

                }
            }
        });




    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(getResources().getDrawable(R.drawable.feed_mixer));
        builder.setPositiveButton("OK",null);
        builder.show();
    }


}