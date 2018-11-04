package apps.akayto.graficos;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);

        //Descrição
        Description description = new Description();
        description.setText("Resumo de vendas do Mês de Junho");
        description.setTextSize(10f);
        description.setTextAlign(Paint.Align.RIGHT);

        pieChart.getDescription().setEnabled(false);
        //pieChart.setDescription(description);

        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        //Hole = circulo no meio
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleRadius(61f);

        //Animação
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        ArrayList<PieEntry> valores = new ArrayList<>();

        valores.add(new PieEntry(10f, "Produto A"));
        valores.add(new PieEntry(20f, "Produto B"));
        valores.add(new PieEntry(30f, "Produto C"));
        valores.add(new PieEntry(40f, "Produto D"));

        PieDataSet dataSet = new PieDataSet(valores, "Valores");

        dataSet.setHighlightEnabled(false);
        dataSet.setSliceSpace(3f); //Espaço entre as marcações/cores
        dataSet.setSelectionShift(2f); //Tamanho+-
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS); //Cores do gráfico
        dataSet.setValueTextColor(Color.WHITE);

        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(15f); //Texto dentro do Gráfico
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);

        Button btnProximo = findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LineGraph.class));
            }
        });
    }
}
