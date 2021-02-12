package entrega0;
import java.math.BigInteger;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JFreeCharScatterChartExample extends JFrame {

	private static final long serialVersionUID = 1L;

	public JFreeCharScatterChartExample(String applicationTitle, String chartTitle, ArrayList<BigInteger> numbers, ArrayList<Long> times) {
		super(applicationTitle);

		// based on the dataset we create the chart
		JFreeChart chart = ChartFactory.createScatterPlot(chartTitle, "Número testeado", "Tiempo de computo", createDataset(numbers, times),
				PlotOrientation.VERTICAL, true, true, true);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(255, 228, 196));

		// Adding chart into a chart panel
		ChartPanel chartPanel = new ChartPanel(chart);

		// settind default size
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));

		// add to contentPane
		setContentPane(chartPanel);
	}

	private XYDataset createDataset(ArrayList<BigInteger> numbers, ArrayList<Long> times) {

		// create the dataset...
		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries chromeValues = new XYSeries("Tiempos");
		for (int i = 0; i< numbers.size(); i++) {
			chromeValues.add(numbers.get(i), times.get(i));
		}
		dataset.addSeries(chromeValues);

		return dataset;

	}
}
