/**
 * Test Sym and SymTab
 *
 * This is a class whose purpose is to test the Sym and SymTab class and the following operations:
 *
 *      class Sym:
 *          - Sym(String type) //constructor
 *          - String getType() //method
 *          - String toString() //method
 *
 *      class SymTab:
 *          - SymTab() constructor
 *          - void addDecl(String name, Sym sym) throws SymDuplicationException, SymTabEmptyException //method
 *          - void addScope() //method
 *          - Sym lookupLocal(String name) throws SymTabEmptyException //method
 *          - Sym lookupGlobal(String name) throws SymTabEmptyException //method
 *          - void removeScope() throws SymTabEmptyException //method
 *          - void print() //method
 *
 * It produces output ONLY if a test fails.
 */
public class P1 {
    public static void main(String[] args) {
        //Sym class TESTS
        Sym symbol = new Sym("INT");

        //TEST Sym.getType()
        if (symbol.getType() != "INT"){
            System.out.println("Failed getType() test. Expected: " + "INT, Actual: " + symbol.getType());
        }

        //TEST Sym.toString()
        if (symbol.toString() != "INT"){
            System.out.println("Failed toString() test. Expected: " + "INT, Actual: " + symbol.toString());
        }

        //SymTab class TESTS
        SymTab symbolTable = new SymTab();

        //TEST SymTab.addDecl() throws SymTabEmptyException when list is empty
        try{
            symbolTable.addDecl("myInt", symbol);
            System.out.println("Failed SymTab.addDecl() SymTabEmptyException test. Expected: SymTabEmptyException. " +
                    "Actual: Success");
        }catch (SymTabEmptyException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.addDecl() SymTabEmptyException test. Expected: SymTabEmptyException. " +
                    "Actual: " + e);
        }

        //reset symbolTable
        symbolTable = new SymTab();

        //Test SymTab.addScope() and SymTab.removeScope() chain
        try{
            symbolTable.addScope();
            symbolTable.removeScope();
        }catch (Exception e){
            System.out.println("Failed SymTab.addScope() and SymTab.removeScope() chain test. Error: "+ e);
        }

        try{
            symbolTable.addDecl("myInt", symbol);
            System.out.println("Failed SymTab.addScope() and SymTab.removeScope() chain test. Expected: SymTabEmptyException. " +
                    "Actual: success" );
        }catch (SymTabEmptyException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.addScope() and SymTab.removeScope() chain test. Expected: SymTabEmptyException. " +
                    "Actual: " + e);
        }

        //reset symbolTable
        symbolTable = new SymTab();

        //TEST SymTab.addDecl() throws SymDuplicationException when name is duplicate
        try{
            symbolTable.addDecl("myInt", symbol);
            symbolTable.addDecl("myInt", symbol);
            System.out.println("Failed SymTab.addDecl() SymDuplicationException test. Expected: SymDuplicationException. " +
                    "Actual: success");
        }catch (SymDuplicationException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.addDecl() SymDuplicationException test. Expected: SymDuplicationException. " +
                    "Actual: " + e);
        }

        //reset symbolTable
        symbolTable = new SymTab();

        //TEST SymTab.addDecl() throws IllegalArgumentException when name or sym is null
        try{
            symbolTable.addDecl( null, symbol);
            symbolTable.addDecl("myInt", null);
            System.out.println("Failed SymTab.addDecl() IllegalArgumentException test. Expected: IllegalArgumentException. " +
                    "Actual: success");
        }catch (IllegalArgumentException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.addDecl() IllegalArgumentException test. Expected: IllegalArgumentException. " +
                    "Actual: " + e);
        }

        try{
            symbolTable.addDecl("myInt", null);
            System.out.println("Failed SymTab.addDecl() IllegalArgumentException test. Expected: IllegalArgumentException. " +
                    "Actual: success");
        }catch (IllegalArgumentException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.addDecl() IllegalArgumentException test. Expected: IllegalArgumentException. " +
                    "Actual: " + e);
        }

        try{
            symbolTable.addDecl( null, null);
            System.out.println("Failed SymTab.addDecl() IllegalArgumentException test. Expected: IllegalArgumentException. " +
                    "Actual: success");
        }catch (IllegalArgumentException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.addDecl() IllegalArgumentException test. Expected: IllegalArgumentException. " +
                    "Actual: " + e);
        }

        //reset symbolTable
        symbolTable = new SymTab();

        //TEST SymTab.lookupLocal() throws SymTabEmptyException when list is empty
        try{
            symbolTable.lookupLocal("myInt");
            System.out.println("Failed SymTab.lookupLocal() SymTabEmptyException test. Expected: SymTabEmptyException. " +
                    "Actual: Success");
        }catch (SymTabEmptyException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.lookupLocal() SymTabEmptyException test. Expected: SymTabEmptyException. " +
                    "Actual: " + e);
        }

        //reset symbolTable
        symbolTable = new SymTab();

        //TEST addDecl()
        try{
            symbolTable.addScope();
            symbolTable.addDecl("myInt", symbol);
        }catch (Exception e){
            System.out.println("Failed addDecl() test. Expected: success, Actual: " + e);
        }

        //TEST lookupLocal()
        try{
            Sym result = symbolTable.lookupLocal("myInt");
            if (!result.equals(symbol)){
                System.out.println("Failed lookupLocal() test. Expected: " +symbol+ ", Actual: " + result);
            }
        }catch (Exception e){
            System.out.println("Failed lookupLocal() test. Expected: success, Actual: " + e);
        }

        //TEST lookupLocal() returns null when list[0] doesn't contain name
        try{
            Sym result = symbolTable.lookupLocal("notMyInt");
            if (result != null){
                System.out.println("Failed lookupLocal() test. Expected: null, " +  "Actual: " + result);
            }
            symbolTable.addScope();
            symbolTable.addDecl("myInt2", symbol);
            result = symbolTable.lookupLocal("myInt2");
            if (result != null){
                System.out.println("Failed lookupLocal() test. Expected: null, " +  "Actual: " + result);
            }
        }catch (Exception e){
            System.out.println("Failed lookupLocal() test. Expected: null, Actual: " + e);
        }

        //reset symbolTable
        symbolTable = new SymTab();

        //TEST SymTab.lookupGlobal() throws SymTabEmptyException when list is empty
        try{
            symbolTable.lookupGlobal("myInt");
            System.out.println("Failed SymTab.lookupGlobal() SymTabEmptyException test. Expected: SymTabEmptyException. " +
                    "Actual: Success");
        }catch (SymTabEmptyException e){

        }catch (Exception e){
            System.out.println("Failed SymTab.lookupGlobal() SymTabEmptyException test. Expected: SymTabEmptyException. " +
                    "Actual: " + e);
        }


    }
}

