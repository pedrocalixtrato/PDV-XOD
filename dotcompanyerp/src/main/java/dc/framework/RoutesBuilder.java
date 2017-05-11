package dc.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;

import dc.entidade.framework.FmModulo;
import dc.visao.framework.geral.MainController;

public class RoutesBuilder {
	
	public List<FmModulo> buildModuleRoutes(ModuleLoader loader,HashMap<String, View> routes,Navigator nav,MainController controller) {
        ArrayList<FmModulo> modules = new ArrayList<FmModulo>();
        List<FmModulo> modulesFromDB = controller.getModules();
        if(modulesFromDB != null){
        	modules.addAll(modulesFromDB);
        }
        
        for(FmModulo m : modules){
            	m.loadRoute(routes,controller);
        }
        for (String route : routes.keySet()) {
               nav.addView("/" + route, routes.get(route));
        }	
        
        return modules;
	}


}
