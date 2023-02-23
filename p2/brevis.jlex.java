import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int lineNum;
    int charNum;
  // constructor
    TokenVal(int lineNum, int charNum) {
        this.lineNum = lineNum;
        this.charNum = charNum;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int lineNum, int charNum, int intVal) {
        super(lineNum, charNum);
        this.intVal = intVal;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int lineNum, int charNum, String idVal) {
        super(lineNum, charNum);
        this.idVal = idVal;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int lineNum, int charNum, String strVal) {
        super(lineNum, charNum);
        this.strVal = strVal;
    }
}
// The following class is used to keep track of the character number at 
// which the current token starts on its line.
class CharNum {
    static int num = 1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"46:9,44,43,46:2,23,46:18,44,46,20,45,46:2,36,22,31,34,42,27,38,33,39,28,26:" +
"10,46,35,32,29,30,22,46,24:26,46,21,46:2,25,46,8,5,16,4,7,14,11,19,3,24:2,6" +
",24,9,2,17,24,12,15,10,13,1,18,24:3,40,37,41,46:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,131,
"0,1,2,3,4,5,6,7,8,9,1,10,11,1:8,12,13,14,1:11,13:4,15,16,13:3,16,13:4,3,17," +
"1:2,18,19,18,20,21,22,15,16,1,23,24,25:2,26,14,27,28,29,30,31,32,33,34,35,3" +
"6,37,38,39,16,40,19,41,42,43,44,45,46,47,48,49,44,49,18,50:2,13,51,52,53,54" +
",55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,13,71,72,73,74,75,76,77,78" +
",79,80,81")[0];

	private int yy_nxt[][] = unpackFromString(82,47,
"1,2,119,50,119,122,119,123,119:2,124,119,125,119,126,127,119,128,129,119,3," +
"4,51,-1,119:2,5,6,7,8,9,10,11,12,13,14,58,130,15,16,17,18,19,20,21,64,51,-1" +
":48,119,91,119:17,-1:4,119,98:2,-1:10,119,-1:10,49:19,23,56,49,62,49:19,24," +
"49:3,-1:29,25,-1:43,5,-1:47,26,-1:47,65,-1:47,27,-1:46,28,-1:46,29,-1:3,30," +
"-1:43,31,-1:2,32,-1:57,21,-1:3,119:19,-1:4,119,98:2,-1:10,119,-1:10,67:20,6" +
"9,67,-1,67:19,-1,67:3,-1,77:19,39,79,77,81,77:19,81,77:3,-1:43,61,-1:4,119:" +
"8,99,119:4,22,119:5,-1:4,119,98:2,-1:10,119,-1:10,84:19,92,85,84,81,84:19,4" +
"0,84:3,-1,83:19,59,86,83,62,83:19,60,83:3,-1,71:8,49:2,71:9,49,73,49,95,71:" +
"19,95,71:3,-1,119:3,35,119:15,-1:4,119,98:2,-1:10,119,-1:45,33,-1:11,62:19," +
"52,75,62:21,24,62:3,-1,119:6,36,119:12,-1:4,119,98:2,-1:10,119,-1:10,65:22," +
"-1,65:19,34,65:3,-1,119:6,37,119:12,-1:4,119,98:2,-1:10,119,-1:10,119:8,38," +
"119:10,-1:4,119,98:2,-1:10,119,-1:10,71:8,67:2,71:9,67,69,67,95,71:19,95,71" +
":3,-1,119:6,41,119:12,-1:4,119,98:2,-1:10,119,-1:10,77:19,39,79,77,81,77:19" +
",40,77:3,-1,119:9,42,119:9,-1:4,119,98:2,-1:10,119,-1:10,83:8,49:2,83:9,23," +
"56,49,96,83:19,53,83:3,-1,119:6,43,119:12,-1:4,119,98:2,-1:10,119,-1:18,62:" +
"2,-1:9,62:3,-1:25,119:8,45,119:10,-1:4,119,98:2,-1:10,119,-1:10,77:19,39,79" +
",77,81,77:19,44,77:3,-1,119:3,46,119:15,-1:4,119,98:2,-1:10,119,-1:10,71:8," +
"77:2,71:9,39,79,77,95,71:19,55,71:3,-1,119:11,47,119:7,-1:4,119,98:2,-1:10," +
"119,-1:10,119:8,48,119:10,-1:4,119,98:2,-1:10,119,-1:10,84:19,92,84:2,81,84" +
":19,44,84:3,-1,95:8,84:2,95:9,92,84:2,95:20,55,95:3,-1,71:8,83:2,71:9,54,89" +
",83,95,71:19,55,71:3,-1,87:19,94,90,87,62,87:19,60,87:3,-1,95:8,87:2,95:9,9" +
"3,87:2,95:20,55,95:3,-1,83:19,59,86,83,97,83:19,53,83:3,-1,84:8,87:2,84:9,9" +
"3,87:2,81,84:19,44,84:3,-1,119:2,57,119:16,-1:4,119,98:2,-1:10,119,-1:10,84" +
":19,92,84:2,81,84:19,81,84:3,-1,87:19,94,88,87,62,87:19,60,87:3,-1,119:9,10" +
"7,119:9,-1:4,119,98:2,-1:10,119,-1:10,119,120,119:17,-1:4,119,98:2,-1:10,11" +
"9,-1:10,119:14,63,119:4,-1:4,119,98:2,-1:10,119,-1:10,119:12,66,119:6,-1:4," +
"119,98:2,-1:10,119,-1:10,119:9,108,119:5,109,119:3,-1:4,119,98:2,-1:10,119," +
"-1:10,119:5,110,119:13,-1:4,119,98:2,-1:10,119,-1:10,119:7,68,119:11,-1:4,1" +
"19,98:2,-1:10,119,-1:10,119:2,111,119:16,-1:4,119,98:2,-1:10,119,-1:10,119:" +
"6,113,119:12,-1:4,119,98:2,-1:10,119,-1:10,119:12,115,119:6,-1:4,119,98:2,-" +
"1:10,119,-1:10,119,116,119:17,-1:4,119,98:2,-1:10,119,-1:10,119:14,70,119:4" +
",-1:4,119,98:2,-1:10,119,-1:10,119:8,72,119:10,-1:4,119,98:2,-1:10,119,-1:1" +
"0,119:5,74,119:13,-1:4,119,98:2,-1:10,119,-1:10,119:10,117,119:8,-1:4,119,9" +
"8:2,-1:10,119,-1:10,119:6,118,119:12,-1:4,119,98:2,-1:10,119,-1:10,119:11,7" +
"6,119:7,-1:4,119,98:2,-1:10,119,-1:10,119:11,78,119:7,-1:4,119,98:2,-1:10,1" +
"19,-1:10,119:6,80,119:12,-1:4,119,98:2,-1:10,119,-1:10,119:7,82,119:11,-1:4" +
",119,98:2,-1:10,119,-1:10,119:5,114,119:13,-1:4,119,98:2,-1:10,119,-1:10,11" +
"9:2,112,119:16,-1:4,119,98:2,-1:10,119,-1:10,119,100,119:17,-1:4,119,98:2,-" +
"1:10,119,-1:10,119:5,101,119:13,-1:4,119,98:2,-1:10,119,-1:10,119:11,102,11" +
"9:7,-1:4,119,98:2,-1:10,119,-1:10,119:6,103,119:12,-1:4,119,98:2,-1:10,119," +
"-1:10,119:7,104,119:11,-1:4,119,98:2,-1:10,119,-1:10,119:15,105,119:3,-1:4," +
"119,98:2,-1:10,119,-1:10,119:11,106,119:7,-1:4,119,98:2,-1:10,119,-1:10,119" +
":18,121,-1:4,119,98:2,-1:10,119,-1:10,119:19,-1:4,119,98:2,-1:10,98,-1:9");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -3:
						break;
					case 3:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -4:
						break;
					case 4:
						{ Symbol S = new Symbol(sym.NOT, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -5:
						break;
					case 5:
						{ 
            try{
                int val = Integer.parseInt(yytext());
                Symbol S = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, val));
                CharNum.num += yytext().length();
                return S;
            }catch(Exception e){
                ErrMsg.fatal(yyline+1, CharNum.num, "integer literal too large (using max value)");
                Symbol S = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, Integer.MAX_VALUE));
                CharNum.num += yytext().length();
                return S;
            }
          }
					case -6:
						break;
					case 6:
						{ Symbol S = new Symbol(sym.PLUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -7:
						break;
					case 7:
						{ Symbol S = new Symbol(sym.DIVIDE, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -8:
						break;
					case 8:
						{ Symbol S = new Symbol(sym.ASSIGN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -9:
						break;
					case 9:
						{ Symbol S = new Symbol(sym.GREATER, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -10:
						break;
					case 10:
						{ Symbol S = new Symbol(sym.LPAREN, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -11:
						break;
					case 11:
						{ Symbol S = new Symbol(sym.LESS, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -12:
						break;
					case 12:
						{ Symbol S = new Symbol(sym.MINUS, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -13:
						break;
					case 13:
						{ Symbol S = new Symbol(sym.RPAREN, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -14:
						break;
					case 14:
						{ Symbol S = new Symbol(sym.SEMICOLON, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -15:
						break;
					case 15:
						{ Symbol S = new Symbol(sym.COMMA, new TokenVal(yyline+1, CharNum.num));
          CharNum.num++;
          return S;
        }
					case -16:
						break;
					case 16:
						{ Symbol S = new Symbol(sym.DOT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -17:
						break;
					case 17:
						{ Symbol S = new Symbol(sym.LCURLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -18:
						break;
					case 18:
						{ Symbol S = new Symbol(sym.RCURLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -19:
						break;
					case 19:
						{ Symbol S = new Symbol(sym.TIMES, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -20:
						break;
					case 20:
						{ CharNum.num = 1; }
					case -21:
						break;
					case 21:
						{ CharNum.num += yytext().length(); }
					case -22:
						break;
					case 22:
						{ Symbol S = new Symbol(sym.IF, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -23:
						break;
					case 23:
						{
            String strVal = yytext().equals("\"\"") ? "" : yytext().substring(1, yytext().length() - 1);
            Symbol S = new Symbol(sym.STRINGLITERAL, new StrLitTokenVal(yyline+1, CharNum.num, strVal));
            CharNum.num += yytext().length();
            return S;
          }
					case -24:
						break;
					case 24:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal");
            CharNum.num = 1;
          }
					case -25:
						break;
					case 25:
						{ Symbol S = new Symbol(sym.NOTEQUALS, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += 2;
          return S;
        }
					case -26:
						break;
					case 26:
						{ Symbol S = new Symbol(sym.PLUSPLUS, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += 2;
          return S;
        }
					case -27:
						break;
					case 27:
						{ Symbol S = new Symbol(sym.EQUALS, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += 2;
          return S;
        }
					case -28:
						break;
					case 28:
						{ Symbol S = new Symbol(sym.GREATEREQ, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += 2;
          return S;
        }
					case -29:
						break;
					case 29:
						{ Symbol S = new Symbol(sym.LESSEQ, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return S;
          }
					case -30:
						break;
					case 30:
						{ Symbol S = new Symbol(sym.WRITE, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += 2;
          return S;
        }
					case -31:
						break;
					case 31:
						{ Symbol S = new Symbol(sym.READ, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += 2;
          return S;
        }
					case -32:
						break;
					case 32:
						{ Symbol S = new Symbol(sym.MINUSMINUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += 2;
            return S;
          }
					case -33:
						break;
					case 33:
						{ Symbol S = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += 2;
          return S;
        }
					case -34:
						break;
					case 34:
						{ CharNum.num = 1; }
					case -35:
						break;
					case 35:
						{ Symbol S = new Symbol(sym.VOID, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -36:
						break;
					case 36:
						{ Symbol S = new Symbol(sym.ELSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -37:
						break;
					case 37:
						{ Symbol S = new Symbol(sym.TRUE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -38:
						break;
					case 38:
						{ Symbol S = new Symbol(sym.SCAN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -39:
						break;
					case 39:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring string literal with bad escaped character");
            CharNum.num += yytext().length();
          }
					case -40:
						break;
					case 40:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal with bad escaped character");
            CharNum.num = 1;
          }
					case -41:
						break;
					case 41:
						{ Symbol S = new Symbol(sym.FALSE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -42:
						break;
					case 42:
						{ Symbol S = new Symbol(sym.PRINT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -43:
						break;
					case 43:
						{ Symbol S = new Symbol(sym.WHILE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -44:
						break;
					case 44:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal with bad escaped character");
            CharNum.num = 1;
          }
					case -45:
						break;
					case 45:
						{ Symbol S = new Symbol(sym.RETURN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -46:
						break;
					case 46:
						{ Symbol S = new Symbol(sym.RECORD, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -47:
						break;
					case 47:
						{ Symbol S = new Symbol(sym.INT, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += yytext().length();
          return S;
        }
					case -48:
						break;
					case 48:
						{ Symbol S = new Symbol(sym.BOOL, new TokenVal(yyline+1, CharNum.num));
          CharNum.num += yytext().length();
          return S;
        }
					case -49:
						break;
					case 50:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -50:
						break;
					case 51:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -51:
						break;
					case 52:
						{
            String strVal = yytext().equals("\"\"") ? "" : yytext().substring(1, yytext().length() - 1);
            Symbol S = new Symbol(sym.STRINGLITERAL, new StrLitTokenVal(yyline+1, CharNum.num, strVal));
            CharNum.num += yytext().length();
            return S;
          }
					case -52:
						break;
					case 53:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal");
            CharNum.num = 1;
          }
					case -53:
						break;
					case 54:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring string literal with bad escaped character");
            CharNum.num += yytext().length();
          }
					case -54:
						break;
					case 55:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal with bad escaped character");
            CharNum.num = 1;
          }
					case -55:
						break;
					case 57:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -56:
						break;
					case 58:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -57:
						break;
					case 59:
						{
            String strVal = yytext().equals("\"\"") ? "" : yytext().substring(1, yytext().length() - 1);
            Symbol S = new Symbol(sym.STRINGLITERAL, new StrLitTokenVal(yyline+1, CharNum.num, strVal));
            CharNum.num += yytext().length();
            return S;
          }
					case -58:
						break;
					case 60:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal");
            CharNum.num = 1;
          }
					case -59:
						break;
					case 61:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring unterminated string literal with bad escaped character");
            CharNum.num = 1;
          }
					case -60:
						break;
					case 63:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -61:
						break;
					case 64:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -62:
						break;
					case 66:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -63:
						break;
					case 68:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -64:
						break;
					case 70:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -65:
						break;
					case 72:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -66:
						break;
					case 74:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -67:
						break;
					case 76:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -68:
						break;
					case 78:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -69:
						break;
					case 80:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -70:
						break;
					case 82:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -71:
						break;
					case 91:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -72:
						break;
					case 92:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring string literal with bad escaped character");
            CharNum.num += yytext().length();
          }
					case -73:
						break;
					case 93:
						{
            ErrMsg.fatal(yyline+1, CharNum.num, "ignoring string literal with bad escaped character");
            CharNum.num += yytext().length();
          }
					case -74:
						break;
					case 94:
						{
            String strVal = yytext().equals("\"\"") ? "" : yytext().substring(1, yytext().length() - 1);
            Symbol S = new Symbol(sym.STRINGLITERAL, new StrLitTokenVal(yyline+1, CharNum.num, strVal));
            CharNum.num += yytext().length();
            return S;
          }
					case -75:
						break;
					case 98:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -76:
						break;
					case 99:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -77:
						break;
					case 100:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -78:
						break;
					case 101:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -79:
						break;
					case 102:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -80:
						break;
					case 103:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -81:
						break;
					case 104:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -82:
						break;
					case 105:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -83:
						break;
					case 106:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -84:
						break;
					case 107:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -85:
						break;
					case 108:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -86:
						break;
					case 109:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -87:
						break;
					case 110:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -88:
						break;
					case 111:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -89:
						break;
					case 112:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -90:
						break;
					case 113:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -91:
						break;
					case 114:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -92:
						break;
					case 115:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -93:
						break;
					case 116:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -94:
						break;
					case 117:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -95:
						break;
					case 118:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -96:
						break;
					case 119:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -97:
						break;
					case 120:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -98:
						break;
					case 121:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -99:
						break;
					case 122:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -100:
						break;
					case 123:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -101:
						break;
					case 124:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -102:
						break;
					case 125:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -103:
						break;
					case 126:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -104:
						break;
					case 127:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -105:
						break;
					case 128:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -106:
						break;
					case 129:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -107:
						break;
					case 130:
						{ Symbol S = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -108:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
