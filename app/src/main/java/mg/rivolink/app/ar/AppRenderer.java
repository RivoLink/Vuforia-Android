package mg.rivolink.app.ar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;

import org.rajawali3d.Object3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.math.Quaternion;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.vuforia.RajawaliVuforiaRenderer;

public class AppRenderer extends RajawaliVuforiaRenderer{

	private Object3D axes;
	private DirectionalLight light;

	public AppRenderer(Context context){
		super(context);
	}

	@Override
	protected void initScene(){
		light=new DirectionalLight(.1f,0,-1.0f);
		light.setColor(1.0f,1.0f,0.8f);
		light.setPower(1);
		getCurrentScene().addLight(light);

		try{
			axes=new LoaderOBJ(mContext.getResources(),mTextureManager,R.raw.axes_obj).parse().getParsedObject();
			axes.setVisible(false);
			getCurrentScene().addChild(axes);
		}
		catch(final Exception e){
			new Handler().post(new Runnable(){
				@Override
				public void run(){
					Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	@Override
	public void onTouchEvent(MotionEvent event){
		// TODO: Implement this method
	}

	@Override
	protected void foundFrameMarker(int markerId,Vector3 position,Quaternion orientation){
		axes.setVisible(true);
		axes.setPosition(position);
		axes.setOrientation(orientation);
	}

	@Override
	protected void foundImageMarker(String trackableName,Vector3 position,Quaternion orientation){
		// TODO: Implement this method
	}

	@Override
	public void noFrameMarkersFound(){
		axes.setVisible(false);
	}

	@Override
	public void onOffsetsChanged(float xOffset,float yOffset,float xOffsetStep,float yOffsetStep,int xPixelOffset,int yPixelOffset){
		// TODO: Implement this method
	}

}
