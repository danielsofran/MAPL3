package graf;

import utils.Pereche;
import exceptii.DuplicatedElementException;
import exceptii.NotExistentException;
import utils.Pair;

import java.util.*;

public class GrafListaAdiacenta<Nod, Muchie extends Pereche<Nod>> implements Graf<Nod, Muchie>{
    private Map<Nod, Set<Muchie>> G;
    private Map<Nod, Boolean> deleted;

    public GrafListaAdiacenta(){
        G = new HashMap<>();
        deleted = new HashMap<>();
    }

    public Boolean hasNod(Nod nod){
        return G.containsKey(nod) && (!deleted.containsKey(nod) || !deleted.get(nod));
    }

    @Override
    public void addNod(Nod nod) throws DuplicatedElementException {
        if(!G.containsKey(nod)){
            G.put(nod, new HashSet<>());
            deleted.put(nod, false);
        }
        else if(deleted.containsKey(nod) && deleted.get(nod))
            deleted.put(nod, false);
        //else throw new DuplicatedElementException("Nod duplicat!");
    }

    @Override
    public void addMuchie(Muchie muchie) throws DuplicatedElementException {
        Nod nod1 = muchie.getFirst();
        Nod nod2 = muchie.getSecond();
        if(!hasNod(nod1)) addNod(nod1);
        if(!hasNod(nod2)) addNod(nod2);
        G.get(nod1).add(muchie);
        G.get(nod2).add(muchie);
    }

    @Override
    public void removeNod(Nod nod) throws NotExistentException {
        if(hasNod(nod))
        {
            G.remove(nod);
            deleted.put(nod, true);
        }
        else throw new NotExistentException("Nod inexistent!");
    }

    @Override
    public void removeMuchie(Muchie muchie) throws NotExistentException {
        Nod nod1 = muchie.getFirst();
        Nod nod2 = muchie.getSecond();
        if(!hasNod(nod1) || !hasNod(nod2))
            throw new NotExistentException("Muchia "+muchie+" este inexistenta!");
        G.get(nod1).removeIf(m -> m.equals(muchie));
        G.get(nod2).removeIf(m -> m.equals(muchie));
    }

    @Override
    public void updateNod(Nod nod, Nod newNod) throws NotExistentException {
        Set<Muchie> muchii = G.get(nod);
        if(muchii == null)
            throw new NotExistentException("Nodul "+nod+" este inexistent!");
        G.remove(nod);
        muchii.forEach(muchie -> {
            if(muchie.getFirst().equals(nod))
                muchie.setFirst(newNod);
            else muchie.setSecond(newNod);
        });
        G.put(newNod, muchii);
    }

    @Override
    public void updateMuchie(Muchie muchie, Muchie newMuchie) throws NotExistentException {
        Nod nod1 = muchie.getFirst();
        Nod nod2 = muchie.getSecond();
        if(!hasNod(nod1) || !hasNod(nod2))
            throw new NotExistentException("Muchia "+muchie+" este inexistenta!");
        if(!hasNod(newMuchie.getFirst())) addNod(newMuchie.getFirst());
        if(!hasNod(newMuchie.getSecond())) addNod(newMuchie.getSecond());
        G.get(nod1).removeIf(m -> m.equals(muchie));
        G.get(nod2).removeIf(m -> m.equals(muchie));
        G.get(newMuchie.getFirst()).add(newMuchie);
        G.get(newMuchie.getSecond()).add(newMuchie);
    }

    public Set<Nod> getVecini(Nod nod){
        Set<Nod> vecini = new HashSet<>();
        G.get(nod).forEach(muchie -> {
            Nod nod1;
            if(muchie.getFirst().equals(nod))
                nod1 = muchie.getSecond();
            else nod1 = muchie.getFirst();
            if(hasNod(nod1))
                vecini.add(nod1);
        });
        return vecini;
    }

    public Set<Muchie> getMuchiiAdiacente(Nod nod){
        Set<Muchie> rez = new HashSet<>();
        G.get(nod).forEach(muchie -> {
            Nod nod1;
            if(muchie.getFirst().equals(nod))
                nod1 = muchie.getSecond();
            else nod1 = muchie.getFirst();
            if(hasNod(nod1))
                rez.add(muchie);
        });
        return rez;
    }

    @Override
    public Set<Nod> getNoduri(){
        return G.keySet();
    }

