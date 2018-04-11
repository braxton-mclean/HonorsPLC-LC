// Literally a copy of LISPNode we had before, need to change it to be more capable of functional shenanigans
// Did add a LAMBDANode list object though which should make it easier to process internal nodes for applications (iirc)

public class LAMBDANode {
    String nodeType; // "num", "plus', "minus", "multiply", "divide", "if"
    double value;
    int listSize;
    LAMBDANode[] nodeList;


    LAMBDANode child1 = null;
    LAMBDANode child2 = null;
    LAMBDANode nextNode = null;

    LAMBDANode (int i) {
        listSize = i;
        nodeList = new LAMBDANode[listSize];
    }

    public void setNodeType(String n) {
        nodeType = n;
    }

    public void setValue(double v) {
        value = v;
    }

    public void setChild1(LAMBDANode n) {
        child1 = n;
    }

    public void setChild2(LAMBDANode n) {
        child2 = n;
    }

    public void setNextNode(LAMBDANode n) {nextNode = n;}

    public String getNodeType() {
        return nodeType;
    }

    public double getValue() {
        return value;
    }

    public LAMBDANode getChild1() {
        return child1;
    }

    public LAMBDANode getChild2() {
        return child2;
    }

    public LAMBDANode getNextNode() { return  nextNode; }
}