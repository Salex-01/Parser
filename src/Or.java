import java.util.List;

public class Or implements Token {
	Token first;
	Token second;
	List<Token> list;

	public Or(List<Token> t, int size) throws InvalidPatternException {
		first = new Pattern(List.copyOf(t), size).simplify();
		t.removeIf(token -> true); // The list has already been copied and saved in the nested Or(.second) if there is one
		list = t;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		second = new Pattern(list, 0).simplify();
		return second == null ? first : (first == null ? second : this);
	}

	@Override
	public ParserResult search(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return null;
		//TODO
	}

	@Override
	public ParserResult match(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return null;
		//TODO
	}

	@Override
	public boolean takeOneMore(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return false; //TODO
	}

	@Override
	public boolean giveOneBack(String s, int flags, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		return false; //TODO
	}
}