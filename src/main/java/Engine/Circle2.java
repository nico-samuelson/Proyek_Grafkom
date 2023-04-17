package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Circle2 extends Object {

    Float radiusX;
    Float radiusY;
    public Circle2(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX,Float radiusY) {
        super(shaderModuleDataList, vertices, color);
        this.centerPoint = centerPoint;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        createCircle();
        setupVAOVBO();
    }

    @Override
    public void draw(Camera camera, Projection projection) {
        drawSetup(camera, projection);
        // Draw the vertices
        //optional
        glLineWidth(5); //ketebalan garis
        glPointSize(3); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP,
                0,
                vertices.size());
    }

    public double degToRad(float degree){
        return (degree * Math.PI / (float) 180);
    }
    public void createCircle(){
        vertices.clear();
        for(float i = 0;i<360;i+=0.1){
            double rad = degToRad(i);
            Float x = (float) (centerPoint.get(0)+Math.cos(rad)*radiusX);
            Float y = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
            Float z = 0.0f;
            vertices.add(new Vector3f(x,y,z));
        }
    }
    public void createRectangle(){
        vertices.clear();
        int degree = 45;
        for(float i = 0;i<4;i++){
            double rad = degToRad(degree);
            Float x = (float) (centerPoint.get(0)+Math.cos(rad)*radiusX);
            Float y = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
            Float z = 0.0f;
            vertices.add(new Vector3f(x,y,z));
            degree+=90;
        }
    }
    public void createTriangle(){
        vertices.clear();
        int degree = 90;
        for(float i = 0;i<3;i++){
            double rad = degToRad(degree);
            Float x = (float) (centerPoint.get(0)+Math.cos(rad)*radiusX);
            Float y = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
            Float z = 0.0f;
            vertices.add(new Vector3f(x,y,z));
            if(degree == 90){
                degree += 135;
            }
            else{
                degree += 90;
            }
        }
    }

    public Float getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(Float radiusX) {
        this.radiusX = radiusX;
    }

    public Float getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(Float radiusY) {
        this.radiusY = radiusY;
    }

    //    public void draw(){
//        drawSetup();
//        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
//    }
}