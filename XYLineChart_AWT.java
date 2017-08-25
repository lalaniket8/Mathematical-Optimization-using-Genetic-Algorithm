import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.ShapeUtilities;

public class XYLineChart_AWT extends ApplicationFrame {
	
	private XYSeriesCollection xydataset; //collection of series
	private XYLineAndShapeRenderer renderer;//renderer for line plotting
	private XYPlot plot;//XYPlot object
	private int PopulationSeriesIndex; 
	private ArrayList<Exp> constraints;
	
	public XYLineChart_AWT(String applicationTitle, String chartTitle, String Xaxis, String Yaxis, ArrayList<Exp> constraints) {

        super(applicationTitle);
        this.constraints = constraints;
        
        //x-axis
        final XYSeries xaxis = new XYSeries( "X-Axis" );          
        for(float i=-1; i<=15; i+=0.1)
        {xaxis.add( i , 0 );}
        
        //y-axis
        final XYSeries yaxis = new XYSeries( "Y-Axis" );          
        for(float i=-2; i<=15; i+=0.1)
        {yaxis.add( 0 , i );}
        
        //populationSeries is a collection of points holding the entire population of points where each point is an individual organism
        XYSeries populationSeries = new XYSeries( "IndivisualSeries" );
        
        this.xydataset = new XYSeriesCollection( ); //init xydataset      
        //add all series
         xydataset.addSeries( xaxis );
         xydataset.addSeries( yaxis );
         xydataset.addSeries( populationSeries );
         PopulationSeriesIndex = xydataset.indexOf(populationSeries);            

         
        //final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(xydataset,chartTitle, Xaxis, Yaxis);

      //  timer.setInitialDelay(2000);

        //Sets background color of chart
        chart.setBackgroundPaint(Color.LIGHT_GRAY);

        //Created JPanel to show graph on screen
        final JPanel content = new JPanel(new BorderLayout());

        //Created Chartpanel for chart area
        final ChartPanel chartPanel = new ChartPanel(chart);

        //Added chartpanel to main panel
        content.add(chartPanel);

        //Sets the size of whole window (JPanel)
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 450));

        //Puts the whole content on a Frame
        setContentPane(content);

