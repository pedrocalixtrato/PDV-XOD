package dc.visao.framework.geral.fake;


import java.util.ArrayList;


import dc.visao.framework.geral.fake.DataProvider.Movie;


public class TopSixTheatersChart /*extends Chart*/ {

    public TopSixTheatersChart() {
/*        // TODO this don't actually visualize top six theaters, but just makes a
        // pie chart
        super(ChartType.PIE);

        setCaption("Avaliação de satisfação por linha de produto e/ou serviço");
        getConfiguration().setTitle("");
        getConfiguration().getChart().setType(ChartType.PIE);
        setWidth("100%");
        setHeight("90%");

        DataSeries series = new DataSeries();

        ArrayList<Movie> movies = DataProvider.getMovies();
        for (int i = 0; i < 6; i++) {
            Movie movie = movies.get(i);
            series.add(new DataSeriesItem(movie.title, movie.score));
        }
        getConfiguration().setSeries(series);
        */
    }

}