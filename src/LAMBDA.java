import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class LAMBDA {

    static boolean evalError = false;

    static public void main(String argv[]) {
        System.out.print("LAMBDA> ");
        do {
            String input = readInput().trim();
            if (input.equals("exit"))
                break;
            else input += ";";
            try {
                CharStream in = CharStreams.fromString(input);
                LAMBDALexer lexer = new LAMBDALexer(in);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                LAMBDAParser parser = new LAMBDAParser(tokens);
                parser.setErrorHandler(new BailErrorStrategy());
                // Having issues here and onward~ For obvious reasons
                System.out.println("Approaching tree");
                LAMBDANode tree = (LAMBDANode) parser.lambdaInput().value;
                //Integer level = new Integer(1);
                //displayTree(tree,level);
                evalError = false;
                double answer = evalTree(tree);
                if (evalError)
                    System.out.println("\nEVALUATION ERROR\n");
                else
                    System.out.println("\nThe value is "+answer+"\n");
            } catch (Exception e) {
                System.out.println("\nSYNTAX ERROR\n");
                //e.printStackTrace();
            }
        } while (true);
    }

    static double evalTree(LAMBDANode t) {
        if (t.getNodeType().equals("num")) {
            return t.getValue();
        }
        else if (t.getNodeType().equals("plus")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            return v1+v2;
        }
        else if (t.getNodeType().equals("minus")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            return v1-v2;
        }
        else if (t.getNodeType().equals("multiply")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            return v1*v2;
        }
        else if (t.getNodeType().equals("divide")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            if (v2 != 0)
                return v1/v2;
            else {
                evalError = true;
                return 0;
            }
        }
        else {
            evalError = true;
            System.out.println("error");
            return 0;
        }
    }

    static void displayTree(LAMBDANode t,Integer level) {
        while(t.getNextNode() != null) {
            for (int i = 1; i < level.intValue(); i++)
                System.out.print("    ");
            System.out.print("NodeType=" + t.getNodeType() + "  ");
            if (t.getNodeType().equals("num"))
                System.out.println("Value=" + t.value);
            else if (t.getNodeType().equals("plus") ||
                    t.getNodeType().equals("minus") ||
                    t.getNodeType().equals("multiply") ||
                    t.getNodeType().equals("divide")) {
                System.out.println();
                int lval = level.intValue();
                lval++;
                level = new Integer(lval);
                displayTree(t.getChild1(), level);
                displayTree(t.getChild2(), level);
            } else {
                System.out.println("");
                int lval = level.intValue();
                lval++;
                level = new Integer(lval);
                displayTree(t.getChild1(), level);
                displayTree(t.getChild2(), level);
            }
        }
    }

    static String readInput() {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.flush();
            int c = System.in.read();
            while(c != ';' && c != -1) {
                if (c != '\n')
                    buffer.append((char)c);
                else {
                    buffer.append(" ");
                    System.out.print("LAMBDA> ");
                    System.out.flush();
                }
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }
}


/**
import org.antlr.v4.runtime.BailErrorStrategy;
        import org.antlr.v4.runtime.CharStream;
        import org.antlr.v4.runtime.CharStreams;
        import org.antlr.v4.runtime.CommonTokenStream;

        import java.io.IOException;

//import org.antlr.v4.runtime.ANTLRErrorStrategy;

public class LISP {

    static boolean evalError = false;

    static public void main(String argv[]) {
        System.out.print("LISP> ");
        do {
            String input = readInput().trim();
            if (input.equals("exit"))
                break;
            else input += ";";
            try {
                CharStream in = CharStreams.fromString(input);
                LISPLexer lexer = new LISPLexer(in);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                LISPParser parser = new LISPParser(tokens);
                parser.setErrorHandler(new BailErrorStrategy());
                LAMBDANode tree = (LAMBDANode) parser.lispStart().value;
                //Integer level = new Integer(1);
                //displayTree(tree,level);
                evalError = false;
                double answer = evalTree(tree);
                if (evalError)
                    System.out.println("\nEVALUATION ERROR\n");
                else
                    System.out.println("\nThe value is "+answer+"\n");
            } catch (Exception e) {
                System.out.println("\nSYNTAX ERROR\n");
                //e.printStackTrace();
            }
        } while (true);
    }

    static double evalTree(LAMBDANode t) {
        if (t.getNodeType().equals("num")) {
            return t.getValue();
        }
        else if (t.getNodeType().equals("plus")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            return v1+v2;
        }
        else if (t.getNodeType().equals("minus")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            return v1-v2;
        }
        else if (t.getNodeType().equals("multiply")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            return v1*v2;
        }
        else if (t.getNodeType().equals("divide")) {
            double v1 = evalTree(t.getChild1());
            if (evalError) return 0;
            double v2 = evalTree(t.getChild2());
            if (evalError) return 0;
            if (v2 != 0)
                return v1/v2;
            else {
                evalError = true;
                return 0;
            }
        }
        else {
            evalError = true;
            System.out.println("error");
            return 0;
        }
    }

    static void displayTree(LAMBDANode t,Integer level) {
        while(t.getNextNode() != null) {
            for (int i = 1; i < level.intValue(); i++)
                System.out.print("    ");
            System.out.print("NodeType=" + t.getNodeType() + "  ");
            if (t.getNodeType().equals("num"))
                System.out.println("Value=" + t.value);
            else if (t.getNodeType().equals("plus") ||
                    t.getNodeType().equals("minus") ||
                    t.getNodeType().equals("multiply") ||
                    t.getNodeType().equals("divide")) {
                System.out.println();
                int lval = level.intValue();
                lval++;
                level = new Integer(lval);
                displayTree(t.getChild1(), level);
                displayTree(t.getChild2(), level);
            } else {
                System.out.println("");
                int lval = level.intValue();
                lval++;
                level = new Integer(lval);
                displayTree(t.getChild1(), level);
                displayTree(t.getChild2(), level);
            }
        }
    }

    static String readInput() {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.flush();
            int c = System.in.read();
            while(c != ';' && c != -1) {
                if (c != '\n')
                    buffer.append((char)c);
                else {
                    buffer.append(" ");
                    System.out.print("LISP> ");
                    System.out.flush();
                }
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }
}
*/