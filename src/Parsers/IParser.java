package Parsers;

/**
 * The Interface IParser.
 *
 * @param <A> the return result
 * @param <B> the generic type
 */
public interface IParser<A,B> {
	
	/**
	 * Parses structure
	 *
	 * @param data the string input
	 * @return the parse result
	 */
	public B parse(A data);
}
