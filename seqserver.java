// Samantha Tolentino
// IT 114, Section 003
// Dr. Halper
// App Project #2
// Dec. 4, 2019

// The following code is a sequential server, meaning
// it processes requests from different clients one at
// a time. It will listen for a request from a client,
// process that request, and will keep looking for requests
// from other clients. Because the loop is infinite, the
// program must be terminated manually.

import java.util.*;
import java.net.*;
import java.io.*;
import java.text.DecimalFormat;

public class seqserver
{

 public static void main(String[] args)
 {

    ServerSocket serverSocket = null;
    Socket socket = null;
    int port;
    boolean listening = true;
    String operation;
    String genome;
    String sequence;

    // Get port number from command line.

    port = Integer.parseInt(args[0]);

    // Try to create a new server socket.

    try
    {
            
      serverSocket = new ServerSocket(port);

    }

    catch(IOException e)
    {

	    System.out.println(e);
	    listening = false;

    }

    if(listening) // serverSocket successfully created.
    {

      // Continue to...
     
      //   (1) Listen for a client request.

      //   (2) Read data from the client.

      //   (3) Process the request: do calculation and return value.

    while(true) // Main processing loop.
    {

      try
      {

        // Listen for a connection request from a client.

        socket = serverSocket.accept();

        // Establish the input and output streams on the socket.

        PrintWriter out = new
                     PrintWriter(socket.getOutputStream(), true);

        Scanner in = new Scanner(new
	                   InputStreamReader(socket.getInputStream()));

        // Read data from the client, do calculation(s).

        operation = in.nextLine();
        genome = in.nextLine();
        sequence = in.nextLine();

        // Return the data values:

        // If the user enters "occur" as the operation, perform
        // the operation by calling the occur method.

        if (operation.equals("occur"))
        {

          // Before calling the occur method, first call the
          // invalidCharacter method to ensure that the inputted
          // genome and sequence do not contain characters other
          // than A, C, G, and T. Store the result of that method
          // in the variable invalid_occur.

          String invalid_occur = invalidCharacter(genome, sequence);

          // If the returned result of invalidCharacter is equal to
          // "Invalid character.", that means the genome or sequence
          // contains characters other than A, C, G, and T. Return
          // the result to the client.

          if (invalid_occur.equals("Invalid character."))
          {

            out.println(invalid_occur);

          }

          // If the returned result of invalidCharacter is not equal
          // to "Invalid character.", that means the genome and the
          // sequence contain the correct characters. Return the 
          // result to the client.

          else
          {

            out.println(occur(genome, sequence));

          }

        }

        // If the user enters "score" as the operation, perform
        // the operation by calling the score method.

        else if (operation.equals("score"))
        {

          // Before calling the score method, first call the
          // invalidCharacter method to ensure that the inputted
          // genome and sequence do not contain characters other
          // than A, C, G, and T. Store the result of that method
          // in the variable invalid_score.

          String invalid_score = invalidCharacter(genome, sequence);

          // If the returned result of invalidCharacter is equal to
          // "Invalid character.", that means the genome or sequence
          // contains characters other than A, C, G, and T. Return
          // the result to the client.

          if (invalid_score.equals("Invalid character."))
          {

            out.println(invalid_score);

          }

          // If the returned result of invalidCharacter is not equal
          // to "Invalid character.", that means the genome and the
          // sequence contain the correct characters. Return the 
          // result to the client.

          else
          {

            out.println(score(genome, sequence));

          }

        }

        // If the user enters "align" as the operation, perform
        // the operation by calling the align method.

        else if (operation.equals("align"))
        {

          // Before calling the align method, first call the
          // invalidCharacter method to ensure that the inputted
          // genome and sequence do not contain characters other
          // than A, C, G, and T. Store the result of that method
          // in the variable invalid_align.

          String invalid_align = invalidCharacter(genome, sequence);

          // If the returned result of invalidCharacter is equal to
          // "Invalid character.", that means the genome or sequence
          // contains characters other than A, C, G, and T. Return
          // the result to the client.

          if (invalid_align.equals("Invalid character."))
          {

            out.println(invalid_align);

          }

          // If the returned result of invalidCharacter is not equal
          // to "Invalid character.", that means the genome and the
          // sequence contain the correct characters. Return the 
          // result to the client.

          else
          {

            out.println(align(genome, sequence));

          }

        }

        // If the user enters an operation that does not equal
        // any of the above ones, return the error message: 
        // "Not a valid operation."

        else
        {

          out.println("Not a valid operation.");

        }

        // Close connection to the client.

          out.close();
          in.close();
          socket.close();

      }

      catch(IOException e)
      {

        System.out.println(e);

      }

     } // End while (main processing loop).

   } // End if listening.

  } // End main.

  // The invalidCharacter method analyzes a given genome and sequence
  // for any characters other than A, C, G, and T. 

