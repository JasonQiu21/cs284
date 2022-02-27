import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.gradescope.jh61b.grader.GradedTest;
import com.gradescope.jh61b.junit.JUnitUtilities;

import java.util.*;

public class AssignmentTest {
	

    @Test
    @GradedTest(name = "Test #1 get(int index)", max_score = 5)
    public void testGetIndex1() {
    	
        String al1[] = new String[4];
		al1[0] = "X";
        al1[1] = "Y";
        al1[2] = "Z";
        al1[3] = "W";
        		
		IDLList<String> L1 = new IDLList<String>(al1);
		try{
			if (L1.get(0).equals("X") == false){
            System.out.println(JUnitUtilities.get_error_message(
                "this.al1 = ['X','Y','Z','W']",
                "index=0", 
                "X", 
                L1.get(0)));
			}
			assertEquals("X", L1.get(0));
		
			if (L1.get(1).equals("Y") == false ){
            System.out.println(JUnitUtilities.get_error_message(
                "this.al1 = ['X','Y','Z','W']",
                "index=1", 
                "Y", 
                L1.get(1)));
			}

            assertEquals("Y", L1.get(1));
		
			if (L1.get(2).equals("Z") == false ){
            System.out.println(JUnitUtilities.get_error_message(
                "this.al1 = ['X','Y','Z','W]",
                "index=2", 
                "Z", 
                L1.get(2)));
			}
            assertEquals("Z", L1.get(2));
    	
			if (L1.get(3).equals("W") == false ){
            System.out.println(JUnitUtilities.get_error_message(
                "this.al1 = ['X','Y','Z','W]",
                "index=3", 
                "W", 
                L1.get(3)));
			}
        assertEquals("W", L1.get(3));
    	}
        catch (Exception e){
    		e.printStackTrace(System.out);
    		assertEquals(0, 1);
    	} 
    }
        
    @Test
    @GradedTest(name = "Test #2 get(int index)", max_score = 5)
    public void testGetIndex2() {
        String al1[] = new String[4];
        al1[0] = "X";
        al1[1] = "Y";
        al1[2] = "Z";
        al1[3] = "W";
                
        IDLList<String> L1 = new IDLList<String>(al1);

        try {

            L1.get(-1);

            System.out.println(JUnitUtilities.get_no_throw_message(
                    "",
                    "index=-1"));

            fail("Should have thrown an Exception");
        } catch (IllegalStateException e) {
            assertEquals(1, 1);
        }

        try {
            L1.get(10);

            System.out.println(JUnitUtilities.get_no_throw_message(
                    "",
                    "index=10"));

            fail("Should have thrown an Exception");
        } catch (IllegalStateException e) {
            assertEquals(1, 1);
        }
    	
    }

