grammar Cmm;

@header {
    import ast.*;
    import ast.definition.*;
    import ast.expression.*;
    import ast.expression.binary.*;
    import ast.expression.literal.*;
    import ast.expression.unary.*;
    import ast.node.*;
    import ast.statement.*;
    import ast.statement.conditional.*;
    import ast.statement.unary.*;
    import ast.type.*;
    import ast.type.builtin.*;
    import ast.type.struct.*;
}

// program

program: definition* main_fn EOF
       ;

main_fn: 'void' 'main' '(' ')' '{' fn_body '}'
       ;

// definitions

definition: var_def
          | fn_definition
          ;

var_def returns [List<VariableDefinition> ast = new ArrayList<>()]:
         t=type i1=ID           { $ast.add(new VariableDefinition($i1.getLine(), $i1.getCharPositionInLine() + 1, $i1.text, $t.ast));}
         (',' in=ID { $ast.add(new VariableDefinition($in.getLine(), $in.getCharPositionInLine() + 1, $in.text, $t.ast)); })* ';'
       ;

fn_definition returns [FunctionDefinition ast]:  // TODO: TYPE HERE IS FUNCTION TYPE!!!!
               t=fn_type n=ID '(' a=fn_args ')' '{' b=fn_body '}'   { $ast = new FunctionDefinition($n.getLine(), $n.getCharPositionInLine() + 1, $n.text, , ); }
             ;

fn_type returns [Type ast]:
         bt=builtin_type    { $ast = $bt.ast; }
       | 'void'             { $ast = new VoidType(); }
       ;

fn_args: builtin_type ID (',' builtin_type ID)*
       | // epsilon
       ;

fn_body: var_def* statement*
       ;

// types


type returns [Type ast]:
      bt=builtin_type                   { $ast = $bt.ast; }
    | t=type '[' ic=INT_CONSTANT ']'    { $ast = new Array($t.ast, LexerHelper.lexemeToInt($ic.text)); }
    | 'struct' '{' sf=struct_fields '}' { $ast = new Struct($sf.ast); }
    ;

builtin_type returns [Type ast]:
              'int'     { $ast = new IntType(); }
            | 'double'  { $ast = new RealType(); }
            | 'char'    { $ast = new CharType(); }
            ;

struct_fields returns [List<StructField> ast = new ArrayList<>()]:
// TODO: complete with var_definition
               (v=var_def {  } )*
             ;

// statements

statement returns [List<Statement> ast = new ArrayList<>()]:
           e1=expression '=' e2=expression ';'                  { $ast.add(new Assignment($e1.ast.getLine(), $e1.ast.getCol(), $e1.ast, $e2.ast)); }
         | 'read' es=exprs ';'                                  { $ast.addAll($es.ast.stream().map(e -> new Read(e.getLine(), e.getCol(), e)).toList()); }
         | 'write' es=exprs ';'                                 { $ast.addAll($es.ast.stream().map(e -> new Write(e.getLine(), e.getCol(), e)).toList()); }
         | r='return' e=expression ';'                          { $ast.add(new Return($r.getLine(), $r.getCharPositionInLine() + 1, $e.ast)); }
         | f=fn_invocation ';'                                  { $ast.add($f.ast); }
         | w='while' '(' e=expression ')' b=block               { $ast.add(new While($w.getLine(), $w.getCharPositionInLine() + 1, $e.ast, $b.ast)); }
         | i='if' '(' e=expression ')' b=block eb=else_block    { $ast.add(new Conditional($i.getLine(), $i.getCharPositionInLine() + 1, $e.ast, $b.ast, $eb.ast)); }
         ;

block returns [List<Statement> ast = new ArrayList<>()]:
       s=statement            { $ast.addAll($s.ast); }
     | '{'
            (s=statement { $ast.addAll($s.ast); })*
       '}'
     ;

else_block returns [List<Statement> ast = new ArrayList<>()]:
            'else' b=block  { $ast.addAll($b.ast); }
          | // epsilon
          ;

exprs returns [List<Expression> ast = new ArrayList<>()]:
       e1=expression { $ast.add($e1.ast); } (',' en=expression { $ast.add($en.ast); } )*
     ;

// expressions

expression returns [Expression ast]:
            ic=INT_CONSTANT     { $ast = new IntLiteral($ic.getLine(), $ic.getCharPositionInLine() + 1, LexerHelper.lexemeToInt($ic.text)); }
          | rc=REAL_CONSTANT    { $ast = new RealLiteral($rc.getLine(), $rc.getCharPositionInLine() + 1, LexerHelper.lexemeToReal($rc.text)); }
          | cc=CHAR_CONSTANT    { $ast = new CharLiteral($cc.getLine(), $cc.getCharPositionInLine() + 1, LexerHelper.lexemeToChar($cc.text)); }
          | v=variable          { $ast = $v.ast; }
          | f=fn_invocation     { $ast = $f.ast; }
          // operands
          | '(' expression ')'                                              { $ast = $expression.ast; }
          | e1=expression '[' e2=expression ']'                             { $ast = new Indexing($e1.ast.getLine(), $e1.ast.getCol(), $e1.ast, $e2.ast); }
          | e=expression '.' ID                                             { $ast = new FieldAccess($e.ast.getLine(), $e.ast.getCol(), $e.ast, $ID.text); }
          | p='(' t=builtin_type ')' e=expression                           { $ast = new Cast($p.getLine(), $p.getCharPositionInLine() + 1, $e.ast, $t.ast); }
          | m='-' e=expression                                              { $ast = new UnaryMinus($m.getLine(), $m.getCharPositionInLine() + 1, $e.ast); }
          | n='!' e=expression                                              { $ast = new Negation($n.getLine(), $n.getCharPositionInLine() + 1, $e.ast); }
          | e1=expression op=('*'|'/'|'%') e2=expression                    { $ast = new ArithmeticExpression($e1.ast.getLine(), $e1.ast.getCol(), $op.text, $e1.ast, $e2.ast); }
          | e1=expression op=('+'|'-') e2=expression                        { $ast = new ArithmeticExpression($e1.ast.getLine(), $e1.ast.getCol(), $op.text, $e1.ast, $e2.ast); }
          | e1=expression op=('>'|'>='|'<'|'<='|'!='|'==') e2=expression    { $ast = new ComparationExpression($e1.ast.getLine(), $e1.ast.getCol(), $op.text, $e1.ast, $e2.ast); }
          | e1=expression op=('&&'|'||') e2=expression                      { $ast = new LogicalExpression($e1.ast.getLine(), $e1.ast.getCol(), $op.text, $e1.ast, $e2.ast); }
          ;

fn_invocation returns [FuncInvocation ast]:
               v=variable '(' p=fn_params ')'   { $ast = new FuncInvocation($v.ast.getLine(), $v.ast.getCol(), $v.ast, $p.ast); }
             ;

variable returns [Variable ast]:
          ID    { $ast = new Variable($ID.getLine(), $ID.getCharPositionInLine() + 1, $ID.text); }
        ;

fn_params returns [List<Expression> ast = new ArrayList<>()]:
           exprs    { $ast.addAll($exprs.ast); }
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