grammar Cmm;	

program: REAL_CONSTANT
       ;
  		 
INT_CONSTANT: [1-9] DIGIT*
            | '0'
            ;

ID: (LETTER|'_') (LETTER|DIGIT|'_')*
  ;

fragment
LETTER: [a-zA-Z]
      ;

fragment
DIGIT: [0-9]
     ;

CHAR_CONSTANT: '\'' . '\''
             | ASCII_CHAR
             | SPECIAL_CHAR
             ;

fragment
ASCII_CHAR: '\'' '\\' INT_CONSTANT '\''
          ;

fragment
SPECIAL_CHAR: '\'' '\\' 'n' '\''
            | '\'' '\\' 't' '\''
            ;

REAL_CONSTANT: (FLOATING_REAL|INT_CONSTANT) [eE] ('-'|'+')? INT_CONSTANT
             | FLOATING_REAL
             ;

fragment
FLOATING_REAL: INT_CONSTANT '.' DIGIT*
             | INT_CONSTANT? '.' DIGIT+
             ;

WHITESPACE: ([ \t]|NEW_LINE)+ -> skip
          ;

MULTILINE_COMMENT: '/*' .*? '*/' -> skip
                 ;

SINGLELINE_COMMENT: '//' .*? (NEW_LINE|EOF) -> skip
                  ;

fragment
NEW_LINE: [\r\n]
        ;