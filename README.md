# Space-Explorer-and-The-Federation-HQ

# Objectives:
   Let there be a galaxy with space explorers and Federation headquarters (HQs). The goal of the
space explorers is to explore all the wormholes so that they have a complete view of the galaxy. The
wormholes connect two star systems together, but entering them requires a perfect decoding of their
sub-space-mambo-jumbonian frequencies. Once a wormhole is explored, the federation sends a special sci-
ence vessel through, which, if it survives, scans the new solar system and provides the data about the next
wormholes and the star systems they connect.
  The Federation has multiple HQs, but they can be busy with other tasks and, as such, they are not al-
ways available. When a Federation HQ is available, it takes the decoded solution from the space explorers
and sends the science vessel. If the decoding is wrong when the science vessel tries to enter the worm-hole,
a great explosion destroys the galaxy, kills everyone, ends the program.

# Space Explorers and the Sub-space-mambo-jumbonian Frequencies:
  Each frequency decoding starts with data from the federation HQ, on which the science vessel uses an ana-
lyzatron and computes the frequency as a seemingly random string. The space explorer then needs to decode
the sub-space-mambo-jumbonian frequencies, in order to enter a wormhole. To do this, the space explorer
needs to compute the hash of the hash of the hash of ... of the hash of the frequency 1 . The number of times
to repeat the hash operation is fixed and given as a parameter to the simulation.
  The space explorers start the simulation at a given set of star systems which can be reached with bor-
ing slower-than-the-speed-of-light drives. Then, they get the list of wormholes from the federation HQs.
Each wormhole has a frequency represented as a string sequence attached to it. An explorer chooses a
wormhole and explores it by decoding the frequency and then sending the result to the Federation HQs,
which verify its correctness by sending the science vessel.

# The Galaxy
   The galaxy is composed of many solar systems. One solar system can be connected through wormholes with
any number of other solar systems, whereas a few well-known solar systems can be accessed with a boring
slower-than-the-speed-of-light drive. There is no guarantee that any system can be reached from any other
system using this mesh of worm-holes. However, there is a guarantee that all interesting solar systems can
be reached if all wormholes are used. You can imagine the galaxy as a graph, where the solar systems are
the vertices and the wormholes are the edges that connect these vertices. The well-known solar systems are
the vertices of the graph that can be accessed at the start of the simulation. The space explorers need to
navigate through the wormholes and decode their frequencies in order to discover the entire galaxy.
It is possible to know where a wormhole will lead before it is decoded, so a science vessel can go through.
However, in order to find the other wormholes from the new solar system, the science vessel needs to go
there.

# Federation HQs
   Federation HQs are responsible not only for space exploration, but they each have a multitude of other tasks
and experiments to complete. Because everyone at the Federation HQ is super important and super busy,
it is possible to have large time periods when they simply do not answer. In this new society, bureaucracy
has reached new, previously undreamed-of levels, so it is not clear when the HQs stop answering and when
they start again.
When a Federation HQ does answer, it receives a wormhole’s decoded sub-space-mambo-jumbonian fre-
quency from a space explorer, along with the names of the two solar systems that are connected by said
wormhole (i.e., the solar system that the space explorer has arrived from, and the solar system it currently
is located in). An incorrect decoding result will trigger an explosion, because everyone at the headquarters
is too busy to double check and they just send the science vessel through.
Once the science vessel returns, the Federation gives the list of new wormholes to the space explorers,
so they can start work on the next decodings.

# The Communication Channel
   The space explorers and the Federation HQs communicate using a special bi-directional communication
channel. For every decoded wormhole frequency, the space explorers give the Federation HQs the following
information in a single message: new solar system, previous solar system, and the decoded string.
For every decoded solution, the federation HQs give the space explorers the list of solar systems connected
through wormholes with the newly-accessible solar system. If the federation HQ gets an incorrect decode or
an incorrect previous solar system, the science vessel enters the wormhole incorrectly and the entire galaxy
explodes and implodes at the same time.
When there are no more wormholes to explore, the federation HQ puts EXIT messages in the commu-
nication channel, one for each space explorer. They also trigger the end of the program.
After a wormhole’s frequency is correctly decoded, the Federation HQs give the space explorers the list
of new solar systems in separate messages that arrive in the following order:
      
      • discovered solar system
      • adjacent undiscovered solar system 1
      • discovered solar system
      • adjacent undiscovered solar system 2
      • ...
      • END.
      
   If two federation HQs give the space explorers this information at the same time, it might confuse them.
Each of the adjacent solar system messages contains the string to be hashed by the space explorers repre-
senting the sub-space-mambo-jumbonian frequencies.

# Scalability
   It should be obvious by now that, if we had more space explorers decoding, the solar systems would be
discovered faster. Because of this, the number of space explorers should match the number of threads and
the execution time should scale with the number of brave space explorers.

# Running and Testing
   The program should be be run as follows:
   
      java Homework <wormHoles> <hashCount> <federationHQCount> <spaceExplorerCount>
      
   The first parameter specifies the files used for input, the second one represents the number of times the hash
function should be applied when decoding a frequency, the third parameter is the number of Federation HQ
threads, while the final parameter specifies the number of space explorer threads.
   As an example, the program in the skeleton can be run as shown below:
   
     java Homework test_cases/test01 100000 4 4
     
   In order to help with the testing, I have provided one set of input files, located in the folder
called test cases. The file test01 answer.txt contains the correct answers for each wormhole (which should
be decoded by the space explorers and verified by the Federation HQs), test01 data.txt contains the values
to be hashed (i.e., undecoded sub-space-mambo-jumbonian frequencies), while test01 graph.txt contains the
layout of the galaxy. All test files should follow the same naming format (e.g., if your first parameter when
running the homework is “mytest”, then you should have the files mytest answer.txt, mytest data.txt and
mytest graph.txt). The files are read only by the Federation HQs, whereas the space explorers only receive
data from the HQs.

# Galaxy Generator and Wormhole Frequency Decoder
   To help with the testing, I have added a Python program that can generate a galaxy (i.e., a graph)
and the frequencies for the wormholes (i.e., the values to be hashed by the explorers). It is located in the
folder called generator, and can be run as follows (where we create a graph with 100 nodes and 150 edges,
stored in files test01 graph.txt and test01 data.txt):

      python generate_graph.py -f test01 -s 100 -e 150
      
  In the homework archive, there is a tool for computing consecutive hashes on some input strings,
which can be used to generate the input files with decoded frequencies that the HQs use to verify the cor-
rectness of the values received from the explorers. The HQ implementation in the skeleton uses those same
hash functions to verify the correctness of the explorers’ computations.
