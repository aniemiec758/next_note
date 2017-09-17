import java.lang.*;
import java.util.*;

class Scale { // holds info of a note
	private String name; // name of the scale
	private double notes[] = new double[8]; // array of the scale's chords, numerically
	private String letters[] = new String[8]; // shows the shit alphabetically
	/*private*/ double fNote; // first note of chord
	private String temp; // temp string
	/*private*/ ArrayList<Note> queue = new ArrayList<Note>(); // ArrayList of Note objects

	public Scale(String n) {
		this.name = n;

		// couldn't make a consistent algorithm due to all the exceptions (uneven distribution of black and white keys)
		if (n.charAt(0) == 'A') { fNote = 1; } else
		if (n.charAt(0) == 'B') { fNote = 2; } else
		if (n.charAt(0) == 'C') { fNote = 2.5; } else
		if (n.charAt(0) == 'D') { fNote = 3.5; } else
		if (n.charAt(0) == 'E') { fNote = 4.5; } else
		if (n.charAt(0) == 'F') { fNote = 5; } else
		if (n.charAt(0) == 'G') { fNote = 6; }

		if (n.charAt(1) == '#') { fNote += .5; } // for # scales
		if (n.charAt(1) == '&') { fNote -= .5; } // for & scales
		
		if (fNote < 1) { fNote += 6; } // wraps around if too low
		if (fNote > 6.5) { fNote -= 6.0; } // wraps around if too high
		
		if (fNote > 6.5) { // i.e. started at 'G' and continued upwards
			fNote -= 6;
		}
		
//****************************************************************************//
		
		// set up the numbers behind the scale:
		if (n.contains("Harmonic Major")) {
			notes[0] = fNote;	notes[1] = fNote+1;
			notes[2] = fNote+2;	notes[3] = fNote+2.5;
			notes[4] = fNote+3.5;	notes[5] = fNote+4;
			notes[6] = fNote+5.5;	notes[7] = fNote+6;
			//this.name = fNote + " Harmonic Major";	// EDIT: make a way to correct the name, because "C shamalama Major" is valid... would change to just "C Major"
		} else if (n.contains("Melodic Major")) {
			notes[0] = fNote;	notes[1] = fNote+1;
			notes[2] = fNote+1.5;	notes[3] = fNote+2.5;
			notes[4] = fNote+3.5;	notes[5] = fNote+4;
			notes[6] = fNote+5;	notes[7] = fNote+6;
			//this.name = fNote + " Melodic Major";
		} else if (n.contains("Major") || n.contains("Natural Major")) { // is a major scale: 	wwhwwwh
			notes[0] = fNote;	notes[1] = fNote+1;
			notes[2] = fNote+2;	notes[3] = fNote+2.5;
			notes[4] = fNote+3.5;	notes[5] = fNote+4.5;
			notes[6] = fNote+5.5;	notes[7] = fNote+6;
			//this.name = fNote + " Major";
		} else if (n.contains("Harmonic Minor")) {
			notes[0] = fNote;	notes[1] = fNote+1;
			notes[2] = fNote+1.5;	notes[3] = fNote+2.5;
			notes[4] = fNote+3.5;	notes[5] = fNote+4;
			notes[6] = fNote+5.5;	notes[7] = fNote+6;
			//this.name = fNote + " Harmonic Minor";
		} else if (n.contains("Melodic Minor")) {
			notes[0] = fNote;	notes[1] = fNote+1;
			notes[2] = fNote+1.5;	notes[3] = fNote+2.5;
			notes[4] = fNote+3.5;	notes[5] = fNote+4.5; System.out.println(fNote + " " + fNote+4.5 + " " + fNote+5.5);
			notes[6] = fNote+5.5;	notes[7] = fNote+6;
			//this.name = fNote + " Melodic Minor";
		} else if (n.contains("Minor") || n.contains("Natural Minor")) { // is a minor scale:	whwwhww
			notes[0] = fNote;	notes[1] = fNote+1;
			notes[2] = fNote+1.5;	notes[3] = fNote+2.5;
			notes[4] = fNote+3.5;	notes[5] = fNote+4;
			notes[6] = fNote+5;	notes[7] = fNote+6;
			//this.name = fNote + " Minor";
		}

		// correcting things
		for (int i = 0; i < 8; i++) {
			if (notes[i] > 6.5) { notes[i] -= 6; } // if notes go above into H, I, etc.
			
			/*if (notes[i] == 2.5 || notes[i] == 5.5) { // B# -> E, E# -> F
				for (int j = i; j < 8; j++) {
					System.out.println("DEBUG: " + notes[j]);
					notes[j] += .5; // corrects any subsequent notes upwards a half-step
					System.out.println(" incremented by .5 into " + notes[j]);
				}
			}*/
		}

		// set up the letters (notes) behind the scale:
		for (int i = 0; i < 8; i++) { // sets up the letters[] array
			// hard-coded due to exceptions in the algorith; unequal spacing of black and white keys
			if (notes[i] == 1.0) { temp = "A"; }
			if (notes[i] == 1.5) { temp = "A#"; }
			if (notes[i] == 2.0) { temp = "B"; }
			if (notes[i] == 2.5) { temp = "C"; }
			if (notes[i] == 3.0) { temp = "C#"; }
			if (notes[i] == 3.5) { temp = "D"; }
			if (notes[i] == 4.0) { temp = "D#"; }
			if (notes[i] == 4.5) { temp = "E"; }
			if (notes[i] == 5.0) { temp = "F"; }
			if (notes[i] == 5.5) { temp = "F#"; }
			if (notes[i] == 6.0) { temp = "G"; }
			if (notes[i] == 6.5) { temp = "G#"; }
			
			letters[i] = temp;
		}

		// set up the chords behind the scale:
// *implement...


	}

