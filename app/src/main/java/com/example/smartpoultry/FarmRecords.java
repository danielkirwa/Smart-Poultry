package com.example.smartpoultry;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.common.collect.Table;

import java.util.ArrayList;

public class FarmRecords extends AppCompatActivity {
    DbHelper dbHelper;
    TextView farmType,smallType,smallPercent,largeType,largePercent;
    TextView eggCollection,allEggs,goodEggs,badEggs,averageCollection,averageBad,damageRate;
    TextView allFlocks,chickenFlock,chickFlock,allPoultry,chickenNumber,chickNumber;
    int totalChicks,totalChicken,totalPoultry;
    int totalEggs,totalGoodEggs,totalBadEggs,totalCollection;
    int totalFlocks,totalChickenFlock,totalChickFlock;
    double chickPercent,chickenPercent;
    double averageEggs,averageBadEggs,eggDamage;
    BarChart barChart,barChartNumbers,barChartEggNumbers;
    PieChart eggPieChart;
    ArrayList<BarEntry> barEntryArrayList,barEntryNumbersArrayList,barEntryEggArrayList;
    ArrayList<String> labelsNames,labelsNamesNumbers,labelsPieChart,labelsEggNumbers;
    ArrayList<FlockGraph> flockGraphArrayList = new ArrayList<>();
    ArrayList<FlockNumbersGraph> flockNumbersGraphArrayList = new ArrayList<>();
    ArrayList<EggGraph> eggGraphArrayList = new ArrayList<>();
    ArrayList<PieEntry> pieEntryArrayList = new ArrayList<>();

