import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    /**
     * This func readFile reads a file, which contains the tests.
     * learn the code from the link
     * https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
     *
     * @param fileName
     *  file name
     *
     * @return List of words in String type
     * */
    public static List<String> readFile(String fileName) {

        List<String> wordsInString = Collections.emptyList();

        try {
            wordsInString = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordsInString;
    }


    private static Node<Character> extendTree(Node<Character> node, char character) {

        // if the node's children does not contains the character,
        // then, add a new child and return it
        List<Node<Character>> children = node.getChildren();
        boolean hasWantedChar = false;
        Node<Character> returnChild = new Node<>();

        // check if the children have the character
        for (Node<Character> curChild : children) {
            if (curChild.getData() == character) {
                hasWantedChar = true;
                returnChild = curChild;
            }
        }

        // if does not have the character, then add a child and return
        // else return
        if (hasWantedChar == false) {
            Node<Character> newChild = new Node<>(character);
            node.addChild(newChild);
            returnChild = newChild;
        }

        return returnChild;
    }

    public static void buildTree(Node<Character> root, char[] word) {

        List<Node<Character>> nodesInList = root.getChildren();
        Node<Character> curNode = root;
        char curChar;

        for (int i = 0; i < word.length; i++) {
            curChar = word[i];
            curNode = extendTree(curNode, curChar);
        }
    }

    private static boolean contain(Node<Character> node, char charInWord) {
        boolean result = false;
        List<Node<Character>> nodeList = node.getChildren();
        Node<Character> currentNode = node;
        int wordIndex = 0;
        char curChar;

        for (Node<Character> child : nodeList) {
            if (child.getData() == charInWord) {
                result = true;
            }
        }

        return result;
    }

    /**
     * This func returns the child node who has the same data as charInWord.
     * */
    private static Node<Character> getChild(Node<Character> node, char charInWord) {
        List<Node<Character>> nodeList = node.getChildren();
        Node<Character> currentNode = node;

        for (Node<Character> curChild : nodeList) {
            if (curChild.getData() == charInWord) {
                currentNode = curChild;
            }
        }

        return currentNode;
    }

    /**
     * This func uses mid traverse method to go through all characters.
     * */
//    private static Character midTrav(Node<Character> node) {
//
//
//    }

    /**
     * This func traverses subTree and return all possibilities in list.
     * */
    private static List<String> getTails(Node<Character> subTree) {
        List<String> tails = new ArrayList<>();
        List<Node<Character>> nodeList = subTree.getChildren();



        return tails;
    }

    public static List<String> findWords(Node<Character> root, String wantedWord) {
        List<String> result = new ArrayList<>();
        char[] charArray = wantedWord.toCharArray();
        // the subTree will be the tail for the exact word
        // for exmaple:
        //  input: app
        //  exact: app
        //  similar: apple, appppp, ...
        // the subTree contains: le, ppp, ...
        Node<Character> subTree;

        char curChar;

        // first check if the tree contains the word exactly
        //              if contains, then, check the similar words and add them to the list
        //              else, return empty list
        Node<Character> currentNode = root;
        List<Node<Character>> nodeList = root.getChildren();
        boolean containRes = false;

        // check if the tree exactly has the word
        for (int i = 0; i < charArray.length; i++) {
            curChar = charArray[i];
            containRes = contain(currentNode, curChar);

            if (containRes == false) {
                return result;
            } else {
                currentNode = getChild(currentNode, curChar);
            }
        }

        // if the tree does have the word
        // then, search for the following nodes which can append to the word
        // else, just return the empty list indicating there is no word found
        if (containRes == true) {
            result.add(wantedWord);


        }

        return result;
    }

    public static void main(String[] args) {

        // capture users' input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the wanted word: ");

        String wanted = scanner.next();
//        System.out.println("wanted: " + wanted);


        // read the file and save words in list
        List wordsInList = readFile("words.txt");

        // print for test
        Iterator<String> iterator = wordsInList.iterator();

        // root does not have any data
        Node<Character> root = new Node<>();

        // based on the file, build the tree
        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
            char[] curWord = (iterator.next()).toCharArray();
            buildTree(root, curWord);
        }

        // find the wanted word
        List<String> findResult = findWords(root, wanted);

        // print the result
        if (findResult.size() == 0 || findResult.isEmpty()) {
            System.out.println("Not found the word: " + wanted);
        } else {
            for (String foundWord : findResult) {
                System.out.println(foundWord);
            }
        }

    }
}
