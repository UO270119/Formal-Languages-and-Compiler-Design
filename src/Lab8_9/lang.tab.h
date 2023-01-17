
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     regular = 258,
     read_n = 259,
     check = 260,
     entonces = 261,
     checkif = 262,
     not = 263,
     loop = 264,
     var = 265,
     rango_a_b = 266,
     input = 267,
     show = 268,
     end = 269,
     y = 270,
     o = 271,
     matriz = 272,
     haz = 273,
     mientras = 274,
     caracter = 275,
     constante = 276,
     programa = 277,
     empieza = 278,
     acaba = 279,
     de = 280,
     BOOLEAN = 281,
     CHAR = 282,
     INTEGER = 283,
     REAL = 284,
     IDENTIFIER = 285,
     CONSTANT = 286,
     DOS_PUNTOS = 287,
     PUNTO_Y_COMA = 288,
     COMA = 289,
     PUNTO = 290,
     MAS = 291,
     MENOS = 292,
     POR = 293,
     ENTRE = 294,
     DIVISION = 295,
     MODULO = 296,
     LEFT_PARENTESIS = 297,
     RIGHT_PARENTESIS = 298,
     LEFT_CORCHETE = 299,
     RIGHT_CORCHETE = 300,
     LEFT_LLAVE = 301,
     RIGHT_LLAVE = 302,
     INTERROGANTE = 303,
     LESS_THAN = 304,
     LESS_OR_EQUAL_THAN = 305,
     EQUAL = 306,
     NOT_EQUAL = 307,
     GREATER_OR_EQUAL_THAN = 308,
     GREATER_THAN = 309,
     ASSIGNMENT = 310,
     AND_OPERATOR = 311,
     OR_OPERATOR = 312
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


