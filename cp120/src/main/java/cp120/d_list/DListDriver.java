package cp120.d_list;

import cp120.d_list.DList;
import cp120.d_list.DNode;

public class DListDriver
{
    public static void main(String[] args)
    {
        new DListDriver().execute();
    }

    private void execute()
    {
        startFromHead();
        System.out.println();
        startFromTail();
    }
    
    private void startFromHead()
    {
        final String    label   = "Start from head";
        final int       limit   = 10;
        DList           list    = new DList();
        String[]    start   = { "2", "1", "0" };
        for ( String str : start ) {
            list.addHead( new DNode(str));
        }
        System.out.println(list.getNext());
        printList( label, list );
        
        for ( int inx = start.length ; inx < limit ; ++inx ) {
            list.addTail(new DNode("" + inx));
        }
            
        printList( label, list );
        
        DNode node = list.getHead();
        
        for ( char inx ='A' ; node != list; ++inx )
        {
            DNode   temp    = node.getNext();
            node.addAfter( new DNode( inx ) );
            node = temp;
        }
        printList( label, list );
        
        node = list.getTail();
        for ( char inx = (char)('a' + limit - 1) ; node != list ; --inx )
        {
            DNode   temp    = node.getPrevious();
            if ( temp == list )
                throw new Error( "List out of order" );
            node.addBefore( new DNode( inx ) );
            node = temp.getPrevious();
        }
        printList( label, list );
    }
    
    private void startFromTail()
    {
        final String    label   = "Start from tail";
        final int       limit   = 10;
        DList   list    = new DList();

        for ( int inx = 0 ; inx < limit ; ++inx )
            list.addTail( new DNode( "" + inx ) );
        printList( label, list );
        
        DNode   node    = list.getHead();
        for ( char inx = 'A' ; node != list ; ++inx )
        {
            DNode   temp    = node.getNext();
            node.addAfter( new DNode( inx ) );
            node = temp;
        }
        printList( label, list );
        
        node = list.getTail();
        for ( char inx = (char)('a' + limit - 1) ; node != list ; --inx )
        {
            DNode   temp    = node.getPrevious();
            if ( temp == list )
                throw new Error( "List out of order" );
            node.addBefore( new DNode( inx ) );
            node = temp.getPrevious();
        }
        printList( label, list );
    }
    
    private void printList( String label, DList list )
    {
        System.out.print(  label + " *** " );
        DNode node = list.getHead() ;
        for ( ; node != list ; node = node.getNext() )
            System.out.print( node.getData() + " " );
        System.out.println( "***" );
    }
}
