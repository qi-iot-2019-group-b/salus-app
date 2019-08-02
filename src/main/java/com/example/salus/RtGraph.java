//package com.example.salus;
//
//import android.graphics.Color;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lecho.lib.hellocharts.model.Axis;
//import lecho.lib.hellocharts.model.Line;
//import lecho.lib.hellocharts.model.LineChartData;
//import lecho.lib.hellocharts.model.PointValue;
//import lecho.lib.hellocharts.model.Viewport;
//import lecho.lib.hellocharts.view.LineChartView;
//
//public class RtGraph extends AppCompatActivity{
//    Integer aqi, heartRate;
//    LineChartView graph;
//    int[] axisData = {190730, 190731, 190801, 190802};
//    int[] yAxisData = {0, 50, 100, 150, 200, 300};
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.rt_graph);
//
////        aqi = 30;
////        heartRate = 150;
//        graph = findViewById(R.id.chart);
//
//        List yAxisValues = new ArrayList();
//        List axisValues = new ArrayList();
////        Line line = new Line(yAxisValues);
//        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));
//
//        for (int i = 0; i < axisData.length; i++) {
//            axisValues.add(new PointValue(i, axisData[i]));
//        }
//
//        for (int i = 0; i < yAxisData.length; i++) {
//            yAxisValues.add(new PointValue(i, yAxisData[i]));
//        }
//
//        //add line
//        List lines = new ArrayList();
//        lines.add(line);
//
//        LineChartData data = new LineChartData();
//        data.setLines(lines);
//
//        //  x_data
//        Axis axis = new Axis();
//        axis.setValues(axisValues);
//        data.setAxisXBottom(axis);
//        axis.setTextSize(16);
//        axis.setTextColor(Color.parseColor("#000000"));
//        axis.setName("Time");
//
//        //  y_data
//        Axis yAxis = new Axis();
//        data.setAxisYLeft(yAxis);
//        yAxis.setTextSize(16);
//        yAxis.setTextColor(Color.parseColor("#000000"));
//        yAxis.setName("Range");
//
//        graph.setLineChartData(data);
//        Viewport viewport = new Viewport(graph.getMaximumViewport());
//        viewport.top = 300;
//        graph.setMaximumViewport(viewport);
//        graph.setCurrentViewport(viewport);
//    }
//}
