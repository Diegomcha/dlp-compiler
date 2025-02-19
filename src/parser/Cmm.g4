grammar Cmm;

// program

program: definition* main_fn EOF
       ;

main_fn: 'void' 'main' '(' ')' '{' fn_body '}'
       ;

// definitions

definition: var_def
          | fn_definition
          ;

var_def: type ID (',' ID)* ';'
       ;

fn_definition: (builtin_type|'void') ID '(' fn_args ')' '{' fn_body '}'
             ;

fn_args: builtin_type ID (',' builtin_type ID)*
       | // epsilon
       ;

fn_body: var_def* statement*
       ;

// types

type: builtin_type
    | type '[' INT_CONSTANT ']'
    | 'struct' '{' struct_fields '}'
    ;

builtin_type: 'int'
            | 'double'
            | 'char'
            ;

struct_fields: var_def*
             ;

// statements

statement: expression '=' expression ';'
         | 'read' params ';'
         | 'write' params ';'
         | 'return' expression ';'
         | fn_invocation ';'
         | 'while' '(' expression ')' block
         | 'if' '(' expression ')' block else_block
         ;

block: statement
     | '{' statement* '}'
     ;

else_block: 'else' block
          | // epsilon
          ;

params: expression (',' expression)*
      ;

// expressions

expression: INT_CONSTANT
          | REAL_CONSTANT
          | CHAR_CONSTANT
          | ID
          | fn_invocation
          // operands
          | '(' expression ')'
          | expression '[' expression ']'
          | expression '.' ID
          | '(' builtin_type ')' expression
          | '-' expression
          | '!' expression
          | expression ('*'|'/'|'%') expression
          | expression ('+'|'-') expression
          | expression ('>'|'>='|'<'|'<='|'!='|'==') expression
          | expression ('&&'|'||') expression
          ;

fn_invocation: ID '(' fn_params ')'
             ;

fn_params: params
         | // epsilon
         ;

// LEXICAL

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
SPECIAL_CHAR: '\'\\n\''
            | '\'\\t\''
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