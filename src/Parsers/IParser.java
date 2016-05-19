package Parsers;

import java.sql.ResultSet;

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
	public B parse(ResultSet rs);
}
