package dev.britannio.firstardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.ar.core.Anchor;

import dev.romainguy.kotlin.math.Float3;
import io.github.sceneview.ar.ArSceneView;
import io.github.sceneview.ar.node.ArModelNode;
import io.github.sceneview.node.ModelNode;

public class MainActivity extends AppCompatActivity {

    ArSceneView sceneView;
    ExtendedFloatingActionButton placeBtn;
    ArModelNode arModelNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sceneView = findViewById(R.id.sceneView);
        placeBtn = findViewById(R.id.place);
        arModelNode = new ArModelNode(
                ArModelNode.Companion.getDEFAULT_PLACEMENT_MODE(),
                ArModelNode.Companion.getDEFAULT_HIT_POSITION(),
                true,
                false
        );

        placeBtn.setOnClickListener(this::onClick);


//        String fileLocation = "models/737-300.glb";
        String fileLocation = "models/swa-737.glb";
        ModelNode modelNode = arModelNode.loadModelGlbAsync(
                fileLocation,
                true,
                0.5f,
                new Float3(0, 0, 0),
                null,
                null
        );
        arModelNode.setOnAnchorChanged((Anchor a) -> {
            placeBtn.hide();
            return null;
        });
        arModelNode.setVisible(true);
        sceneView.getPlaneRenderer().setVisible(true);

        sceneView.addChild(arModelNode);


    }

    void placeModel() {
        arModelNode.anchor();
        sceneView.getPlaneRenderer().setVisible(false);
    }

    private void onClick(View v) {
        placeModel();

    }
}