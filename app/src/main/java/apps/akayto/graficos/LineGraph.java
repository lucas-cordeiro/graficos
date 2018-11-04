package apps.akayto.graficos;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class LineGraph extends AppCompatActivity /*implements OnChartGestureListener, OnChartValueSelectedListener  */{

    private static final String TAG = "LineGraph";

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        mChart = findViewById(R.id.lineChart);
/*
        mChart.setOnChartGestureListener(LineGraph.this);
        mChart.setOnChartValueSelectedListener(LineGraph.this);
*/
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        mChart.setDescription(null);

        LimitLine lowerLimitLine = new LimitLine(25f, "Poucas Vendas");
        lowerLimitLine.setLineWidth(2f); // width da linha
        lowerLimitLine.enableDashedLine(10f,10f,0f); //opcões da linha
        lowerLimitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP); //posição do label
        lowerLimitLine.setTextSize(12f); //tamanho do texto
        lowerLimitLine.setLineColor(Color.rgb(63,81,181));

        LimitLine upperLimitLine = new LimitLine(45f, "Muitas Vendas");
        upperLimitLine.setLineWidth(2f);
        upperLimitLine.enableDashedLine(10f,10f,10f);
        upperLimitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upperLimitLine.setTextSize(12f);
        upperLimitLine.setLineColor(Color.rgb(63,81,181));

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(lowerLimitLine);
        leftAxis.addLimitLine(upperLimitLine);
        //leftAxis.setAxisMaximum(60f); //Máximo que irá mostrar
        //leftAxis.setAxisMinimum(10f); //Mínimo que irá mostrar
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);//Habilita / debasilita a barra da direita

        ArrayList<Entry> valores = new ArrayList<>();
        valores.add(new Entry(0,20f));
        valores.add(new Entry(1,30f));
        valores.add(new Entry(2,40f));
        valores.add(new Entry(3,50f));

        LineDataSet dataSet1 = new LineDataSet(valores, "Vendas");
        dataSet1.setFillAlpha(110);
        dataSet1.setValueTextSize(12f);
        dataSet1.setColor(Color.rgb(63,81,181));
        dataSet1.setValueTextColor(Color.rgb(63,81,181));
        dataSet1.setCircleColor(Color.rgb(63,81,181));

        valores = new ArrayList<>();
        valores.add(new Entry(0,15f));
        valores.add(new Entry(1,20f));
        valores.add(new Entry(2,16f));
        valores.add(new Entry(3,18f));

        LineDataSet dataSet2 = new LineDataSet(valores, "Vendas Canceladas");
        dataSet2.setFillAlpha(110);
        dataSet2.setLineWidth(2f);//Largura
        dataSet2.setValueTextSize(12f);
        dataSet2.setColor(Color.rgb(255,64,129));//Line
        dataSet2.setValueTextColor(Color.rgb(255,64,129));//Cor do text
        dataSet2.setCircleColor(Color.rgb(255,64,129)); //Cor do

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);

        LineData data = new LineData(dataSets);
        mChart.setData(data);

        String[] values = new String[]{"1ª Semana", "2ª Semana","3ª Semana", "4ª Semana"};

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setGranularity(1);
        //xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{

        private String[] mValues;

        public MyXAxisValueFormatter(String[] values){
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }
}