    // hack variables
    String eggCollectionCount;
    String hktotalChickenFlock,hktotalChickFlock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_records);

        dbHelper = new DbHelper(this);

        farmType = findViewById(R.id.rp_farm_type);
        smallType = findViewById(R.id.rp_small_type);
        smallPercent = findViewById(R.id.rp_small_percent);
        largeType = findViewById(R.id.rp_large_type);
        largePercent = findViewById(R.id.rp_large_percent);
        eggCollection = findViewById(R.id.rp_total_egg_collection);
        allEggs = findViewById(R.id.rp_total_egg_collected);
        goodEggs = findViewById(R.id.rp_total_good_eggs);
        badEggs = findViewById(R.id.rp_total_bad_eggs);
        averageCollection = findViewById(R.id.rp_average_collection);
        averageBad = findViewById(R.id.rp_average_bad_eggs);
        damageRate = findViewById(R.id.rp_egg_damage_rate);
        allFlocks = findViewById(R.id.rp_total_flocks);
        chickenFlock = findViewById(R.id.rp_total_chicken_flock);
        chickFlock = findViewById(R.id.rp_total_chick_flock);
        allPoultry = findViewById(R.id.rp_total_poultry_number);
        chickenNumber = findViewById(R.id.rp_total_chicken_number);
        chickNumber = findViewById(R.id.rp_total_chick_number);
        barChart = findViewById(R.id.flock_bar_graph);
        barChartNumbers = findViewById(R.id.flock_number_bar_graph);
        //eggPieChart = findViewById(R.id.egg_pie_graph);
        barChartEggNumbers = findViewById(R.id.egg_bar_graph);


        // compute farm type
        farmTypeDisplay();
        farmEggReport();
        farmPoultryReport();

        barEntryArrayList = new ArrayList<>();
        labelsNames = new ArrayList<>();


        barEntryArrayList.clear();
         labelsNames.clear();


        fillFlockGraph();
        for (int i = 0 ; i < flockGraphArrayList.size();i ++){
            String flock = flockGraphArrayList.get(i).getFlock();
            int flockNumbers = flockGraphArrayList.get(i).getFlockNumbers();
            barEntryArrayList.add(new BarEntry(i,flockNumbers));
            labelsNames.add(flock);
        }
        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"All flocks ");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Description description = new Description();
        //description.setText("Flocks");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        // axi
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsNames));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelsNames.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();

            // graph for numbers
        barEntryNumbersArrayList = new ArrayList<>();
        labelsNamesNumbers = new ArrayList<>();

        barEntryNumbersArrayList.clear();
        labelsNamesNumbers.clear();


        fillFlockNumbersGraph();
        for (int i = 0 ; i < flockNumbersGraphArrayList.size();i ++){
            String flockName = flockNumbersGraphArrayList.get(i).getFlockNames();
            int flockNumberCount = flockNumbersGraphArrayList.get(i).getFlockNumberCount();
            barEntryNumbersArrayList.add(new BarEntry(i,flockNumberCount));
            labelsNamesNumbers.add(flockName);
        }
        BarDataSet barDataSetNumbers = new BarDataSet(barEntryNumbersArrayList,"All flocks numbers ");
        barDataSetNumbers.setColors(ColorTemplate.COLORFUL_COLORS);
        //Description description = new Description();
        //description.setText("Flocks");
        BarData barDataNumbers = new BarData(barDataSetNumbers);
        barChartNumbers.setData(barDataNumbers);
        // axi
        XAxis xAxisNumbers = barChartNumbers.getXAxis();
        xAxisNumbers.setValueFormatter(new IndexAxisValueFormatter(labelsNamesNumbers));
        xAxisNumbers.setPosition(XAxis.XAxisPosition.TOP);
        xAxisNumbers.setDrawGridLines(false);
        xAxisNumbers.setDrawAxisLine(false);
        xAxisNumbers.setGranularity(1f);
        xAxisNumbers.setLabelCount(labelsNamesNumbers.size());
        xAxisNumbers.setLabelRotationAngle(270);
        barChartNumbers.animateY(2000);
        barChartNumbers.invalidate();



        // graph for numbers
        barEntryEggArrayList = new ArrayList<>();
        labelsEggNumbers = new ArrayList<>();

        barEntryEggArrayList.clear();
        labelsEggNumbers.clear();


        fillEggGraph();
        for (int i = 0 ; i < eggGraphArrayList.size();i ++){
            String eggname = eggGraphArrayList.get(i).getPieEggName();
            int eggNumberCount = eggGraphArrayList.get(i).getPieEggNumber();
            barEntryEggArrayList.add(new BarEntry(i,eggNumberCount));
            labelsEggNumbers.add(eggname);
        }
        BarDataSet barDataSetEggNumbers = new BarDataSet(barEntryEggArrayList,"All flocks numbers ");
        barDataSetEggNumbers.setColors(ColorTemplate.COLORFUL_COLORS);
        //Description description = new Description();
        //description.setText("Flocks");
        BarData barDataEggNumbers = new BarData(barDataSetEggNumbers);
        barChartEggNumbers.setData(barDataEggNumbers);
        // axi
        XAxis xAxisEggNumbers = barChartEggNumbers.getXAxis();
        xAxisEggNumbers.setValueFormatter(new IndexAxisValueFormatter(labelsEggNumbers));
        xAxisEggNumbers.setPosition(XAxis.XAxisPosition.TOP);
        xAxisEggNumbers.setDrawGridLines(false);
        xAxisEggNumbers.setDrawAxisLine(false);
        xAxisEggNumbers.setGranularity(1f);
        xAxisEggNumbers.setLabelCount(labelsEggNumbers.size());
        xAxisEggNumbers.setLabelRotationAngle(270);
        barChartEggNumbers.animateY(2000);
        barChartEggNumbers.invalidate();




        // graph for eggs pie chart
        /*pieEntryArrayList = new ArrayList<>();
        labelsPieChart = new ArrayList<>();

        pieEntryArrayList.clear();
        labelsPieChart.clear();


        fillEggGraph();
        for (int i = 0 ; i < eggGraphArrayList.size();i ++){
            String eggName = eggGraphArrayList.get(i).getPieEggName();
            int eggNumberCount = eggGraphArrayList.get(i).getPieEggNumber();
            pieEntryArrayList.add(new PieEntry(i,eggNumberCount));
            labelsPieChart.add(eggName);
        }
        PieDataSet pieDataSetNumbers = new PieDataSet(pieEntryArrayList,"All collection");
        pieDataSetNumbers.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSetNumbers.setDrawValues(true);
        pieDataSetNumbers.setValueTextColor(Color.BLACK);
        PieData pieDataNumbers = new PieData(pieDataSetNumbers);
        pieDataNumbers.setValueFormatter(new PercentFormatter());
        pieDataNumbers.setDrawValues(true);
        pieDataNumbers.setValueTextSize(12f);
        pieDataNumbers.setValueTextColor(Color.BLACK);

        eggPieChart.setData(pieDataNumbers);
        // pie chart design
        eggPieChart.setDrawHoleEnabled(true);
        eggPieChart.setUsePercentValues(true);
        eggPieChart.setCenterText("Egg Collection");
        eggPieChart.setCenterTextSize(18);
        eggPieChart.setEntryLabelTextSize(12);
        eggPieChart.setEntryLabelColor(Color.BLACK);
        eggPieChart.getDescription().setEnabled(true);
        // set legend
        Legend legendPie = eggPieChart.getLegend();
        legendPie.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legendPie.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legendPie.setOrientation(Legend.LegendOrientation.VERTICAL);
        legendPie.setWordWrapEnabled(true);
        legendPie.setDrawInside(false);
        legendPie.setYOffset(5f);

        eggPieChart.animateY(2000);
        eggPieChart.invalidate();*/


    }

    private void farmPoultryReport() {
        try{

            totalChicks = dbHelper.ChickSum();
            totalChicken = dbHelper.ChickenSum();
            totalPoultry = totalChicken + totalChicks;
            hktotalChickFlock = ""+dbHelper.ChickFlockCount();
            hktotalChickenFlock = ""+ dbHelper.ChickenFlockCount();
            totalChickenFlock = Integer.parseInt(hktotalChickenFlock);
            totalChickFlock = Integer.parseInt(hktotalChickFlock);
            totalFlocks = totalChickenFlock + totalChickFlock;


            allFlocks.setText(""+totalFlocks);
            chickenFlock.setText(""+totalChickenFlock);
            chickFlock.setText(""+ totalChickFlock);
            allPoultry.setText(""+totalPoultry);
            chickenNumber.setText(""+totalChicken);
            chickNumber.setText(""+totalChicks);
        }catch(Exception ex){
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_LONG).show();
        }

    }

    // egg report
    private void farmEggReport() {
        try {
            totalGoodEggs = dbHelper.GoodEggSum();
            totalBadEggs = dbHelper.BadEggSum();
            totalEggs = totalBadEggs + totalGoodEggs;
            eggCollectionCount = ""+dbHelper.EggCollectionCount();
            totalCollection = Integer.parseInt(eggCollectionCount);
            // compute
            averageEggs = totalEggs / totalCollection;
            averageBadEggs = totalBadEggs / totalCollection;
            eggDamage = (totalBadEggs * 100)/totalEggs;

            // display data
            eggCollection.setText(eggCollectionCount);
            allEggs.setText("" + totalEggs);
            goodEggs.setText("" + totalGoodEggs);
            badEggs.setText("" + totalBadEggs);
            averageCollection.setText("" + averageEggs);
            averageBad.setText("" + averageBadEggs);
            damageRate.setText("" + eggDamage + "%");


        }catch (Exception ex){
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_LONG).show();
        }

    }

    // display snapshot of sum of data in database
    public void farmTypeDisplay(){

        try {
            totalChicks = dbHelper.ChickSum();
            totalChicken = dbHelper.ChickenSum();
            totalPoultry = totalChicken + totalChicks;

            if(totalChicken >= totalChicks){
                chickenPercent = (totalChicken*100)/totalPoultry;
                chickPercent = (totalChicks*100)/totalPoultry;
                farmType.setText("Chicken farm");
                largeType.setText("Chicken");
                smallType.setText("Chicks");
                largePercent.setText("" + chickenPercent + "%");

                smallPercent.setText("" + chickPercent + "%");
            }else{
                chickenPercent = (totalChicken*100)/totalPoultry;
                chickPercent = (totalChicks*100)/totalPoultry;
                farmType.setText("Chick farm");
                largeType.setText("Chick");
                smallType.setText("Chicken");
                largePercent.setText("" + chickPercent + "%");

                smallPercent.setText("" + chickenPercent + "%");

            }




        }catch(Exception ex){
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_LONG).show();
        }
    }

    // graph data method
    private void fillFlockGraph(){
        flockGraphArrayList.clear();
        flockGraphArrayList.add(new FlockGraph( "Total Flock", totalFlocks));
        flockGraphArrayList.add(new FlockGraph( "Chicken Flock", totalChickenFlock));
        flockGraphArrayList.add(new FlockGraph( "Chick Flock", totalChickFlock));
        flockGraphArrayList.add(new FlockGraph( "", 0));

    }
    // graph data method
    private void fillFlockNumbersGraph(){
        flockNumbersGraphArrayList.clear();
        flockNumbersGraphArrayList.add(new FlockNumbersGraph( "Total Poultry", totalPoultry));
        flockNumbersGraphArrayList.add(new FlockNumbersGraph( "Total Chicken", totalChicken));
        flockNumbersGraphArrayList.add(new FlockNumbersGraph( "Total Chick", totalChicks));
        flockNumbersGraphArrayList.add(new FlockNumbersGraph( "", 0));

    }

    // graph data method
    private void fillEggGraph(){
        eggGraphArrayList.clear();
        eggGraphArrayList.add(new EggGraph( "Total Eggs", totalEggs));
        eggGraphArrayList.add(new EggGraph( "Good Eggs", totalGoodEggs));
        eggGraphArrayList.add(new EggGraph( "Bad eggs", totalBadEggs));
        eggGraphArrayList.add(new EggGraph( "", 0));



    }
}
