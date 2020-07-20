import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class NoAnimaLineChart extends Application {
	private LineChart<Number, Number> chart;
	private NumberAxis xAxis;
	private NumberAxis yAxis;

	private ArrayList<ArrayList<Integer>> list;
	private XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
	private XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
	private XYChart.Series<Number, Number> series3 = new XYChart.Series<Number, Number>();

	private Line line1 = new Line(63, 15, 63, 120);
	private Line line2 = new Line(63,122,63,227);
	private Line line3 = new Line(63,229,63,334);
	double x1;
	double y1;
	double x_stage;
	double y_stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		xAxis = new NumberAxis(0, 18 * 5000, 5000);
		yAxis = new NumberAxis(0, 120000, 40000);
		chart = new LineChart<>(xAxis, yAxis);
		AnchorPane root = new AnchorPane();
		// setup chart
		chart.setCreateSymbols(false);
		final String stockLineChartCss = getClass().getResource("TestCss.css").toExternalForm();
		chart.getStylesheets().add(stockLineChartCss);

		setPersonData();
		chart.getData().addAll(series1, series2, series3);

		root.getChildren().addAll(chart, line1,line2,line3);
		Scene scene = new Scene(root, 540, 540);
		primaryStage.setScene(scene);
		primaryStage.show();

		// --------------------------------------------------------
		// 设置line属性
		// --------------------------------------------------------
		line1.setStroke(Color.RED);
		line1.setStrokeWidth(1.25);
		line1.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 计算
				double s = x_stage + m.getSceneX() - x1;
				if (0 <= s && s <= 422)
					line1.setLayoutX(s);
			}
		});
		line1.setOnDragEntered(null);
		line1.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 按下鼠标后，记录当前鼠标的坐标
				x1 = m.getSceneX();
				y1 = m.getSceneY();
				x_stage = line1.getLayoutX();
				y_stage = line1.getLayoutY();
				System.out.println(x_stage);
			}
		});
		line2.setStroke(Color.RED);
		line2.setStrokeWidth(1.25);
		line2.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 计算
				double s = x_stage + m.getSceneX() - x1;
				if (0 <= s && s <= 422)
					line2.setLayoutX(s);
			}
		});
		line2.setOnDragEntered(null);
		line2.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 按下鼠标后，记录当前鼠标的坐标
				x1 = m.getSceneX();
				y1 = m.getSceneY();
				x_stage = line2.getLayoutX();
				y_stage = line2.getLayoutY();
				System.out.println(x_stage);
			}
		});
		line3.setStroke(Color.RED);
		line3.setStrokeWidth(1.25);
		line3.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 计算
				double s = x_stage + m.getSceneX() - x1;
				if (0 <= s && s <= 422)
					line3.setLayoutX(s);
			}
		});
		line3.setOnDragEntered(null);
		line3.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 按下鼠标后，记录当前鼠标的坐标
				x1 = m.getSceneX();
				y1 = m.getSceneY();
				x_stage = line3.getLayoutX();
				y_stage = line3.getLayoutY();
				System.out.println(x_stage);
			}
		});
	}

	public void setPersonData() {
		System.out.println("开始读取CSV文件");
		ReadCSVTest r = new ReadCSVTest("C:/Users/Sunny/Desktop/5moti/25613 2020-05-01 09-33-15`05.csv");
		try {
			list = r.readContents(1);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取CSV文件完毕");
		if (3 != list.size())
			return;
		System.out.println(list.get(0).size());
		for (int i = 0; i < list.get(0).size(); i++) {
			series1.getData().add(new XYChart.Data<>(i, list.get(0).get(i) + 20000));
			series2.getData().add(new XYChart.Data<>(i, list.get(1).get(i) + 60000));
			series3.getData().add(new XYChart.Data<>(i, list.get(2).get(i) + 100000));
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		launch(args);
	}
}
