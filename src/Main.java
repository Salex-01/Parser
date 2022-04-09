public class Main {
	public static void main(String[] args) throws InvalidPatternException {
		String[] patterns = new String[args.length - 2];
		System.arraycopy(args, 1, patterns, 0, args.length - 2);
		ParserResult res = new SParser().parse(patterns, args[args.length - 1]);
		//TODO : un seul match par token simple
	}
}