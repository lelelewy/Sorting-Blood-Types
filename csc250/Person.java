/**
* Assignment: Project 4
* Due date: 12/6/13
* Instructor: Dr. DePasquale 
* Submitted by: Leah Lewy
*/

package csc250;

/**
* The Person class encapsulates the representation of a person object. It implements the
* comparable interface, which means it needs to include a compareTo method. It contains information
* on the name, address, and bloodtype of the person.
* @author Leah Lewy
*/
public class Person implements Comparable<Person> {
	/** This string object represents the first name of the person */
	private String givenName;
	/** This string object represents the last name of the person */
	private String surname;
	/** This string object represents the street of the person */
	private String streetAddress;
	/** This string object represents the city of the person */
	private String city;
	/** This string object represents the state of the person */
	private String state;
	/** This integer variable represents the zip code of the person */
	private int zipCode;
	/** This string object represents the blood type of the person */
	private String bloodType;
	/** This string object represents the rhesus factor of the person */
	private String rhesus;
	/** This long will count the number of comparisons made when sorting */
	private static long counter = 0;

	/**
    * This is a constructor. It sets up the ability for a member info object to be instantiated.
    * @param first The string value of this object's first name
    * @param last The string value of this object's last name
    * @param street The string value of the object's street
    * @param stat The string value of this object's state
    * @param zip The int value of this object's zip code
    * @param bloodT The string value of this object's blood type
    * @param rhes The string value of this object's rhesus factor
    */
	public Person (String first, String last, String street, String cit, String stat, int zip, String bloodT, String rhes) {
		givenName = first;
		surname = last;
		streetAddress = street;
		city = cit;
		state = stat;
		zipCode = zip;
		bloodType = bloodT;
		rhesus = rhes;
	}

	/** 
    *Returns a string representation of the object
    *@return string representation
    */
	public String toString () {
		return givenName + "|" + surname + "|" + streetAddress + "|" + city + "|" + state + "|" + zipCode + "|" + bloodType + rhesus;
	}

	/**
	* This is the required comparedTo method. It sets up the ability for the person objects in the array
	* to be sorted. Based on the code, the objects will first be ordered in ascending order based on blood type.
	* If those values are equal, then they are to be placed in ascending order based on rhesus factors. The last
	* value that is checked in the state. These are to be placed also in ascending order
	* @param Person other This is the person object that will be compared to another person object.
	*/
	public int compareTo (Person other) {
		counter++;
		if (bloodType.compareTo(other.bloodType) < 0)
				return -1;
		else if (bloodType.compareTo(other.bloodType) >= 1)
				return 1;
		else {
			if (rhesus.compareTo(other.rhesus)<0)
				return -1;
			else if (rhesus.compareTo(other.rhesus)>=1)
				return 1;
			else {
				if (state.compareTo(other.state)<0)
					return -1;
				else if (state.compareTo(other.state)>=1)
					return 1;
				else {
					return 0;
				}
			}
		}
	}

	/**
	* This is an accessor method for the counter variable.
	* @return counter - the number of comparisons
	*/
	public static long getCounter() {
		return counter;
	}

	/**
	* This is a mutator method for the counter variable.
	* @param count - this is needed to reset the counter variable
	*/
	public static void setCounter(int count) {
		counter = count;
	}
}