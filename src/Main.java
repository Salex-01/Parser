public class Main {
	public static void main(String[] args) throws InvalidPatternException {
		String[] patterns = new String[args.length - 2];
		for (int i = 1; i < args.length - 1; i++) {
			patterns[i - 1] = args[i];
		}
		ParserResult res = new SParser().parse(patterns, args[args.length - 1]);
		//TODO : un seul match par token simple
	}
}