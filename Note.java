import java.lang.*;
import java.util.*;

class Note {
	String name; // holds name of note
	double num; // holds numerical representation of note
	double duration; // holds length of note (half note, whole, etc.)
	
	public Note(String n, double d) {
		this.name = n;
		
		// numerical representation
		if (this.name.equals("A")) { this.num = 1; } else
		if (this.name.equals("A#") || this.name.equals("B&")) { this.num = 1.5; } else
		if (this.name.equals("B") || this.name.equals("C&")) { this.num = 2; } else
		if (this.name.equals("C") || this.name.equals("B#")) { this.num = 2.5; } else
		if (this.name.equals("C#") || this.name.equals("D&")) { this.num = 3; } else
		if (this.name.equals("D")) { this.num = 3.5; } else
		if (this.name.equals("D#") || this.name.equals("E&")) { this.num = 4; } else
		if (this.name.equals("E") || this.name.equals("F&")) { this.num = 4.5; } else
		if (this.name.equals("F") || this.name.equals("E#")) { this.num = 5; } else
		if (this.name.equals("F#") || this.name.equals("G&")) { this.num = 5.5; } else
		if (this.name.equals("G")) { this.num = 6; } else
		if (this.name.equals("G#")) { this.num = 6.5; } else
		if (this.name.equals("rest")) { this.num = 0; }
		
		// duration
		this.duration = d;
    }
    
    String getName() { return this.name; } void setName(String s) { this.name = s; 	if (this.name.equals("A")) { this.num = 1; }
																					if (this.name.equals("A#")) { this.num = 1.5; }
																					if (this.name.equals("B")) { this.num = 2; }
																					if (this.name.equals("C")) { this.num = 2.5; }
																					if (this.name.equals("C#")) { this.num = 3; }
																					if (this.name.equals("D")) { this.num = 3.5; }
																					if (this.name.equals("D#")) { this.num = 4; }
																					if (this.name.equals("E")) { this.num = 4.5; }
																					if (this.name.equals("F")) { this.num = 5; }
																					if (this.name.equals("F#")) { this.num = 5.5; }
																					if (this.name.equals("G")) { this.num = 6; }
																					if (this.name.equals("G#")) { this.num = 6.5; }}
    double getNum() { return this.num; }
    double getDur() { return this.duration; } void setDur(double d) { this.duration = d; }
}