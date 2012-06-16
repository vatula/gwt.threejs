package gwt.threejs.globe.client;

import com.github.gwt.stats.client.Stats;
import gwt.threejs.globe.client.resources.Resources;

import com.google.code.gwt.threejs.client.cameras.Camera;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.extras.ImageUtils;
import com.google.code.gwt.threejs.client.extras.geometries.Plane;
import com.google.code.gwt.threejs.client.extras.geometries.Sphere;
import com.google.code.gwt.threejs.client.materials.MeshBasicMaterial;
import com.google.code.gwt.threejs.client.objects.Mesh;
import com.google.code.gwt.threejs.client.renderers.CanvasRenderer;
import com.google.code.gwt.threejs.client.scenes.Scene;
import com.google.gwt.animation.client.Animation;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Globe implements EntryPoint {
	private int mouseX;
	private int mouseY;
	
	private Stats stats;
	private Camera camera;
	private Mesh mesh;
	private Scene scene;
	private CanvasRenderer renderer;
	private Animation animation;
	
	public void onModuleLoad() {
		initStats();
		initGlobe();
		RootPanel.get().getElement().appendChild(stats.getDomElement());
		animation.run(Integer.MAX_VALUE);
	}
	
	private void initStats(){
		this.stats = new Stats();
		Style ss = this.stats.getDomElement().getStyle();
		ss.setPosition(Position.ABSOLUTE);
		ss.setTop(0, Unit.PX);
	}
	
	private void initGlobe(){
		Canvas canvasScreen = Canvas.createIfSupported();
		if (canvasScreen != null) {

			int w = Window.getClientWidth(),
				h = Window.getClientHeight();
			
			final double windowHalfX = w/2;
			final double windowHalfY = h/2;
			
			camera = new Camera(60, (double)w/h, 1, 10000, null);
			camera.getPosition().setZ(500);
			
			scene = new Scene();
			MeshBasicMaterial.MeshBasicMaterialOptions options = new MeshBasicMaterial.MeshBasicMaterialOptions();
			options.map = ImageUtils.loadTexture(Resources.INSTANCE.shadow().getURL(), null, null);
			mesh = new Mesh(new Plane(300, 300, 3, 3), new MeshBasicMaterial(options));
			mesh.getPosition().setY(-250);
			mesh.getRotation().setX(-90*Math.PI/180);
			scene.addChild(mesh);
			
			options = new MeshBasicMaterial.MeshBasicMaterialOptions();
			options.map = ImageUtils.loadTexture(Resources.INSTANCE.globeTexture().getURL(), null, null);
			mesh = new Mesh(new Sphere(200,20,20), new MeshBasicMaterial(options));
			mesh.setOverdraw(true);
			scene.addChild(mesh);
			
			renderer = new CanvasRenderer(canvasScreen);
			renderer.setSize(w, h);
			
			RootPanel.get().add(renderer.getDomElement());
			RootPanel.get().addDomHandler(new MouseMoveHandler(){

				@Override
				public void onMouseMove(MouseMoveEvent event) {
					mouseX = (int)(event.getClientX() - windowHalfX);
					mouseY = (int)(event.getClientY() - windowHalfY);
				}}, MouseMoveEvent.getType());
		}
		animation = new Animation() {
			
			@Override
			protected void onUpdate(double progress) {
				render();
				stats.update();
			}
		};
	}
	
	private void render() {
		Vector3 position = camera.getPosition();
		double x = position.getX();
		double y = position.getY();
		position.setX(x + (mouseX - x)*0.05);
		position.setY(y + (-mouseY - y)*0.05);
		
		y = mesh.getRotation().getY();
		mesh.getRotation().setY(y - 0.005);
		renderer.render(scene, camera);
	}
}
