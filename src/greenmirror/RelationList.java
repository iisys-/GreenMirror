package greenmirror;

import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A custom <tt>LinkedList&gt;Relation&lt;</tt> class which includes some filters.
 * 
 * @author Karim El Assal
 */
public class RelationList extends LinkedList<Relation> {
    
    
    // -- Queries ----------------------------------------------------------------------------
    
    /**
     * @param name
     * @return <tt>Relation</tt>s with the given <tt>name</tt>.
     */
    //@ requires name != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withName(String name) {
        return withFilter(relation -> name.equals(relation.getName()));
    }

    /**
     * @param rigid
     * @return <tt>Relation</tt>s with the given <tt>rigid</tt>ity.
     */
    //@ requires rigid != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withIsRirid(boolean rigid) {
        return withFilter(relation -> relation.isRigid() == rigid);
    }

    /**
     * @param placement
     * @return <tt>Relation</tt>s with the given <tt>placement</tt>.
     */
    //@ requires placement != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withPlacement(Placement placement) {
        return withFilter(relation -> relation.getPlacement() == placement);
    }

    /**
     * @return <tt>Relation</tt>s with no placement.
     */
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withNoPlacement() {
        return withFilter(relation -> relation.getPlacement() == Placement.NONE);
    }

    /**
     * @param nodes
     * @return <tt>Relation</tt>s that are connected to one of the nodes of <tt>nodes</tt>.
     */
    //@ requires nodes != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withNodes(NodeList nodes) {
        return withFilter(relation -> 
                    nodes.contains(relation.getNodeA()) 
                 || nodes.contains(relation.getNodeB()));
    }

    /**
     * @param node
     * @return <tt>Relation</tt>s that are connected to <tt>node</tt>.
     */
    //@ requires node != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withNode(Node node) {
        return withFilter(relation -> 
                    node.equals(relation.getNodeA()) 
                 || node.equals(relation.getNodeB()));
    }

    /**
     * @param nodes
     * @return <tt>Relation</tt>s in which node A is one of the nodes of <tt>nodes</tt>.
     */
    //@ requires nodes != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withNodeA(NodeList nodes) {
        return withFilter(relation -> 
                    nodes.contains(relation.getNodeA()));
    }

    /**
     * @param node
     * @return <tt>Relation</tt>s in which node A equals <tt>node</tt>.
     */
    //@ requires node != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withNodeA(Node node) {
        return withFilter(relation -> 
                    node.equals(relation.getNodeA()));
    }

    /**
     * @param nodes
     * @return <tt>Relation</tt>s in which node B is one of the nodes of <tt>nodes</tt>.
     */
    //@ requires nodes != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withNodeB(NodeList nodes) {
        return withFilter(relation -> 
                    nodes.contains(relation.getNodeB()));
    }

    /**
     * @param node
     * @return <tt>Relation</tt>s in which node B equals <tt>node</tt>.
     */
    //@ requires node != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ public RelationList withNodeB(Node node) {
        return withFilter(relation -> 
                    node.equals(relation.getNodeB()));
    }
    
    /**
     * @param predicate The filter.
     * @return          A new <tt>RelationList</tt> with a filter applied.
     */
    //@ requires predicate != null;
    //@ ensures \result != null;
    //@ ensures \result.size() <= this.size();
    /*@ pure */ private RelationList withFilter(Predicate<Relation> predicate) {
        return this.stream().filter(predicate)
                .collect(Collectors.toCollection(RelationList::new));
    }
    

    // -- Commands ---------------------------------------------------------------------------

    /**
     * Remove all <tt>Relation</tt>s in this <tt>RelationList</tt>.
     */
    //@ ensures this.size() == 0;
    public void removeAll() {
        this.forEach(relation -> {
            relation.remove();
            this.remove(relation);
        });
    }

}