import Engine.*;
import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window = new Window(1200, 800, "Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectPeluru = new ArrayList<>();
    public String statusBody = "null";
    private MouseInput mouseInput;
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    Camera camera = new Camera();
    String legDirection = "front";
    boolean isVentOpened = false;
    boolean isVented = false;
    boolean isVenting = false;
    Object ventSrc, ventDest;
    float move = .02f;
    ArrayList<Vector4f> colors = new ArrayList<>();

    public void init() {
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        camera.setPosition(0, 0.25f, 1.7f);

        colors.add(new Vector4f(1f, 0f, 0f, 1f));
        colors.add(new Vector4f(0.067f, 0.5f, 0.18f, 1f));
        colors.add(new Vector4f(0f, 0f, 1f, 1f));
        //BADAN
        objects.add(new Sphere("body",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, .0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.12f,
                0.12f,
                0.12f,
                348,
                348,
                180f
        ));

        objects.get(0).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);

        // KAKI
        objects.get(0).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(0).getChildObject().get(0).translateObject(0.065f, 0.f, -0.14f);
        objects.get(0).getChildObject().get(0).setCenterPoint(Arrays.asList(0.25f, 0.0f, 0.0f));
        objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);

        objects.get(0).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(0).getChildObject().get(1).translateObject(-0.065f, 0.f, -0.14f);
        objects.get(0).getChildObject().get(1).setCenterPoint(Arrays.asList(0.25f, 0.0f, 0.0f));
        objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);


        // KACAMATA
        objects.get(0).getChildObject().add(new Sphere("glasses",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.08f,
                0.04f,
                0.06f,
                108,
                72,
                180f
        ));

        objects.get(0).getChildObject().get(2).translateObject(.0f, .0f, -0.09f);


        // TAS
        objects.get(0).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0f, 0f, 1.0f),
                Arrays.asList(0.0f, -0.04f, 0.0f),
                0.12f,
                0.12f,
                0.12f,
                108,
                72,
                180f
        ));
        objects.get(0).getChildObject().get(3).translateObject(.0f, .0f, 0.12f);

        // PISTOL
        objects.get(0).getChildObject().add(new Sphere("gun",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.0f, 0f, 0f, 0.0f),
                Arrays.asList(0.15f, -0.08f, 0.0f),
                0.03f,
                0.08f,
                0.05f,
                108,
                72,
                180f
        ));
        objects.get(0).getChildObject().get(4).getChildObject().add(new Sphere("gun",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.15f, -0.05f, 0.06f),
                0.03f,
                0.08f,
                0.04f,
                108,
                72,
                180f
        ));
        objects.get(0).getChildObject().get(4).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.f, 0.f, 0.f, 1.0f),
                Arrays.asList(0.15f, -0.05f, -0.04f),
                0.03f,
                0.04f,
                0.12f,
                108,
                72,
                180f
        ));
        objects.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(90f), 1.0f, 0.0f, 0.0f);

        // MEDBAY CENTER
        objects.add(new Sphere("medbay", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(.5f, .69f, .75f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.2f,
                0.2f,
                0.05f,
                108,
                72,
                180f
        ));

        objects.get(1).rotateObject((float) Math.toRadians(270f), 1.0f, 0.0f, 0.0f);
        objects.get(1).translateObject(2f, -.2f, -0f);

        // MEDBAY LEG
        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(.4f, .59f, .63f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.25f,
                0.25f,
                0.25f,
                108,
                72,
                180f
        ));

        objects.get(1).getChildObject().get(0).rotateObject((float) Math.toRadians(180f), 0.0f, 0.0f, 1.0f);
        objects.get(1).getChildObject().get(0).translateObject(2.2f, -0.15f, -0.f);

        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(.4f, .59f, .63f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.25f,
                0.25f,
                0.25f,
                108,
                72,
                180f
        ));

        objects.get(1).getChildObject().get(1).rotateObject((float) Math.toRadians(180f), 0.0f, 0.0f, 1.0f);
        objects.get(1).getChildObject().get(1).translateObject(1.8f, -.15f, -0.f);

        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(.4f, .59f, .63f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.25f,
                0.25f,
                0.25f,
                108,
                72,
                180f
        ));

        objects.get(1).getChildObject().get(2).rotateObject((float) Math.toRadians(180f), 0.0f, 0.0f, 1.0f);
        objects.get(1).getChildObject().get(2).translateObject(2f, -.15f, -0.2f);

        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(.4f, .59f, .63f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.25f,
                0.25f,
                0.25f,
                108,
                72,
                180f
        ));

        objects.get(1).getChildObject().get(3).rotateObject((float) Math.toRadians(180f), 0.0f, 0.0f, 1.0f);
        objects.get(1).getChildObject().get(3).translateObject(2f, -0.15f, .2f);

        //SCANNER
        objects.get(1).getChildObject().add(new Circle(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.0625f, 0.785f, .0625f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.16f,
                0.16f
        ));

        objects.get(1).getChildObject().get(4).rotateObject((float) Math.toRadians(270f), 1.0f, 0.0f, 0.0f);
        objects.get(1).getChildObject().get(4).translateObject(2f, -.199f, -0f);

        objects.get(1).getChildObject().add(new Circle(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.7f, 0.7f, .7f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.16f,
                0.16f
        ));

        objects.get(1).getChildObject().get(5).rotateObject((float) Math.toRadians(270f), 1.0f, 0.0f, 0.0f);
        objects.get(1).getChildObject().get(5).translateObject(2f, -.19999f, -0f);

        //BEZIER CABLE
        objects.get(1).getChildObject().add(new Bezier(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0f, 0.f, 1.0f, 1.0f),
                Arrays.asList(
                        new float[]{0f, 0f, 0f},
                        new float[]{0.03f, 0f, 0.1f},
                        new float[]{0.06f, 0f, 0.2f},
                        new float[]{0.09f, 0f, 0.3f},
                        new float[]{0.12f, 0f, 0.2f},
                        new float[]{0.15f, 0.05f, 0.1f},
                        new float[]{0.18f, 0.05f, 0f},
                        new float[]{0.21f, 0.1f, 0f},
                        new float[]{0.24f, 0.15f, 0f},
                        new float[]{0.3f, 0.2f, 0f}
                ),
                3f
        ));

        objects.get(1).getChildObject().get(6).rotateObject((float) Math.toRadians(30f), .0f, 1.0f, 0.0f);
        objects.get(1).getChildObject().get(6).translateObject(2.2f, -0.2f, 0f);

        objects.get(1).getChildObject().add(new Bezier(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(10f, 10.f, .0f, 1.0f),
                Arrays.asList(
                        new float[]{0f, 0f, 0f},
                        new float[]{0.03f, 0f, 0.1f},
                        new float[]{0.06f, 0f, 0.2f},
                        new float[]{0.09f, 0f, 0.3f},
                        new float[]{0.12f, 0f, 0.2f},
                        new float[]{0.15f, 0.05f, 0.1f},
                        new float[]{0.18f, 0.05f, 0f},
                        new float[]{0.21f, 0.1f, 0f},
                        new float[]{0.24f, 0.15f, 0f},
                        new float[]{0.3f, 0.2f, 0f}
                ),
                3f
        ));

        objects.get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(-30f), .0f, 1.0f, 0.0f);
        objects.get(1).getChildObject().get(7).translateObject(2.2f, -0.2f, 0f);

        // SCREEN
        objects.get(1).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f, 1f, 1f, 0.0f),
                Arrays.asList(-0.15f, -0.08f, 0.0f),
                0.75f,
                0.375f,
                0.02f,
                108,
                72,
                180f
        ));

        objects.get(1).getChildObject().get(8).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        objects.get(1).getChildObject().get(8).translateObject(2.48f, 0.25f, 0f);

        objects.get(1).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f, 1f, 0f, 0.0f),
                Arrays.asList(-0.15f, -0.08f, 0.0f),
                0.675f,
                0.3f,
                0.02f,
                108,
                72,
                180f
        ));

        objects.get(1).getChildObject().get(9).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        objects.get(1).getChildObject().get(9).translateObject(2.47f, 0.25f, 0f);

        // LANTAI
        objects.add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.7f, 0.69f, 0.62f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                5f,
                0.02f,
                5f,
                108,
                72,
                180f
        ));
        objects.get(2).translateObject(.0f, -0.265f, 0f);


        // DINDING ATAS
        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.49f, 0.62f, 0.675f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                5f,
                1.5f,
                .005f,
                108,
                72,
                180f
        ));
        objects.get(2).getChildObject().get(0).translateObject(.0f, 1f, -2.5f);

        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.49f, 0.62f, 0.675f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                5f,
                1.5f,
                .005f,
                108,
                72,
                180f
        ));

        objects.get(2).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 0f, 1f, 0f);
        objects.get(2).getChildObject().get(1).translateObject(-2.5f, 1f, 0.0f);


        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.49f, 0.62f, 0.675f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                5f,
                1.5f,
                .005f,
                108,
                72,
                180f
        ));

        objects.get(2).getChildObject().get(2).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        objects.get(2).getChildObject().get(2).translateObject(2.5f, 1f, 0.0f);

        // MEJA
        objects.get(2).getChildObject().add(new Sphere("tablebase", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.15f, 0.28f, 0.47f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.075f,
                0.075f,
                0.02f,
                360,
                180,
                180f
        ));

        objects.get(2).getChildObject().add(new Sphere("leg", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.27f, 0.48f, 0.63f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.75f,
                0.75f,
                0.05f,
                360,
                180,
                180f
        ));

        objects.get(2).getChildObject().add(new Sphere("tablebase", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.15f, 0.28f, 0.47f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.075f,
                0.075f,
                0.02f,
                360,
                180,
                180f
        ));

        objects.get(2).getChildObject().add(new Sphere("leg", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.27f, 0.48f, 0.63f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.75f,
                0.75f,
                0.05f,
                360,
                180,
                180f
        ));

        objects.get(2).getChildObject().add(new Sphere("bag", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(.0f, 0.6f, 0.8f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.4f,
                0.24f,
                0.02f,
                360,
                180,
                180f
        ));

        objects.get(2).getChildObject().add(new Sphere("bag", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0f, 0f, 0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.3f,
                0.18f,
                0.001f,
                360,
                180,
                180f
        ));

        objects.get(2).getChildObject().get(3).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(3).translateObject(-1.25f, -0.1f, 1.25f);

        objects.get(2).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(4).translateObject(-1.25f, -0.1f, 1.25f);

        objects.get(2).getChildObject().get(5).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(5).translateObject(1.25f, -0.1f, -1.25f);

        objects.get(2).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(6).translateObject(1.25f, -0.1f, -1.25f);

        objects.get(2).getChildObject().get(7).rotateObject((float) Math.toRadians(120f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(7).translateObject(1.25f, 0.001f, -.9f);

        objects.get(2).getChildObject().get(8).rotateObject((float) Math.toRadians(120f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(8).translateObject(1.25f, 0.001f, -.86f);

        // DINDING BAWAH
        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.47f, 0.067f, 0.13f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                5f,
                0.5f,
                .005f,
                108,
                72,
                180f
        ));
        objects.get(2).getChildObject().get(9).translateObject(.0f, 0f, -2.5f);

        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.47f, 0.067f, 0.13f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                5f,
                0.5f,
                .005f,
                108,
                72,
                180f
        ));

        objects.get(2).getChildObject().get(10).rotateObject((float) Math.toRadians(270f), 0f, 1f, 0f);
        objects.get(2).getChildObject().get(10).translateObject(-2.5f, 0f, 0.0f);


        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.47f, 0.067f, 0.13f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                5f,
                0.5f,
                .005f,
                108,
                72,
                180f
        ));

        objects.get(2).getChildObject().get(11).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        objects.get(2).getChildObject().get(11).translateObject(2.5f, 0f, 0.0f);

        // GELAS
        objects.get(2).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.7f, 0.73f, 0.75f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(2).getChildObject().get(12).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(12).translateObject(-0.8f, -0.075f, 1f);

        objects.get(2).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.7f, 0.73f, 0.75f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(2).getChildObject().get(13).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        objects.get(2).getChildObject().get(13).translateObject(-1.6f, -0.075f, 1.6f);

        // EMERGENCY BUTTON
        objects.get(2).getChildObject().add(new Sphere("button",Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0f, 0f, 0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.2f,
                0.05f,
                0.2f,
                360,
                180,
                180f
        ));
        objects.get(2).getChildObject().add(new Sphere("leg",Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(1f, 0f, 0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.06f,
                0.05f,
                0.06f,
                360,
                180,
                180f
        ));
        objects.get(2).getChildObject().add(new Sphere("bag",Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(1f, 1f, 1f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.2f,
                0.1f,
                0.2f,
                360,
                180,
                180f
        ));

        objects.get(2).getChildObject().get(14).translateObject(1.25f,-0.05f,-1.25f);
        objects.get(2).getChildObject().get(15).rotateObject((float) Math.toRadians(90f),1f,0f,0f);
        objects.get(2).getChildObject().get(15).translateObject(1.25f,-0.045f,-1.28f);
        objects.get(2).getChildObject().get(16).translateObject(1.25f,0.02f,-1.25f);

        // WASTAFEL
        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.5f,
                0.05f,
                0.2f,
                108,
                72,
                180f)
        );

        objects.get(2).getChildObject().get(17).translateObject(-2.4f,0f, 0f);

        objects.get(2).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f, 0f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0f),
                0.425f,
                0.05f,
                0.125f,
                108,
                72,
                180f)
        );
        objects.get(2).getChildObject().get(18).translateObject(-2.4f,0.01f, -0.05f);