    private void BFS(Nod source, Map<Nod, Boolean> visited, Map<Nod, Nod> parent, Map<Nod, Integer> distance){
        Queue<Nod> Q = new LinkedList<>();
        Q.add(source);
        visited.put(source, true);
        parent.put(source, null);
        distance.put(source, 0);
        while(!Q.isEmpty()){
            Nod nod = Q.poll();
            for(Nod vecin : getVecini(nod)){
                if(!visited.get(vecin)){
                    visited.put(vecin, true);
                    parent.put(vecin, nod);
                    distance.put(vecin, distance.get(nod) + 1);
                    Q.add(vecin);
                }
            }
        }
    }


    private Integer dmax = 0;
    public void BackDrum(Nod source, Map<Nod, Boolean> visited, Map<Nod, Integer> distance){
        for(Nod nod : getVecini(source)){
            if(!visited.get(nod)){
                visited.put(nod, true);
                int d = distance.get(source) + 1;
                distance.put(nod, d);
                if(d > dmax) dmax = d;
                BackDrum(nod, visited, distance);
                visited.put(nod, false);
                distance.put(nod, d-1);
            }
        }
    }

    public Pair<GrafListaAdiacenta<Nod, Muchie>, Integer> componentWithLongestPath(){
        int dmaxall = -1;
        GrafListaAdiacenta<Nod, Muchie> compmax = new GrafListaAdiacenta<>();
        for(GrafListaAdiacenta<Nod, Muchie> comp : componenteConexe()){
            Set<Nod> noduri = comp.getNoduri();
            // get first element of noduri
            Iterator<Nod> it = noduri.iterator();
            Nod source = it.next();
            Map<Nod, Boolean> visited = new HashMap<>();
            Map<Nod, Integer> distance = new HashMap<>();
            for(Nod nod : noduri){
                visited.put(nod, false);
                distance.put(nod, 0);
            }
            visited.put(source, true);
            BackDrum(source, visited, distance);
            if(dmax > dmaxall) {
                dmaxall = dmax;
                compmax = comp;
            }
        }
        return new Pair<>(compmax, dmaxall);
    }

    public Pair<GrafListaAdiacenta<Nod, Muchie>, Integer> componentWithLongestPath2(){
        int dmax = -1;
        Nod source = null;
        Map<Nod, Nod> parent = new HashMap<>();
        Map<Nod, Boolean> visited = new HashMap<>();
        Map<Nod, Integer> distance = new HashMap<>();
        for(Nod nod : getNoduri()){
            visited.put(nod, false);
            parent.put(nod, null);
            distance.put(nod, 0);
        }
        for(Nod nod : G.keySet()){
            BFS(nod, visited, parent, distance);
            int d = distance.values().stream().max(Integer::compareTo).get();
            if(d > dmax){
                dmax = d;
                source = nod;
            }
            for(Nod nod2 : G.keySet()){
                visited.put(nod2, false);
                parent.put(nod2, null);
                distance.put(nod2, 0);
            }
        }
        return new Pair<>(componentaConexa(source), dmax);
    }

    public GrafListaAdiacenta<Nod, Muchie> componentaConexa(Nod source){
        GrafListaAdiacenta<Nod, Muchie> graf = new GrafListaAdiacenta<>();
        Queue<Nod> queue = new LinkedList<>();
        Map<Nod, Boolean> visited = new HashMap<>();
        for(Nod nod : G.keySet()) {
            visited.put(nod, false);
        }
        queue.add(source);
        graf.addNod(source);
        while (!queue.isEmpty()){
            Nod t = queue.poll();
            if(!visited.get(t)){
                visited.put(t, true);
                for(Muchie m : getMuchiiAdiacente(t)){
                    Nod nod = m.getFirst().equals(t) ? m.getSecond() : m.getFirst();
                    if(!visited.get(nod)){
                        queue.add(nod);
                        graf.addMuchie(m);
                    }
                }
            }
        }
        return graf;
    }

    public List<GrafListaAdiacenta<Nod, Muchie>> componenteConexe(){
        List<GrafListaAdiacenta<Nod, Muchie>> grafuri = new LinkedList<>();
        Map<Nod, Boolean> visited = new HashMap<>();
        for(Nod nod : G.keySet()) {
            visited.put(nod, false);
        }
        for(Nod nod : G.keySet()){
            if(!visited.get(nod)){
                GrafListaAdiacenta<Nod, Muchie> graf = componentaConexa(nod);
                grafuri.add(graf);
                graf.getNoduri().forEach(n -> visited.put(n, true));
            }
        }
        return grafuri;
    }
}
