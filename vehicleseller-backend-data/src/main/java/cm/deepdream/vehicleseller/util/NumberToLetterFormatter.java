package cm.deepdream.vehicleseller.util;
import java.util.logging.Logger;

public class NumberToLetterFormatter {
	private Logger logger = Logger.getLogger(NumberToLetterFormatter.class.getName());
	private static NumberToLetterFormatter instance = null ;

	
	private NumberToLetterFormatter(){

	}
	
	public static synchronized  NumberToLetterFormatter getInstance(){
		if(instance == null ){
			instance=new NumberToLetterFormatter() ;
		}
		return instance;
	}

	
	private  String Unity(String value){
	    String str = "" ;
	    switch (Integer.parseInt(value)){
	    	case 0: str = "Zero" ; break;
	    	case 1: str = "un" ; break ;
	    	case 2: str = "deux" ; break;
	    	case 3: str = "trois" ; break;
	    	case 4: str = "quatre" ; break;
	    	case 5: str = "cinq" ; break;
	    	case 6: str = "six" ; break;
	    	case 7: str = "sept" ; break;
	    	case 8: str = "huit" ; break;
	    	case 9: str = "neuf" ; break;
	    }
	    return str;
	}
	
	
	
	private  String Ten(String value){
	    String ch = "" ; 
	    String x1 = value.substring(0,1)  ; 
	    String x2 = value.substring(1,2) ;
	    switch(Integer.parseInt(x1)){
	    	case 0: ch = Unity(x2);break;
	    	case 1:
	    		switch(Integer.parseInt(x2)){
	    			case 0: ch="dix"; break;
	    			case 1: ch="onze"; break;
	    			case 2: ch="douze"; break;
	    			case 3: ch="treize"; break;
	    			case 4: ch="quatorze"; break;
	    			case 5: ch="quinze"; break;
	        		case 6: ch="seize"; break;
	        		case 7: ch="dix-sept"; break;
	        		case 8: ch="dix-huit"; break;
	        		case 9: ch="dix-neuf"; break;
	        }
	        break;
	    case 2:
	        ch="vingt";
	        switch(Integer.parseInt(x2)){
	        	case 0: ch="vingt"; break;
	        	default: ch=ch + " " + Unity(x2);break;
	        }
	        break;
	    case 3:
	        ch="trente";
	        	switch(Integer.parseInt(x2)){
	        	case 0: ch = "trente";break;
	        	default: ch = ch + " " + Unity(x2);break;
	        }
	        break;
	    case 4:
	        ch="quarante";
	        switch(Integer.parseInt(x2)){
	        case 0: ch="quarante";break;
	        default: ch=ch + " " + Unity(x2);break;
	        }
	        break;
	    case 5:
	        ch="cinquante";
	        switch(Integer.parseInt(x2)){
	        case 0: ch="cinquante";break;
	        default: ch = ch + " " + Unity(x2);break;
	        }
	        break;
	    case 6:
	        ch="soixante";
	        switch(Integer.parseInt(x2)){
	        case 0: ch="soixante";break;
	        default: ch=ch + " " + Unity(x2);break;
	        }
	        break;
	    case 7:
	        switch(Integer.parseInt(x2)){
	        case 0: ch="soixante dix";break;
	        case 1: ch="soixante onze";break;
	        case 2: ch="soixante douze";break;
	        case 3: ch="soixante treize";break;
	        case 4: ch="soixante quatorze";break;
	        case 5: ch="soixante quinze";break;
	        case 6: ch="soixante seize";break;
	        case 7: ch="soixante dix-sept";break;
	        case 8: ch="soixante dix-huit";break;
	        case 9: ch="soixante dix-neuf";break;
	        }
	        break;
	    case 8:
	        ch="quatre-vingt";
	        switch(Integer.parseInt(x2)){
	        case 0: ch="quatre-vingts";break;
	        default: ch=ch + " " + Unity(x2);break;
	        }
	        break;
	    case 9:
	        ch="quatre-vingt dix";
	        switch(Integer.parseInt(x2)){
	        case 0: ch="quatre-vingt dix";break;
	        case 1: ch="quatre-vingt onze";break;
	        case 2: ch="quatre-vingt douze";break;
	        case 3: ch="quatre-vingt treize";break;
	        case 4: ch="quatre-vingt quatorze";break;
	        case 5: ch="quatre-vingt quinze";break;
	        case 6: ch="quatre-vingt seize";break;
	        case 7: ch="quatre-vingt dix-sept";break;
	        case 8: ch="quatre-vingt dix-huit";break;
	        case 9: ch="quatre-vingt dix-neuf";break;
	        }
	        break;
	    }
	    return ch;

	}

