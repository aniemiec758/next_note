import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.*;
//include arraylists

public class next_note {

	public static void main(String[] ars) {
		System.out.println("=== Welcome to next_note! ===");
		Scale s = setup(); // sets up the user's workspace before inf loop of creation
		loop(s); // sets up the loop until the user types 'quit' and chooses if they want to save their work into a .txt file
		
		System.out.println("Goodbye!");
	}

	static Scale setup() { // sets up workspace (key, preferences, etc)
		char temp = ' '; // random user-input text
		String tempS = " "; // random string-comparison var
		int q = -1; // random loop-control variable
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Would you like to import a file? (y/n) ");
		while (temp != 'y' && temp != 'n') {
			temp = scan.nextLine().charAt(0);
		}
		if (temp == 'y') { // they're importing a file
			System.out.println("What is the name of the file?");
			String fName = scan.nextLine();
			try {
				File file = new File(fName);
				Scanner fScan = new Scanner(file);
				String s2 = fScan.nextLine(); System.out.println("DEBUG: " + s2);
				Scale scale = new Scale(fScan.nextLine()); // first line is the scale
				while (fScan.hasNextLine()) {
			System.out.println("deb 5");
					String s = fScan.next();
					System.out.println("DEBUG: " + s); // DEBUG
				}
			} catch (FileNotFoundException e) {
				/*(goes straight to normal creation)*/
				System.out.println("Couldn't find the specified file. A blank project\n  has been created in C Major. (this can be changed with 'change_scale')");
			}
		} else {
			System.out.println("Ok! We'll start from scratch.");
			System.out.println("What key shall your song be in? (type \"list\" for scales,\n\tor \"help\" for help)");
			tempS = selScale();
			Scale scale = new Scale(tempS); // creates the scale
			
/************************************************************/
			
			System.out.println("\nGreat! I'll be your companion while you compose in " + scale.getName() + ".");
			System.out.println("\nI can help you keep track of notes you play if you type 'push [note].'");
			System.out.println("\t(Remember, I only take CAPITAL letters!)\n");
			// EDIT: update this to reflect important bits of 'help'
			System.out.println("Other useful tools are...");
			System.out.println("=========================================");
			System.out.println("\tpush\t\tenters a note into the queue");
			System.out.println("\tshow_queue\tprints the current note queue");
			System.out.println("\tshow_queue_detail\n\t\t\t\tprints the current note queue alphabetically and\n\t\t\t\t\tnumerically, with duration");
			System.out.println("\tedit_queue\tedits notes you've played (type 'help' for more info)");
			System.out.println("\tnext_note\tsuggest notes to play, based on notes you've played");
			System.out.println("\tnext_chord\tsuggest a chord, based on notes you've played");
			System.out.println("\tshow_scale\tshows all notes in the scale");
			System.out.println("\tshow_nums\tshows the scale numerically (A=1.0,A#=1.5,...)\n\t\t\t\t(This is useful when calculating half-steps manually)");
			System.out.println("\tnext_scale\tsuggests scale shifts based on previous notes and the circle of fifths");
			System.out.println("\tchange_scale [note] [adjustment] [type]\n\t\t\t\tchange the scale");
			System.out.println("\tscale_help\tshows how to manually construct scales");
			System.out.println("\thelp\t\tshows this help page");
			System.out.println("\tquit\t\tasks the user to save progress to a .txt file, then exits the program");
			System.out.println("=========================================");
			System.out.println("\nHave fun composing!\n");	
			return scale;
		}
		Scale scale = new Scale("C Major"); // creates C Major in case of error
		return scale; // passes Scale over to main, passes to loop()
	} // end of setup
	
