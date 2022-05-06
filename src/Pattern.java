import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Pattern implements Token {
	List<Token> tokens = new LinkedList<>();
	int size = 0;
	boolean simplified = false;

	public Pattern(String p, HashMap<String, Token> dictionary) throws InvalidPatternException {
		boolean over = false;
		for (int i = 0; i < p.length(); i++) {
			char c = p.charAt(i);
			while (c != '\\' && c != '(' && c != ')' && c != '[' && c != ']' && c != '*'
					&& c != '?' && c != '+' && c != '^' && c != '|' && c != '.' && c != '$' && c != '{' && c != '}') {
				tokens.add(new Litteral(c + ""));
				size++;
				if (i < p.length() - 1) {
					c = p.charAt(++i);
				} else {
					over = true;
					break;
				}
			}
			if (over) break;
			switch (c) {
				case '\\':
					if (i < p.length() - 1) {
						switch (p.charAt(++i)) {
							case 's':
								tokens.add(Whitespace.getInstance(true));
								size += 2;
								break;
							case 'S':
								tokens.add(Whitespace.getInstance(false));
								size += 2;
								break;
							case 'd':
								tokens.add(Digit.getInstance(true));
								size += 2;
								break;
							case 'D':
								tokens.add(Digit.getInstance(false));
								size += 2;
								break;
							case 'w':
								tokens.add(Word.getInstance(true));
								size += 2;
								break;
							case 'W':
								tokens.add(Word.getInstance(false));
								size += 2;
								break;
							case 'R':
								tokens.add(this);
								size += 2;
								break;
							case '{':
								int j = p.indexOf('}', i + 1);
								if (j == -1 || j == i + 1) throw new InvalidPatternException();
								if (i == 1 && p.charAt(j - 1) == '=') {    // Save this pattern as ABCD if \{ABCD=} at the beginning
									if (j == i + 2) {
										throw new InvalidPatternException();
									}
									dictionary.put(p.substring(i + 1, j - 1), this);
								} else {
									boolean getter = true;
									for (char c2 : p.substring(i + 1, j).toCharArray()) {
										if ((c2 < '0' || c2 > '9') && c2 != '.') {
											tokens.add(new PlaceHolder(p.substring(i + 1, j), dictionary));
											getter = false;
											break;
										}
									}
									if (getter) {
										tokens.add(new GroupGetter(p.substring(i + 1, j)));
									}
								}
								size += 2 + j - i;
								i = j;
								break;
							default:
								tokens.add(new Litteral(p.charAt(i) + ""));
								size += 2;
								break;
						}
					} else throw new InvalidPatternException();
					break;
				case '(':
					Pattern pattern = new Pattern(p.substring(i + 1), dictionary);
					tokens.add(new GroupCatcher(pattern.simplify()));
					i += pattern.size + 1;
					break;
				case ')':
					size++;
					simplify();
					return;
				case '[':
					boolean inverted = ((++i) < p.length() && p.charAt(i) == '^');
					i++;
					ArrayList<Character> list = new ArrayList<>();
					while (i < p.length() && p.charAt(i) != ']') {
						if (p.charAt(i) == '\\' && i < p.length() - 1) {
							list.add(p.charAt(i + 1));
							i += 2;
						} else {
							list.add(p.charAt(i));
							i++;
						}
					}
					if (i == p.length()) throw new InvalidPatternException();
					tokens.add(new AnyLitteral(inverted, list));
					break;
				case '{':
					i++;
					int j = i;
					while (j < p.length() && p.charAt(j) != '}') j++;
					if (j >= p.length()) throw new InvalidPatternException();
					String[] vals = p.substring(i, j).split(",");
					if (vals.length == 1) {
						int n = getIntOrIPE(vals[0]);
						tokens.add(new NTimes(tokens.remove(tokens.size() - 1), n, n));
					} else if (vals.length == 2) {
						tokens.add(new NTimes(tokens.remove(tokens.size() - 1), getIntOrIPE(vals[0]), getIntOrIPE(vals[1])));
					} else throw new InvalidPatternException();
					break;
				case '*':
					tokens.add(new ZeroToMany(tokens.remove(tokens.size() - 1)));
					size++;
					break;
				case '?':
					tokens.add(new ZeroOrOne(tokens.remove(tokens.size() - 1)));
					size++;
					break;
				case '+':
					tokens.add(new OneOrMany(tokens.remove(tokens.size() - 1)));
					size++;
					break;
				case '^':
					tokens.add(Beginning.getInstance());
					size++;
					break;
				case '$':
					tokens.add(Ending.getInstance());
					break;
				case '|':
					tokens.add(new Or(tokens.remove(tokens.size() - 1)));
					size++;
					break;
				case '.':
					tokens.add(Any.getInstance());
					size++;
					break;
			}
		}
		simplify();
	}

	private int getIntOrIPE(String val) throws InvalidPatternException {
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			throw new InvalidPatternException();
		}
	}

	public Token simplify() throws InvalidPatternException {
		if (!simplified) {
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
						if (last != null) {
							res.add(last);
							last = null;
						}
						res.add(t);
					}
				}
			}
			tokens = res;
			simplified = true;
		}
		return tokens.isEmpty() ? null : this;
	}

	@Override
	public ParserResult search(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) {
		return null;
	}

	@Override
	public ParserResult match(String s, SParser.Flag flags, boolean greedy, ParserResult pr, long offset, long lineOffset) {
		return null;
	}
}
