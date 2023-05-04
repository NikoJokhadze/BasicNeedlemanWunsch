import java.util.Scanner;

class NeedlemanWunsch {
    private static final int MATCH = 1;
    private static final int MISMATCH = -1;
    private static final int GAP = -2;

    public static void NeedlemanWunsch(String sequenceOne, String sequenceTwo) {
        int lengthAmount = sequenceOne.length(); //finds the length of the first sequence
        int heightAmount = sequenceTwo.length(); //finds the length of the second sequence

        char[] charArray1 = sequenceOne.toCharArray(); //inserts every individual letter of the line into a character array
        char[] charArray2 = sequenceTwo.toCharArray(); //inserts every individual letter of the line into a character array

        int[][] nwTable = new int[lengthAmount + 2][heightAmount + 2];

        for (int i = 0; i < lengthAmount; i++){ // this will occupy the spots of letters using numerical equivalents
            for (int j = 0; j < heightAmount; j++) { // as this is a 2D int array
                switch (charArray2[j]) {
                    case 'A':
                        nwTable[0][j + 2] = 'A';
                        break;
                    case 'C':
                        nwTable[0][j + 2] = 'C';
                        break;
                    case 'G':
                        nwTable[0][j + 2] = 'G';
                        break;
                    case 'T':
                        nwTable[0][j + 2] = 'T';
                        break;
                    default:
                        nwTable[0][j + 2] = 0;
                        break;
                }
            }
            switch (charArray1[i]) {
                case 'A':
                    nwTable[i + 2][0] = 'A';
                    break;
                case 'C':
                    nwTable[i + 2][0] = 'C';
                    break;
                case 'G':
                    nwTable[i + 2][0] = 'G';
                    break;
                case 'T':
                    nwTable[i + 2][0] = 'T';
                    break;
                default:
                    nwTable[i + 2][0] = 0;
                    break;
            }
        }

        for (int i = 0; i <= lengthAmount; i++){ // this implements the scoring system of the table by
            for (int j = 0; j <= heightAmount; j++) { //decreasing by 2 for every spot moved
                nwTable[1][j + 1] = -j * 2;
            }
            nwTable[i + 1][1] = -i * 2;
        }

        int northTemp = 0;
        int westTemp = 0;
        int northWestTemp = 0;

        for (int i = 2; i <= lengthAmount + 1; i++){ // this is where we preform the scoring for the
            for (int j = 2; j <= heightAmount + 1; j++) { // rest of the table
                northTemp = nwTable[i][j - 1] + GAP; // accounts for both north and west gap subtraction
                westTemp = nwTable[i - 1][j] + GAP; // according to the scoring system

                if (nwTable[i][0] == nwTable[0][j]){ //checks to see if the current spot is a match
                    northWestTemp = nwTable[i - 1][j - 1] + MATCH;
                } else {
                    northWestTemp = nwTable[i - 1][j - 1] + MISMATCH;
                }

                if (northTemp > westTemp && northTemp > northWestTemp){ //compares each number to determine which one is the biggest
                    nwTable[i][j] = northTemp; // the biggest number becomes the new spot in the table
                } else if (westTemp > northTemp && westTemp > northWestTemp){
                    nwTable[i][j] = westTemp;
                } else {
                    nwTable[i][j] = northWestTemp;
                }
            }
        }

        for(int[] row : nwTable) {
            for (int i : row) {
                System.out.print(i);
                System.out.print("\t");
            }
            System.out.println();
        }

        //System.out.println(Arrays.deepToString(nwTable));
        System.out.println("The final, best possible alignment score is " + nwTable[lengthAmount + 1][heightAmount + 1] + ".");
    }

    public static void main(String[] args) {
        Scanner sequence = new Scanner(System.in);
        String leftSequence;
        String topSequence;

        System.out.print("Enter your 1st sequence (left column): ");
        leftSequence = sequence.next();

        System.out.print("Enter your 2nd sequence (top row): ");
        topSequence = sequence.next();

        NeedlemanWunsch( leftSequence, topSequence);
    }
}