//        PIPA WASTAFEL
        objects.get(2).getChildObject().add(new Bezier(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.75f, 0.75f, 1.0f),
                Arrays.asList(
                        new float[]{0f, 0f, 0f},
                        new float[]{0f, -0.05f, 0f},
                        new float[]{-0.05f, -0.1f, 0f},
                        new float[]{-0.1f, -0.15f, 0f},
                        new float[]{-0.15f, -0.2f, 0f}
                ),
                40f
        ));

        objects.get(2).getChildObject().get(19).rotateObject((float) Math.toRadians(30f), .0f, 1.0f, 0.0f);
        objects.get(2).getChildObject().get(19).translateObject(-2.35f, 0f, 0f);

        //MAYAT
        objects.add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f, 1f, 0.f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.12f,
                0.12f,
                0.12f,
                108,
                72,
                180f
        ));

        objects.get(3).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
        objects.get(3).translateObject(-2f, -.12f, -2f);

        //badan dalam
        objects.get(3).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f, 0f, 0.f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.10f,
                0.10f,
                0.10f,
                108,
                72,
                180f
        ));

        objects.get(3).getChildObject().get(0).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
        objects.get(3).getChildObject().get(0).translateObject(0f, 0.00009f, 0f);
        objects.get(3).getChildObject().get(0).translateObject(-2f, -.12f, -2f);

        //tulang
        objects.get(3).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.02f,
                0.02f,
                0.15f,
                108,
                72,
                180f
        ));

        objects.get(3).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
        objects.get(3).getChildObject().get(1).translateObject(0f, 0.05f, 0f);
        objects.get(3).getChildObject().get(1).translateObject(-2f, -.12f, -2f);

        objects.get(3).getChildObject().add(new Sphere("sphere",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.02f,
                0.02f,
                0.02f,
                108,
                72,
                180f
        ));

        objects.get(3).getChildObject().get(2).translateObject(0.02f, 0.05f, 0f);
        objects.get(3).getChildObject().get(2).rotateObject((float) Math.toRadians(45f), 0.0f, 1.0f, 0f);
        objects.get(3).getChildObject().get(2).translateObject(-2f, -.12f, -2f);

        objects.get(3).getChildObject().add(new Sphere("sphere",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.02f,
                0.02f,
                0.02f,
                108,
                72,
                180f
        ));

        objects.get(3).getChildObject().get(3).translateObject(-0.02f, 0.05f, 0f);
        objects.get(3).getChildObject().get(3).rotateObject((float) Math.toRadians(45f), 0.0f, 1.0f, 0f);
        objects.get(3).getChildObject().get(3).translateObject(-2f, -.12f, -2f);

        //kaki
        objects.get(3).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.0f, 1.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(3).getChildObject().get(4).translateObject(0.065f, -0.09f, 0.14f);
        objects.get(3).getChildObject().get(4).rotateObject((float) Math.toRadians(45f), 0.0f, 1f, 0f);
        objects.get(3).getChildObject().get(4).translateObject(-2f, -.12f, -2f);

        objects.get(3).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.0f, 1.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(3).getChildObject().get(5).translateObject(-0.065f, -0.09f, 0.14f);
        objects.get(3).getChildObject().get(5).rotateObject((float) Math.toRadians(45f), 0.0f, 1f, 0f);
        objects.get(3).getChildObject().get(5).translateObject(-2f, -.12f, -2f);

        //vent 1
        objects.add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.4f, .4f, .4f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.3f,
                0.02f,
                0.3f,
                108,
                72,
                180f)
        );

        objects.get(4).translateObject(-0.85f, -0.23f, .0f);

        objects.get(4).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.03f,
                0.02f,
                0.2f,
                108,
                72,
                180f)
        );

        objects.get(4).getChildObject().get(0).translateObject(-0.925f, -0.22f, .0f);

        objects.get(4).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.03f,
                0.02f,
                0.2f,
                108,
                72,
                180f)
        );

        objects.get(4).getChildObject().get(1).translateObject(-0.85f, -0.22f, .0f);

        objects.get(4).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.03f,
                0.02f,
                0.2f,
                108,
                72,
                180f)
        );

        objects.get(4).getChildObject().get(2).translateObject(-0.775f, -0.22f, .0f);

        //vent 2
        objects.add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.4f, .4f, .4f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.3f,
                0.02f,
                0.3f,
                108,
                72,
                180f)
        );
        objects.get(5).translateObject(0.85f, -0.23f, .0f);

        objects.get(5).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.03f,
                0.02f,
                0.2f,
                108,
                72,
                180f)
        );

        objects.get(5).getChildObject().get(0).translateObject(0.925f, -0.22f, .0f);

        objects.get(5).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.03f,
                0.02f,
                0.2f,
                108,
                72,
                180f)
        );

        objects.get(5).getChildObject().get(1).translateObject(0.85f, -0.22f, .0f);

        objects.get(5).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.03f,
                0.02f,
                0.2f,
                108,
                72,
                180f)
        );

        objects.get(5).getChildObject().get(2).translateObject(0.775f, -0.22f, .0f);

        objects.add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.3f,
                0.0f,
                0.3f,
                108,
                72,
                180f)
        );

        objects.get(6).translateObject(-0.85f, -0.23f, .0f);


        objects.add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.f, .0f, .0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.3f,
                0.0f,
                0.3f,
                108,
                72,
                180f)
        );

        objects.get(7).translateObject(0.85f, -0.23f, .0f);

        // BADAN 2
        objects.add(new Sphere("body",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.12f,
                0.12f,
                0.12f,
                348,
                348,
                180f
        ));

        objects.get(8).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
        objects.get(8).translateObject(0f, 0f, -2f);

        // KAKI 2
        objects.get(8).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(8).getChildObject().get(0).translateObject(0.065f, 0.f, -0.14f);
        objects.get(8).getChildObject().get(0).setCenterPoint(Arrays.asList(0.25f, 0.0f, 0.0f));
        objects.get(8).getChildObject().get(0).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
        objects.get(8).getChildObject().get(0).translateObject(0.0f, 0.f, -2f);

        objects.get(8).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.1f,
                108,
                72,
                180f
        ));

        objects.get(8).getChildObject().get(1).translateObject(-0.065f, 0.f, -0.14f);
        objects.get(8).getChildObject().get(1).setCenterPoint(Arrays.asList(0.25f, 0.0f, 0.0f));
        objects.get(8).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
        objects.get(8).getChildObject().get(1).translateObject(0.0f, 0.f, -2f);


        // KACAMATA 2
        objects.get(8).getChildObject().add(new Sphere("glasses",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.08f,
                0.04f,
                0.06f,
                108,
                72,
                180f
        ));

        objects.get(8).getChildObject().get(2).translateObject(.0f, .0f, 0.09f);
        objects.get(8).getChildObject().get(2).translateObject(0.0f, 0.f, -2f);


        // TAS
        objects.get(8).getChildObject().add(new Sphere("bag",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1f, 0f, 1.0f),
                Arrays.asList(0.0f, -0.04f, 0.0f),
                0.12f,
                0.12f,
                0.12f,
                108,
                72,
                180f
        ));
        objects.get(8).getChildObject().get(3).translateObject(.0f, .0f, -2.12f);

        //RELAY
        objects.add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.3f, 0.3f, 0.5f, 1.0f),
                Arrays.asList(1f, 1.5f, 0f),
                0.12f,
                0.12f,
                0.15f,
                108,
                72,
                180f
        ));

        objects.get(9).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
        objects.get(9).translateObject(0f, -0.25f, 0f);


        objects.get(9).getChildObject().add(new Sphere("tablebase",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.3f, .3f, 0.5f, 1.0f),
                Arrays.asList(0f, 0f, 0f),
                0.012f,
                0.02f,
                0.03f,
                108,
                72,
                180f
        ));

        objects.get(9).getChildObject().get(0).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        objects.get(9).getChildObject().get(0).translateObject(1f, 0.08f, 1.5f);

        objects.get(9).getChildObject().get(0).getChildObject().add(new Circle2(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.0f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.03f,
                0.03f
        ));

        objects.get(9).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(270f), 1.0f, 0.0f, 0.0f);
        objects.get(9).getChildObject().get(0).getChildObject().get(0).translateObject(1f, 0.06f, 1.5f);

        objects.get(9).getChildObject().get(0).getChildObject().add(new Circle2(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.0f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.06f,
                0.06f
        ));

        objects.get(9).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 1.0f, 0.0f, 0.0f);
        objects.get(9).getChildObject().get(0).getChildObject().get(1).translateObject(1f, 0.01f, 1.5f);

        objects.get(9).getChildObject().get(0).getChildObject().add(new Circle2(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(0.0f, 1f, 1f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f
        ));

        objects.get(9).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(270f), 1.0f, 0.0f, 0.0f);
        objects.get(9).getChildObject().get(0).getChildObject().get(2).translateObject(1f, -0.04f, 1.5f);

        objects.add(new bezier2(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(1f, 0.f, .0f, 1.0f),
                Arrays.asList(
                        new float[]{0.2f, 0f, 0f},
                        new float[]{0.4f, .3f, 0.f},
                        new float[]{0.2f, .5f, 0.f},
                        new float[]{0.2f,0.6f,0.f}
                ),
                5f
        ));
        objects.get(10).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f, 0f, 0f, 1.0f),
                Arrays.asList(0f, 0f, 0f),
                0.02f,
                0.02f,
                0.08f,
                108,
                72,
                180f
        ));
        objects.get(10).getChildObject().get(0).rotateObject((float)Math.toRadians(90f),1f,0f,0f);
        objects.get(10).rotateObject((float) Math.toRadians(225f),0f,0f,1f);
        objects.get(10).translateObject(0f,1.2f,-2.4f);
        objects.get(10).getChildObject().get(0).translateObject(0.23f,-0.53f,0f);

        objects.add(new bezier2(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(1f, 0.f, .0f, 1.0f),
                Arrays.asList(
                        new float[]{0.2f, 0f, 0f},
                        new float[]{0.4f, .3f, 0.f},
                        new float[]{0.2f, .5f, 0.f},
                        new float[]{0.2f,0.6f,0.f}
                ),
                5f
        ));
        objects.get(11).getChildObject().add(new Sphere("leg",
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f, 0f, 0f, 1.0f),
                Arrays.asList(0f, 0f, 0f),
                0.02f,
                0.02f,
                0.08f,
                108,
                72,
                180f
        ));
        objects.get(11).getChildObject().get(0).rotateObject((float)Math.toRadians(90f),1f,0f,0f);

        objects.get(11).rotateObject((float) Math.toRadians(225f),0f,0f,1f);
        objects.get(11).rotateObject((float)Math.toRadians(180f),0f,1f,0f);
        objects.get(11).getChildObject().get(0).translateObject(-0.225f,-0.53f,0f);
        objects.get(11).translateObject(0f,1.2f,-2.4f);

    }

    boolean cekscan = false;
    boolean scannow = true;
    boolean scanning = false;
    String currentColor = "red";
    boolean cekColor = false;
    boolean colorChanged = false;
    boolean boxOpen = false;
    boolean pressButton = false;
    boolean buttonPressed = false;
    boolean weapon = false;

    public void input(float x, float y, float z) {

            if (mouseInput.isLeftButtonPressed()) {
                shoot();
            }

        if (window.isKeyPressed(GLFW_KEY_M)) {
            if (v3R(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2),
                    objects.get(2).getChildObject().get(14).getCenterPoint().get(0),
                    objects.get(2).getChildObject().get(14).getCenterPoint().get(1),
                    objects.get(2).getChildObject().get(14).getCenterPoint().get(2)
            ) <= 0.95f) {
                pressButton = true;
            }
        }

        if (window.isKeyPressed(GLFW_KEY_R)) {
            if (dead)
                resurrect(objects.get(8));
        }

        if (window.isKeyPressed(GLFW_KEY_E)) {
            // scan
            if (scannow && cekscan && !isVenting) {
                System.out.println("scan");
                scanning = true;
                scannow = false;
            }

            // ganti warna
            else if (cekColor && !colorChanged) {
                if (currentColor.equals("red")) {
                    for (int i = 0; i < objects.get(0).getChildObject().size(); i++) {
                        if (i == 2 || i == 4 || i == 5) {
                            continue;
                        }
                        objects.get(0).getChildObject().get(i).changeColor(colors.get(1));
                    }
                    objects.get(0).changeColor(colors.get(1));
                    currentColor = "green";
                    colorChanged = true;
                } else if (currentColor.equals("green")) {
                    for (int i = 0; i < objects.get(0).getChildObject().size(); i++) {
                        if (i == 2 || i == 4 || i == 5) {
                            continue;
                        }
                        objects.get(0).getChildObject().get(i).changeColor(colors.get(2));
                    }
                    objects.get(0).changeColor(colors.get(2));
                    currentColor = "blue";
                    colorChanged = true;
                } else {
                    for (int i = 0; i < objects.get(0).getChildObject().size(); i++) {
                        if (i == 2 || i == 4 || i == 5) {
                            continue;
                        }
                        objects.get(0).getChildObject().get(i).changeColor(colors.get(0));
                    }
                    objects.get(0).changeColor(colors.get(0));
                    currentColor = "red";
                    colorChanged = true;
                }
            }

            // vent
            else if (!isVenting && !scanning && !cekscan) {
                float jarak1 = v3R(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2),
                        objects.get(4).getCenterPoint().get(0),
                        objects.get(4).getCenterPoint().get(1),
                        objects.get(4).getCenterPoint().get(2)
                );

                float jarak2 = v3R(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2),
                        objects.get(5).getCenterPoint().get(0),
                        objects.get(5).getCenterPoint().get(1),
                        objects.get(5).getCenterPoint().get(2)
                );

                if (jarak1 <= 0.35f) {
                    ventSrc = objects.get(4);
                    ventDest = objects.get(5);
                    isVenting = true;
                } else if (jarak2 <= 0.35f) {
                    ventSrc = objects.get(5);
                    ventDest = objects.get(4);
                    isVenting = true;
                }
            }
        }

        if (window.isKeyPressed(GLFW_KEY_W) && !scanning && !isVenting && (z > -2.3f)) {
            objects.get(0).setAngle(180f);
            if (!collideTable(x, y, z - 0.1f)) move_forward();
        }


        if (window.isKeyPressed(GLFW_KEY_S) && !scanning && !isVenting && (z < 2.3f)) {
            objects.get(0).setAngle(0f);
            if (!collideTable(x, y, z + 0.1f)) move_backward();
        }


        if (window.isKeyPressed(GLFW_KEY_A) && !scanning && !isVenting && (x > -2.3f)) {
            objects.get(0).setAngle(270);
            if (!collideTable(x - 0.1f, y, z)) move_left();
        }


        if (window.isKeyPressed(GLFW_KEY_D) && !scanning && !isVenting && (x < 2.3f)) {
            objects.get(0).setAngle(90);
            if (!collideTable(x + 0.1f, y, z)) move_right();
        }

        if (window.isKeyPressed(GLFW_KEY_SPACE) && !scanning && !isVenting) {
            jumping = true;
        }
    }

    private boolean scanup = true;

    public void scanup() {
        objects.get(1).getChildObject().get(4).translateObject(0f, 0.002f, 0f);
    }

    public void scandown() {
        objects.get(1).getChildObject().get(4).translateObject(0f, -0.002f, 0f);
    }

    public void move_forward() {
        objects.get(0).translateObject(.0f, .0f, -move);
        camera.moveForward(move);
        statusBody = "depan";

        if (!window.isKeyPressed(GLFW_KEY_A) && !window.isKeyPressed(GLFW_KEY_D)) {
            if (legDirection.equals("front")) {
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) <= objects.get(0).getCenterPoint().get(2) - 0.1f)
                    legDirection = "back";
            } else if (legDirection.equals("back")) {
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) > objects.get(0).getCenterPoint().get(2) + 0.1f) {
                    legDirection = "front";
                }
            }
        }
    }

    public void move_backward() {
        objects.get(0).translateObject(.0f, .0f, move);
        camera.moveBackwards(move);
        statusBody = "belakang";

        if (!window.isKeyPressed(GLFW_KEY_A) && !window.isKeyPressed(GLFW_KEY_D)) {
            if (legDirection.equals("front")) {
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );
                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) <= objects.get(0).getCenterPoint().get(2) - 0.1f)
                    legDirection = "back";
            } else if (legDirection.equals("back")) {
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) > objects.get(0).getCenterPoint().get(2) + 0.1f) {
                    legDirection = "front";
                }
            }
        }
    }

    public void move_right() {
        objects.get(0).translateObject(move, .0f, .0f);
        camera.moveRight(move);
        statusBody = "kanan";

        if (legDirection.equals("front")) {
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) <= objects.get(0).getCenterPoint().get(0) - 0.04f)
                legDirection = "back";
        } else if (legDirection.equals("back")) {
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) > objects.get(0).getCenterPoint().get(0) + 0.04f) {
                legDirection = "front";
            }
        }
    }

    public void move_left() {
        objects.get(0).translateObject(-move, .0f, .0f);
        camera.moveLeft(move);
        statusBody = "kiri";

        if (legDirection.equals("front")) {
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) <= objects.get(0).getCenterPoint().get(0) - 0.04f)
                legDirection = "back";
        } else if (legDirection.equals("back")) {
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) > objects.get(0).getCenterPoint().get(0) + 0.04f) {
                legDirection = "front";
            }
        }
    }

    boolean jumped = false;
    boolean jumping = false;

    public void scan() {
        if (scanup) {
            scanup();
            if (objects.get(1).getChildObject().get(4).getCenterPoint().get(1) >= 0.15f) scanup = false;
        } else {
            scandown();
            if (objects.get(1).getChildObject().get(4).getCenterPoint().get(1) <= -.199f) {
                scanup = true;
                scanning = false;
                scannow = true;
            }
        }
    }

    public void jump() {
        if (!jumped) {
            jumpup();
            if (objects.get(0).getCenterPoint().get(1) >= 0.2f) jumped = true;
        } else {
            jumpdown();
            if (objects.get(0).getCenterPoint().get(1) <= 0) {
                jumped = false;
                jumping = false;
            }
        }
    }

    public void jumpup() {
        objects.get(0).translateObject(0f, 0.01f, 0f);
        camera.moveUp(move - 0.01f);
    }

    public void jumpdown() {
        objects.get(0).translateObject(0f, -0.01f, 0f);
        camera.moveDown(move - 0.01f);
    }

    public void vent(Object src, Object dest) {
        if (objects.get(0).getCenterPoint().get(0) <= src.getCenterPoint().get(0) + 0.25f &&
                objects.get(0).getCenterPoint().get(0) >= src.getCenterPoint().get(0) - 0.25f &&
                objects.get(0).getCenterPoint().get(2) <= src.getCenterPoint().get(2) + 0.25f &&
                objects.get(0).getCenterPoint().get(2) >= src.getCenterPoint().get(2) - 0.25f
        ) {
            // open vent
            if (!isVentOpened) {
                openVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f), 1.5f);
                openVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f), 1.5f);
            }

            // vent opened
            if (openVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f), 1.5f) && openVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f), 1.5f)) {
                isVentOpened = true;

                // character animation
                objects.get(0).translateObject(0f, -.02f, 0f); // animasi turun

                if (objects.get(0).getCenterPoint().get(1) <= -0.5f && !isVented) {
                    System.out.println("vented!");
                    // teleport character
                    objects.get(0).translateObject(
                            dest.getCenterPoint().get(0) - src.getCenterPoint().get(0),
                            dest.getCenterPoint().get(1) - src.getCenterPoint().get(1),
                            dest.getCenterPoint().get(2) - src.getCenterPoint().get(2)
                    );
                    // change camera position
                    Vector3f temp = camera.getPosition();
                    camera.setPosition(objects.get(0).getCenterPoint().get(0), temp.y, temp.z);
                    isVented = true;
                }
            }
        }

        if (objects.get(0).getCenterPoint().get(0) <= dest.getCenterPoint().get(0) + 0.25f &&
                objects.get(0).getCenterPoint().get(0) >= dest.getCenterPoint().get(0) - 0.25f &&
                objects.get(0).getCenterPoint().get(2) <= dest.getCenterPoint().get(2) + 0.25f &&
                objects.get(0).getCenterPoint().get(2) >= dest.getCenterPoint().get(2) - 0.25f
        ) {
            // close vent
            if (isVentOpened && objects.get(0).getCenterPoint().get(1) >= 0f) {
                closeVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f), 0.15f, 1.5f);
                closeVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f), 0.15f, 1.5f);

                // vent closed
                if (closeVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f), 0.15f, 1.5f) && closeVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f),  0.15f, 1.5f)) {
                    isVentOpened = false;
                    isVented = false;
                    isVenting = false;
                }
            }

            // character animation
            if (isVented && isVentOpened) {
                if (objects.get(0).getCenterPoint().get(1) <= 0f)
                    objects.get(0).translateObject(0f, .02f, 0f); // animasi naik
            }
        }
    }

    public boolean closeVent(Object vent, Vector3f engsel, float offset, float speed) {
        if (vent.getCenterPoint().get(0) <= engsel.x + offset) {
            vent.translateObject(
                    -engsel.x,
                    -engsel.y,
                    -engsel.z
            );
            vent.rotateObject((float) -Math.toRadians(speed), 0.0f, 0.0f, 1.0f);
            vent.translateObject(
                    engsel.x,
                    engsel.y,
                    engsel.z
            );
        }

        return vent.getCenterPoint().get(0) > engsel.x + offset;
    }

    public boolean openVent(Object vent, Vector3f engsel, float speed) {
//        System.out.println("buka ajg");
        if (vent.getCenterPoint().get(0) > engsel.x) {
            vent.translateObject(
                    -engsel.x,
                    -engsel.y,
                    -engsel.z
            );
            vent.rotateObject((float) Math.toRadians(speed), 0.0f, 0.0f, 1.0f);
            vent.translateObject(
                    engsel.x,
                    engsel.y,
                    engsel.z
            );
        }

        return vent.getCenterPoint().get(0) <= engsel.x;
    }

    public boolean collideTable(float x, float y, float z) {
        if (v3R(x, y, z, objects.get(2).getChildObject().get(3).getCenterPoint().get(0), objects.get(2).getChildObject().get(3).getCenterPoint().get(1), objects.get(2).getChildObject().get(3).getCenterPoint().get(2)) <= 0.8f) {
            return true;
        }

        if (v3R(x, y, z, objects.get(2).getChildObject().get(5).getCenterPoint().get(0), objects.get(2).getChildObject().get(5).getCenterPoint().get(1), objects.get(2).getChildObject().get(5).getCenterPoint().get(2)) <= 0.8f) {
            return true;
        }

        if (v3R(x, y, z, objects.get(3).getCenterPoint().get(0), objects.get(3).getCenterPoint().get(1), objects.get(3).getCenterPoint().get(2)) <= 0.2f) {
            return true;
        }

        if (v3R(x, y, z, objects.get(8).getCenterPoint().get(0), objects.get(8).getCenterPoint().get(1), objects.get(8).getCenterPoint().get(2)) <= 0.2f) {
            return true;
        }

        return false;
    }

    boolean dead = false;
    boolean isHit = false;

    public void shoot() {
        if (!isHit) {
            if (objectPeluru.size() == 0) {
                objectPeluru.add(new Sphere("sphere",
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f, .0f, 0.0f, 1.0f),
                        Arrays.asList(0.0f, 0.0f, 0.0f),
                        0.01f,
                        0.01f,
                        0.01f,
                        108,
                        72,
                        180f
                ));
                objectPeluru.get(0).translateObject(
                        objects.get(0).getChildObject().get(4).getChildObject().get(1).getCenterPoint().get(0),
                        objects.get(0).getChildObject().get(4).getChildObject().get(1).getCenterPoint().get(1),
                        objects.get(0).getChildObject().get(4).getChildObject().get(1).getCenterPoint().get(2)
                );
            }

            switch (statusBody) {
                case "depan":
                    objectPeluru.get(0).translateObject(0.15f, -0.065f, -0.1f);
                    while (objectPeluru.get(0).getCenterPoint().get(2) >= -2.5f) {
                        isDead();
                        objectPeluru.get(0).draw(camera, projection);
                        objectPeluru.get(0).translateObject(0f, 0f, -0.01f);
                    }
                    objectPeluru.remove(0);
                    break;

                case "belakang":
                    objectPeluru.get(0).translateObject(-0.15f, -0.06f, 0.0f);
                    while (objectPeluru.get(0).getCenterPoint().get(2) <= 2.5f) {
                        isDead();
                        objectPeluru.get(0).draw(camera, projection);
                        objectPeluru.get(0).translateObject(0f, 0f, 0.01f);
                    }
                    objectPeluru.remove(0);
                    break;

                case "kiri":
                    objectPeluru.get(0).translateObject(-0.15f, -0.06f, 0.0f);
                    while (objectPeluru.get(0).getCenterPoint().get(0) >= -2.5f) {
                        isDead();
                        objectPeluru.get(0).draw(camera, projection);
                        objectPeluru.get(0).translateObject(-0.01f, 0f, 0.0f);
                    }
                    objectPeluru.remove(0);
                    break;

                case "kanan":
                    objectPeluru.get(0).translateObject(0.15f, -0.06f, 0.1f);
                    while (objectPeluru.get(0).getCenterPoint().get(0) <= 2.5f) {
                        isDead();
                        objectPeluru.get(0).draw(camera, projection);
                        objectPeluru.get(0).translateObject(0.01f, 0f, 0f);
                    }
                    objectPeluru.remove(0);
                    break;
            }
        }
    }

    public void isDead() {
        if (v3R(
                objectPeluru.get(0).getCenterPoint().get(0),
                objectPeluru.get(0).getCenterPoint().get(1),
                objectPeluru.get(0).getCenterPoint().get(2),
                objects.get(8).getCenterPoint().get(0),
                objects.get(8).getCenterPoint().get(1),
                objects.get(8).getCenterPoint().get(2)) <= 0.2f
                && !dead) {
            isHit = true;
            dead = true;
            dead(objects.get(8));
        }
    }

    public void dead(Object character) {
        if (dead) {
            Vector4f color = character.getColor();
            List<Float> centerPoint = character.getCenterPoint();
            centerPoint.set(1, centerPoint.get(1) - 0.12f);
            int index = objects.indexOf(character);

            // badan utama
            objects.set(index, new Sphere("leg",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    color,
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.12f,
                    0.12f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
            objects.get(index).translateObject(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2));

            //badan dalam
            objects.get(index).getChildObject().add(new Sphere("leg",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(1f, 0f, 0f, 1f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.10f,
                    0.10f,
                    0.10f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(0).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
            objects.get(index).getChildObject().get(0).translateObject(0f, 0.00009f, 0f);
            objects.get(index).getChildObject().get(0).translateObject(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2));

            //tulang
            objects.get(index).getChildObject().add(new Sphere("leg",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(1f, 1f, 1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.02f,
                    0.02f,
                    0.15f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
            objects.get(index).getChildObject().get(1).translateObject(0f, 0.05f, 0f);
            objects.get(index).getChildObject().get(1).translateObject(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2));

            objects.get(index).getChildObject().add(new Sphere("sphere",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(1f, 1f, 1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.02f,
                    0.02f,
                    0.02f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(2).translateObject(0.02f, 0.05f, 0f);
            objects.get(index).getChildObject().get(2).rotateObject((float) Math.toRadians(45f), 0.0f, 1.0f, 0f);
            objects.get(index).getChildObject().get(2).translateObject(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2));

            objects.get(index).getChildObject().add(new Sphere("sphere",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(1f, 1f, 1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.02f,
                    0.02f,
                    0.02f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(3).translateObject(-0.02f, 0.05f, 0f);
            objects.get(index).getChildObject().get(3).rotateObject((float) Math.toRadians(45f), 0.0f, 1.0f, 0f);
            objects.get(index).getChildObject().get(3).translateObject(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2));

            //kaki
            objects.get(index).getChildObject().add(new Sphere("leg",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    color,
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.1f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(4).translateObject(0.065f, -0.09f, 0.14f);
            objects.get(index).getChildObject().get(4).rotateObject((float) Math.toRadians(45f), 0.0f, 1f, 0f);
            objects.get(index).getChildObject().get(4).translateObject(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2));

            objects.get(index).getChildObject().add(new Sphere("leg",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    color,
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.1f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(5).translateObject(-0.065f, -0.09f, 0.14f);
            objects.get(index).getChildObject().get(5).rotateObject((float) Math.toRadians(45f), 0.0f, 1f, 0f);
            objects.get(index).getChildObject().get(5).translateObject(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2));

//            objects.get(1).getChildObject().add(new Bezier(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(0f, 0.f, 1.0f, 1.0f),
//                    Arrays.asList(
//                            new float[]{0f, 0f, 0f},
//                            new float[]{0.03f, 0f, 0.1f},e
//                            new float[]{0.06f, 0f, 0.2f},
//                            new float[]{0.09f, 0f, 0.3f},
//                            new float[]{0.12f, 0f, 0.2f},
//                            new float[]{0.15f, 0.05f, 0.1f},
//                            new float[]{0.18f, 0.05f, 0f},
//                            new float[]{0.21f, 0.1f, 0f},
//                            new float[]{0.24f, 0.15f, 0f},
//                            new float[]{0.3f, 0.2f, 0f}
//                    ),
//                    3f
//            ));

            objects.get(index).getChildObject().add(new bezier2(Arrays.asList(
                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
            ),
                    new ArrayList<>(),
                    new Vector4f(1f, 0.f, .0f, 1.0f),
                    Arrays.asList(
                            new float[]{0f, 0f, 0f},
                            new float[]{-0.2f, .1f, 0.1f},
                            new float[]{-0.2f, 0f, 0.2f},
                            new float[]{0f,-.2f,0.3f},
                            new float[]{.1f, 0f, 0.5f},
                            new float[]{-.2f, .1f, 0.3f},
                            new float[]{0f, .1f, 0.1f}
                    ),
                    5f
            ));

            
            objects.get(index).getChildObject().get(6).translateObject(0f, -.12f, -2f);

            objects.get(index).getChildObject().add(new bezier2(Arrays.asList(
                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
            ),
                    new ArrayList<>(),
                    new Vector4f(1f, 0.f, .0f, 1.0f),
                    Arrays.asList(
                            new float[]{0f, 0f, 0f},
                            new float[]{-0.2f, .1f, 0.1f},
                            new float[]{-0.2f, 0f, 0.2f},
                            new float[]{0f,-.2f,0.3f},
                            new float[]{.1f, 0f, 0.5f},
                            new float[]{-.2f, .1f, 0.3f},
                            new float[]{0f, .1f, 0.1f}
                    ),
                    5f
            ));

            objects.get(index).getChildObject().get(7).rotateObject((float)Math.toRadians(180f),0f,1f,0f);
            objects.get(index).getChildObject().get(7).translateObject(-0.01f, -.12f, -2f);

            isHit = false;
        }
    }

    public void resurrect(Object character) {
        System.out.println("tes");
        if (dead) {
            Vector4f color = character.getColor();
            List<Float> centerPoint = character.getCenterPoint();
            int index = objects.indexOf(character);

            objects.set(index, new Sphere("body",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    color,
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.12f,
                    0.12f,
                    348,
                    348,
                    180f
            ));

            objects.get(index).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
            objects.get(index).translateObject(0f, 0f, centerPoint.get(2));

            // KAKI 2
            objects.get(index).getChildObject().add(new Sphere("leg",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    color,
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.1f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(0).translateObject(.065f, 0.f, -0.14f);
            objects.get(index).getChildObject().get(0).setCenterPoint(Arrays.asList(0.25f, 0.0f, 0.0f));
            objects.get(index).getChildObject().get(0).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
            objects.get(index).getChildObject().get(0).translateObject(0.0f, 0.f, centerPoint.get(2));

            objects.get(index).getChildObject().add(new Sphere("leg",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    color,
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.1f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(1).translateObject(-0.065f, 0.f, -0.14f);
            objects.get(index).getChildObject().get(1).setCenterPoint(Arrays.asList(0.25f, 0.0f, 0.0f));
            objects.get(index).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 1.0f, .0f, .0f);
            objects.get(index).getChildObject().get(1).translateObject(0.0f, 0.f, centerPoint.get(2));


            // KACAMATA 2
            objects.get(index).getChildObject().add(new Sphere("glasses",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.0f, 1f, 1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.08f,
                    0.04f,
                    0.06f,
                    108,
                    72,
                    180f
            ));

            objects.get(index).getChildObject().get(2).translateObject(.0f, .0f, 0.09f);
            objects.get(index).getChildObject().get(2).translateObject(0.0f, 0.f, centerPoint.get(2));


            // TAS
            objects.get(index).getChildObject().add(new Sphere("bag",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    color,
                    Arrays.asList(0.0f, -0.04f, 0.0f),
                    0.12f,
                    0.12f,
                    0.12f,
                    108,
                    72,
                    180f
            ));
            objects.get(index).getChildObject().get(3).translateObject(.0f, .0f, centerPoint.get(2));

            dead = false;
        }
    }

    public void press() {
        if (!boxOpen) {
            if (openVent(objects.get(2).getChildObject().get(16), new Vector3f(1.15f, -0.05f, -1.15f), 0.5f)) {
                boxOpen = true;
            }
        }
        if (boxOpen) {
            if (!buttonPressed) {
                objects.get(2).getChildObject().get(15).translateObject(0f, -0.001f, 0f);
                if (objects.get(2).getChildObject().get(15).getCenterPoint().get(1) <= -0.07f) {
                    buttonPressed = true;
                }
            }
            if (buttonPressed) {
                objects.get(2).getChildObject().get(15).translateObject(0f, 0.001f, 0f);
                if (objects.get(2).getChildObject().get(15).getCenterPoint().get(1) >= -0.045f)
                    buttonPressed = false;
            }
            if(closeVent(objects.get(2).getChildObject().get(16), new Vector3f(1.15f, -0.05f, -1.15f), 0.1f, 0.5f)) {
                boxOpen = false;
                pressButton = false;
            }
        }
    }

    public void loop() {
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();
            float x1 = objects.get(0).getCenterPoint().get(0);
            float y1 = objects.get(0).getCenterPoint().get(1);
            float z1 = objects.get(0).getCenterPoint().get(2);

            input(x1, y1, z1);

            //code
            for (Object object : objects) {
                object.draw(camera, projection);
            }

            for (Object object : objectPeluru) {
                object.draw(camera, projection);
            }

            if (isVenting) {
                vent(ventSrc, ventDest);
            }

            if (scanning) {
                scan();
            }

            if (v3R(x1, y1, z1, objects.get(1).getCenterPoint().get(0), objects.get(1).getCenterPoint().get(1), objects.get(1).getCenterPoint().get(2)) <= 0.22f) {
                cekscan = true;
            } else {
                cekscan = false;
            }

            if (jumping) {
                jump();
            }

            float jarakLaptop = v3R(
                    x1, y1, z1,
                    objects.get(2).getChildObject().get(7).getCenterPoint().get(0),
                    objects.get(2).getChildObject().get(7).getCenterPoint().get(1),
                    objects.get(2).getChildObject().get(7).getCenterPoint().get(2)
            );
            if (jarakLaptop <= 0.6f && !colorChanged) {
                objects.get(2).getChildObject().get(8).changeColor(new Vector4f(1f, 1f, 1f, 1f));
                cekColor = true;
            }
            else {
                objects.get(2).getChildObject().get(8).changeColor(new Vector4f(0, 0f, 0f, 1f));
                colorChanged = false;
                cekColor = false;
            }

            if (pressButton) {
                press();
            }
            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public float v3R(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (float) Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y2 - y1), 2) + Math.pow(Math.abs(z2 - z1), 2));
    }

    public void run() {
        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}