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
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) throws InvalidPatternException {
		if (value == null) {
			value = dictionary.get(name);
			if (value == null) throw new InvalidPatternException();
		}
		return value.check(s, flags, greedy);
	}

	@Override
	public ParserResult checkAtBeginning(String s, SParser.Flag[] flags, boolean greedy) throws InvalidPatternException {
		if (value == null) {
			value = dictionary.get(name);
			if (value == null) throw new InvalidPatternException();
		}
		return value.checkAtBeginning(s, flags, greedy);
	}
}