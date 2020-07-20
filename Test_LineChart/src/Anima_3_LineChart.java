import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Anima_3_LineChart extends Application {

	private LineChart<Number, Number> chart;
	private Series<Number, Number> minuteDataSeries;
	private NumberAxis xAxis;
	private Timeline animation;
	private ArrayList<ArrayList<Integer>> list;
	int i = 0;

	public Anima_3_LineChart() {
		// 6 minutes data per frame
		final KeyFrame frame = new KeyFrame(Duration.millis(1000 / 60), (ActionEvent actionEvent) -> {
			for (int count = 0; count < 6; count++) {
				plotTime();
			}
		});
		animation = new Timeline();
		animation.getKeyFrames().add(frame);
		animation.setCycleCount(Animation.INDEFINITE);
	}

	public Parent createContent() {
		xAxis = new NumberAxis(0, 5 * 5000, 5000);
		final NumberAxis yAxis = new NumberAxis(0, 40000, 20000);
		chart = new LineChart<>(xAxis, yAxis);
		// setup chart
		chart.setCreateSymbols(false);
		chart.setAnimated(false);
		final String stockLineChartCss = getClass().getResource("TestCss.css").toExternalForm();
		chart.getStylesheets().add(stockLineChartCss);
		// add starting data
		minuteDataSeries = new Series<>();
		chart.getData().add(minuteDataSeries);
		return chart;
	}

	private void plotTime() {
		if (i >= 5 * 5000 && i < 18 * 5000) {
			if (i % 5000 == 0) {
				xAxis.setUpperBound(xAxis.getUpperBound() + 5000);
			}
		}
		final ObservableList<Data<Number, Number>> minuteList = minuteDataSeries.getData();
		if (i < 18 * 5000)
			minuteList.add(new XYChart.Data<>(i++, list.get(0).get(i++) + 20000));
	}

	public void play() {
		animation.play();
	}

	@Override
	public void stop() {
		animation.pause();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setPersonData();
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		play();
	}

	public void setPersonData() {
		System.out.println("开始读取CSV文件");
		ReadCSVTest r = new ReadCSVTest("C:/Users/Sunny/Desktop/5moti/25613 2020-05-01 09-33-15`05.csv");
		try {
			list = r.readContents(18);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取CSV文件完毕");
		if (3 != list.size())
			return;
		System.out.println(list.get(0).size());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
