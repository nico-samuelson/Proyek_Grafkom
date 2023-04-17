package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class bezier2 extends Object{
    private List<float[]> PointControl = new ArrayList<>();
    private float lineWidth;
    public bezier2(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<float[]> PointControl, float lineWidth) {
        super(shaderModuleDataList, vertices, color);
        this.centerPoint=Arrays.asList(PointControl.get(0)[0], PointControl.get(0)[1], PointControl.get(0)[2]);
        this.PointControl = PointControl;
        this.lineWidth = lineWidth;
        curve();
        setupVAOVBO();
    }


    private int factorial (int a){
        int b =1;
        for (int i = 1; i <= a; i++){
            b*=i;
        }
        return b;
    }

    private int combination(int n, int r){
        return factorial(n)/(factorial(r) * factorial(n-r));
    }

    private int[] pascal(List<float[]> temp){
        int[] pas = new int[temp.size()];

        for (int i =0; i < temp.size(); i++){
            pas[i] = combination(temp.size()-1, i);
        }

        return pas;
    }

    private ArrayList<float[]> bezier(){
        ArrayList<float[]> bz = new ArrayList<>();

        int[] pascal = pascal(this.PointControl);

        for (float t = 0f; t <= 1; t += 0.01f){
            float x = 0;
            int temp = this.PointControl.size()-1;
            for (int a = 0; a < this.PointControl.size(); a++){
                x += pascal[a]*Math.pow((1-t), temp)*Math.pow(t, a)*this.PointControl.get(a)[0];
                temp--;
            }

            float y = 0;
            temp = this.PointControl.size()-1;
            for (int a = 0; a < this.PointControl.size(); a++){
                y += pascal[a]*Math.pow((1-t), temp)*Math.pow(t, a)*this.PointControl.get(a)[1];
                temp--;
            }

            float z = 0;
            temp = this.PointControl.size()-1;
            for (int a = 0; a < this.PointControl.size(); a++){
                z += pascal[a]*Math.pow((1-t), temp)*Math.pow(t, a)*this.PointControl.get(a)[2];
                temp--;
            }

            float[] coord = {x, y, z};
            bz.add(coord);
        }

        return bz;
    }

    public void curve(){
        if (this.PointControl.size() <= 0) return;

        ArrayList<float[]> bz = bezier();

        for (float[] i:
                bz) {
//            System.out.println(i[0] + " " + i[1] + " " + i[2] + " ");
            vertices.add(new Vector3f(i[0],i[1],0));
        }
    }
}