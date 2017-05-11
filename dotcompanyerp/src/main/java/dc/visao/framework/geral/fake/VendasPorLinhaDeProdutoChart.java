package dc.visao.framework.geral.fake;

import java.util.ArrayList;
import java.util.List;

import dc.visao.framework.geral.fake.DataProvider.Movie;



public class VendasPorLinhaDeProdutoChart /*extends Chart*/ {

    public VendasPorLinhaDeProdutoChart() {
       /* // TODO this don't actually visualize top grossing movies, but just
        // makes a
        // bar chart of movie scores

        setCaption("Vendas por Linha de Produto");
        getConfiguration().setTitle("");
        getConfiguration().getChart().setType(ChartType.BAR);
        getConfiguration().getxAxis().getLabels().setEnabled(false);
        getConfiguration().getxAxis().setTickWidth(0);
        setWidth("100%");
        setHeight("90%");

        ArrayList<Movie> movies = DataProvider.getMovies();
        List<Series> series = new ArrayList<Series>();
        for (int i = 0; i < 6; i++) {
            Movie movie = movies.get(i);
            series.add(new ListSeries(movie.title, movie.score));
        }
        getConfiguration().setSeries(series);
*/
    }

}