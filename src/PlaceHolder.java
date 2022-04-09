import java.util.HashMap;

public class PlaceHolder implements Token {
	String name;
	Token value;
	HashMap<String, Token> dictionary;
	boolean simplified = false;

	public PlaceHolder(String n, HashMap<String, Token> dict) {
		dictionary = dict;
		name = n;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		if (simplified) return value;
		simplified = true;
		if (value == null) {
			value = dictionary.get(name);
			//if (value == null) throw new InvalidPatternException();
		}
		return value.simplify();
	}

	@Override
	public ParserResult search(String s, SParser.Flag flags, boolean greedy,ParserResult pr) throws InvalidPatternException {
		if (value == null) {
			value = dictionary.get(name);
			if (value == null) throw new InvalidPatternException();
		}
		return value.search(s, flags, greedy,pr);
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy,ParserResult pr) throws InvalidPatternException {
		if (value == null) {
			value = dictionary.get(name);
			if (value == null) throw new InvalidPatternException();
		}
		return value.match(s, flags, greedy,pr);
	}
}