	static void loop(Scale scale) { // the infinite loop of creation
		Scanner scan = new Scanner(System.in);
		char c = ' '; // holds 'y/n' info
		String cmd; // holds user's commands
		String tempS, tempS2; // temporary strings
		double tempD; // temporary double
		
		int k = 0;
		while (k<1) { // list of all functions
			cmd = scan.nextLine();
			
			if (cmd.contains("push")) { // putting a note into the queue
				scale.push(scale.createNote(scale));
				// EDIT: implement; if ( it's a good push command ) { execute, add to arraylist }
			} else if (cmd.equals("show_queue")) { // print queue
				if (scale.getQLen() > 0) {
					System.out.print("  " + scale.getNatQ(0).name);
					for (int i = 1; i < scale.getQLen(); i++) {
						System.out.print(" " + scale.getNatQ(i).name); // getNatQ returns the Note, and this harvests the .name attribute
						if (i%7 == 0) { // pretty printing: 8 notes per line
							System.out.print("\n  ");
						}
					}
					System.out.println();
				} else {
					System.out.println("ERROR: No notes in queue.");
				}
			} else if (cmd.equals("show_queue_detail")) {
				if (scale.getQLen() > 0) {
					System.out.println("\t" + scale.getNatQ(0).name + "\t" + scale.getNatQ(0).num + "\t" + scale.getNatQ(0).duration + " counts"); // EDIT: Note attributes aren't private
					for (int i = 1; i < scale.getQLen(); i++) {
						System.out.println("\t" + scale.getNatQ(i).name + "\t" + scale.getNatQ(i).num + "\t" + scale.getNatQ(i).duration + " counts");
					}
				} else {
					System.out.println("ERROR: No notes in queue.");
				}
			} else if (cmd.equals("next_note")) { // suggests next note
				System.out.print("Here are some notes you haven't recently played in " + scale.getName() + ":\n  ");
				ArrayList<String> rec = new ArrayList<String>(); //(Arrays.asList("A","A#","B","C","C#","D","D#","E","F","F#","G","G#"));
				for (int i = 0; i < 7; i++) {
					rec.add(scale.getLet(i));
				}
				
				int i;
				if (scale.getQLen() < 3) { i = 0; } else { i = scale.getQLen() - 3; }
				for (int j = i; j < scale.getQLen(); j++) { // removes past 5 notes
					rec.remove(scale.getNatQ(j).getName());
				}
				for (String s : rec) {
					System.out.print(" " + s);
				} System.out.println();
				
				// suggest chords if there are applicable ones
				System.out.println("Here are some chords you could play: ");
				//scale.nextChord();
			} else if (cmd.equals("next_chord")) { // suggests next chord
				scale.showChord();
			} else if (cmd.equals("show_scale")) { // shows the scale in an alphabetical context
				scale.printName(); scale.printScale();
			} else if (cmd.equals("show_nums")) { // shows the scale in a numerical context
				scale.printName(); scale.printNums();
			} else if (cmd.equals("next_scale")) { // suggests another scale based on circle of fifths
				System.out.println("Here is a graph of scales similar to " + scale.getName() + ".");
				scale.showGraph();
				
				//EDIT: implement
			} else if (cmd.equals("change_scale")) { // changes the scale, walks them through the process
				System.out.println("  What would you like to change the scale to?\n  (from " + scale.getName() + ")");
				scale = new Scale(selScale());
				System.out.println("  The scale is now " + scale.getName() + ".");
			} else if (cmd.equals("scale_help")) { // says how to construct scales
				System.out.println("  Some patterns to manually construct scales starting at any note:\n\t(w = whole step, h = half step)");
				System.out.println("-Major (Natural)\twwhwwwh"); // EDIT: implement
				System.out.println("-Harmonic Major\t\twwhwh(w+h)h");
				System.out.println("-Melodic Major\t\twhwwhww");
				System.out.println("-Minor (Natural)\twhwwhww");
				System.out.println("-Harmonic Minor\t\twhwwh(w+h)h");
				System.out.println("-Melodic Minor\t\twhwwwwh");
			} else if (cmd.equals("edit_queue")) { // edits the queue
				System.out.println("  How would you like to edit the queue? (insert, delete, replace) ");
				do {
					tempS = scan.nextLine();
				} while (!(tempS.equals("insert")) && !(tempS.equals("delete")) && !(tempS.equals("replace")));
				if (tempS.equals("insert")) {
					scale.insert(scale);
				} else if (tempS.equals("delete")) {
					scale.delete(scale);
				} else if (tempS.equals("replace")) {
					scale.replace(scale);
				}
			} else if (cmd.equals("help") || cmd.equals("Help")) {
				// EDIT: print all commands, in page form (i.e. === (1/2 ) ===)
				// EDIT: alphabatize/group!!!
				System.out.println("=========================================");
				System.out.println("\tpush\t\tenters a note into the queue");
				System.out.println("\tshow_queue\tprints the current note queue");
				System.out.println("\tshow_queue_detail\n\t\t\t\tprints the current note queue alphabetically and\n\t\t\t\t\tnumerically, with duration");
				System.out.println("\tedit_queue\tedits notes you've played (type 'help' for more info)");
				System.out.println("\tnext_note\tsuggest notes to play, based on notes you've played");
				System.out.println("\tnext_chord\tsuggest a chord, based on notes you've played");
				System.out.println("\tshow_scale\tshows all notes in the scale");
				System.out.println("\tshow_nums\tshows the scale numerically (A=1.0,A#=1.5,...)\n\t\t\t\t(This is useful when calculating half-steps manually)");
				System.out.println("\tnext_scale\tsuggests scale shifts based on previous notes and the circle of fifths");
				System.out.println("\tchange_scale [note] [adjustment] [type]\n\t\t\t\tchange the scale");
				System.out.println("\tscale_help\tshows how to manually construct scales");
				System.out.println("\thelp\t\tshows this help page");
				System.out.println("\tquit\t\tasks the user to save progress to a .txt file, then exits the program");
				System.out.println("=========================================");
			
			// quitting (end of program)
			} else if (cmd.equals("quit") || cmd.equals("Quit")) {
				System.out.print("\nWould you like to save your work to a file? (y/n) ");
				while (c!='y' && c!='n') {
					tempS = scan.nextLine();
					c = tempS.charAt(0);
				}
				if (c == 'y'){
					saveWork(scale); // allows users to export
				} else {
					c = ' ';
					System.out.print("Are you sure you want to exit WITHOUT saving? (y/n) ");
					while (c!='y' && c!='n') {
						tempS = scan.nextLine();
						c = tempS.charAt(0);
					}
					if (c == 'n') {
						saveWork(scale);
					}
				}
				k = 1; // exits the loop
			} else {
				System.out.println("\tERROR: Invalid command. (type 'help' for a list of commands)");
			}
		}
		
	}
	
