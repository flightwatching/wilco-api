package models.io;


import java.util.List;
import java.util.Map;
import java.util.Set;

public class DashboardV3IO  {

	public String id;
	@CollectionComponent(value=RuleV3IO.class)
	public List<RuleV3IO> rules;
	public String svg;
	public Map<String, String> eltMap;
	public String name;

	@CollectionComponent(value=DashboardSymbolV3IO.class)
	public Set<DashboardSymbolV3IO> symbols;



}