  public static String invalidCharacter(String g_nome, String seq)
  {

    // Create a list called "invalidList" containing characters
    // that are not permitted. 

    List <String> invalidList = new ArrayList<>(Arrays.asList("B", "D",
     "E", "F", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
     "S", "U", "V", "W", "X", "Y", "Z"));

    String invalid = "";

    // Loop through invalidList. If the genome contains the character
    // at the index of i in invalidList, then add "Invalid character"
    // to the empty invalid string. Break out of the for loop if 
    // an invalid character is found.

    for (int i = 0; i < invalidList.size(); i++)
    {

      if (g_nome.contains(invalidList.get(i)))
      {

        invalid += "Invalid character.";
        break;

      }

      // If the sequence contains the character at the index of i
      // in invalidList, then add "Invalid character" to the empty
      // invalid string. Break out of the for loop if an invalid
      // character is found.

      if (seq.contains(invalidList.get(i)))
      {

        invalid += "Invalid character.";
        break;

      }

    }

    return invalid;

  }

  // The occur method counts how many times a given sequence
  // appears in a given genome.

  public static int occur(String g_nome, String seq)
  {

    int lastIndex = 0;
    int count = 0;

    // While the variable lastIndex is not equal to -1,
    // perform the following code. If lastIndex is not 
    // equal to -1, that means there are more occurrences
    // of the sequence.
    
    while (lastIndex != -1) 
    {

      lastIndex = g_nome.indexOf(seq, lastIndex);

      // lastIndex will eventually reach -1 as it
      // does not find any more occurrences of the
      // sequence in the genome. Increment count
      // every time lastIndex is not equal to -1, 
      // and add the value of lastIndex by the length
      // of sequence.

      if(lastIndex != -1)
      {

        count++;
        lastIndex += seq.length();

      }

    }

    // If lastIndex is equal to -1, return count.

    return count;

 }

 // The score method will determine the best similarity
 // score for a given genome and sequence. 

 public static String score(String g_nome, String seq)
 {

  List <Integer> hamm_list = new ArrayList<>();

  DecimalFormat df = new DecimalFormat("#.00");

  // First, iterate through the characters in genome.
  // Substring the genome from the index of a to the
  // length of the genome (exclusive). 

  for (int a = 0; a < g_nome.length(); a++)
  {
    String substr = g_nome.substring(a, g_nome.length());
    int hamming = 0;

    // The following if statement is meant to break out of
    // the initial for loop once the length of the sequence
    // - 1 is equal to the length of the genome - the index
    // of a. If the sequence and genome are equal in length,
    // then no more comparison can be done.

    // Thus, break entirely out of the for loop and go straight
    // to processing the similarity score.

    if((seq.length() - 1) == (g_nome.length() - a))
    {

      break;

    }

    // If sequence and genome are not yet equal in length, 
    // continue with this for loop. Iterate through the
    // characters in sequence. If the character at the index
    // of b in sequence is NOT equal to the character at the
    // index of b in the substring-ed genome, increment 
    // hamming by one.

    // We do this in order to get the hamming distance. The
    // hamming distance is needed to get the similarity score.

    for (int b = 0; b < seq.length(); b++)
    {

      if (seq.charAt(b) != substr.charAt(b))
      {

        hamming++;

      }
      
    }

  // Once for loop is finished, add the value of hamming to
  // our list: hamm_list. Keep doing this until the initial
  // for loop is broken out of.  

  hamm_list.add(hamming);

  }

  // This code is for performing operations to get the
  // similarity score. 

  // Set the minimum variable to the smallest value in
  // hamm_list using the Collections.min() method.

  double minimum = Collections.min(hamm_list);

  // Get similarity score and format it to two deimcal
  // places. Initialize that value to fmt_score and return
  // it.

  double sim_score = (seq.length() - minimum) / seq.length();

  String fmt_score = df.format(sim_score);

  return fmt_score;

 }

 // The align method matches the first occurrence of a sequence
 // in the genome and displays it as a new string. The rest of
 // the string is filled with dashes.

 public static String align(String g_nome, String seq)
 {

    // the value of index will be equal to the first occurrence
    // of the sequence in the genome.

    int index = g_nome.indexOf(seq);
    int dash_count = 0;
    String alignment = "";

    // If index is equal to -1, this means that no sequence
    // has been found in the genome. Return "No alignment
    // found."

    if (index == -1)
    {

      return "No alignment found."; 

    }

    // While the dash_count is not equal to the index, 
    // add a dash to the alignment variable. Dashes 
    // will continue to be added until it reaches the
    // first occurrence of the sequence in the genome.

    while (dash_count != index)
    {

      alignment += "-";
      dash_count++;

    }

    // After the while loop has been executed, 
    // add the inputted sequence to alignment. The
    // format would now be dashes first, and then the
    // sequence (ie: -----seq).

    alignment += seq;

    // If the length of alignment is equal to the 
    // length of genome, this means the processing
    // has finished. Return the alignment.

    if (alignment.length() == g_nome.length())
    {

      return alignment;

    }

    // The following code is to fill in the rest of
    // the alignment. Subtract the length of genome
    // by the length of alignment to know how many
    // more dashes to add. Return alignment.

    else
    {

      int count = g_nome.length() - alignment.length();

      for (int i = 0; i < count; i++)
      {

        alignment += "-";

      }

      return alignment;

    }

  }

} // End seqserver