	static void saveWork(Scale scale) { // lets users save their work into a .txt file
		/*try {
		String fileName;
		fileName = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(scale.getName() + "\n");
		for (Note n : scale.queue) { // EDIT: dropped 'private' status, this is a bad workaround
			writer.write(n.getName() + " " + n.getDur() + " ");
		System.out.println("Successfully wrote to " + fileName + "!");
		} catch (IOException e) {}*/ // to implement later
	}

	static String selScale() { // selects a scale in beginning, lets user change scales later on
		int q=-1;
		String tempS = "";
		Scanner scan = new Scanner(System.in);
		while (q==-1) { // selects which scale
			tempS = scan.nextLine();
			if (tempS.length() > 0) { // in case they ONLY put in '\n', because the .charAt(0) will throw an OutOfBounds exception; could've done 'catch' but that would just be extra steps of the same solution, man
				if ((tempS.charAt(0) > 64 && tempS.charAt(0) < 72) && (tempS.contains("Minor") || tempS.contains("Major"))) { // if (has 'A' to 'G') and (has "Minor" or "Major")
					q = 0; // breaks the loop
				} else {
					System.out.println("Error: Type [note] [adjustment] [type], or 'help' if you're confused\n\t(Make sure you capitalize every starting letter)");
				}
			}
			
			// other commands
			if (tempS.equals("list")) { // list scales // EDIT: do 'help' here instead?
				System.out.println("=========================================");
				System.out.println("*Write the following: [Note] [Adjustment] [Type]\n\t[ex: 'C Harmonic Major']");
				System.out.println("Notes\t\tA, B, C, D, E, F, G");
				System.out.println("Adjustment\tNatural, Harmonic, Melodic");
				System.out.println("Type\t\tMinor, Major");
				System.out.println("=========================================");
			}
		}
		return tempS;
	}

} // end of file


//asks about key, help settings, etc
//LIST OF COMMANDS:
/* 	
	clear_notes
		forgets about notes played so far
	switch_hand ('left' or 'right')
		changes to the other hand
	put_note (note)
		inserts a note into the queue; checks if it's in the scale
		if it isn't in the key, gives a warning, and says what scale it COULD be/transition to (circle of fifths)
	del_note
		will delete a note in history and shift all notes back one
	ins_note
		will insert a note in the history
	rep_note
		replaces a note in history
	wheel
		shows wheel of perfect fifths
	scale_help
		shows how to make major, minor, harmonic, and melodic scales (wwhwwwh...)
	chord_help
		says formula for making chords ( x[www]y[perf. 5th]z )
	
	*be able to set timing of notes
	*some function to play notes via midi files
	*plot notes onto sheet music
	*putting in half and whole rests, etc
	*a list of all scales that have been used, and what notes they were used for
*/