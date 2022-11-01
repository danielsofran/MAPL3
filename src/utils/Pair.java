package utils;

public class Pair<TF, TS>{
    public TF first;
    public TS second;

    /**
     * Constructorul cu parametri
     * @param first - primul element din pereche
     * @param second - al doilea element din pereche
     */
    public Pair(TF first, TS second){
        this.first = first;
        this.second = second;
    }
}