	void printScale() { // prints the whole scale to the screen
		System.out.print("  " + this.letters[0]);
		for (int i = 1; i < 8; i++) {
			System.out.print(" " + this.letters[i]);
		}
		System.out.println();
	}
	
	void printNums() { // prints out the scale in terms of numbers, A=1, B=2, C#=3.5, ...
		System.out.print("  " + this.notes[0]);
		for (int i = 1; i < 8; i++) {
			System.out.print(" " + this.notes[i]);
		}
		System.out.println();
	}
	
	void printName() { // prints name of the scale to the screen
		System.out.println("  Currently in: " + this.name);
	}
	
	void setName(String s) {
		this.name = s;
	}
	
	String getName() {
		return this.name;
	}
	
	int getQLen() { // size of queue
		return this.queue.size();
	}
	
	Note getNatQ(int i) { // returns note from Queue
		return this.queue.get(i);
	}

	void push(Note n) {
		this.queue.add(n);
	}
	
	String getLet(int i) {
		if (i > 7) { i = 7; }
		if (i < 0) { i = 0; }
		return this.letters[i];
	}
	
	void showChord() { // shows all chords in the scale
		//EDIT: implement /**11**//
	}
	
	void showGraph() { // ASCII representation of circle of fifths
		System.out.println(" [stand-in sample text for circle of fifths] "); // EDIT: implement
	}
	
	Note createNote(Scale scale) { // handles the creation of note objects
		Scanner scan = new Scanner(System.in);
		String tempS = "q"; // gives a value to begin with
		
		System.out.print("What note? ");
		while ((tempS.charAt(0) < 65 || tempS.charAt(0) > 71) && !(tempS.equals("rest"))) { // while it isn't A thru G
			tempS = scan.nextLine(); //EDIT: skips right through the "duration" step when pushing a note, maybe a newline is to be caught somewhere?
		}
		System.out.println("How long to play " + tempS + " for?\n\t(.25,.5,1,1.5,2,4) ");
		double tempD = scan.nextDouble();
		while (!(tempD%.25==0) || (tempD < 0 || tempD > 4)) { // it must be divisible by 1/4, and it also must be a value between 0 and 4
			tempD = scan.nextDouble();
		}
		Note n = new Note(tempS,tempD);
		System.out.println("Added note to queue."); scan.nextLine();
		boolean flag = true; // sees if this note is in the scale
		for (int i = 0; i < 7; i++) {
			if (scale.getLet(i).equals(tempS)) {
				flag = false; // if it's in the scale, we're in the clear
			}
		} if (flag) { // if it isn't in the scale, give WARNING
			System.out.println("  WARNING: This note isn't in the traditional " + scale.getName() + " scale.");
		}
		return n;
	}
	
	void insert(Scale scale) { // edits queue via insert
		System.out.println("  Select a number, and a note will be inserted BEFORE that current note. ('q' to cancel)");
		printQueue();
		int i = getQueue();
		Note n = createNote(scale);
		this.queue.add(i, n);
		System.out.println("  Put the note " + n.getName() + " at queue position " + i + ".");
		//EDIT: implement 'q' to cancel, in 'insert', 'delete', and 'replace'
	} void delete(Scale scale) { // edits queue via delete
		System.out.println("  Select a number, and that note will be DELETED. ('q' to cancel)");
		printQueue();
		int i = getQueue();
		System.out.println("  Deleted note " + this.queue.get(i).getName() + " at index " + i + ".");
		this.queue.remove(i);
	} void replace(Scale scale) { // edits queue via replace
		System.out.println("  Select a number, and that note will be REPLACED. ('q' to cancel)");
		printQueue();
		int i = getQueue();
		Note n = createNote(scale);
		System.out.println("  Replaced note " + this.queue.get(i).getName() + " with note " + n.getName() + " at index " + i + ".");
		this.queue.set(i, n); // EDIT: doesn't work!!!***
	} void printQueue() {
		int i = 0; int i2 = 0; // incrementors for num and note loops
		int k; // temp LCV
		System.out.print("\t");
		int size = this.queue.size();
		
		System.out.print("\n\t");
		while (i != this.queue.size()) {
			if (size > 7 && (size-i>7)) { // queue is larger than 7, will go to new line
				for (int j = 0; j < 7; j++) { // print a line of numbers
					System.out.print(i + " ");
					i++;
				} System.out.print("\n\t");
				for (int j = 0; j < 7; j++) { // print a line of notes
					System.out.print(queue.get(i2).getName() + " ");
					i2++;
				}  System.out.print("\n\n\t");
			} else {
				k = i;
				for (int j = 0; j < this.queue.size()-k; j++) {
					System.out.print((i) + " ");
					i++;
				} System.out.print("\n\t");
				k = i2;
				for (int j = 0; j < this.queue.size()-k; j++) {
					System.out.print(this.queue.get(i2).getName() + " ");
					i2++;
				}
			}
		}System.out.print("\n\n  Enter your queue number... ");
	}
	
	int getQueue() { // gets queue of item and returns... bleh
		int i = -1;
		Scanner scan = new Scanner(System.in);
		while (i < 0 || i > this.queue.size()) {
			i = scan.nextInt();
		}
		return i;
	}

} // end of file