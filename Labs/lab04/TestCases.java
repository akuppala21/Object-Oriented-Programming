import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;


public class TestCases
{
   public static final double DELTA = 0.00001;

   /* put your tests here */

   
   public void testPerimeter(Circle c, Rectangle r, Triangle t, ConvexPolygon cp){
	
   

   //test perimeter of circle
	assertEquals(Math.PI*4.0, c.getPerimeter(), DELTA);
   //test perimeter of rectangel
   assertEquals(8.0, r.getPerimeter(), DELTA);
   //test perimeter of triangle
   assertEquals(3.41421356, t.getPerimeter(), DELTA);
   //test perimeter of convex polygon
   assertEquals(8.0644951, cp.getPerimeter(), DELTA);


   }

    public void testArea(Circle c, Rectangle r, Triangle t, ConvexPolygon cp){

         //test perimeter of circle
         assertEquals(Math.PI*2.0*2.0, c.getArea(), DELTA);
         //test perimeter of rectangel
         assertEquals(4.0, r.getArea(), DELTA);
         //test perimeter of triangle
         assertEquals(0.5, t.getArea(), DELTA);
         //test perimeter of convex polygon
         assertEquals(3.0, cp.getArea(), DELTA);
   
   
    }
    public void testColor(Circle c, Rectangle r, Triangle t, ConvexPolygon cp){
         //test perimeter of circle
         assertEquals(Color.ORANGE, c.getColor());
         //test perimeter of rectangel
         assertEquals(Color.ORANGE, r.getColor());
         //test perimeter of triangle
         assertEquals(Color.ORANGE, t.getColor());
         //test perimeter of convex polygon
         assertEquals(Color.ORANGE, cp.getColor());


    }

    public void testTranslate(Circle c, Rectangle r, Triangle t, ConvexPolygon cp){
         
         c.translate(new Point(1,1));
         r.translate( new Point(1,1));
         t.translate(new Point(1,1));
         cp.translate( new Point(1,1));
         //test perimeter of circle
         assertEquals( new Point(1,1), c.getCenter());
         //test perimeter of rectangel
         assertEquals(new Point(1,1), r.getUpperLeft() );
         //test perimeter of triangle
         assertEquals(new Point(2,1), t.getVertexA());

          assertEquals(new Point(1,2), t.getVertexB());

          assertEquals(new Point(1,1), t.getVertexC());

         //test perimeter of convex polygon
        assertEquals(new Point[]{new Point(1,1), new Point(1,3), new Point(-1,1), new Point(-1,0)}, new Point[]{cp.getVertex(0),cp.getVertex(1), cp.getVertex(2),cp.getVertex(3)});




    }
   

	public void TestGetShapes(WorkSpace wk,Circle c, Rectangle r, Triangle t, ConvexPolygon cp){


		List<Circle> circle = new ArrayList<Circle>();
		List<Triangle> triangle = new ArrayList<Triangle>();
		List<Rectangle> rectangle = new ArrayList<Rectangle>();	
		List<ConvexPolygon> convexP = new ArrayList<ConvexPolygon>();
		
		circle.add(0,c);
		triangle.add(0,t);
		rectangle.add(0,r);
		convexP.add(0,cp);
		
		//TestGetShapes(assertEquals(circle,wk.getShapesByColors(Color.ORANGE);
		addToArrayList(wk,c,r,t,cp);
		assertEquals(circle,wk.getCircles());
		assertEquals(triangle, wk.getTriangles());
		assertEquals(rectangle, wk.getRectangles());
		assertEquals(convexP, wk.getConvexPolygons());
		

		//test all shapes
		assertEquals(32.0450792 ,wk.getPerimeterOfAllShapes(),DELTA);

		assertEquals( 20.0663706 ,wk.getAreaOfAllShapes(),DELTA);
	}


	public void addToArrayList(WorkSpace wk,Circle c, Rectangle r, Triangle t, ConvexPolygon cp){
		
		
                wk.add((Shape)c);
                 wk.add((Shape)r);
                wk.add((Shape)t);
                wk.add((Shape)cp);
	}




  

    @Test
     public void TestAll(){

      WorkSpace w = new WorkSpace();
	 

      Point [] p= new Point[]{new Point(0,0), new Point(0,2), new Point(-2,0), new Point(-2,-1)};

      Circle c1 = new Circle(2.0,new Point(0,0), new Color(255,200,0));
      
      Rectangle r1 = new Rectangle(2.0,2.0, new Point(2,0), new Color(255,200,0));

      Triangle t1 = new Triangle ( new Point ( 1,0), new Point (0,1), new Point(0,0), new Color ( 255,200,0));

      ConvexPolygon cp1 = new ConvexPolygon(p, new Color (255,200,0));


     testPerimeter(c1, r1, t1, cp1);
      testArea(c1, r1, t1, cp1);
      testTranslate(c1, r1, t1, cp1);
     testColor(c1, r1, t1, cp1);
	TestGetShapes(w, c1,r1,t1,cp1);   

     }


  /* /* HINT - comment out implementation tests for the classes that you have not yet implemented */
   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getRadius", "setRadius", "getCenter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getWidth", "setWidth", "getHeight", "setHeight", "getUpperLeft");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class}, new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testTriangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertexA", "getVertexB", "getVertexC");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class, Point.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testConvexPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertex");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[] {int.class});

      verifyImplSpecifics(ConvexPolygon.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
