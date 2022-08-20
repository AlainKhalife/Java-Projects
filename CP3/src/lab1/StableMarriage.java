package lab1;

public class StableMarriage {
	
	private Person m1 = new Person("m1"); // This is man1
	private Person m2 = new Person("m2"); // This is man2
	private Person m3 = new Person("m3"); // This is man3
	private Person w1 = new Person("w1"); // This is women1
	private Person w2 = new Person("w2"); // This is women2
	private Person w3 = new Person("w3"); // This is women3
	private Person[] men = {m1, m2, m3}; // Array that will hold the mens
	private Person[] women = {w1, w2, w3}; // Array that will hold the womens
	
	private void setup() {
		// Setting the preferences of each men and women
		// The preference here are set IN ORDER FROM LEFT TO RIGHT (HIGH PREFERENCE TO LEAST PREFERED)
		m1.set_preferences(w3, w1, w2); // Example here the order of preference is w3 at the top then w2 and the bottom is w1
		m2.set_preferences(w3, w2, w1); // Same applies for all the below
		m3.set_preferences(w1, w3, w2);
		w1.set_preferences(m2, m3, m1);
		w2.set_preferences(m2, m1, m3);
		w3.set_preferences(m3, m2, m1);
	}
	
	private void m_proposal() {
		// In case the men proposes instead of women
		setup();
		int numberOfFreeWomen = 3; // Keeping track of the free woman to engage to
		while(numberOfFreeWomen>0) {
			for(int i =0; i<men.length; i++) {
				
				if(!men[i].is_engaged() && !men[i].getTopPreference().is_engaged()) {
					// If the men is not engaged and the women is not engaged then we have a matching
					men[i].setIs_engaged(true);
					men[i].setIsEngagedTo(men[i].getTopPreference());
					men[i].getPersonEngagedTo().setIs_engaged(true);
					men[i].getPersonEngagedTo().setIsEngagedTo(men[i]);
					numberOfFreeWomen--;
				}
				
				else if (!men[i].is_engaged() && men[i].getTopPreference().is_engaged()){
					// if the men proposes and the women is already engaged then she has a choice to either breakup with the current partner or reject the proposal
					int current_engaged_rank = 0;
					int proposed_to_rank = 0;
					
					for(int j=0; j<men[i].getPreference().length; j++) {
						// Here I am looping through the preference list of the women to see in which ranking the men proposing to and the men currently engaged to stand in
						if(men[i].getTopPreference().getPreference()[j]==men[i])
							proposed_to_rank = j;
						
						else if(men[i].getTopPreference().getPreference()[j]==men[i].getTopPreference().getPersonEngagedTo())
							current_engaged_rank = j;
					}
					
					if(current_engaged_rank<proposed_to_rank) {
						// Following the structure of my code, if the index of the men currently engaged to in the women preference list array is less than the men proposing to her
						// Then he has a higher ranking, according to the the structure of my code
						// Therefore he scratches her from his preference list
						men[i].decreaseTopPref();
					}
					
					else {
						// Otherwise she brakes up with her current partner and engages with men that he proposed to her
						men[i].getTopPreference().getPersonEngagedTo().setIsEngagedTo(null); // setting the partner of the top preference person on the list to be single again
						men[i].getTopPreference().getPersonEngagedTo().setIs_engaged(false);
						men[i].getTopPreference().getPersonEngagedTo().decreaseTopPref();
						men[i].getTopPreference().setIsEngagedTo(men[i]); // Engaging women to proposing men
						men[i].getTopPreference().setIs_engaged(true);
						men[i].setIsEngagedTo(men[i].getTopPreference()); // Engaging men to top preference women
						men[i].setIs_engaged(true);
					}
						
				}
			}
		}
		
		for(int i=0; i<men.length; i++) {
			// Just printing the results
			System.out.println(men[i].toString() + " is engaged to " + men[i].getPersonEngagedTo().toString() );
		}
	}
	
	private void w_proposal() {
		// In this method, the women will propose instead of the men
		setup();
		int numberOfFreeWomen = 3; // Keeping track of the free woman to engage to
		while(numberOfFreeWomen>0) {
			for(int i =0; i<women.length; i++) {
				
				if(!women[i].is_engaged() && !women[i].getTopPreference().is_engaged()) {
					// If the men is not engaged and the women is not engaged then we have a matching
					women[i].setIs_engaged(true);
					women[i].setIsEngagedTo(women[i].getTopPreference());
					women[i].getPersonEngagedTo().setIs_engaged(true);
					women[i].getPersonEngagedTo().setIsEngagedTo(women[i]);
					numberOfFreeWomen--;
				}
				
				else if (!women[i].is_engaged() && women[i].getTopPreference().is_engaged()){
					// if the women proposes and the men is already engaged then she has a choice to either breakup with the current partner or reject the proposal
					int current_engaged_rank = 0;
					int proposed_to_rank = 0;
					
					for(int j=0; j<women[i].getPreference().length; j++) {
						if(women[i].getTopPreference().getPreference()[j]==women[i])
							proposed_to_rank = j;
						
						else if(women[i].getTopPreference().getPreference()[j]==women[i].getTopPreference().getPersonEngagedTo())
							current_engaged_rank = j;
					}
					
					if(current_engaged_rank<proposed_to_rank) {
						women[i].decreaseTopPref();
					}
					
					else {
						women[i].getTopPreference().getPersonEngagedTo().setIsEngagedTo(null); // setting the partner of the top preference person on the list to be single again
						women[i].getTopPreference().getPersonEngagedTo().setIs_engaged(false);
						women[i].getTopPreference().setIsEngagedTo(women[i]); // Engaging women to proposing men
						women[i].getTopPreference().setIs_engaged(true);
						women[i].setIsEngagedTo(women[i].getTopPreference()); // Engaging men to top preference women
						women[i].setIs_engaged(true);
					}
						
				}
			}
		}
		
		for(int i=0; i<women.length; i++) {
			System.out.println(women[i].toString() + " is engaged to " + women[i].getPersonEngagedTo().toString() );
		}
	}
	
	public static void main(String[] args) {
		System.out.println("In case men proposes to women");
		StableMarriage m2 = new StableMarriage();
		m2.m_proposal();
		System.out.println("\nIn case women proposes to men");
		StableMarriage m = new StableMarriage();
		m.w_proposal();
	}

}
