// --== CS536 File Header Information ==--
// Name: Jerry Xu
// Email: mxu269@wisc.edu
// Lecturer: Beck Hasti
import java.io.StringWriter;
import java.util.*;
public class SymTab {

//    private ArrayList<HashMap<String, Sym>> list;
    private LinkedList<HashMap<String, Sym>> list;
    public SymTab(){
        HashMap<String, Sym> newHM = new HashMap<>();
        list = new LinkedList<>();
        list.add(newHM);
    }

    void addDecl(String name, Sym sym) throws SymDuplicationException, SymTabEmptyException{
        if (list.isEmpty()){
            throw new SymTabEmptyException();
        }else if (name == null || sym == null){
            throw new IllegalArgumentException();
        }

        HashMap<String, Sym> hm = list.getFirst();
        if (hm.containsKey(name)){
            throw new SymDuplicationException();
        }

        hm.put(name, sym);

    }

    void addScope(){
        HashMap<String, Sym> newHM = new HashMap<>();
        list.addFirst(newHM);

    }

    Sym lookupLocal(String name) throws SymTabEmptyException{
        if (list.isEmpty()){
            throw new SymTabEmptyException();
        }
        return list.getFirst().get(name);
    }

    Sym lookupGlobal(String name) throws SymTabEmptyException{
        if (list.isEmpty()){
            throw new SymTabEmptyException();
        }

        for(HashMap<String, Sym> hm : list){
            Sym candidate = hm.get(name);
            if (candidate != null) {
                return candidate;
            }
        }

        return null;
    }

    void removeScope() throws SymTabEmptyException{
        if (list.isEmpty()){
            throw new SymTabEmptyException();
        }
        list.removeFirst();

    }

    void print(){
        StringWriter sw = new StringWriter();
        sw.write("\n--- Symbol Table ---\n");
        for(HashMap<String, Sym> hm: list){
            sw.write(hm + "\n");
        }
        sw.flush();
        System.out.println(sw.toString());

    }
}