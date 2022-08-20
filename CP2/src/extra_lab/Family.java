package extra_lab;

public class Family {
	Parent parent1,parent2;
	Child[] children;
	
	public Family(Parent parent1, Parent parent2, Child[] children) {
		this.parent1=parent1;
		this.parent2=parent2;
		this.children=children;
	}
	
	public String toString() {
		String toReturn=parent1.toString()+"\n"+parent2.toString()+"\n";
		for (int i = 0; i < children.length; i++) {
			if(children[i]!=null)
				toReturn=toReturn+children[i].toString()+"\n";
		}
		return toReturn;
	}
	public String familyFormat() {
		String toReturn=parent1.parentFormat()+","+parent2.parentFormat();
		for (int i = 0; i < children.length; i++) {
			if(children[i]!=null)
				toReturn=toReturn+","+children[i].childFormat();
		}
		return toReturn;
	}
	public Parent getParent1() {
		return parent1;
	}

	public void setParent1(Parent parent1) {
		this.parent1 = parent1;
	}

	public Parent getParent2() {
		return parent2;
	}

	public void setParent2(Parent parent2) {
		this.parent2 = parent2;
	}
}
