package example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlHelper {

	
	private static final Pattern ID_IN_LOCATION = Pattern.compile("\\/([0-9]+)$");
	
	public static Long getIdFromLocation(String location){
		Matcher matcher =  ID_IN_LOCATION.matcher(location);
		if(matcher.find())
			return new Long(matcher.group(1));
		else 
			return null;
	}
}