//        timer.start();

    }
 private JFreeChart createChart(XYDataset dataset, String chartTitle, String Xaxis, String Yaxis) {
	JFreeChart xylineChart = ChartFactory.createXYLineChart(
	         chartTitle ,
	         Xaxis ,
	         Yaxis ,
	         //samplexydataset2(),
	         xydataset ,
	         PlotOrientation.VERTICAL ,
	         true , true , false);


        plot = xylineChart.getXYPlot();
        
        renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.BLACK );
        renderer.setSeriesPaint( 1 , Color.BLACK );
        renderer.setSeriesStroke( 0 , new BasicStroke( 1.5f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 1.5f ) );
        renderer.setSeriesShape(0, ShapeUtilities.createDiamond( 0.5f ));
        renderer.setSeriesShape(1, ShapeUtilities.createDiamond( 0.5f ));
        renderer.setSeriesLinesVisible(PopulationSeriesIndex,false);
        renderer.setSeriesPaint( PopulationSeriesIndex , Color.BLACK );
        renderer.setSeriesShape(PopulationSeriesIndex, ShapeUtilities.createDownTriangle(1.5f));
        plot.setRenderer( renderer ); 

  /*      plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);*/

        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);

        //Domain axis would show data of 60 seconds for a time
       //setFixedAutoRange(60000.0);  // 60 seconds
       // xaxis.setVerticalTickLabels(true);

        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setAutoRange(true);

        return xylineChart;
    }
 
 public void dispConstraints() {

	 for(int index=0; index<constraints.size(); index++) {
		//System.out.println(constraints.get(index).toString());
		 XYSeries func = new XYSeries(constraints.get(index).toString());
		 
		/* ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		    String foo = "40+2";
		    try {
				System.out.println(engine.eval(foo));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		 
		 ArrayList<SimplePoint> coordsSet = constraints.get(index).getCoordsSet(0, 10, 0.1);
		 
		 for(SimplePoint sp:coordsSet)
		 {
			// System.out.println(sp.x+" "+sp.y);
			 func.add( sp.x , sp.y);
		 }      
		 
		 xydataset.addSeries(func);
		 int seriesIndex = xydataset.indexOf(func);
	 
     
     //xydataset.addSeries(fun2);
     
     renderer.setSeriesPaint( seriesIndex , Color.BLUE );
     renderer.setSeriesStroke(  seriesIndex , new BasicStroke( 1.5f ) );
     renderer.setSeriesShape( seriesIndex, ShapeUtilities.createDiamond( 0.5f ));
	
	 }
   //  renderer.setSeriesPaint( xydataset.indexOf(fun2) , Color.BLUE );
   //  renderer.setSeriesStroke(  xydataset.indexOf(fun2) , new BasicStroke( 1.5f ) );
   //  renderer.setSeriesShape( xydataset.indexOf(fun2), ShapeUtilities.createDiamond( 0.5f ));
     
     plot.setRenderer( renderer );
     System.out.println("axes added");
 }
  /* public XYLineChart_AWT( String applicationTitle, String chartTitle, String Xaxis, String Yaxis, int WindowLen, int WindowHght ) {
      super(applicationTitle);
      createDataset();
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         Xaxis ,
         Yaxis ,
         //samplexydataset2(),
         xydataset ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( WindowLen , WindowHght ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.BLUE );
      renderer.setSeriesPaint( 1 , Color.BLUE );
      renderer.setSeriesPaint( 2 , Color.BLACK );
      renderer.setSeriesPaint( 3 , Color.BLACK );
      renderer.setSeriesPaint( 4 , Color.RED );
      renderer.setSeriesPaint( 5 , Color.RED );
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.5f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 1.5f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 1.5f ) );
      renderer.setSeriesStroke( 3 , new BasicStroke( 1.5f ) );
      renderer.setSeriesShape(0, ShapeUtilities.createDiamond( 0.5f ));
      renderer.setSeriesShape(1, ShapeUtilities.createDiamond( 0.5f ));
      renderer.setSeriesShape(2, ShapeUtilities.createDiamond( 0.5f ));
      renderer.setSeriesShape(3, ShapeUtilities.createDiamond( 0.5f ));
      renderer.setSeriesShape(4, ShapeUtilities.createDiamond( 1.5f ));
      renderer.setSeriesShape(5, ShapeUtilities.createDiamond( 1.5f ));
      renderer.setSeriesLinesVisible(4,false);renderer.setSeriesLinesVisible(5,false);
      
      plot.setRenderer( renderer ); 
      
      setContentPane( chartPanel ); 
      createDataset();
      setContentPane( chartPanel );

   }*/
   
  /* private static XYDataset samplexydataset2() {
      // int cols = 20;
      // int rows = 20;
      // double[][] values = new double[cols][rows];

       XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
       XYSeries series = new XYSeries("Random");
     //  Random rand = new Random();
      for (int i = 0; i < values.length; i++) {
           for (int j = 0; j < values[i].length; j++) {
               double x = Math.round(rand.nextDouble() * 500);
               double y = Math.round(rand.nextDouble() * 500);

               series.add(3,6);series.add(3,7);series.add(4,6);
    //       }
    //   }
       xySeriesCollection.addSeries(series);
       return (XYDataset)xySeriesCollection;
   }*/
   
   public void initPopulation(Point[] values, Boolean populationInitFlag) {
	   if(populationInitFlag) {
	   xydataset.getSeries(PopulationSeriesIndex).clear();	   
	   for (int i=0; i<values.length; i++) {		   
       	   try {
			xydataset.getSeries(PopulationSeriesIndex).add(values[i].getX(),values[i].getY());
		} catch (PointUsageException e) {
			System.out.println("PointUsageException: Point array index:"+i);// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	   }      
   }
   }
}