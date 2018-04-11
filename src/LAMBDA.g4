grammar LAMBDA;

lambdaInput returns [LAMBDANode value]:
    lambda SEMI
    | LET bindings IN lambda SEMI
;

atomicLam :
    VAR
    | GLB
    | LPAREN lambda RPAREN
;

appLams :
    atomicLam
    | appLams atomicLam
;

lambda :
    appLams
    | LAM varSeq DOT lambda
;

varSeq :
    VAR
    | VAR varSeq
;

bindings :
    binding
    | binding COMMA bindings
;

binding :
    VAR EQUALS lambda
;

fragment VALID_ID_START : ('a'..'z') | ('A'..'Z') ;
fragment VALID_ID_CHAR : VALID_ID_START | ('0'..'9') | '_';

// generic tokens
NUMBER : ('0'..'9')+;
LPAREN : '(';
RPAREN : ')';
SEMI : ';';
PLUS : '+';
MINUS : '-';
TIMES : '*';
DIV : '/';
EQUALS : '=';

//lambda tokens
LAM : '\\';
COMMA : ',';
DOT : '.';
LET : 'let';
IN : 'in';
VAR : VALID_ID_START VALID_ID_CHAR*;
GLB : '$' VALID_ID_START VALID_ID_CHAR*;

//White Space
WS : [ \r\n\t]+ -> skip;


// Grammar rules example from LISP
/**
lispStart returns [LISPNode value] :
     w=lisp SEMI {$value = $w.value;};

lisp returns [LISPNode value] :
    num=NUMBER
  { LISPNode n = new LISPNode();
    n.setNodeType("num");
    n.setValue(Double.parseDouble($num.text));
    $value = n;
  }
  | LPAREN PLUS w1=lisp w2=lisp RPAREN
  { LISPNode n = new LISPNode();
    n.setNodeType("plus");
    n.setChild1($w1.value);
    n.setChild2($w2.value);
    $value = n;
  }
  | LPAREN MINUS w1=lisp w2=lisp RPAREN
  { LISPNode n = new LISPNode();
    n.setNodeType("minus");
    n.setChild1($w1.value);
    n.setChild2($w2.value);
    $value = n;
  }
  | LPAREN TIMES w1=lisp w2=lisp RPAREN
  { LISPNode n = new LISPNode();
    n.setNodeType("multiply");
    n.setChild1($w1.value);
    n.setChild2($w2.value);
    $value = n;
  }
  | LPAREN DIV w1=lisp w2=lisp RPAREN
  { LISPNode n = new LISPNode();
    n.setNodeType("divide");
    n.setChild1($w1.value);
    n.setChild2($w2.value);
    $value = n;
  }
  | LPAREN CAR LPAREN l1=listExp RPAREN RPAREN
  { LISPNode n = new LISPNode();
    n.setNodeType($l1.value.nodeType);
    n.setValue($l1.value.value);
    $value = n;
  }
;

listExp returns [LISPNode value] :
    CDR LPAREN l1=listExp RPAREN
  { LISPNode n = new LISPNode();
    n.setValue($l1.value.nextNode.value);
    n.setNextNode($l1.value.nextNode.nextNode);
    n.setNodeType($l1.value.nextNode.nodeType);
    $value = n;
  }
  | w1=lisp w2=listExp
  { LISPNode n = new LISPNode();
    n.setNodeType($w1.value.nodeType);
    n.setValue($w1.value.value);
    n.setNextNode($w2.value);
    $value = n;
  }
  | w1=lisp
  {
    LISPNode n = new LISPNode();
    n.setValue($w1.value.value);
    n.setNodeType($w1.value.nodeType);
    $value = n;
  }

;

// Lexer rules
// fragments are not tokens
//fragment VALID_ID_START : ('a'..'z') | ('A'..'Z') | '_';
//fragment VALID_ID_CHAR : VALID_ID_START | ('0'..'9');
fragment C          : ('C'|'c') ;
fragment D          : ('D'|'d') ;
fragment A          : ('A'|'a') ;
fragment R          : ('R'|'r') ;

//tokens
NUMBER : ('0'..'9')+;
LPAREN : '(';
RPAREN : ')';
SEMI : ';';
PLUS : '+';
MINUS : '-';
TIMES : '*';
DIV : '/';
CDR : C D R;
CAR : C A R;
//ID : VALID_ID_START VALID_ID_CHAR*;
//White Space
WS : [ \r\n\t]+ -> skip;