    @Test
    @GradedTest(name = "Test getHead()", max_score = 10)
    public void testGetHead() {
    	
        IDLList<String> L21 = new IDLList<String>();
        try {
        	if (L21.getHead() != null ){
                System.out.println(JUnitUtilities.get_error_message(
                    "",
                    "", 
                    null, 
                    L21.getHead()));
    			}
        	assertEquals(null, L21.getHead());
        }
        catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 
        
        String al2[] = new String[4];
		al2[0] = "X";
        al2[1] = "Y";
        al2[2] = "Z";
        al2[3] = "W";       		
		IDLList<String> L22 = new IDLList<String>(al2);
		
		try{
			if (L22.getHead().equals("X") == false ){
            System.out.println(JUnitUtilities.get_error_message(
                "this.al2 = ['X','Y','Z','W]",
                "", 
                "X", 
                L22.getHead()));
			}
            assertEquals("X", L22.getHead());
    	}
		catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 
		
    }

//
    @Test
    @GradedTest(name = "Test getLast()", max_score = 10)
    public void testGetLast() {
        IDLList<String> L31 = new IDLList<String>();
        try {
        	if (L31.getLast() != null ){
                System.out.println(JUnitUtilities.get_error_message(
                    "",
                    "", 
                    null, 
                    L31.getLast()));
    			}
        	 assertEquals(null, L31.getLast());
        }catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 

        String al3[] = new String[4];
		al3[0] = "X";
        al3[1] = "Y";
        al3[2] = "Z";
        al3[3] = "W";       		
		IDLList<String> L32 = new IDLList<String>(al3);
		try {
			if (L32.getLast().equals("W") == false ){
                System.out.println(JUnitUtilities.get_error_message(
                    "this.al3 = ['X','Y','Z','W']",
                    "", 
                    "W", 
                    L32.getLast()));
    			}
            assertEquals("W", L32.getLast());
    	}catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 

    }
//
    @Test
    @GradedTest(name = "Test add(E elem)", max_score = 10)
    public void testAddElem() {
    	
        IDLList<String> L4 = new IDLList<String>();
        
        try {
            assertEquals(true, L4.add("A"));

            if (L4.toString().equals("[A]") == false) {
                System.out.println(JUnitUtilities.get_error_message(
                    "",
                    "elem='A'", 
                    "[A]", 
                    L4.toString()));
                }
            assertEquals("[A]", L4.toString());
        
            assertEquals(true, L4.add("B"));

        	if (L4.toString().equals("[B, A]") == false){
                System.out.println(JUnitUtilities.get_error_message(
                    "[A]",
                    "elem='B'", 
                    "[B, A]", 
                    L4.toString()));
    			}
            assertEquals("[B, A]", L4.toString());
    	
        }
        catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(1, 0);
        } 
    }

    @Test
    @GradedTest(name = "Test add(int index, E elem)", max_score = 10)
    public void testAddIndexElem() {

        try{
    	
            IDLList<String> L5 = new IDLList<String>();
            L5.add(0, "A");
            L5.add(1, "B");

            try {

                L5.add(-1, "C");

                System.out.println(JUnitUtilities.get_no_throw_message(
                        "",
                        "index=-1, elem='C'"));

                fail("Should have thrown an Exception");
            } catch (IllegalStateException e) {
                assertEquals(1, 1);
            }

            try {
                L5.add(3, "D");

                System.out.println(JUnitUtilities.get_no_throw_message(
                        "",
                        "index=3, elem='D'"));

                fail("Should have thrown an Exception");
            } catch (IllegalStateException e) {
                assertEquals(1, 1);
            }
        }
        catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(1, 0);
        } 
    }

    @Test
    @GradedTest(name = "Test append(E elem)", max_score = 10)
    public void testAppend() {
        IDLList<String> L6 = new IDLList<String>();
        try {
		
        assertEquals(true, L6.append("X"));
        
        assertEquals(true, L6.append("Y"));
        
    	if (L6.toString().equals("[X, Y]") == false){
            System.out.println(JUnitUtilities.get_error_message(
                "[X]",
                "elem='Y'", 
                "[X, Y]", 
                L6.toString()));
			}
	    assertEquals("[X, Y]", L6.toString()); 

        assertEquals(true, L6.append("Z"));
    
    	if (L6.toString().equals("[X, Y, Z]") == false){
            System.out.println(JUnitUtilities.get_error_message(
                "[X, Y]",
                "elem = 'Z'", 
                "[X, Y, Z]", 
                L6.toString()));
		}

        assertEquals("[X, Y, Z]", L6.toString());
        
        }catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 
    }

//
    @Test
    @GradedTest(name = "Test remove()", max_score = 10)
    public void testRemove() {
    	
        IDLList<String> L71 = new IDLList<String>();        
        try {
            L71.remove();
            System.out.println(JUnitUtilities.get_no_throw_message(
                    "",
                    ""));

            fail("Should have thrown an Exception");
        } catch (Exception e) {
            assertEquals(1, 1);
        }

        String al7[] = new String[7];
		al7[0] = "A";
        al7[1] = "B";
        al7[2] = "C";
        al7[3] = "D";
        al7[4] = "E";
        al7[5] = "F";
        al7[6] = "G";
		IDLList<String> L72 = new IDLList<String>(al7);
		
		try {

          String l72_remove = L72.remove();
			
          if (l72_remove.equals("A") == false){
            System.out.println(JUnitUtilities.get_error_message(
                "[A, B, C, D, E, F, G]",
                "", 
                "A", 
                l72_remove));
            }

		  assertEquals("A", l72_remove);

          l72_remove = L72.remove();

          if (l72_remove.equals("B") == false){
            System.out.println(JUnitUtilities.get_error_message(
                "[B, C, D, E, F, G]",
                "", 
                "B", 
                l72_remove));
            }
		
          assertEquals("B", l72_remove);

    
    	  if (L72.toString().equals("[C, D, E, F, G]") == false){
            System.out.println(JUnitUtilities.get_error_message(
                "[B, C, D, E, F, G]",
                "", 
                "[C, D, E, F, G]", 
                L72.toString()));
			}
		  assertEquals("[C, D, E, F, G]", L72.toString());
    	}
    	catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 
    }


    @Test
    @GradedTest(name = "Test removeLast()", max_score = 10)
    public void testRemoveLast() {
    	
        IDLList<String> L81 = new IDLList<String>();

        try {
            L81.removeLast();

            System.out.println(JUnitUtilities.get_no_throw_message(
                    "",
                    ""));

            fail("Should have thrown an Exception");
        } catch (IllegalStateException e) {
            assertEquals(1, 1);
        }
        String al8[] = new String[7];
		al8[0] = "A";
        al8[1] = "B";
        al8[2] = "C";
        al8[3] = "D";
        al8[4] = "E";
        al8[5] = "F";
        al8[6] = "G";
		IDLList<String> L82 = new IDLList<String>(al8);
		
		try {

            String l82_removelast = L82.removeLast();

            if (l82_removelast.equals("G") == false){
                System.out.println(JUnitUtilities.get_error_message(
                    "[A, B, C, D, E, F, G]",
                    "", 
                    "G", 
                    l82_removelast));
                }

			assertEquals("G", l82_removelast);

            l82_removelast = L82.removeLast();

            if (l82_removelast.equals("F") == false){
                System.out.println(JUnitUtilities.get_error_message(
                    "[A, B, C, D, E, F]",
                    "", 
                    "F", 
                    l82_removelast));
                }
//		
			assertEquals("F", l82_removelast);
		
        	if (L82.toString().equals("[A, B, C, D, E]") == false){
                System.out.println(JUnitUtilities.get_error_message(
                    "[A, B, C, D, E, F]",
                    "", 
                    "[A, B, C, D, E]", 
                    L82.toString()));
    			}
		    assertEquals("[A, B, C, D, E]", L82.toString());
		
    	}catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 
    	
    }

