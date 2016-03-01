package Parsers;

public interface IParser<A,B> {
	public B Parse(A data);
}
