/**
 * 
 */
package jdransoft.model;

/**
 * Request object to use when specifying a specific deck by name
 * 
 * @author jacobr
 *
 */
public class DeckRequest {
	String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
