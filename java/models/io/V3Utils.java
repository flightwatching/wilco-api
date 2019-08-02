package models.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class V3Utils {
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <M extends IV3Exportable> List createList(Collection<M> models, boolean head) {
		List ret =  new ArrayList();
		if (models!=null) {
			ret =  new ArrayList(models.size());		
			for (M p : models) {
				ret.add(p.getV3IO(head));
			}			
		}
		return ret;
	}

}
