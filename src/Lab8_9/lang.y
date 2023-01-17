%{
#include <stdio.h>
#include <stdlib.h>

#define YYDEBUG 1
%}

%token regular
%token read_n
%token check
%token entonces
%token checkif
%token not
%token loop
%token var
%token rango_a_b
%token input
%token show
%token end
%token y
%token o
%token matriz
%token haz
%token mientras
%token caracter
%token constante
%token programa
%token empieza
%token acaba
%token de
%token BOOLEAN
%token CHAR
%token INTEGER
%token REAL
%token IDENTIFIER
%token CONSTANT
%token DOS_PUNTOS
%token PUNTO_Y_COMA
%token COMA
%token PUNTO
%token MAS
%token MENOS
%token POR
%token ENTRE
%token DIVISION
%token MODULO
%token LEFT_PARENTESIS
%token RIGHT_PARENTESIS
%token LEFT_CORCHETE
%token RIGHT_CORCHETE
%token LEFT_LLAVE
%token RIGHT_LLAVE
%token INTERROGANTE
%token LESS_THAN
%token LESS_OR_EQUAL_THAN
%token EQUAL
%token NOT_EQUAL
%token GREATER_OR_EQUAL_THAN
%token GREATER_THAN
%token ASSIGNMENT
%token AND_OPERATOR
%token OR_OPERATOR

%start program

%%

program : var decllist PUNTO_Y_COMA cmpdstmt PUNTO ;
decllist : declaration PUNTO_Y_COMA decllist | declaration;
declaration : IDENTIFIER DOS_PUNTOS type ;
type : type1 | arraydecl ;
type1 : BOOLEAN | CHAR | INTEGER | REAL ;
arraydecl : matriz LEFT_CORCHETE CONSTANT RIGHT_CORCHETE de type1 ;
cmpdstmt : empieza stmtlist acaba ;
stmtlist : stmt | stmt PUNTO_Y_COMA stmtlist ;
stmt : simplstmt | structstmt ;
simplstmt : assignstmt | iostmt ;
assignstmt : IDENTIFIER ASSIGNMENT expression ;
expression : expression MAS term | term ;
term : term POR factor | factor ;
factor : LEFT_PARENTESIS expression RIGHT_PARENTESIS | IDENTIFIER ;
iostmt : input | show LEFT_PARENTESIS IDENTIFIER RIGHT_PARENTESIS ;
structstmt : cmpdstmt | ifstmt | whilestmt ;
ifstmt : check condition entonces stmt LEFT_CORCHETE not stmt RIGHT_CORCHETE ;
whilestmt : mientras condition haz stmt ;
condition : expression RELATION expression ;
RELATION : LESS_THAN | LESS_OR_EQUAL_THAN | EQUAL | NOT_EQUAL | GREATER_OR_EQUAL_THAN | GREATER_THAN ;

%%

yyerror(char *s)
{
  printf("%s\n", s);
}

extern FILE *yyin;

