public class SymTab {
    public SymTab(){

    }

    void addDecl(String name, Sym sym) throws SymDuplicationException, SymTabEmptyException{

    }

    void addScope(){

    }

    Sym lookupLocal(String name) throws SymTabEmptyException{
        return new Sym("invalid");
    }

    Sym lookupGlobal(String name) throws SymTabEmptyException{
        return new Sym("invalid");
    }

    void removeScope() throws SymTabEmptyException{

    }

    void print(){

    }
}