//
    @Test
    @GradedTest(name = "Test removeAt(int index)", max_score = 10)
    public void testRemoveAt() {
    	
        IDLList<String> L91 = new IDLList<String>();

        try {
            L91.removeAt(1);
            System.out.println(JUnitUtilities.get_no_throw_message(
                    "",
                    "index=1"));

            fail("Should have thrown an Exception");
        } catch (IllegalStateException e) {
            assertEquals(1, 1);
        }
	
        try{
            String al9[] = new String[7];
    		al9[0] = "A";
            al9[1] = "B";
            al9[2] = "C";
            al9[3] = "D";
            al9[4] = "E";
            al9[5] = "F";
            al9[6] = "G";
    		IDLList<String> L92 = new IDLList<String>(al9);

        try {
            L92.removeAt(-1);

            System.out.println(JUnitUtilities.get_no_throw_message(
                    "",
                    "index=-1"));

            fail("Should have thrown an Exception");
        } catch (IllegalStateException e) {
            assertEquals(1, 1);
        }

        try {
            L92.removeAt(10);

            System.out.println(JUnitUtilities.get_no_throw_message(
                    "",
                    "index=10"));

            fail("Should have thrown an Exception");
        } catch (IllegalStateException e) {
            assertEquals(1, 1);
        }

            String l92_remove = L92.removeAt(2);

            if (l92_remove.equals("C") == false){
                System.out.println(JUnitUtilities.get_error_message(
                    "[A, B, C, D, E, F, G]",
                    "index=2", 
                    "C", 
                    l92_remove));
                }

		    assertEquals("C", l92_remove);

            l92_remove = L92.removeAt(5);

            if (l92_remove.equals("G") == false){
                System.out.println(JUnitUtilities.get_error_message(
                    "[A, B, D, E, F, G]",
                    "index=5", 
                    "G", 
                    l92_remove));
                }
        
		    assertEquals("G", l92_remove);
 
			if (L92.toString().equals("[A, B, D, E, F]") == false){
                System.out.println(JUnitUtilities.get_error_message(
                    "[A, B, D, E, F, G]",
                    "index=5", 
                    "[A, B, D, E, F]", 
                    L92.toString()));
    			}
		    assertEquals("[A, B, D, E, F]", L92.toString());
		
    	}catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 
    	
    }
//
//
    @Test
    @GradedTest(name = "Test remove(E elem)", max_score = 10)
    public void testRemoveElem() {
    	
        IDLList<String> L101 = new IDLList<String>();
        try {
        	if (L101.remove("X") != false){
                System.out.println(JUnitUtilities.get_error_message(
                    "",
                    "X", 
                    false, 
                    L101.remove("X")));
    			}

        String al10[] = new String[7];
		al10[0] = "A";
        al10[1] = "B";
        al10[2] = "C";
        al10[3] = "D";
        al10[4] = "E";
        al10[5] = "F";
        al10[6] = "G";
		IDLList<String> L102 = new IDLList<String>(al10);

		assertEquals(true, L102.remove("D"));
		
		assertEquals(true, L102.remove("A"));
        
    	if (L102.toString().equals("[B, C, E, F, G]") == false){
            System.out.println(JUnitUtilities.get_error_message(
                "[A, B, C, E, F, G]",
                "elem=A", 
                "[B, C, E, F, G]", 
                L102.toString()));
			}
		assertEquals("[B, C, E, F, G]", L102.toString());
    	}catch (Exception e){
            e.printStackTrace(System.out);
            assertEquals(0, 1);
        } 
	}
}