import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Pattern implements Token {
	List<Token> tokens = new LinkedList<>();

	public Pattern(String p, HashMap<String, Token> dictionary) throws InvalidPatternException {
		List<Character> temp = new ArrayList<>();
		for (int i = 0; i < p.length(); i++) {
			char c = p.charAt(i);
			while (c != '\\' && c != '(' && c != ')' && c != '[' && c != ']' && c != '*'
					&& c != '?' && c != '+' && c != '^' && c != '|' && c != '.' && c != '$') {
				temp.add(c);
				if (i < p.length() - 1) {
					c = p.charAt(++i);
				} else {
					break;
				}
			}
			if (!temp.isEmpty()) {
				tokens.add(new Litteral((Character[]) temp.toArray()));
				temp = new ArrayList<>();
			}
			switch (c) {
				case '\\':
					if (i < p.length() - 1) {
						switch (p.charAt(++i)) {
							case 's':
								tokens.add(Whitespace.getInstance());
								break;
							case 'd':
								tokens.add(Digit.getInstance(true));
								break;
							case 'D':
								tokens.add(Digit.getInstance(false));
								break;
							case 'w':
								tokens.add(Word.getInstance(true));
								break;
							case 'W':
								tokens.add(Word.getInstance(false));
								break;
							case 'R':
								tokens.add(this);
								break;
							case '{':
								int j = p.indexOf('}', i + 1);
								if (j == -1) throw new InvalidPatternException();
								tokens.add(new PlaceHolder(p.substring(i + 1, j), dictionary));
								i = j;
								break;
							case '\\' | '(' | ')' | '[' | ']' | '*' | '?' | '+' | '^' | '|' | '.' | '$':
								tokens.add(new Litteral(p.charAt(i) + ""));
								break;
							default:
								throw new InvalidPatternException();
						}
					} else throw new InvalidPatternException();
					break;
				case '(':

					break;
				case ')':
					break;
				case '[':
					break;
				case ']':
					break;
				case '*':
					break;
				case '?':
					break;
				case '+':
					break;
				case '^':
					break;
				case '|':
					break;
				case '.':
					break;
				case '$':
					break;
			}
		}
		simplify();
	}

	public Token simplify() throws InvalidPatternException {
		List<Token> res = new LinkedList<>();
		Litteral last = null;
		for (Token t : tokens) {
			t = t.simplify();
			if (t != null) {
				if (t instanceof Litteral) {
					if (last == null) {
						last = (Litteral) t;
					} else {
						last.value += ((Litteral) t).value;
					}
				} else {
					res.add(last);
					last = null;
					res.add(t);
				}
			}
		}
		tokens = res;
		return tokens.isEmpty() ? null : this;
	}

	@Override
	public boolean check(String s, SParser.Flag[] flags, boolean greedy) {
		return false;
	}

	@Override
	public ParserResult checkAtBeginning(String s, SParser.Flag[] flags, boolean greedy) {
		return null;
	}
}