	private String Hundred(String x){
	    String ch="";
	    if(x.length() ==1){
	        ch = Unity(x);
	        return ch;
	    }
	    if(x.length() ==2){
	        ch = Ten(x);
	        return ch;
	    }
	    String x1=x.substring(0,1);
	    String x2=x.substring(1,2);
	    String x3=x.substring(2,3);
	    switch (Integer.parseInt(x1)){
	    case 0: ch=Ten(x2+x3);break;
	    case 1:
	        ch="cent";
	        switch(Integer.parseInt(x2)){
	        case 0:
	            switch(Integer.parseInt(x3)){
	            case 0:
	            default: ch=ch + " " + Unity(x3);break;
	            }
	        default:ch=ch + " " + Ten(x2+x3);break;
	        }
	        break;
	    default:
	        ch= Unity(x1);
	        switch(Integer.parseInt(x2)){
	        case 0:
	            switch(Integer.parseInt(x3)){
	            case 0: ch=ch + " cents"; break;
	            default: ch=ch + " cent " + Unity(x3);break;
	            }
	            break;
	        default: ch = ch + " cent " + Ten(x2+x3);break;
	        }
	        break;
	    }
	    return ch;
	}

	private  String Thousand(String x){
	    String ch="";int i=x.length()-3;
	    String x1=x.substring(0,i); String x2=x.substring(i,x.length());
	    if(Integer.parseInt(x1) ==0){
	        ch = Hundred(x2);
	    }else{
	        if(Integer.parseInt(x2) ==0){
	            ch = Hundred(x1) + " mille ";
	        }else{
	            ch = Hundred(x1) + " mille " + Hundred(x2);
	        }
	    }
	    return ch;
	}

	private  String Million(String x){
	    String ch=""; int i=x.length()-6;
	    String x1=x.substring(0,i); String x2=x.substring(i,x.length());
	    if(Integer.parseInt(x1) ==0){
	        ch = Thousand(x2);
	    }else{
	        if(Integer.parseInt(x2) ==0){
	            ch = Hundred(x1) + " million(s) ";
	        }else{
	            ch = Hundred(x1) + " million(s) " + Thousand(x2);
	        }
	    }

	    return ch;
	}
	
	
	private  String Billion(String x){
	    String ch=""; int i=x.length()-9;
	    String x1=x.substring(0,i); String x2=x.substring(i,x.length());
	    if(Integer.parseInt(x1) ==0){
	        ch = Million(x2);
	    }else{
	        if(Integer.parseInt(x2) ==0){
	            ch = Hundred(x1) + " milliard(s) ";
	        }else{
	            ch = Hundred(x1) + " milliard(s) " + Million(x2);
	        }
	    }

	    return ch;
	}
	
	public synchronized String convertToLetters(Long montant){
		String montantChiffre = Long.toString(montant) ;
		String montantLettre = "" ;
		
		if(montantChiffre.length() >= 10 ) return Billion(montantChiffre) ;
		else if(montantChiffre.length() >= 7) montantLettre= Million(montantChiffre);
		else if(montantChiffre.length() >= 4) montantLettre= Thousand(montantChiffre);
		else if(montantChiffre.length() >= 3) montantLettre= Hundred(montantChiffre);
		else if(montantChiffre.length() >= 2) montantLettre= Ten(montantChiffre);
		else if(montantChiffre.length() >= 1) montantLettre= Unity(montantChiffre);
		else montantLettre= "" ;
		char firstChar = montantLettre.charAt(0);
		
		montantLettre = montantLettre.replaceFirst(""+firstChar, (""+firstChar).toUpperCase()) ;
		if(montantLettre.endsWith("un un"))
			montantLettre = montantLettre.substring(0, montantLettre.length()-3) ;
		return montantLettre;
	}

	
	
	
}
