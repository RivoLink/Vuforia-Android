package mg.rivolink.app.ar;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import org.rajawali3d.util.RajLog;
import org.rajawali3d.vuforia.RajawaliVuforiaActivity;

public class MainActivity extends RajawaliVuforiaActivity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER);

		addContentView(ll,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

		startVuforia();
	}

	@Override
	protected void setupTracker(){
		int result=initTracker(TRACKER_TYPE_MARKER);
		if(result==1){
			result=initTracker(TRACKER_TYPE_IMAGE);
			if(result==1){
				super.setupTracker();
			}
			else{
				RajLog.e("Couldn't initialize image tracker.");
			}
		}
		else{
			RajLog.e("Couldn't initialize marker tracker.");
		}
	}

	@Override
	protected void initApplicationAR(){
		super.initApplicationAR();

		createFrameMarker(0,"Marker0",50,50);
		createFrameMarker(1,"Marker1",50,50);

	}

	@Override
	protected void initRajawali(){
        setRenderer(new AppRenderer(this));
		super.initRajawali();
	}
}

