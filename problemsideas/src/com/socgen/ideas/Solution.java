package com.socgen.ideas;

public class Solution {
	public static void main(String[] args) {
		// firstnames with mix of capitalized and non capitalized letters
		String[] firstname = { "nATHALIE", "sTEVEN", "Léa", "dAVID", "lOUIS", "cYRIL", "pHILIPPE", "alex", "VIKRAM",
				"Ioana", "richard", "Rémy", "tantely", "patricia", "thierry", "pascal", "anne-sophie", "Jonathan" };
		// lastnames with mix of capitalized and non capitalized letters
		String[] lastname = { "robert", "BeaubruN En FaMILLe DIANt", "limelette", "Dik", "GOdlewSKi", "Da SiLva",
				"amice", "mokkadem", "RUNGLOLL", "ardelean", "PHONTHIBSVADS", "guilloux", "ANDRIAMALALA", "leclerc",
				"villepreux", "dennu", "leriC", "luminuku" };
		//		String firstname[] = { "steven", "Jean-Luc" };
		//		String lastname[] = { "BeaubruN-En-FaMILLe-DIANt", "Maeckelberghe" };

		// empty String array where to store concatenation of firstname and lastname
		// dynamic sizing of the arrays according to firstname[] length
		String[] names = new String[firstname.length];
		// empty string array where to store initials
		String[] initials = new String[firstname.length];

		// loop within each firstname
		for (int i = 0; i < firstname.length; i++) {
			firstname[i] = formatFirstname(firstname[i]);

			// System.out.print(lastname[i] + " ==> ");
			lastname[i] = formatLastname(lastname[i]);

			// concatenate firstname and lastname in names array
			names[i] = firstname[i];
			names[i] = names[i] + " ";
			names[i] = names[i] + lastname[i];
			// build a string of initials from firstname and lastname
			initials[i] = BuildInitial(firstname[i]) + " " + BuildInitial(lastname[i]);

			System.out.println("Prénom + nom " + String.format("%02d", i + 1) + " : " + names[i]);
			System.out.println("Initiales    " + String.format("%02d", i + 1) + " : " + initials[i]);
		}
	}

	public static String formatFirstname(String firstname) {
		// use of stringbuffer for better performance
		StringBuffer sb = new StringBuffer();
		// populate stringbuffer with firstname
		sb.append(firstname);
		// force first letter to uppercase
		sb.replace(0, 1, sb.substring(0, 1).toUpperCase());

		// loop into firstname searching for ' ' or '-'
		for (int i = 1; i < firstname.length(); i++) {
			// if the previous character is " " or "-", then capitalize the letter
			if (sb.substring(i - 1, i).equalsIgnoreCase(" ") || sb.substring(i - 1, i).equalsIgnoreCase("-")) {
				sb.replace(i, i + 1, sb.substring(i, i + 1).toUpperCase());
			} else {
				sb.replace(i, i + 1, sb.substring(i, i + 1).toLowerCase());
			}
		}

		// return formatted firstname
		return sb.toString();
	}

	public static String formatLastname(String lastname) {
		// use of stringbuffer for better performance
		StringBuffer sb = new StringBuffer();
		// populate stringbuffer with lastname
		sb.append(lastname);
		// force the whole lastname to uppercase
		sb.replace(0, sb.length(), sb.toString().toUpperCase());

		// return formatted lastname
		return sb.toString();
	}

	public static String BuildInitial(String name) {
		/*
		 * Create a string of initials from a firstname or a lastname.
		 * For a composed-name :
		 *  - the method takes the first letter of each term, and add a dot.
		 *  - if terms are separated by a dash, replicate the dash in the initials string
		 * Examples : Jean NEMARD 		  ==> J. N.
		 *  		  Paul SAINT-GERMAIN  ==> P. S.-G.
		 * 			  Pierre Paul JACQUES ==> P.P. J. 
		 * 			  Jean-Paul OCHON 	  ==> J.-P. O. 
		 * 			  Alain TERIEUR-DETOI ==> A. T.-D.
		 */
		StringBuffer sbInitials = new StringBuffer();

		// force first letter of name to uppercase...
		sbInitials.append(name.substring(0, 1));
		// ... followed by a point
		sbInitials.append(".");
		// loop within letters of name, beginning index 1,
		// first index have been treated above
		for (int j = 1; j < name.length(); j++) {

			if (name.substring(j - 1, j).equalsIgnoreCase(" ") || name.substring(j - 1, j).equalsIgnoreCase("-")) {
				// separate first initial from next one
				if (name.substring(j - 1, j).equalsIgnoreCase("-")) {
					// copy the dash in the initials if it exists in the name
					sbInitials.append("-");
				}
				// add the initial...
				sbInitials.append(name.charAt(j));
				// ...followed by a dot
				sbInitials.append(".");
			}
		}

		return sbInitials.toString();
	}
}