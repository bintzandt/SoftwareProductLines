/**
 * For lambda function that converts an API value to a "shown" value
 */
public class ValueConverter {
	
	// Should be overridden, no worries about stuff like Float.parseFloat() on invalid values
	String getShownValue(String apiValue) throws NumberFormatException;
	
	// Should not be overridden
	String safeGetShownValue(String apiValue) {
		try {
			String val = getShownValue(apiValue);
			if (val == null || val.equals(""))
				return "n.v.t.";
			return val;
		}
		catch (NumberFormatException e) {
			return "n.v.t.";
		}
	}
}