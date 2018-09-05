import java.util.ArrayList;
import java.util.List;

/**
 * This class Node has a list of alphabet and each character in the list has a list of alphabet.
 * Code is learned from the link:
 * https://www.javagists.com/java-tree-data-structure
 * */
public class Node<T> {

    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
//    private boolean isLeaf = false;

    public Node() {
        this.data = null;
    }

    public Node(T data) {
        this.data = data;
    }

    public Node<T> addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
//        child.setIsLeaf();
        return child;
    }

    public void addChildren(List<Node<T>> children) {
        children.forEach(each -> each.setParent(this));
//        children.forEach(each -> each.setIsLeaf());
        this.children.addAll(children);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

//    /**
//     * This func check if current node contains the character.
//     * @param character
//     *  wanted data
//     *
//     * @return true if contains the character; false if not
//     * */
//    public boolean containCharacter(T character) {
//
//        if (this.data == character) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void traverse(char character) {

        if (this.children == null || this.children.isEmpty()) {
            return;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

//    public boolean contain(char word) {
//        boolean containResult = false;
//
//
//
//        return containResult;
//    }

//    public void setIsLeaf() {
//        this.isLeaf = true;
//    }
}
