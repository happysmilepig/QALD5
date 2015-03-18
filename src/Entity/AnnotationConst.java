package Entity;

public class AnnotationConst {
	
	public final static String spotlightSpotURI = "http://spotlight.dbpedia.org/rest/spot/";
	public final static String spotlightURI = "http://spotlight.dbpedia.org/rest/annotate/";
	public final static String spotlightCandURI = "http://spotlight.dbpedia.org/rest/candidates/";
	public final static String spotlightURI2 = "http://spotlight.sztaki.hu:2222/rest/annotate/";
	public final static String minerURI = "http://wikipedia-miner.cms.waikato.ac.nz/services/wikify";
	public final static String dexterURI = "http://localhost:8080/rest/annotate/";
//	public final static String dexterSpotURI = "http://localhost:8080/rest/spot/";
	public final static String dexterURI2 = "http://localhost:8080/dexter-webapp/api/rest/annotate";
	public final static String tagmeURI = "http://tagme.di.unipi.it/tag";
	
	
	public final static String [] serviceList = {spotlightSpotURI,spotlightURI,spotlightCandURI,spotlightURI2,minerURI,dexterURI,dexterURI2,tagmeURI};
	
//	public final static String spotlightParas = "";
//	public final static String minerParas = "&responseFormat=JSON&sourceMode=HTML&references=true&disambiguationPolicy=loose&linkFormat=html_id_weight";
//	public final static String dexterParas = "&n=10";
//	public final static String tagmeParas = "&key=abAnBGgAqA2015&include_categories=true&include_all_spots=true";
	
	public final static String spotlightParas = "";
	public final static String minerParas = "&responseFormat=JSON&sourceMode=HTML&references=true&minProbability=0&disambiguationPolicy=loose";
	public final static String dexterParas = "&n=10";
	public final static String tagmeParas = "&key=abAnBGgAqA2015";
	public final static String [] servicetParas = {spotlightParas,spotlightParas,spotlightParas,spotlightParas,minerParas,dexterParas,dexterParas,tagmeParas};

	public final static String dexterURI2ID = "http://localhost:8080/dexter-webapp/api/rest/get-desc?id=";
}
