package rows.model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle ;


public class RowModel { 
	   public static int findColumn(double x) 
	    {
	    	double width = 600/7 ; 
	    	int i ; 
	    	for (i = 0; i < 8 ; i++)
	    	{
	    		if ( x < i*width ) return i-1 ; 
	    			
	    	}
	    	return i-1 ; 
	    }
	   
	   public static int findRow(int column, int takenSpaces) throws Exception
	   {
		   	int row = 6 - takenSpaces; 
	     	if (row <= 0) throw new Exception() ; 
	     	return row ; 
	   }
	   public static boolean victory(int column, int row, Circle [] circles)
	   {
		   System.out.println("Column: " + Integer.toString(column)) ; 
		   System.out.println("Row: " + Integer.toString(row)) ; 
		   
		   int same = 0 ; 
		   if ( column >= 3 ) {
			   for (int i = 0 ; i < 4 ; i++) {
				   if ( circles[6*(column - i)  + row - 1].getFill() == Color.BLUEVIOLET ) same = same + 1;  
			   }
			   if (same == 4) return true ; 
		   } 
		   same = 0 ; 
		   if ( column <= 3) {
			   
		   for (int i = 0 ; i < 4 ; i++) {
			   if ( circles[6*(column+i) + row - 1].getFill() == Color.BLUEVIOLET ) same = same + 1;
		   		}	
		   		if (same == 4) return true ; 
		   }
		   same = 0 ; 
		   if ( row > 3 ) {
			   for (int i = 0 ; i < 4 ; i++) {
				   if ( circles[6*column + row - 1 - i ].getFill() == Color.BLUEVIOLET ) same ++ ; 
			   }
			   if (same == 4) return true ; 
		   } 
		   same = 0 ; 
		   if ( row <= 3 ) {
			   for (int i = 0 ; i < 4 ; i++) {
				   if ( circles[6*column + row - 1 + i ].getFill() == Color.BLUEVIOLET ) same ++ ; 
			   }
			   if (same == 4) return true ; 
		   }
		   return false ; 
		   
	   }

}
