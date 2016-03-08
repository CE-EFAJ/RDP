package Logic;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import nl.flotsam.xeger.Xeger;

public class Regex {
	
	public Boolean IsARegex(String regex){
		try {
            Pattern.compile(regex);
            
        } catch (PatternSyntaxException exception) {
            return false;
            }
		return true;
	}
	public Boolean Validate(String regex, String example){
		boolean match = Pattern.matches(regex, example);
		return match;
	}
	
	public String Generator(String regex){
		Xeger generator = new Xeger(regex); 
		String result = generator.generate(); 
		assert result.matches(regex);
		return result;
	}
}
