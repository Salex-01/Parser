public class GroupGetter implements Token {
	String name;
	Token runTimeEval;

	public GroupGetter(String n) {
		name = n;
	}

	@Override
	public Token simplify() throws InvalidPatternException {
		return this;
	}

	@Override
	public ParserResult search(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		setRuntimeEval(pr);
		return runTimeEval.search(s, flags, greedy, pr, offset, lineOffset);
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) throws InvalidPatternException {
		setRuntimeEval(pr);
		return runTimeEval.match(s, flags, greedy, pr, offset, lineOffset);
	}

	private void setRuntimeEval(ParserResult pr) throws InvalidPatternException {
		if (runTimeEval == null) {
			ParserResult pr2 = pr;
			String[] indexes = name.split("\\.");
			for (String value : indexes) {
				int index = Integer.parseInt(value);
				if (pr2.capturedGroups.size() <= index) throw new InvalidPatternException();
				pr2 = pr2.capturedGroups.get(index);
			}
			if (pr2.value == null) throw new InvalidPatternException();
			runTimeEval = new Litteral(pr2.value);
		}
	}
}